package com.simulador.modules;

import com.simulador.dbms.Event;
import com.simulador.dbms.Query;
import com.simulador.dbms.Simulator;
import com.simulador.enums.EventType;
import com.simulador.statistics.DistributionGenerationNumber;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 */
public class ClientAdmModule extends GeneralModule {


    public static final double LAMBDA = 0.5;

    private List<Query> allQueries;

    private int kConnections;

    private int rejectedConnections;

    private int currentConnections;

    private int totalProcessedQueriesFromLastModule;

    private int currentId;

    private int numberOfConnections;

    public ClientAdmModule(Simulator simulation, GeneralModule nextModule, int kConnections) {
        this.simulation = simulation;
        this.nextModule = nextModule;
        this.kConnections = kConnections;
        allQueries = new LinkedList<>();
        queue = new LinkedBlockingQueue<>();
        currentId = 1;
        setRejectedConnections(0);
        setCurrentConnections(0);
        setTotalProcessedQueries(0);
        servers = kConnections;
        hasQueue = false;
        setTotalProcessedQueriesFromLastModule(0);


    }

    @Override
    public void processEntry(Query query) {
        if (query.isReady()) {
            processArrivalToLastModule(query);

        } else {
            processArrivalToFirstModule(query);

        }

    }

    @Override
    public void processExit(Query query) {
        if (query.isReady())
            processSuccessExit(query);

        else
            processExitToNextModule(query);
    }

    @Override
    public void processTerminate(Query query) {

        if (!query.isReady())
            query.setTerminate(true);
    }

    @Override
    public void generateEvent(Query query) {
        if (query == null) {
            currentId++;
            query = new Query(currentId, simulation.getClock(), DistributionGenerationNumber.generateType(),1);
        }
        if (query.isReady()) {
            simulation.addEvent(new Event(simulation.getClock(), query,
                    EventType.enterClientAdmModule));
        } else {
            double nextArrivalTime = DistributionGenerationNumber.getNextArrivalTime(LAMBDA);
            simulation.addEvent(new Event(simulation.getClock() + nextArrivalTime, query,
                    EventType.enterClientAdmModule));
            Event killEvent = new Event(simulation.getClock() + nextArrivalTime + simulation.getTimeOut(), query,
                    EventType.kill);
            killEvent.setTerminate(true);
            simulation.addEvent(killEvent);
            simulation.getKillEventsTable().put(query.getId(), killEvent);
        }
    }

    @Override
    public boolean isBusy() {
        if(getCurrentConnections() == kConnections)
            return true;
        else
            return false;
    }

    @Override
    public int getNumberOfFreeServers() {
        return kConnections- getCurrentConnections();
    }

    public void generateFirstArrival() {
        currentId++;
        Query query = new Query(currentId, simulation.getClock(), DistributionGenerationNumber.generateType(),1);
        simulation.addEvent(new Event(simulation.getClock(), query,
                EventType.enterClientAdmModule));

    }

    private void processArrivalToFirstModule(Query query) {
        if (isBusy())
            setRejectedConnections(getRejectedConnections() + 1);
        else {
            setNumberOfConnections(getNumberOfConnections() + 1);
            setCurrentConnections(getCurrentConnections() + 1);
            double time = getNextExitTime();
            simulation.addEvent(new Event(simulation.getClock() + time, query,
                    EventType.exitClientAdmModule));

            allQueries.add(query);
        }
        generateEvent(null);
    }

    private void processArrivalToLastModule(Query query) {

        double time = getResultantTime(query.getNumberOfBlocks());
        simulation.addEvent(new Event(time + simulation.getClock(),
                query, EventType.exitClientAdmModule));

    }

    private void processExitToNextModule(Query query) {
        setTotalProcessedQueries(getTotalProcessedQueries() + 1);
        if (!query.isTerminate()) {
            nextModule.generateEvent(query);

        } else {
            setCurrentConnections(getCurrentConnections() - 1);
        }

    }

    private void processSuccessExit(Query query) {
        setTotalProcessedQueriesFromLastModule(getTotalProcessedQueriesFromLastModule() + 1);
        setCurrentConnections(getCurrentConnections() - 1);
        query.setLifeTime(simulation.getClock() - query.getEntryTime());
        Event eventToRemove = simulation.getKillEventsTable().get(query.getId());
        simulation.getEventList().remove(eventToRemove);


    }

    public double getResultantTime(int numberOfBlocks) {
        double average = numberOfBlocks / 3;
        return average / 2;
    }

    public double getNextExitTime() {
        return DistributionGenerationNumber.getNextRandomValueByUniform(0.01, 0.05);
    }

    public int getCurrentConnections() {
        return currentConnections;
    }

    public void setCurrentConnections(int currentConnections) {
        this.currentConnections = currentConnections;
    }

    public int getTotalProcessedQueriesFromLastModule() {
        return totalProcessedQueriesFromLastModule;
    }

    public void setTotalProcessedQueriesFromLastModule(int totalProcessedQueriesFromLastModule) {
        this.totalProcessedQueriesFromLastModule = totalProcessedQueriesFromLastModule;
    }

    public int getRejectedConnections() {
        return rejectedConnections;
    }

    public void setRejectedConnections(int rejectedConnections) {
        this.rejectedConnections = rejectedConnections;
    }

    public int getNumberOfConnections() {
        return numberOfConnections;
    }

    public void setNumberOfConnections(int numberOfConnections) {
        this.numberOfConnections = numberOfConnections;
    }
}
