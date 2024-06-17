package com.example.rental.utils;

import cn.hutool.core.util.StrUtil;

import java.util.UUID;

public class FileUtils {

    public static String getFileExtension(String fileName) {
        // 通过"."和true参数，从最后一个"."之后获取子字符串，用于得到文件扩展名
        return "." + StrUtil.subAfter(fileName, ".", true);
    }
    public static String getFileName() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    public static String getFileName(String fileName){
        return getFileName()+getFileExtension(fileName);
    }
}
