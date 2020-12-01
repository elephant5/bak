package com.colourful.colourful.pay.wechat.api.common;


import com.colourful.colourful.pay.wechat.api.sns.domain.WxSnsUnionIdUserInfo;
import com.colourful.colourful.pay.wechat.common.WxException;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.util.Objects;

public class WxSnsUnionIDApi extends WxAbstractApi {
    private static final HttpUrl SNS_UNIONID_API = Objects
            .requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/cgi-bin/user/info"));

    public WxSnsUnionIDApi(String appId, String appSecret) {
        super(appId, appSecret);
    }

    public WxSnsUnionIdUserInfo getUnionUserInfo(String accessToken, String openId) throws WxException {
        return get(
                new Request.Builder().url(SNS_UNIONID_API.newBuilder()
                        .addQueryParameter("access_token", accessToken)
                        .addQueryParameter("openid", openId)
                        .addQueryParameter("lang", "zh_CN")
                        .build()), WxSnsUnionIdUserInfo.class);
    }

    /**
     * 批量获取用户信息
     * TODO
     * @See https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140839
     */
}
