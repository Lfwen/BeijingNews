package com.newsapp.lfw.beijingnews.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by LiFei on 2017/6/11.
 * 缓存软件的一些参数和数据
 */

public class CacheUtils {
    /**
     *
     * 得到缓存值
     * @param context 上下文
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("news", Context.MODE_PRIVATE);

        return false;
    }
}
