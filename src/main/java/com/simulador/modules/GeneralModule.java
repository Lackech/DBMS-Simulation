package com.simulador.modules;

import com.simulador.dbms.*;

import java.util.List;
import java.util.Queue;

/**
 *  Abstract class of a module.
 */
public abstract class GeneralModule {

    private int totalProcessedQueries;

    protected boolean busy;

    protected GeneralModule nextModule;

    protected Queue<Query> queue;

    protected int servers;

    protected Simulator simulation;

    protected boolean hasQueue = true;

    public abstract void processEntry(Query query);

    public abstract void processExit(Query query);

    public abstract void processTerminate(Query query);

    public abstract void generateEvent(Query query);

    public abstract boolean isBusy();

    public abstract int getNumberOfFreeServers();

    public int getQueueSize() {
        return queue.size();
    }

    public int getTotalProcessedQueries() {
        return totalProcessedQueries;
    }

    public void setTotalProcessedQueries(int totalProcessedQueries) {
        this.totalProcessedQueries = totalProcessedQueries;
    }
}
