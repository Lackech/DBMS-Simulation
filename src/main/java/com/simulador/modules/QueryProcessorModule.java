package com.simulador.modules;

import com.simulador.dbms.Event;
import com.simulador.dbms.Query;
import com.simulador.dbms.Simulator;
import com.simulador.enums.EventType;
import com.simulador.enums.QueryType;
import com.simulador.statistics.DistributionGenerationNumber;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 */
public class QueryProcessorModule extends GeneralModule {


    /**
     * Maximum amount of user-defined processes
     */
    private int nAvailableProcesses;

    private int currentProcesses;

    public QueryProcessorModule(Simulator simulation, GeneralModule nextModule, int nAvailableProcesses) {
        this.simulation = simulation;
        this.nextModule = nextModule;
        queue = new LinkedBlockingQueue<>();
        this.nAvailableProcesses = nAvailableProcesses;
        setCurrentProcesses(0);
        servers = nAvailableProcesses;
    }


    @Override
    public void processEntry(Query query) {

        if (isBusy()) {
            query.setInQueue(true);
            queue.offer(query);

        } else {

            setCurrentProcesses(getCurrentProcesses() + 1);
            double exitTime = timeInQueryProcessingModule(query.getType());
            simulation.addEvent(new Event(simulation.getClock() + exitTime,
                    query, EventType.exitQueryProcessorModule));
        }
    }

    @Override
    public void processExit(Query query) {

        if (queue.size() > 0) {
            double exitTime = timeInQueryProcessingModule(queue.peek().getType());
            Query query1 = queue.poll();
            query1.setInQueue(false);
            simulation.addEvent(new Event(simulation.getClock() + exitTime,
                    query1, EventType.exitQueryProcessorModule));

        } else {
            setCurrentProcesses(getCurrentProcesses() - 1);

        }

        if (!query.isTerminate()) {
            nextModule.generateServiceEvent(query);
        } else {
            int actualConnections = simulation.getClientConnectionModule().getCurrentConnections() - 1;
            simulation.getClientConnectionModule().setCurrentConnections(actualConnections);
        }
    }

    @Override
    public void processTerminate(Query query) {
        setTotalProcessedQueries(getTotalProcessedQueries()+1);

        if (query.isInQueue()) {
            queue.remove(query);

            int actualConnections = simulation.getClientConnectionModule().getCurrentConnections() - 1;
            simulation.getClientConnectionModule().setCurrentConnections(actualConnections);

        } else {
            query.setTerminate(true);
        }
        Event terminateEventToRemove = simulation.getKillEventsTable().get(query.getId());
        simulation.getKillEventsTable().remove(terminateEventToRemove);
        simulation.getEventList().remove(terminateEventToRemove);
    }

    @Override
    public void generateServiceEvent(Query query) {

        query.setModule(3);
        simulation.addEvent(new Event(simulation.getClock(), query, EventType.enterQueryProcessorModule));

    }

    @Override
    public boolean isBusy() {
        if(nAvailableProcesses == getCurrentProcesses())
            return true;
        else
            return false;
    }

    @Override
    public int getNumberOfFreeServers() {
        return nAvailableProcesses - getCurrentProcesses();
    }



    /**
     * Calculates the amount of validation time of a specific query inside the Query Processing Module.
     *
     * @param query to be validated
     * @return the amount of time it took for the query to be validated
     */
    private double timeInQueryProcessingModule(QueryType query) {
        Random rnd = new Random();
        double totalTime;
        double lexicalValidationTime;
        double syntacticalValidationTime;
        double semanticValidationTime;
        double permitVerificationTime;
        double queryOptimizationTime;
        double randomNumber = rnd.nextFloat();

        if (randomNumber < 0.7) {
            lexicalValidationTime = 0.1;
        } else {
            lexicalValidationTime = 0.4;
        }
        syntacticalValidationTime = DistributionGenerationNumber.getNextRandomValueByUniform(0, 0.8);
        semanticValidationTime = DistributionGenerationNumber.getNextRandomValueByNormal(1, 0.5);
        permitVerificationTime = DistributionGenerationNumber.getNextRandomValueByExponential(1 / 0.7);

        if (query.equals(QueryType.SELECT) || query.equals(QueryType.JOIN)) {
            queryOptimizationTime = 0.1;
        } else {
            queryOptimizationTime = 0.5;
        }
        totalTime = lexicalValidationTime + syntacticalValidationTime + semanticValidationTime + permitVerificationTime + queryOptimizationTime;
        return totalTime;
    }


    /**
     * Amount of processes in a specific instant.
     */
    public int getCurrentProcesses() {
        return currentProcesses;
    }

    public void setCurrentProcesses(int currentProcesses) {
        this.currentProcesses = currentProcesses;
    }
}
