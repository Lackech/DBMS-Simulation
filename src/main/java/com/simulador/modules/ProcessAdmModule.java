package com.simulador.modules;

import com.simulador.dbms.Event;
import com.simulador.dbms.Query;
import com.simulador.dbms.Simulator;
import com.simulador.enums.EventType;
import com.simulador.statistics.DistributionGenerationNumber;

import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 */
public class ProcessAdmModule extends GeneralModule {

    private int currentSystemCalls;

    private int availableSystemCalls;

    public ProcessAdmModule(Simulator simulation, GeneralModule nextModule, int availableSystemCalls) {
        this.simulation = simulation;
        this.nextModule = nextModule;
        this.queue = new LinkedBlockingQueue<>();
        busy = false;
        setTotalProcessedQueries(0);
        setCurrentSystemCalls(0);
        this.availableSystemCalls = availableSystemCalls;
        servers = availableSystemCalls;

    }

    @Override
    public void processEntry(Query query) {

        if (this.isBusy()) {
            query.setInQueue(true);
            queue.add(query);
        } else {
            setCurrentSystemCalls(getCurrentSystemCalls() + 1);
            double normalValue = DistributionGenerationNumber.getNextRandomValueByNormal(1.5, Math.sqrt(0.1));
            simulation.addEvent(new Event(simulation.getClock() + normalValue,
                    query, EventType.exitProcessAdmModule));
        }
    }

    @Override
    public void processExit(Query query) {

        setTotalProcessedQueries(getTotalProcessedQueries()+1);
        if (queue.size() > 0) {
            double normalValue = DistributionGenerationNumber.getNextRandomValueByNormal(1.5, Math.sqrt(0.1));
            Query quer = queue.poll();
            quer.setInQueue(false);
            simulation.addEvent(new Event(simulation.getClock() + normalValue,
                    quer, EventType.exitProcessAdmModule));


        } else {
            setCurrentSystemCalls(getCurrentSystemCalls() - 1);

        }
        if (!query.isTerminate()) {
            nextModule.generateEvent(query);

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


        } else
            query.setTerminate(true);

        Event killEventToRemove = simulation.getKillEventsTable().get(query.getId());
        simulation.getKillEventsTable().remove(killEventToRemove);
        simulation.getEventList().remove(killEventToRemove);

    }

    @Override
    public void generateEvent(Query query) {

        query.setModule(2);
        simulation.addEvent(new Event(simulation.getClock(), query, EventType.enterProcessAdmModule));

    }

    @Override
    public boolean isBusy() {
        if(getCurrentSystemCalls() == availableSystemCalls)
            return true;
        else
            return false;
    }

    @Override
    public int getNumberOfFreeServers() {
        return availableSystemCalls- getCurrentSystemCalls();
    }

    public int getCurrentSystemCalls() {
        return currentSystemCalls;
    }

    public void setCurrentSystemCalls(int currentSystemCalls) {
        this.currentSystemCalls = currentSystemCalls;
    }
}
