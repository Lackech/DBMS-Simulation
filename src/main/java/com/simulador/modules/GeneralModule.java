package com.simulador.modules;

import com.simulador.dbms.*;
/**
 *
 */
public abstract class GeneralModule {

    private double timeOut;
    private boolean busy;

    public GeneralModule() {

        timeOut = 0;
        busy = false;
    }


    boolean reachTimeOut(Query query){

        return true;
    }

    void processEvent(Event event){

    }

    void generateExitEvent(Event event){


    }

    void generateNextEvent(Event event){


    }
    Integer obtainSizeQueue(){

        Integer i = 0;

        return i;
    }

    boolean isBusy(){

        return busy;
    }

}
