package com.simulador.dbms;

import com.simulador.enums.DistributionType;
import com.simulador.modules.*;
import com.simulador.statistics.DistributionGenerationNumber;
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
        System.out.println("Pruebas");
        DistributionGenerationNumber DGN = new DistributionGenerationNumber();
        DistributionType dt = DistributionType.RANDOM;

        for (int i = 0; i < 10; i++) {

            System.out.println(DGN.identifyDistribution(dt));

            dt = DistributionType.EXPONENTIAL;
            DGN.setMEAN(1/0.7);
            System.out.println(DGN.identifyDistribution(dt));

            dt = DistributionType.NORMAL;
            System.out.println(DGN.identifyDistribution(dt));

            dt = DistributionType.UNIFORM;
            DGN.setParams(1, 64);
            System.out.println(DGN.identifyDistribution(dt));
        }

    }



}
