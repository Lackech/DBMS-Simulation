package com.simulador.statistics;

import com.simulador.enums.QueryType;

import java.util.List;
import java.util.Random;

/**
 * Class with all the distributions, also we need to make the distr to generate the type of the query
 */
public class DistributionGenerationNumber {

    public static QueryType generateType() {
        Random rnd = new Random();
        double randomNumber = rnd.nextDouble();
        QueryType query;

        if (randomNumber < 0.30) {
            query = QueryType.SELECT;
        } else if (randomNumber > 0.29 && randomNumber < 0.56) {
            query = QueryType.UPDATE;
        } else if (randomNumber > 0.56 && randomNumber < 0.91) {
            query = QueryType.JOIN;
        } else {
            query = QueryType.DDL;
        }

        return query;
    }

    public static double getNextArrivalTime(double lambda) {
        Random rnd = new Random();
        double randNumber = rnd.nextDouble();
        return (-1/lambda)*Math.log(randNumber);
    }

    public static double getNextRandomValueByUniform(double a, double b) {
        Random rnd = new Random();
        double rand = rnd.nextDouble();
        return a + ((b-a)*rand);
    }

    public static double getNextRandomValueByExponential(double lambda) {
        Random rnd = new Random();
        double r = rnd.nextDouble();
        return -Math.log(r) / (lambda);
    }

    public static double getNextRandomValueByNormal(double average, double standardDeviation) {
        Random random = new Random();
        double randomNumer = 0;
        for (int i = 0; i < 12; i++) {

            randomNumer += random.nextDouble();
        }

        double Z = randomNumer - 6;

        return 1 + (Math.sqrt(0.01) * Z);
        //x = average + standardDeviation * z;
        //return x;
    }
}
