package com.imooc.o2o.entity;

import java.util.Date;

public class Area {
    //区域ID
    private Integer areaId;
    //名称
    private String areaName;
    //权重，权重越大越靠前
    private  Integer priority;
    //createTime创建时间
    private Date createTime;
    //更新
    private Date lastEditTime;
}
