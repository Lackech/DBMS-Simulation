package com.simulador.statistics;

import java.util.ArrayList;

/**
 *
 */
public class Statistics {

    /**
     *  Declaracion global de variables para la ejecucion general de la simulacion
     */
    double simulationTime;
    Integer analizedQueries;
    Integer refusedQueries;

    /**
     * Contructor estandar para la clase
     *
     * @param analizedQueries Numero total de consultas analizadas en la simulacion
     * @param refusedQueries  Numero total de consultas rechazadas a la hora de entablar conexion
     * @param simulationTime  Numero total de duracion de la simulacion
     */
    public Statistics(double simulationTime, Integer analizedQueries, Integer refusedQueries) {
        this.simulationTime = simulationTime;
        this.analizedQueries = analizedQueries;
        this.refusedQueries = refusedQueries;
    }

    double calculateAverageSizeQueque(ArrayList<Integer> queueSizes){

        double resultAverageQueque = 0;

        for (int i = 0; i < queueSizes.size(); i++) {

            resultAverageQueque += queueSizes.get(i);
        }

        resultAverageQueque = resultAverageQueque/queueSizes.size();

        return resultAverageQueque;
    }

    double calculateAverageLifetimeQuery(ArrayList<Float> responseTime){

        double resultAverageLifeTime = 0;

        for (int i = 0; i < responseTime.size(); i++) {

            resultAverageLifeTime += responseTime.get(i);
        }

        resultAverageLifeTime = resultAverageLifeTime/this.analizedQueries;

        // o si no...
       /* int responseTime = this.analizedQueries / lambda;
        resultAverageLifeTime = responseTime;*/
       
        return resultAverageLifeTime;
    }
    double calculateAverageTimePerQueryType(){

        double resultTPQ = 0;

        return resultTPQ;
    }

    public double getSimulationTime() {
        return simulationTime;
    }

    public void setSimulationTime(double simulationTime) {
        this.simulationTime = simulationTime;
    }

    public Integer getAnalizedQueries() {
        return analizedQueries;
    }

    public void setAnalizedQueries(Integer analizedQueries) {
        this.analizedQueries = analizedQueries;
    }

    public Integer getRefusedQueries() {
        return refusedQueries;
    }

    public void setRefusedQueries(Integer refusedQueries) {
        this.refusedQueries = refusedQueries;
    }
}
