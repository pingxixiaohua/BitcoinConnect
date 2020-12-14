package com.btc.rpc.ent;

import java.util.ArrayList;
import java.util.List;

public class Listunspent {
    private List<String> listtunspent = new ArrayList<>();

    public List<String> getListtunspent() {
        return listtunspent;
    }

    public void setListtunspent(List<String> listtunspent) {
        this.listtunspent = listtunspent;
    }
}
