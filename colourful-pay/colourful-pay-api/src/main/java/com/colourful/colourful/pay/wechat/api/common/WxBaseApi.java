package com.colourful.colourful.pay.wechat.api.common;


import com.colourful.colourful.pay.wechat.api.base.domain.WxIpList;
import com.colourful.colourful.pay.wechat.common.WxException;
import com.colourful.colourful.pay.wechat.common.bean.WxAccessToken;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.util.Objects;

/**
 * Created by jill on 2018/5/2.
 */
public class WxBaseApi extends WxAbstractApi {

    private static final HttpUrl BASE_API = Objects.requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/cgi-bin"));

    public WxBaseApi(String appId, String appSecret) {
        super(appId, appSecret);
    }

    /**
     * 获取access_token
     *
     * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&amp;appid=APPID&amp;secret=APPSECRET
     *
     * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421140183">doc</a>
     */
    public WxAccessToken token() throws WxException {
        return getWxAccessToken();
    }

    /**
     * 获取微信服务器IP地址
     *
     * https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN
     *
     * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421140187">doc</a>
     */
    public WxIpList getCallbackIp() throws WxException {
        return get(
                new Request.Builder().url(
                        BASE_API.newBuilder()
                                .addPathSegment("getcallbackip")
                                .addQueryParameter("access_token", getAccessToken())
                                .build()),
                WxIpList.class);
    }
}
