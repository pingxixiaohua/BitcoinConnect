package com.btc.rpc.entity;

import com.btc.rpc.Status;

public class Bip {
    private Status reject;
    private String id;
    private int version;

    public Status getReject() {
        return reject;
    }

    public void setReject(Status reject) {
        this.reject = reject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
