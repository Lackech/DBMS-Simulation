package com.simulador.dbms;

import com.simulador.modules.*;
import com.simulador.statistics.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 */
public class Simulador {

    /**
     *
     * @param args
     */

    ClientAdmModule clientsModule;

    ProcessAdmModule processModule;

    QueryProcessorModule queryModule;

    TransactionAdmModule transModule;

    QueryExecutionerModule queryExecModule;

    PriorityQueue<Event> eventList = new PriorityQueue<>();

    List<Query> queryList = new ArrayList<>();

    Statistics simulationStatistics;

    Statistics totalStatistics;












    public static void main(String[] args){
        System.out.println("Hola Mundo, esto es una prueba");
    }



}
