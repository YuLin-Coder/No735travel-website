package com.admin.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @Author: Mr.Ye
 * @Description: TODO(图片上传)
 */
public class Upload {

    public static final String RIMAGE     = "E:/All-workspace/travel/src/main/webapp/img/product/small";
    public static final String BIGPATH    = "E:/All-workspace/travel/src/main/webapp/img/product/size4";
    public static final String SMALLPATH  = "E:/All-workspace/travel/src/main/webapp/img/product/size2";
    public static final String HIMAGE     = "E:/All-workspace/travel/src/main/webapp/img/hotel";

    public static String uploadImg(String filepath, MultipartFile multipartFile)throws Exception{
        String filename = "";
        //生成uuid作为文件名
        String uuid = UUID.randomUUID().toString().replace("-","");
        //获取文件类型
        String contentType = multipartFile.getContentType();
        //获取文件后缀名
        String suffixName = contentType.substring(contentType.indexOf("/")+1);
        //生成新的文件名
        filename = uuid+"."+suffixName;
        //保存文件到路径
        multipartFile.transferTo(new File(filepath,filename));

        return filename;
    }
}
