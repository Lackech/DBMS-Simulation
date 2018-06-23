package com.simulador.modules;

import com.simulador.dbms.Event;
import com.simulador.dbms.Query;
import com.simulador.dbms.Simulator;
import com.simulador.enums.EventType;
import com.simulador.enums.QueryType;

import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 */
public class QueryExecutionerModule extends GeneralModule {


    /**
     * The fixed amount of time taken for a DDL query type to execute.
     */
    private final double DDL_RESTRUCTRATION_TIME = 0.5;

    /**
     * The fixed amount of time taken for an Update query type to execute.
     */
    private final double UPDATE_RESTRUCTURATION_TIME = 1;

    /**
     * The maximum amount of sentences defined by the user in this module.
     */
    private int mSentences;

    private int currentSentences;

    public QueryExecutionerModule(Simulator simulation, int mSentences) {
        this.simulation = simulation;
        queue = new LinkedBlockingQueue<>();
        this.mSentences = mSentences;
        setCurrentSentences(0);
        servers = mSentences;

    }


    @Override
    public void processEntry(Query query) {
        if (isBusy()) {
            query.setInQueue(true);
            queue.offer(query);

        } else {

            setCurrentSentences(getCurrentSentences() + 1);
            double time = getTotalTime(query);
            double exitTime = simulation.getClock() + time;
            simulation.addEvent(new Event(exitTime, query, EventType.exitQueryExecModule));

        }

    }

    @Override
    public void processExit(Query query) {
        setTotalProcessedQueries(getTotalProcessedQueries()+1);



        if (queue.size() > 0) {
            Query quer = queue.poll();
            double time = getTotalTime(quer);
            double exitTime = simulation.getClock() + time;
            quer.setInQueue(false);
            simulation.addEvent(new Event(exitTime, quer, EventType.exitQueryExecModule));

        } else {
            setCurrentSentences(getCurrentSentences() - 1);

        }

        if (!query.isTerminate()) {
            query.setReady(true);

            nextModule.generateServiceEvent(query);


        } else {
            int actualConnections = simulation.getClientConnectionModule().getCurrentConnections() - 1;
            simulation.getClientConnectionModule().setCurrentConnections(actualConnections);
        }
    }

    @Override
    public void processTerminate(Query query) {

        if (query.isInQueue()) {
            queue.remove(query);

            int actualConnections = simulation.getClientConnectionModule().getCurrentConnections() - 1;
            simulation.getClientConnectionModule().setCurrentConnections(actualConnections);
        } else {
            query.setTerminate(true);
        }
        Event killEventToRemove = simulation.getKillEventsTable().get(query.getId());
        simulation.getKillEventsTable().remove(killEventToRemove);
        simulation.getEventList().remove(killEventToRemove);
    }

    @Override
    public void generateServiceEvent(Query query) {
        query.setModule(5);
        simulation.addEvent(new Event(simulation.getClock(), query, EventType.enterQueryExecModule));

    }

    @Override
    public boolean isBusy() {
        if(getCurrentSentences() == mSentences)
            return true;
        else
            return false;
    }

    @Override
    public int getNumberOfFreeServers() {
        return mSentences- getCurrentSentences();
    }

    public double getBlockExecutingTime(int numberOfBlocks) {
        return Math.pow(numberOfBlocks, 2) / 1000;
    }



    public double getTotalTime(Query query) {
        double totalTime = this.getBlockExecutingTime(query.getNumberOfBlocks());
        totalTime += getRestructurationTime(query.getType());
        return totalTime;
    }

    /**
     * Sums the special restructuration time in case the query type is DDL or Update.
     *
     * @param query the specific query to be analized.
     * @return 0 if the query doesn't fall in any of the two cases.
     */
    private double getRestructurationTime(QueryType query) {
        double time = 0;
        if (query == QueryType.DDL) {
            time = DDL_RESTRUCTRATION_TIME;
        } else if (query == QueryType.UPDATE) {
            time = UPDATE_RESTRUCTURATION_TIME;
        }
        return time;
    }

    public void setNextModule(GeneralModule nextModule) {
        this.nextModule = nextModule;
    }


    /**
     * The amount of sentences being occupied at an instant.
     */
    public int getCurrentSentences() {
        return currentSentences;
    }

    public void setCurrentSentences(int currentSentences) {
        this.currentSentences = currentSentences;
    }
}
