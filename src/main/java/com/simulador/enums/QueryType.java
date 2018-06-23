package com.simulador.enums;

/**
 *
 */
public enum QueryType {
    SELECT(4,true),UPDATE(2,false),JOIN(3,true),DDL(1,false);

    private int priority;
    private boolean READ_ONLY;

    QueryType(int priority, boolean read) {
        this.priority = priority;
        this.READ_ONLY = read;
    }

    public int getPriority() {
        return priority;
    }
}
