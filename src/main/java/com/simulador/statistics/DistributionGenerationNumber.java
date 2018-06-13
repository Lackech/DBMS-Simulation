package com.simulador.statistics;
import com.simulador.enums.DistributionType;

import java.util.*;

/**
 * Clase que genera un numero aleatorio respecto a un tipo de distribucion deseada
 */
public class DistributionGenerationNumber {

    /**
     * lista global donde se alamcenan los tipos de distribucioes solicitadas
     * */
    private List<DistributionType> listaDistribucion;
    Random random;

    /**
     * Constructor basico para la clase  */
    public DistributionGenerationNumber() {


        random = new Random();
    }

    /**
     * Metodo encargado de reconocer el tipo de distribucion deseada
     * @param DT tipo de distribucion
     * */
   public double identifyDistribution(DistributionType DT ){

        double out = 0;
       switch(DT){

           case NORMAL: out = generateNormalDistributionNumber();
               break;

           case UNIFORM: out = generateUniformDistributionNumber();
               break;

           case RANDOM: out = generateRandomNumber();
               break;

           case EXPONENTIAL: out = generateExponentialDistributionNumber();
               break;
       }

       return out;

    }


    private double generateUniformDistributionNumber(){
        double a = 0;
        return a;
    }
    private double generateNormalDistributionNumber(){

        double a = 0;
        return a;
    }
    private double generateExponentialDistributionNumber(){
        double a = 0;
        return a;

    }
    private double generateRandomNumber(){

        return random.nextDouble();
    }


    public List<DistributionType> getListaDistribucion() {
        return listaDistribucion;
    }

    public void setListaDistribucion(List<DistributionType> listaDistribucion) {
        this.listaDistribucion = listaDistribucion;
    }
}
