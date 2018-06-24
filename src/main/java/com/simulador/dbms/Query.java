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

    /**
     *
     * @param id
     * @param entryTime
     * @param type
     */
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
        int toReturn = 0;
        if (type.getPriority() < o.getPriority())
            toReturn = -1;

        else if (type.getPriority() > o.getPriority())
            toReturn = 1;

        return toReturn;

    }

    /**
     * Identifier for the query. Its unique.
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Statistics instance to store all the information about the query for the calcs.
     */
    public QueryStatistics getQueryStatistics() {
        return queryStatistics;
    }

    public void setQueryStatistics(QueryStatistics queryStatistics) {
        this.queryStatistics = queryStatistics;
    }

    /**
     * Type of the query. It can be SELECT,JOIN,DDL,UPDATE.
     */
    public QueryType getType() {
        return type;
    }

    public void setType(QueryType type) {
        this.type = type;
    }

    /**
     *  Bool to check if the query hasn't reached timeout.
     */
    public boolean isExceedTimeOut() {
        return exceedTimeOut;
    }

    public void setExceedTimeOut(boolean exceedTimeOut) {
        this.exceedTimeOut = exceedTimeOut;
    }

    /**
     *  Bool to check if the query was refused to connect.
     */
    public boolean isConnectionRefused() {
        return connectionRefused;
    }

    public void setConnectionRefused(boolean connectionRefused) {
        this.connectionRefused = connectionRefused;
    }

    /**
     *  Bool to check if the query is in some queue.
     */
    public boolean isInQueue() {
        return inQueue;
    }

    public void setInQueue(boolean inQueue) {
        this.inQueue = inQueue;
    }

    /**
     *  Number that indicates the time of the clock when its created.
     */
    public double getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(double entryTime) {
        this.entryTime = entryTime;
    }

    /**
     *  Timelife of the query.
     */
    public double getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(double lifeTime) {
        this.lifeTime = lifeTime;
    }

    /**
     *  Bool that indicates if the query is ready to pass to the next module.
     */
    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    /**
     *  Return the priority of the query based on its type.
     */
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
