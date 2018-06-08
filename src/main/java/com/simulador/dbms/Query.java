package com.simulador.dbms;

import com.simulador.enums.QueryType;
import com.simulador.statistics.QueryStatistics;

/**
 *
 */
public class Query {

    /**
     *
     */
    QueryStatistics actualQueryStatistics;

    /**
     *
     */
    QueryType typeOfQuery;

    /**
     *
     */
    Boolean exccedTimeOut;

    /**
     *
     */
    Boolean connectionRefused;

}
