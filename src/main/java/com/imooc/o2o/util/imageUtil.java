//package com.imooc.o2o.util;
//
//import net.coobird.thumbnailator.Thumbnails;
//import net.coobird.thumbnailator.geometry.Positions;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//
//import javax.imageio.ImageIO;
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Random;
//import java.util.SimpleTimeZone;
//
///**
// * 处理图片
// */
//public class imageUtil {
//    /*当前线程的类加载器得到资源路径*/
//    private static String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
//    private static final SimpleDateFormat sDateFormat=new SimpleTimeZone("yyyyMMddHHmmss");
//    public static final Random r=new Random();
//    /**
//     * 处理缩略图
//     * @param thumbnail CommonsMultipartFile是string自带的文件处理对象
//     * @param targetAddr 图片的存储路径
//     * @return
//     */
//    public static String generateThumbnail(CommonsMultipartFile thumbnail,String targetAddr){
//        /*系统随机生成的不重名的文件*/
//        String realFileName=getRandomFileName();
//        /*文件的扩展名*/
//        String extension=getFileExtension(thumbnail);
//        /*创建目标目录*/
//        makeDirPath(targetAddr);
//        /*得到相对路径*/
//        String relativeAddr=targetAddr+realFileName+extension;
//        /*新的文件的路径*/
//        File dest=new File(PathUtil.getImgBasePath()+relativeAddr);
//        try{
//            Thumbnails.of(thumbnail.getInputStream())
//                    .size(200,200)
//                    .watermark(Positions.BOTTOM_RIGHT,
//                            ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f)
//                    .outputQuality(0.8f)
//                    .toFile(dest);
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     *
//     * @return
//     */
//    private static String getRandomFileName(){
//
//    }
//
//    public static void main(String[] args) throws IOException {
//        /*of用于传入文件或者图片流 .size指定输出照片的大小 .watermark添加水印，读入图片的路径*/
//        /*outputQuality(0.8f)压缩至80%*/
//        Thumbnails.of(new File("C:\\Users\\CHENVI12\\Desktop\\sad.jpg"))
//                .size(200,200)
//                .watermark(Positions.BOTTOM_RIGHT,
//                ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f)
//                .outputQuality(0.8f)
//                .toFile("C:\\Users\\CHENVI12\\Desktop\\sadness.jpg");
//    }
//}
package com.imooc.o2o.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 将CommonsMultipartFile转换成File类
     *
     * @param cFile
     * @return
     */
    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
        File newFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IllegalStateException e) {
            logger.error(e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }

    /**
     * 处理缩略图，并返回新生成图片的相对值路径
     *
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(CommonsMultipartFile thumbnail, String targetAddr) {
        // 获取不重复的随机名
        String realFileName = getRandomFileName();
        // 获取文件的扩展名如png,jpg等
        String extension = getFileExtension(thumbnail);
        // 如果目标路径不存在，则自动创建
        makeDirPath(targetAddr);
        // 获取文件存储的相对路径(带文件名)
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is :" + relativeAddr);
        // 获取文件要保存到的目标路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is :" + PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("basePath is :" + basePath);
        // 调用Thumbnails生成带有水印的图片
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        // 返回图片相对路径地址
        return relativeAddr;
    }



    /**
     * 创建目标路径所涉及到的目录，即/home/work/xiangze/xxx.jpg, 那么 home work xiangze
     * 这三个文件夹都得自动创建
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /**
     * 获取输入文件流的扩展名
     *
     * @param
     * @return
     */
    private static String getFileExtension(CommonsMultipartFile cFile) {
        String originalFileName=cFile.getOriginalFilename();
        return originalFileName.substring(originalFileName.lastIndexOf(","));
    }

    /**
     * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
     *
     * @return
     */
    public static String getRandomFileName() {
        // 获取随机的五位数
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File("/Users/baidu/work/image/xiaohuangren.jpg")).size(200, 200)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                .outputQuality(0.8f).toFile("/Users/baidu/work/image/xiaohuangrennew.jpg");
    }

}
