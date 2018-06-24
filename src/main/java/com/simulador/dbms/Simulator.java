package com.simulador.dbms;

import com.simulador.modules.*;

import javax.swing.*;
import java.util.Hashtable;
import java.util.PriorityQueue;

import static com.simulador.enums.EventType.kill;

/**
 *
 */
public class Simulator {

    private int kConnections;

    private int availableSystemCalls;

    private int nAvailableProcesses;

    private int pConcurrentQueries;

    private int mParalellSentences;

    private double timeOut;

    private double maxTimeSimulation;

    private int totalSimulations;

    private double clock;

    private boolean slowMode;

    private double delayTime;

    private PriorityQueue<Event> eventList;

    private Hashtable<Integer, Event> terminateEventsTable;

    private int simulationId;

    private ClientAdmModule clientConnectionModule;

    private ProcessAdmModule processManagerModule;

    private QueryProcessorModule queryProcessingModule;

    private TransactionAdmModule transactionAndDataAccessModule;

    private QueryExecutionerModule executionModule;

    public Simulator(int simulationId, double delayTime, int kConnections, int availableSystemCalls, int nAvailableProcesses,
                      int pConcurrentQueries, int mSentences, double timeOut, double maxTimeSimulation) {


        this.setSimulationId(simulationId);
        this.timeOut = timeOut;
        clock = 0;
        totalSimulations = 0;
        this.maxTimeSimulation = maxTimeSimulation;
        eventList = new PriorityQueue<>();
        this.availableSystemCalls = availableSystemCalls;
        this.delayTime = delayTime;

        setExecutionModule(new QueryExecutionerModule(this, mSentences));
        setTransactionAndDataAccessModule(new TransactionAdmModule(this, getExecutionModule(), pConcurrentQueries));
        setQueryProcessingModule(new QueryProcessorModule(this, getTransactionAndDataAccessModule(), nAvailableProcesses));
        setProcessManagerModule(new ProcessAdmModule(this, getQueryProcessingModule(), availableSystemCalls));
        setClientConnectionModule(new ClientAdmModule(this, getProcessManagerModule(), kConnections));
        getExecutionModule().setNextModule(getClientConnectionModule());

        this.kConnections = kConnections;
        this.availableSystemCalls = availableSystemCalls;
        this.nAvailableProcesses = nAvailableProcesses;
        this.pConcurrentQueries = pConcurrentQueries;
        this.mParalellSentences = mSentences;
        getClientConnectionModule().generateFirstArrival();
        terminateEventsTable = new Hashtable<>();

    }

    private void manageEvent(Event event) {

        if (event.getTypeOfEvent() == kill){
            switch (event.getModule()) {
                case 1:
                    getClientConnectionModule().processTerminate(event.getAssociatedQuery());
                    break;

                case 2:
                    getProcessManagerModule().processTerminate(event.getAssociatedQuery());
                    break;

                case 3:
                    getQueryProcessingModule().processTerminate(event.getAssociatedQuery());
                    break;

                case 4:
                    getTransactionAndDataAccessModule().processTerminate(event.getAssociatedQuery());
                    break;

                case 5:
                    getExecutionModule().processTerminate(event.getAssociatedQuery());
                    break;
            }
        }else if (event.isBehavior()) {
            switch (event.getModule()) {
                    case 1:
                        getClientConnectionModule().processEntry(event.getAssociatedQuery());
                        break;

                    case 2:
                        getProcessManagerModule().processEntry(event.getAssociatedQuery());
                        break;

                    case 3:
                        getQueryProcessingModule().processEntry(event.getAssociatedQuery());
                        break;

                    case 4:
                        getTransactionAndDataAccessModule().processEntry(event.getAssociatedQuery());
                        break;

                    case 5:
                        getExecutionModule().processEntry(event.getAssociatedQuery());
                        break;
            }
        } else {
            switch (event.getModule()) {
                    case 1:
                        getClientConnectionModule().processExit(event.getAssociatedQuery());
                        break;

                    case 2:
                        getProcessManagerModule().processExit(event.getAssociatedQuery());
                        break;

                    case 3:
                        getQueryProcessingModule().processExit(event.getAssociatedQuery());
                        break;

                    case 4:
                        getTransactionAndDataAccessModule().processExit(event.getAssociatedQuery());
                        break;

                    case 5:
                        getExecutionModule().processExit(event.getAssociatedQuery());
                        break;
            }
        }
    }

    public String getData(Event event) {
        String simulation = "Simulation number " + getSimulationId() + "\n";
        String parameters = "Available connections(k): " + getClientConnectionModule().getNumberOfFreeServers() +
                "\nAvailable Systems Calls: " + getProcessManagerModule().getNumberOfFreeServers() +
                "\nAvailable Processes for query processing(n): " + getQueryProcessingModule().getNumberOfFreeServers() +
                "\nAvailable processes for query transactions(p): " + getTransactionAndDataAccessModule().getNumberOfFreeServers() +
                "\nAvailable processes for query executions(m): " + getExecutionModule().getNumberOfFreeServers();

        String clock = "\nClock time: " + getClock();

        String eventInExecution = "\nExecuting " + event.getTypeOfEvent() + " in " + event.getModule() + " module\n\n";

        String clientConnectionData = "Client Connection Module: \n" +
                "Occupied servers: " + getClientConnectionModule().getCurrentConnections() + "\n" +
                "Free Servers: " + getClientConnectionModule().getNumberOfFreeServers() + "\n" +
                "Size of the Queue: " + getClientConnectionModule().getQueueSize() + "\n" +
                "Processed queries: " + clientConnectionModule.getTotalProcessedQueries() + "\n\n";

        String processManagerData = "Process Manager Module: \n" +
                "Occupied servers: " + getProcessManagerModule().getCurrentSystemCalls() + "\n" +
                "Free Servers: " + getProcessManagerModule().getNumberOfFreeServers() + "\n" +
                "Size of the Queue: " + getProcessManagerModule().getQueueSize() + "\n" +
                "Processed queries: " + processManagerModule.getTotalProcessedQueries() + "\n\n";

        String queryProcessingData = "Query Processing Module: \n" +
                "Occupied servers: " + getQueryProcessingModule().getCurrentProcesses() + "\n" +
                "Free Servers: " + getQueryProcessingModule().getNumberOfFreeServers() + "\n" +
                "Size of the Queue: " + getQueryProcessingModule().getQueueSize() + "\n" +
                "Processed queries: " + queryProcessingModule.getTotalProcessedQueries() + "\n\n";

        String transactionAndDataAccessData = "Transaction and Data Access Module: \n" +
                "Occupied servers: " + getTransactionAndDataAccessModule().getNumberOfFreeServers() + "\n" +
                "Free Servers: " + getTransactionAndDataAccessModule().getNumberOfFreeServers() + "\n" +
                "Size of the Queue: " + getTransactionAndDataAccessModule().getQueueSize() + "\n" +
                "Processed queries: " + transactionAndDataAccessModule.getTotalProcessedQueries() + "\n\n";

        String executionData = "Execution Module: \n" +
                "Occupied servers: " + getExecutionModule().getCurrentSentences() + "\n" +
                "Free Servers: " + getExecutionModule().getNumberOfFreeServers() + "\n" +
                "Size of the Queue: " + getExecutionModule().getQueueSize() + "\n" +
                "Processed queries: " + executionModule.getTotalProcessedQueries() + "\n\n";

        String lastModuleData = "Exit Module: \n" +
                "Occupied servers: " + getClientConnectionModule().getCurrentConnections() + "\n" +
                "Free Servers: " + getClientConnectionModule().getNumberOfFreeServers() + "\n" +
                "Size of the Queue: " + getClientConnectionModule().getQueueSize() + "\n" +
                "Processed queries: " + clientConnectionModule.getTotalProcessedQueriesFromLastModule() + "\n\n";

        return simulation + parameters + clock + eventInExecution + clientConnectionData +
                processManagerData + queryProcessingData + transactionAndDataAccessData + executionData + lastModuleData;
    }

    public void startSimulation(JTextArea data) {
        while (getClock() < maxTimeSimulation) {
            Event event = eventList.poll();
            clock = event.getAssociatedTime();
            this.manageEvent(event);
            System.out.println(this.getData(event));
            data.setText(this.getData(event));
            data.update(data.getGraphics());

            try {
                //Thread.sleep((long) delayTime * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setClientConnectionModule(ClientAdmModule clientConnectionModule) {
        this.clientConnectionModule = clientConnectionModule;
    }

    public void setProcessManagerModule(ProcessAdmModule processManagerModule) {
        this.processManagerModule = processManagerModule;
    }

    public void setQueryProcessingModule(QueryProcessorModule queryProcessingModule) {
        this.queryProcessingModule = queryProcessingModule;
    }

    public void setTransactionAndDataAccessModule(TransactionAdmModule transactionAndDataAccessModule) {
        this.transactionAndDataAccessModule = transactionAndDataAccessModule;
    }

    public QueryProcessorModule getQueryProcessingModule() {
        return queryProcessingModule;
    }

    public ProcessAdmModule getProcessManagerModule() {
        return processManagerModule;
    }

    public int getSimulationId() {
        return simulationId;
    }

    public void setSimulationId(int simulationId) {
        this.simulationId = simulationId;
    }

    public ClientAdmModule getClientConnectionModule() {
        return clientConnectionModule;
    }

    public TransactionAdmModule getTransactionAndDataAccessModule() {
        return transactionAndDataAccessModule;
    }

    public QueryExecutionerModule getExecutionModule() {
        return executionModule;
    }

    public void setExecutionModule(QueryExecutionerModule executionModule) {
        this.executionModule = executionModule;
    }

    public Hashtable<Integer, Event> getKillEventsTable() {
        return terminateEventsTable;
    }

    public void addEvent(Event event) {
        eventList.add(event);
    }

    public int getkConnections() {
        return kConnections;
    }

    public void setkConnections(int kConnections) {
        this.kConnections = kConnections;
    }

    public int getnAvailableProcesses() {
        return nAvailableProcesses;
    }

    public void setnAvailableProcesses(int nAvailableProcesses) {
        this.nAvailableProcesses = nAvailableProcesses;
    }

    public int getpConcurrentQueries() {
        return pConcurrentQueries;
    }

    public void setpConcurrentQueries(int pConcurrentQueries) {
        this.pConcurrentQueries = pConcurrentQueries;
    }

    public int getmParalellSentences() {
        return mParalellSentences;
    }

    public void setmParalellSentences(int mParalellSentences) {
        this.mParalellSentences = mParalellSentences;
    }

    public double getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(double timeOut) {
        this.timeOut = timeOut;
    }

    public double getMaxTimeSimulation() {
        return maxTimeSimulation;
    }

    public void setMaxTimeSimulation(double maxTimeSimulation) {
        this.maxTimeSimulation = maxTimeSimulation;
    }

    public int getTotalSimulations() {
        return totalSimulations;
    }

    public void setTotalSimulations(int totalSimulations) {
        this.totalSimulations = totalSimulations;
    }

    public double getClock() {
        return clock;
    }

    public void setClock(double clock) {
        this.clock = clock;
    }

    public boolean isSlowMode() {
        return slowMode;
    }

    public void setSlowMode(boolean slowMode) {
        this.slowMode = slowMode;
    }

    public PriorityQueue<Event> getEventList() {
        return eventList;
    }

    public void setEventList(PriorityQueue<Event> eventList) {
        this.eventList = eventList;
    }

    public int getAvailableSystemCalls() {
        return availableSystemCalls;
    }

    public void setAvailableSystemCalls(int availableSystemCalls) {
        this.availableSystemCalls = availableSystemCalls;
    }


}
