package com.imooc.o2o.util;


public class PathUtil {

    /*获取文件分隔符*/
    private static String seperator = System.getProperty("file.seperator");

    public static String getImgBasePath() {
        /*根据不同的操作系统来选择不同的根目录*/
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/o2o_picture/";
        } else {
            basePath = "/home/image/";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    public static String getShopImagePath(long shopId) {
        String imagePath = "upload/item/shop/" + shopId + "/";
        return imagePath.replace("/", seperator);
    }
}
