package com.simulador.statistics;
import com.simulador.enums.DistributionType;

import java.util.*;
import java.lang.Math;

/**
 * Clase que genera un numero aleatorio respecto a un tipo de distribucion deseada
 */
public class DistributionGenerationNumber {

    /**
     * lista global donde se alamcenan los tipos de distribucioes solicitadas
     * */
    private List<DistributionType> listaDistribucion;
    Random random;
    int A, B;
    double MEAN;

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

           case UNIFORM: out = generateUniformDistributionNumber(A,B);
               break;

           case RANDOM: out = generateRandomNumber();
               break;

           case EXPONENTIAL: out = generateExponentialDistributionNumber(MEAN);
               break;
       }

       return out;

    }


    private double generateUniformDistributionNumber(int low, int high){
        int a = low, b = high;
        double rand = random.nextDouble();
        return a + (b-a)*rand;
    }
    private double generateNormalDistributionNumber(){

       double randomNumer = 0;
        for (int i = 0; i < 12; i++) {

            randomNumer += random.nextDouble();
        }

        double Z = randomNumer - 6;

        return 1 + (Math.sqrt(0.01) * Z);
    }
    private double generateExponentialDistributionNumber(double mean){
        double lambda = mean;
        return (-1/lambda)*Math.log(random.nextDouble());

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

    public void setParams(int a, int b) {
        A = a;
        B = b;
    }

    public void setMEAN(double MEAN) {
        this.MEAN = MEAN;
    }
}
