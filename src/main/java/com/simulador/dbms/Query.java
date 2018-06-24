package com.simulador.dbms;

import com.simulador.enums.QueryType;
import com.simulador.statistics.QueryStatistics;

/**
 * Class for queries that store information about itself.
 */
public class Query implements Comparable<Query>{

    private int id;

    private QueryStatistics queryStatistics;

    private QueryType type;

    private boolean exceedTimeOut;

    private int module;

    private boolean connectionRefused;

    private boolean inQueue;

    private double entryTime;

    private double lifeTime;

    private int numberOfBlocks;

    private boolean ready;

    private boolean terminate;

    public Query(int id, double entryTime, QueryType type, int module) {
        this.setId(id);
        this.setEntryTime(entryTime);
        this.setType(type);
        setQueryStatistics(new QueryStatistics());
        setLifeTime(0);
        setInQueue(false);
        setReady(false);
        setExceedTimeOut(false);
        setConnectionRefused(false);
        setModule(module);
        setNumberOfBlocks(0);
        setTerminate(false);
    }

    @Override
    public int compareTo(Query o) {
        int ret = 0;
        if (type.getPriority() < o.getPriority())
            ret = -1;

        else if (type.getPriority() > o.getPriority())
            ret = 1;

        return ret;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QueryStatistics getQueryStatistics() {
        return queryStatistics;
    }

    public void setQueryStatistics(QueryStatistics queryStatistics) {
        this.queryStatistics = queryStatistics;
    }

    public QueryType getType() {
        return type;
    }

    public void setType(QueryType type) {
        this.type = type;
    }

    public boolean isExceedTimeOut() {
        return exceedTimeOut;
    }

    public void setExceedTimeOut(boolean exceedTimeOut) {
        this.exceedTimeOut = exceedTimeOut;
    }

    public boolean isConnectionRefused() {
        return connectionRefused;
    }

    public void setConnectionRefused(boolean connectionRefused) {
        this.connectionRefused = connectionRefused;
    }

    public boolean isInQueue() {
        return inQueue;
    }

    public void setInQueue(boolean inQueue) {
        this.inQueue = inQueue;
    }

    public double getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(double entryTime) {
        this.entryTime = entryTime;
    }

    public double getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(double lifeTime) {
        this.lifeTime = lifeTime;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public int getPriority(){
        return type.getPriority();
    }

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
    }

    public int getNumberOfBlocks() {
        return numberOfBlocks;
    }

    public void setNumberOfBlocks(int numberOfBlocks) {
        this.numberOfBlocks = numberOfBlocks;
    }

    public boolean isTerminate() {
        return terminate;
    }

    public void setTerminate(boolean terminate) {
        this.terminate = terminate;
    }
}
