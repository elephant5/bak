package com.colourful.colourful.pay.wechat.common.cache;

/**
 * Created by jill on 2018/5/2.
 */
public interface Cache {
    Object put(String key, Object value);

    Object get(String key);
}
