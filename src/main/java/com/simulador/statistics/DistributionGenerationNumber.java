package com.simulador.statistics;

import com.simulador.enums.DistributionType;
import com.simulador.enums.QueryType;

import java.util.List;
import java.util.Random;

/**
 * Class with all the distributions, also we need to make the distr to generate the type of the query
 */
public class DistributionGenerationNumber {


    /**
     * Uses Montecarlo's method to generate the next type of query.
     *
     * @return an type of query.
     */
    public static QueryType generateType() {
        Random rnd = new Random();
        double randomNumber = rnd.nextDouble();
        QueryType query;

        if (randomNumber < 0.32) {
            query = QueryType.SELECT;
        } else if (randomNumber > 0.31 && randomNumber < 0.60) {
            query = QueryType.UPDATE;
        } else if (randomNumber > 0.59 && randomNumber < 0.93) {
            query = QueryType.JOIN;
        } else {
            query = QueryType.DDL;
        }

        return query;
    }

    /**
     * Uses the Poisson distribution to generate the time of the next arrival.
     *
     * @param lambda Average of arrivals per unit time.
     * @return The time from the next arrival.
     */
    public static double getNextArrivalTime(double lambda) {
        Random rnd = new Random();
        double aleatoryNumber = rnd.nextDouble();
        return -Math.log(aleatoryNumber) / lambda;
    }


    /**
     * Uses the inverse transform sampling with the uniform distribution to generate a random value.
     *
     * @param a Is the lowest value.
     * @param b Is the higer value.
     * @return A random value belonging to the interval [a , b].
     */
    public static double getNextRandomValueByUniform(double a, double b) {
        Random rnd = new Random();
        double r = rnd.nextDouble();
        return (r * (b - a)) + a;
    }


    /**
     * Uses the inverse transform sampling with the exponential distribution to generate a random value.
     *
     * @param lambda Average time between arrivals.
     * @return A random value.
     */
    public static double getNextRandomValueByExponential(double lambda) {
        Random rnd = new Random();
        double r = rnd.nextDouble();
        return -Math.log(r) / (lambda);
    }


    /**
     * Uses the inverse transform sampling with the normal distribution to generate a random value.
     *
     * @param average           Param of the median value inside the distribution.
     * @param standardDeviation Param of the standard deviation inside the distribution.
     * @return A random value.
     */
    public static double getNextRandomValueByNormal(double average, double standardDeviation) {
        double z = 0;
        double x;
        Random rnd = new Random();
        for (int i = 0; i < 12; i++) {
            z += rnd.nextDouble();
        }
        z -= 6;
        x = average + standardDeviation * z;
        return x;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////





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
