package com.simulador.statistics;

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
