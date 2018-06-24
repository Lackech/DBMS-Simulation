package com.simulador.modules;

import com.simulador.dbms.Event;
import com.simulador.dbms.Query;
import com.simulador.dbms.Simulator;
import com.simulador.enums.EventType;
import com.simulador.enums.QueryType;
import com.simulador.statistics.DistributionGenerationNumber;

import java.util.PriorityQueue;

/**
 *
 */
public class TransactionAdmModule extends GeneralModule {

    private int pQueries;

    private int currentProcessedQueries;

    private boolean blocked;

    private Query pendingQuery;

    public TransactionAdmModule(Simulator simulation, GeneralModule nextModule, int pQueries) {
        this.simulation = simulation;
        this.nextModule = nextModule;
        queue = new PriorityQueue<>();
        this.pQueries = pQueries;
        setCurrentProcessedQueries(0);
        pendingQuery = null;
        blocked = false; //booleano para caso DDL
        servers = pQueries;
    }

    @Override
    public void processEntry(Query query) {


        //si está ocupado o bloqueado
        if (isBusy() || blocked) {
            //encole
            query.setInQueue(true);
            queue.add(query);

        } else {
            //en caso de que pueda atender

            //Si la consulta es DDL
            if (query.getType() == QueryType.DDL) {
                //Bloquee el Sistema y almacene cual es la consulta por hacer
                blocked = true;
                pendingQuery = query;


                if (getCurrentProcessedQueries() == 0) {
                    // si la consulta es DDL pero no hay queries
                    setCurrentProcessedQueries(getCurrentProcessedQueries() + 1);
                    // Agregar el tiempo Respectivo que se debe sumar al clock
                    double time = getTotalTime(pendingQuery);
                    simulation.addEvent(new Event(simulation.getClock() + time,
                            pendingQuery, EventType.exitTransactionModule));

                }

            } else {
                // si la consulta no es DDL => atienda
                setCurrentProcessedQueries(getCurrentProcessedQueries() + 1);
                // Agregar el tiempo Respectivo que se debe sumar al clock
                double time = getTotalTime(query);
                simulation.addEvent(new Event((simulation.getClock() + time),
                        query, EventType.exitTransactionModule)); //REVISAR

            }
        }
    }

    @Override
    public void processExit(Query query) {

        setTotalProcessedQueries(getTotalProcessedQueries()+1);

        if (query.getType() == QueryType.DDL) {
            blocked = false;
        }
        setCurrentProcessedQueries(getCurrentProcessedQueries() - 1);
        if (queue.size() > 0) {
            if (!blocked) {
                //agregar tiempo que suma al clock
                //que se pueda, que hayan y que el siguiente no sea DDL
                while (getCurrentProcessedQueries() < pQueries && queue.size() > 0 && queue.peek().getType() != QueryType.DDL) {
                    Query quer = queue.poll();
                    quer.setInQueue(false);
                    double time = getTotalTime(quer);


                    simulation.addEvent(new Event(simulation.getClock() + time, quer, EventType.exitTransactionModule));
                    setCurrentProcessedQueries(getCurrentProcessedQueries() + 1);

                }
                if (queue.size() > 0 && queue.peek().getType() == QueryType.DDL) {
                    blocked = true;
                    pendingQuery = queue.poll();
                    if (getCurrentProcessedQueries() == 0) {
                        double time = getTotalTime(pendingQuery);
                        simulation.addEvent(new Event(simulation.getClock() + time, pendingQuery, EventType.exitTransactionModule));
                        setCurrentProcessedQueries(getCurrentProcessedQueries() + 1);

                    }
                }
            } else {
                double time = getTotalTime(pendingQuery);
                simulation.addEvent(new Event(simulation.getClock() + time,
                        pendingQuery, EventType.exitTransactionModule));

                setCurrentProcessedQueries(getCurrentProcessedQueries() + 1);

            }
        } else {
            if (getCurrentProcessedQueries() == 0) {
                if (blocked) {
                    //Ejecuta consulta pendinte

                    double time = getTotalTime(pendingQuery);
                    simulation.addEvent(new Event(simulation.getClock() + time,
                            pendingQuery, EventType.exitTransactionModule));
                    setCurrentProcessedQueries(getCurrentProcessedQueries() + 1);

                    pendingQuery = null;
                }
            }
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
            //momento en que sale de la cola
            int actualConnections = simulation.getClientConnectionModule().getCurrentConnections() - 1;
            simulation.getClientConnectionModule().setCurrentConnections(actualConnections);
        } else {
            //si es el que tiene bloqueado el sistema
            if (query == pendingQuery) {
                blocked = false;
                pendingQuery = null;

                int actualConnections = simulation.getClientConnectionModule().getCurrentConnections() - 1;
                simulation.getClientConnectionModule().setCurrentConnections(actualConnections);
                //entonces está siendo procesado
            } else {
                //matar proceso en cambio de módulo
                query.setTerminate(true);
            }
        }
        Event terminateEventToRemove = simulation.getKillEventsTable().get(query.getId());
        simulation.getKillEventsTable().remove(terminateEventToRemove);
        simulation.getEventList().remove(terminateEventToRemove);
    }

    @Override
    public void generateEvent(Query query) {

        query.setModule(4);
        simulation.addEvent(new Event(simulation.getClock(), query, EventType.enterTransactionModule));

    }

    @Override
    public boolean isBusy() {
        if(pQueries == getCurrentProcessedQueries())
            return true;
        else
            return false;
    }

    @Override
    public int getNumberOfFreeServers() {
        return pQueries- getCurrentProcessedQueries();
    }

    private int getBlockNumber(QueryType query) {
        int numberOfBlocks = 0;
        switch (query) {
            case DDL:
                numberOfBlocks = 0;
                break;

            case UPDATE:
                numberOfBlocks = 0;
                break;

            case JOIN:
                int x = (int) Math.nextUp(DistributionGenerationNumber.getNextRandomValueByUniform((double) 1, (double) 16));
                int y = (int) Math.nextUp(DistributionGenerationNumber.getNextRandomValueByUniform((double) 1, (double) 12));
                numberOfBlocks = x + y;
                break;

            case SELECT:
                numberOfBlocks = (int) Math.nextUp(DistributionGenerationNumber.getNextRandomValueByUniform((double) 1, (double) 64));
                break;
        }
        return numberOfBlocks;
    }

    public double getTotalTime(Query query) {
        int blockNumber = getBlockNumber(query.getType());
        query.setNumberOfBlocks(blockNumber);
        double totalTime = getExecutionCoordinationTime() + getBlockLoadingTime(blockNumber);
        return totalTime;
    }

    public double getExecutionCoordinationTime() {
        return pQueries * 0.03;
    }

    public double getBlockLoadingTime(int numberOfBlocks) {
        return numberOfBlocks * 0.1;
    }

    public int getCurrentProcessedQueries() {
        return currentProcessedQueries;
    }

    public void setCurrentProcessedQueries(int currentProcessedQueries) {
        this.currentProcessedQueries = currentProcessedQueries;
    }
}
