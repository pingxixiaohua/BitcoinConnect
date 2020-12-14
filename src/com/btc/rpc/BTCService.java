package com.btc.rpc;

import com.alibaba.fastjson.JSON;
import com.btc.rpc.ent.*;
import com.btc.rpc.entity.*;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 *  比特币服务类，用于封装rpc的各个命令的功能调用方法
 */
public class BTCService {

    // 静态代码块
    private static Map<String, String> map = new HashMap();

    //静态代码块
    static {
        map.put("Encoding","UTF-8");
        map.put("Content-Type", "application/json");
        map.put("Authorization", "Basic " + RPCUtils.base64Str(Constants.USER + ":" + Constants.PASSWORD));
    }

    /**
     * 生成一个新的比特币地址
     * @param label 标签， 可自定义指定
     * @param address_type 地址的类型，三种：legacy, p2sh-segwit, bech32
     *
     */
    public String getNewAddress(String label,ADDRESS_TYPE address_type){
        //0、
        String type = EnumUtils.getAddressType(address_type);
        //1、准备一个json-rpc通信的数据格式
        String json = RPCUtils.prepareJSON("getnewaddress", label, type);
        //2、发送post网络请求
        RPCResult rpcResult = RPCUtils.doPost(Constants.RPCURL, map, json);

        if (rpcResult == null) {
            return null;
        }

        if (rpcResult.getCode() == HttpStatus.SC_OK) {
            return rpcResult.getData().getResult();
        }
        return null;
    }

    /**
     *  根据区块的hash值获取区块的信息
     *
     * @param hash 要获取具体区块的hash值
     * @return 返回区块的内容
     *
     */
    public GetBlock getBlock(String hash){
        String json = RPCUtils.prepareJSON("getblock",hash);
        RPCResult rpcResult = RPCUtils.doPost(Constants.RPCURL, map, json);

        if (rpcResult == null){
            return null;
        }
        if (rpcResult.getCode() == HttpStatus.SC_OK) {
            String resultStr = rpcResult.getData().getResult();
            //反序列化
            GetBlock block = JSON.parseObject(resultStr, GetBlock.class);

            return block;

        }
        return null;
    }

    //需求：根据指定高度获取该高度区块的hash
    public String getBlockHash(int height){
        String json = RPCUtils.prepareJSON("getblockhash",height);
        RPCResult rpcResult = RPCUtils.doPost(Constants.RPCURL, map, json);
        if (rpcResult == null){
            return null;
        }
        if (rpcResult.getCode() == HttpStatus.SC_OK){
            return rpcResult.getData().getResult();
        }
        return null;
    }

    /**
     * 获取区块链节点信息
     *
     * @return 返回区块链信息类对象
     */
    public BlockChainInfo getBlockChainInfo(){
        String json = RPCUtils.prepareJSON("getblockchaininfo");

        RPCResult rpcResult = RPCUtils.doPost(Constants.RPCURL, map, json);
        if (rpcResult == null){
            return null;
        }
        if (rpcResult.getCode() == HttpStatus.SC_OK) {
            String resultStr = rpcResult.getData().getResult();
            //反序列化
            BlockChainInfo blockChainInfo = JSON.parseObject(resultStr, BlockChainInfo.class);

            return blockChainInfo;

        }
        return null;
    }

    /**
     *  获取最新区块的hash
     *
     * @return 返回最新区块的hash值，查询失败则返回null
     */
    public String getBestBlockHash(){
        String json = RPCUtils.prepareJSON("getbestblockhash");

        RPCResult rpcResult = RPCUtils.doPost(Constants.RPCURL, map, json);
        if (rpcResult == null){
            return null;
        }
        if (rpcResult.getCode() == HttpStatus.SC_OK){
            return rpcResult.getData().getResult();
        }
        return null;//空戴白哦请求失败
    }

    /**
     *  该方法用于获取比特币当前节点的区块数，
     *
     * @return 节点的区块的数量
     */
    public static int getBlockCount(){

        String json = RPCUtils.prepareJSON("getblockcount");
//        Map<String, String> map = new HashMap<>();
//        map.put("Encoding", "UTF-8");
//        map.put("Content-Type", "application/json");
//        map.put("Authorization", "Basic " + RPCUtils.base64Str(Constants.USER+ ":" + Constants.PASSWORD));
        RPCResult result = RPCUtils.doPost(Constants.RPCURL, map, json);
        if (result == null){
            return -1;
        }
        if (result.getCode() == HttpStatus.SC_OK) {
            String count = result.getData().getResult();
            return Integer.parseInt(count);
        }
        System.out.println(result.getCode());
        return -1;//-1代表请求失败，未查到数据
    }

    /**
     *  获取到钱包版本、余额、交易数量等信息
     * @return 返回钱包信息
     */
    public WalletInfo getWalletInfo(){
        String json = RPCUtils.prepareJSON("getwalletinfo");
        RPCResult rpcResult = RPCUtils.doPost(Constants.RPCURL, map, json);
        if (rpcResult == null){
            return null;
        }
        if (rpcResult.getCode() == HttpStatus.SC_OK){
            String resultStr = rpcResult.getData().getResult();
            return JSON.parseObject(resultStr, WalletInfo.class);
        }

        return null;
    }

    /**
     *  查询余额信息
     * @return 返回查询到的余额
     */
    public Balances getBalances(){
        String json = RPCUtils.prepareJSON("getbalances");
        RPCResult rpcResult = RPCUtils.doPost(Constants.RPCURL, map, json);
        if (rpcResult == null){
            return null;
        }
        if (rpcResult.getCode() == HttpStatus.SC_OK) {
            String resultStr = rpcResult.getData().getResult();
            return JSON.parseObject(resultStr, Balances.class);
        }
        return null;
    }

    /**
     *  查询未花费的交易
     * @return
     */
    public Listunspent listUnspent(){
        String json = RPCUtils.prepareJSON("listunspent");
        RPCResult rpcResult = RPCUtils.doPost(Constants.RPCURL, map, json);
        if (rpcResult == null){
            return null;
        }
        if (rpcResult.getCode() == HttpStatus.SC_OK) {
            String resultStr = rpcResult.getData().getResult();
            return JSON.parseObject(resultStr, Listunspent.class);
        }
        return null;
    }

    /**
     * 分组列出钱包中的地址，并携带余额
     * @return
     */
    public Listaddressgroupings listAddressGroupings(){
        String json = RPCUtils.prepareJSON("listaddressgroupings");
        RPCResult rpcResult = RPCUtils.doPost(Constants.RPCURL, map, json);
        if (rpcResult == null){
            return null;
        }
        if (rpcResult.getCode() == HttpStatus.SC_OK) {
            String resultStr = rpcResult.getData().getResult();
            return JSON.parseObject(resultStr, Listaddressgroupings.class);
        }
        return null;
    }

    /**
     * 获取钱包列表
     * @return
     */
    public Listwalletdir listWalletDir(){
        String json = RPCUtils.prepareJSON("listwalletdir");
        RPCResult rpcResult = RPCUtils.doPost(Constants.RPCURL, map, json);
        if (rpcResult == null){
            return null;
        }
        if (rpcResult.getCode() == HttpStatus.SC_OK) {
            String resultStr = rpcResult.getData().getResult();
            return JSON.parseObject(resultStr, Listwalletdir.class);
        }
        return null;
    }

    /**
     * 创建一个新钱包
     * @param name 钱包名
     * @return
     */
    public Newwallet createWallet(String name){
        String json = RPCUtils.prepareJSON("createwallet",name);
        RPCResult rpcResult = RPCUtils.doPost(Constants.RPCURL, map, json);
        if (rpcResult == null){
            return null;
        }
        if (rpcResult.getCode() == HttpStatus.SC_OK) {
            String resultStr = rpcResult.getData().getResult();
            return JSON.parseObject(resultStr, Newwallet.class);
        }
        return null;
    }

    /**
     * 关闭钱包
     * @return
     */
    public void stop(){
        String json = RPCUtils.prepareJSON("stop");
        RPCResult rpcResult = RPCUtils.doPost(Constants.RPCURL, map, json);

    }

    /**
     * 清除禁止的节点
     * @return
     */
    public String clearBanned(){
        String json = RPCUtils.prepareJSON("clearbanned");
        RPCResult rpcResult = RPCUtils.doPost(Constants.RPCURL, map, json);
        if (rpcResult == null){
            return null;
        }
        if (rpcResult.getCode() == HttpStatus.SC_OK) {
            return rpcResult.getData().getResult();
        }
        return null;
    }

    /**
     * 获取pow难度
     * @return
     */
    public int getDifficulty(){
        String json = RPCUtils.prepareJSON("getdifficulty");
        RPCResult rpcResult = RPCUtils.doPost(Constants.RPCURL, map, json);
        if (rpcResult == null){
            return -1;
        }
        if (rpcResult.getCode() == HttpStatus.SC_OK) {
            String resultStr = rpcResult.getData().getResult();
            return Integer.parseInt(resultStr);
        }
        return -1;
    }

    /**
     *  获取Rpc服务器详情
     * @return
     */
    public RpcInfo getRpcInfo(){
        String json = RPCUtils.prepareJSON("getrpcinfo");
        RPCResult rpcResult = RPCUtils.doPost(Constants.RPCURL, map, json);
        if (rpcResult == null){
            return null;
        }
        if (rpcResult.getCode() == HttpStatus.SC_OK) {
            String resultStr = rpcResult.getData().getResult();
            return JSON.parseObject(resultStr, RpcInfo.class);
        }
        return null;
    }

}
