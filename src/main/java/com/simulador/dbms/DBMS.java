package com.simulador.dbms;

import javax.swing.*;

public class DBMS {

    private int kConnections;

    private int nAvailableProcesses;

    private int pConcurrentQueries;

    private int mParalellSentences;

    private double timeOut;

    private double maxTimeSimulation;

    private int totalSimulations;

    private int availableSystemCalls;

    private double delay;

    public DBMS(int kConnections,int availableSystemCalls, int nAvailableProcesses, int pConcurrentQueries, int mParalellSentences,
                double timeOut,double delay, double maxTimeSimulation, int totalSimulations){
        this.setkConnections(kConnections);
        this.setnAvailableProcesses(nAvailableProcesses);
        this.setpConcurrentQueries(pConcurrentQueries);
        this.setmParalellSentences(mParalellSentences);
        this.setTimeOut(timeOut);
        this.setMaxTimeSimulation(maxTimeSimulation);
        this.setTotalSimulations(totalSimulations);
        this.availableSystemCalls = availableSystemCalls;
        this.delay = delay;
    }

    public void runForestRun(JTextArea txtData){
        for(int i = 0;i < this.getTotalSimulations();i++){
            //Start
            Simulator simulation = new Simulator(i + 1,delay,kConnections,availableSystemCalls,
                    nAvailableProcesses,pConcurrentQueries,mParalellSentences,timeOut,maxTimeSimulation);
            simulation.startSimulation(txtData);
        }
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
}
