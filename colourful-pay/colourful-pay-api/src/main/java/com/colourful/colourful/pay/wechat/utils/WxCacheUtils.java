package com.colourful.colourful.pay.wechat.utils;

import com.colourful.colourful.pay.wechat.common.cache.Cache;

/**
 * Created by jill on 2018/5/2.
 */
public class WxCacheUtils {
    private static Cache cache;

    public static void setCache(Cache cache) {
        WxCacheUtils.cache = cache;
    }

    public static Cache getCache() {
        return cache;
    }
}
