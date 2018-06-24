package com.simulador.enums;

/**
 *
 */
public enum EventType {
    enterClientAdmModule(1,true,false) ,exitClientAdmModule(1,false,false), enterProcessAdmModule(2,true,false), exitProcessAdmModule(2,false,false),
    enterQueryProcessorModule(3,true,false), exitQueryProcessorModule(3,false,false), enterTransactionModule(4,true,false),
    exitTransactionModule(4,false,false), enterQueryExecModule(5,true,false), exitQueryExecModule(5,false,false),kill(0,false,true);

    private int typeMod;
    private boolean isEntry;
    private boolean terminate;

    EventType(int typeMod, boolean entry,boolean terminate) {
        this.typeMod = typeMod;
        this.isEntry = entry;
        this.setTerminate(terminate);
    }

    public int getTypeMod(){return typeMod; }

    public boolean getIsEntry(){return isEntry; }

    public boolean isTerminate() {
        return terminate;
    }

    public void setTerminate(boolean terminate) {
        this.terminate = terminate;
    }
}
