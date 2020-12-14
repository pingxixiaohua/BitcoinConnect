package com.btc.rpc.entity;

public class BopSoftforks {
    private SoftFork segwit;
    private SoftFork csv;

    public SoftFork getSegwit() {
        return segwit;
    }

    public void setSegwit(SoftFork segwit) {
        this.segwit = segwit;
    }

    public SoftFork getCsv() {
        return csv;
    }

    public void setCsv(SoftFork csv) {
        this.csv = csv;
    }
}
