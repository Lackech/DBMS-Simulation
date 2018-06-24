package com.simulador.dbms;

import com.simulador.enums.EventType;

/**
 *
 */
public class Event implements Comparable<Event>{

    private EventType typeOfEvent;

    private Double associatedTime;

    private Query associatedQuery;

    private int module;

    private boolean behavior;

    private boolean terminate;

    public Event(double time, Query query, EventType eventType) {
        this.setModule(eventType.getTypeMod());
        this.setAssociatedQuery(query);
        this.setTypeOfEvent(eventType);
        this.setAssociatedTime(time);
        this.setBehavior(eventType.getIsEntry());
        this.setTerminate(eventType.isTerminate());
    }

    @Override
    public int compareTo(Event o) {
        int toReturn = 0;
        if (associatedTime < o.getAssociatedTime())
            toReturn = -1;

        else if (associatedTime >= o.getAssociatedTime())
            toReturn = 1;

        return toReturn;

    }

    public EventType getTypeOfEvent() {
        return typeOfEvent;
    }

    public void setTypeOfEvent(EventType typeOfEvent) {
        this.typeOfEvent = typeOfEvent;
    }

    public Double getAssociatedTime() {
        return associatedTime;
    }

    public void setAssociatedTime(Double associatedTime) {
        this.associatedTime = associatedTime;
    }

    public Query getAssociatedQuery() {
        return associatedQuery;
    }

    public void setAssociatedQuery(Query associatedQuery) {
        this.associatedQuery = associatedQuery;
    }

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
    }

    public boolean isBehavior() {
        return behavior;
    }

    public void setBehavior(boolean behavior) {
        this.behavior = behavior;
    }

    public boolean isTerminate() {
        return terminate;
    }

    public void setTerminate(boolean terminate) {
        this.terminate = terminate;
    }
}
