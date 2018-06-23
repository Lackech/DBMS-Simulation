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

    //////////////////////////////////////////// BEFORE THE QUERY IS PROCESSED ///////////////////////////////////////
    /**
     * Average number of arrivals to the system per time second.
     */
    public static final double LAMBDA = 0.5;

    /**
     * A list of all the queries that have entered successfully to the system.
     */
    private List<Query> allQueries;

    /**
     * User defined parameter of the amount of concurrent connections the system can handle.
     */
    private int kConnections;

    /**
     * Counter that measures the amount of connections rejected by the system.
     */
    private int rejectedConnections;

    private int currentConnections;

    private int totalProcessedQueriesFromLastModule;


    /**
     * Each query is tagged with an ID (name defined by the number of query that manages to connect into the system).
     * Essentially, this variable traces the current query number.
     */
    private int currentId;

    public ClientAdmModule(Simulator simulation, GeneralModule nextModule, int kConnections) {
        this.simulation = simulation;
        this.nextModule = nextModule;
        this.kConnections = kConnections;
        allQueries = new LinkedList<>();
        queue = new LinkedBlockingQueue<>();
        currentId = 1;
        rejectedConnections = 0;
        setCurrentConnections(0);
        setTotalProcessedQueries(0);
        servers = kConnections;
        hasQueue = false;
        setTotalProcessedQueriesFromLastModule(0);


    }


    @Override
    public void processEntry(Query query) {
        if (query.isReady()) {
            processArrivalLastModule(query);

        } else {
            processArrivalFirstModule(query);

        }

    }

    @Override
    public void processExit(Query query) {
        if (query.isReady())
            processDepartureOfSystem(query);

        else
            processDepartureToNextModule(query);
    }

    @Override
    public void processTerminate(Query query) {

        if (!query.isReady())
            query.setTerminate(true);
    }

    @Override
    public void generateServiceEvent(Query query) {
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


    private void processArrivalFirstModule(Query query) {
        if (isBusy())
            rejectedConnections++;
        else {

            setCurrentConnections(getCurrentConnections() + 1);
            double time = getNextExitTime();
            simulation.addEvent(new Event(simulation.getClock() + time, query,
                    EventType.exitClientAdmModule));

            allQueries.add(query);
        }
        generateServiceEvent(null);
    }

    /**
     * Decides what to do with the query in case it's resolved.
     *
     * @param query specific resolved query.
     */
    private void processArrivalLastModule(Query query) {

        double time = getResultantTime(query.getNumberOfBlocks());
        simulation.addEvent(new Event(time + simulation.getClock(),
                query, EventType.exitClientAdmModule));

    }

    /**
     * Creates the first event and places it in this simulation in order to start in execution time.
     */
    public void generateFirstArrival() {
        currentId++;
        Query query = new Query(currentId, simulation.getClock(), DistributionGenerationNumber.generateType(),1);
        simulation.addEvent(new Event(simulation.getClock(), query,
                EventType.enterClientAdmModule));

    }

    /**
     * Handles the unresolved query's departure to the next module.
     *
     * @param query specific unresolved query.
     */
    private void processDepartureToNextModule(Query query) {
        setTotalProcessedQueries(getTotalProcessedQueries() + 1);
        if (!query.isTerminate()) {
            nextModule.generateServiceEvent(query);

        } else {
            setCurrentConnections(getCurrentConnections() - 1);
        }

    }

    /**
     * Handles the resolved query's departure out of the system.
     *
     * @param query specific resolved query.
     */
    private void processDepartureOfSystem(Query query) {
        setTotalProcessedQueriesFromLastModule(getTotalProcessedQueriesFromLastModule() + 1);

        setCurrentConnections(getCurrentConnections() - 1);
        query.setLifeTime(simulation.getClock() - query.getEntryTime());
        Event eventToRemove = simulation.getKillEventsTable().get(query.getId());
        simulation.getEventList().remove(eventToRemove);


    }

    /**
     * Generates the exit time of the module using the uniform distribution random number generator.
     *
     * @return the random value.
     */
    public double getNextExitTime() {
        return DistributionGenerationNumber.getNextRandomValueByUniform(0.01, 0.05);
    }

    /**
     * Creates the time the query takes to display its results to the user.
     *
     * @param numberOfBlocks the amount of blocks that had to be loaded for the query.
     * @return the resultant time.
     */
    public double getResultantTime(int numberOfBlocks) {
        double average = numberOfBlocks / 3;
        return average / 2;
    }


    /**
     * The amount of connections managed by the system in a specific moment.
     */
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
}
