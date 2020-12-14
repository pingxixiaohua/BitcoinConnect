package com.btc.rpc;

import com.btc.rpc.ent.*;
import com.btc.rpc.entity.*;

import java.util.List;

public class TestBc {
    public static void main(String[] args) {



        BTCService service = new BTCService();

        //测试：获取最新区块的hash值
        String bestblockhash = service.getBestBlockHash();
        System.out.println("最新区块的hash：" + bestblockhash);

        //该方法用于获取比特币当前节点的区块数
        int blockCount = service.getBlockCount();
        System.out.println("当前节点的区块的个数：" + blockCount);


        //测试：获取当前节点的区块链信息
        BlockChainInfo chainInfo = service.getBlockChainInfo();
        if (chainInfo != null) {
            System.out.println("当前节点的区块数" + chainInfo.getBlocks());
            System.out.println("当前节点的难度值" + chainInfo.getDifficulty());
            List<Bip> bipSoftForks = chainInfo.getSoftforks();
            for (Bip bip:bipSoftForks) {
                System.out.println(bip.getId());
            }
        }

        //测试：获取指定高度的区块的hash值
        String hash0 = service.getBlockHash(0);
        System.out.println("高度为0的hash值" + hash0);

        //根据区块的hash值获取区块的信息
        GetBlock block0 = service.getBlock("000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f");
        if (block0 != null){
            System.out.println("strippedsize值" + block0.getStrippedsize());
            List<String> tx = block0.getTx();
            System.out.println("Tx值" + tx);
        }

        //生成新地址
        String address = service.getNewAddress("getnewaddress",ADDRESS_TYPE.LEGACY);
        System.out.println("新地址是：" + address);


        //获取到钱包版本、余额、交易数量等信息
        WalletInfo walletInfo = service.getWalletInfo();
        if (walletInfo != null){
            System.out.println("交易次数：" + walletInfo.getTxcount());
        }

        //查询余额
        Balances blances = service.getBalances();
        System.out.println("钱包的余额为：" + blances.getMine().getImmature());

        //查询未花费的交易
        Listunspent listunspent = service.listUnspent();
        System.out.println("未花费的交易：" + listunspent);

        //分组列出钱包中的地址，并携带余额
        Listaddressgroupings listaddressgroupings = service.listAddressGroupings();
        System.out.println("分组列出钱包中的地址:" + listaddressgroupings);

        //获取钱包列表
        Listwalletdir listwalletdir = service.listWalletDir();
        System.out.println("钱包列表：" + listwalletdir.getWallets());

        //创建一个新的钱包
        //Newwallet newwallet = service.createWallet("nihao");
        //System.out.println("新建钱包" + newwallet);

        //关闭钱包
        //service.stop();

        //清除禁止的节点
        String clearbanned = service.clearBanned();
        System.out.println("清除结果："+clearbanned);

        //POW难度
        int difficulty = service.getDifficulty();
        System.out.println("难度："+difficulty);

        //Rpc服务器详情
        RpcInfo rpcInfo = service.getRpcInfo();
        if (rpcInfo != null) {
            System.out.println("log的路径"+rpcInfo.getLogpath());
        }
    }
}
