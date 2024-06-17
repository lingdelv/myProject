package com.example.rental.utils;

import cn.hutool.extra.pinyin.PinyinUtil;

/**
 * 将文字转成拼音首字母 大写
 */
public class PinYinUtils {
    public static String getPinYin(String str) {
        return PinyinUtil.getFirstLetter(str, "").toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(getPinYin("常三666"));
    }


}
