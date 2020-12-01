package com.colourful.colourful.pay.wechat.api.common;


import com.colourful.colourful.pay.wechat.api.base.domain.WxTicket;
import com.colourful.colourful.pay.wechat.common.WxException;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jill on 2018/5/2.
 */
public class WxTicketApi extends WxAbstractApi {
    private static final HttpUrl TICKET_API = Objects
            .requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/cgi-bin/ticket"));

    protected static final Map<String, WxTicket> jsTicketCache = new ConcurrentHashMap<>();
    protected static final Map<String, Instant> jsTicketExpireCache = new ConcurrentHashMap<>();

    public WxTicketApi(String appId, String appSecret) {
        super(appId, appSecret);
    }

    /**
     * 获取微信ticket
     *
     * https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&amp;type=jsapi
     *
     * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;id=mp1421141115">doc</a>
     */
    public WxTicket getTicket(String type) throws WxException {
        WxTicket ticket;
        String key = getAppId() + "_" + getAppSecret() + "_" + type + "_ticket";
        String keyForExpire = key + "_expire";
        if(jsTicketExpireCache.containsKey(keyForExpire) && jsTicketCache.containsKey(key)) {
            Instant expireTime = jsTicketExpireCache.getOrDefault(keyForExpire, Instant.now().minusSeconds(1800));
            if(expireTime.isAfter(Instant.now())) {
                return jsTicketCache.get(key);
            }
        }

        ticket = get(
            new Request.Builder().url(
                TICKET_API.newBuilder()
                .addPathSegment("getticket")
                .addQueryParameter("access_token", getAccessToken())
                .addQueryParameter("type", type).build()),
            WxTicket.class);
        jsTicketCache.put(key, ticket);
        jsTicketExpireCache.put(keyForExpire, Instant.now().plusSeconds(ticket.getExpiresIn() / 2));
        return ticket;
    }

    /**
     * 获取微信jsapi ticket
     */
    public WxTicket getTicket() throws WxException {
        return getTicket("jsapi");
    }
}
