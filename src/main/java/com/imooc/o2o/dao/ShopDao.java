package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;

/**
 * 店铺的注册功能
 * dao声明接口
 */
public interface ShopDao {
    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
