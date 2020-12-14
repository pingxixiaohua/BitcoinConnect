package com.btc.rpc.ent;

public class Mine {
    private double trusted;
    private double untrusted_pending;
    private double immature;

    public double getTrusted() {
        return trusted;
    }

    public void setTrusted(double trusted) {
        this.trusted = trusted;
    }

    public double getUntrusted_pending() {
        return untrusted_pending;
    }

    public void setUntrusted_pending(double untrusted_pending) {
        this.untrusted_pending = untrusted_pending;
    }

    public double getImmature() {
        return immature;
    }

    public void setImmature(double immature) {
        this.immature = immature;
    }
}
