package com.simulador.statistics;

/**
 *
 */
public class QueryStatistics {

    private double activationTime;
    private double threadCreationTime;
    private double processQueryTime;
    private double transactionModuleTime;
    private double chargeBlocksTime;
    private double execBlocksTime;
    private double closeConnectionTime;
    private int numberBlocks;

    public QueryStatistics(double activationTime, double threadCreationTime, double processQueryTime,
                            double transactionModuleTime, double chargeBlocksTime, double execBlocksTime,
                              double closeConnectionTime , int numberBlocks) {

        this.activationTime = activationTime;
        this.threadCreationTime = threadCreationTime;
        this.processQueryTime = processQueryTime;
        this.transactionModuleTime = transactionModuleTime;
        this.chargeBlocksTime = chargeBlocksTime;
        this.execBlocksTime = execBlocksTime;
        this.closeConnectionTime = closeConnectionTime;
        this.numberBlocks = numberBlocks;
    }

    public double getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(double activationTime) {
        this.activationTime = activationTime;
    }

    public double getThreadCreationTime() {
        return threadCreationTime;
    }

    public void setThreadCreationTime(double threadCreationTime) {
        this.threadCreationTime = threadCreationTime;
    }

    public double getProcessQueryTime() {
        return processQueryTime;
    }

    public void setProcessQueryTime(double processQueryTime) {
        this.processQueryTime = processQueryTime;
    }

    public double getTransactionModuleTime() {
        return transactionModuleTime;
    }

    public void setTransactionModuleTime(double transactionModuleTime) {
        this.transactionModuleTime = transactionModuleTime;
    }

    public double getChargeBlocksTime() {
        return chargeBlocksTime;
    }

    public void setChargeBlocksTime(double chargeBlocksTime) {
        this.chargeBlocksTime = chargeBlocksTime;
    }

    public double getExecBlocksTime() {
        return execBlocksTime;
    }

    public void setExecBlocksTime(double execBlocksTime) {
        this.execBlocksTime = execBlocksTime;
    }

    public double getCloseConnectionTime() {
        return closeConnectionTime;
    }

    public void setCloseConnectionTime(double closeConnectionTime) {
        this.closeConnectionTime = closeConnectionTime;
    }

    public int getNumberBlocks() {
        return numberBlocks;
    }

    public void setNumberBlocks(int numberBlocks) {
        this.numberBlocks = numberBlocks;
    }
}
