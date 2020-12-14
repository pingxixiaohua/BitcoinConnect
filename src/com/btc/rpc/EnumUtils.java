package com.btc.rpc;

import java.util.HashMap;
import java.util.Map;

enum ADDRESS_TYPE {
    LEGACY,
    P2SH_SEGWIT,
    BECH32
}

public class EnumUtils{

    private static Map<ADDRESS_TYPE, String> map = new HashMap<ADDRESS_TYPE, String>();

    static {
        map.put(ADDRESS_TYPE.LEGACY, "legacy");
        map.put(ADDRESS_TYPE.P2SH_SEGWIT, "p2sh-segwit");
        map.put(ADDRESS_TYPE.BECH32, "bech32");
    }
    public static String getAddressType2(ADDRESS_TYPE type){
        return map.get(type);
    }
    /**
     *  根据枚举返回对应的字符串
     *
     * @param type 枚举变量
     * @return 地址类型
     */
    public static String getAddressType(ADDRESS_TYPE type){
        switch (type) {
            case LEGACY:
                return "legacy";
            case P2SH_SEGWIT:
                return "p2sh-segwit";
            case BECH32:
                return "bech32";
        }
        return null;
    }

}
