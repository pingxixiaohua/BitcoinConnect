package com.btc.rpc.ent;

import java.util.ArrayList;
import java.util.List;

public class RpcInfo {
    private List<Active_commands> active_commands = new ArrayList<>();
    private String logpath;

    public List<Active_commands> getActive_commands() {
        return active_commands;
    }

    public void setActive_commands(List<Active_commands> active_commands) {
        this.active_commands = active_commands;
    }

    public String getLogpath() {
        return logpath;
    }

    public void setLogpath(String logpath) {
        this.logpath = logpath;
    }
}
