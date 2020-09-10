package com.imooc.o2o.service;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;

import java.io.File;

public interface ShopService {
    /*注册店铺*/
    ShopExecution addShop(Shop shop, File shopImg);
}
