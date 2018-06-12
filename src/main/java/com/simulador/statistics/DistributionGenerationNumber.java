package com.simulador.statistics;
import com.simulador.enums.DistributionType;

import java.util.List;

/**
 * Clase que genera un numero aleatorio respecto a un tipo de distribucion deseada
 */
public class DistributionGenerationNumber {

    /**
     * lista global donde se alamcenan los tipos de distribucioes solicitadas
     * */
    private List<DistributionType> listaDistribucion;

    /**
     * Constructor basico para la clase  */
    public DistributionGenerationNumber() {
    }

    /**
     * Metodo encargado de reconocer el tipo de distribucion deseada
     * @param DT tipo de distribucion
     * */
   public void identifyDistribution(DistributionType DT ){


    }


    double generateUniformDistributionNumber(){
        double a = 0;
        return a;
    }
    double generateNormalDistributionNumber(){

        double a = 0;
        return a;
    }
    double generateExponentialDistributionNumber(){
        double a = 0;
        return a;

    }
    double generateRandomNumber(){

        double a = 0;
        return a;
    }


    public List<DistributionType> getListaDistribucion() {
        return listaDistribucion;
    }

    public void setListaDistribucion(List<DistributionType> listaDistribucion) {
        this.listaDistribucion = listaDistribucion;
    }
}
