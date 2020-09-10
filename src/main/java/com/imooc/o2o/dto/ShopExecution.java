package com.imooc.o2o.dto;

import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;

import java.util.List;

/**
 * 1.存储店铺的信息
 * 2.存储状态值
 */
public class ShopExecution {
    /*结果状态*/
    private int state;
    //状态标识
    private String stateInfo;
    //店铺数量
    private int count;
    //操作的shop（增删改店铺的时候用到）
    private Shop shop;
    //shop列表（查询店铺列表的时候使用）
    public List<Shop> shopList;

    //默认构造函数
    public ShopExecution(){

    }

    //构造器---店铺操作失败的时候使用的构造器，只返回其状态，不返回shop对象
    public ShopExecution(ShopStateEnum stateEnum){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
    }
    //店铺操作成功的构造器,返回单个shop
    public ShopExecution(ShopStateEnum stateEnum, Shop shop){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.shop=shop;
    }
    //店铺操作成功的构造器，返回列表
    public ShopExecution(ShopStateEnum stateEnum, List<Shop> shop){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.shopList=shopList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
