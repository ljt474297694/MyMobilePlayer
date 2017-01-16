package com.atguigu.ljt.mymobileplayer.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 李金桐 on 2017/1/16.
 * QQ: 474297694
 * 功能: 缓存工具类
 */

public class CacheUtils {
    public static String getString(Context mContext, String key) {
        SharedPreferences sp = mContext.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    /**
     * 保持数据
     * @param mContext
     * @param key
     * @param value
     */
    public static void putString(Context mContext, String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

}
