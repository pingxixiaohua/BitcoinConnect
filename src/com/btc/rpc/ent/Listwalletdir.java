package com.btc.rpc.ent;

import java.util.ArrayList;
import java.util.List;

public class Listwalletdir {
    private List<Wallets> wallets = new ArrayList<>();

    public List<Wallets> getWallets() {
        return wallets;
    }

    public void setWallets(List<Wallets> wallets) {
        this.wallets = wallets;
    }
}
