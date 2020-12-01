package com.colourful.colourful.pay.wechat.api.common;


import com.colourful.colourful.pay.wechat.api.pay.domain.*;
import com.colourful.colourful.pay.wechat.api.pay.req.*;
import com.colourful.colourful.pay.wechat.common.WxException;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.springframework.stereotype.Component;

import java.util.Objects;

public class WxPayApi extends WxPayAbstractApi {

    private static final HttpUrl PAY_API = Objects
            .requireNonNull(HttpUrl.parse("https://api.mch.weixin.qq.com/pay"));

    private static final HttpUrl REFUND_API = Objects
            .requireNonNull(HttpUrl.parse("https://api.mch.weixin.qq.com/secapi/pay"));

    public WxPayApi(String appId, String appSecret, String secretKey, String certPath) {
        super(appId, appSecret, secretKey, certPath);
    }

    /**
     * 统一下单
     * @param req
     * @return
     * @throws WxException
     */
    public UnifiedOrderRes unifiedOrder(UnifiedOrderReq req) throws WxException {
        return post(
            new Request.Builder().url(PAY_API.newBuilder()
                .addPathSegment("unifiedorder")
                .build()), req, UnifiedOrderRes.class);
    }

    /**
     * 查询下单
     * @param req
     * @return
     * @throws WxException
     */
    public OrderQueryRes orderQuery(OrderQueryReq req) throws WxException {
        return post(
            new Request.Builder().url(PAY_API.newBuilder()
                .addPathSegment("orderquery")
                .build()), req, OrderQueryRes.class);
    }

    /**
     * 关闭下单
     * @param req
     * @return
     * @throws WxException
     */
    public CloseOrderRes closeOrder(CloseOrderReq req) throws WxException {
        return post(
            new Request.Builder().url(PAY_API.newBuilder()
                .addPathSegment("closeorder")
                .build()), req, CloseOrderRes.class);
    }

    /**
     * 订单退款申请
     * @param req
     * @return
     * @throws WxException
     */
    public OrderRefundRes refundOrder(OrderRefundReq req) throws WxException {
        /**
         * TODO
         * 需要考虑证书验证
         * @See https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
         */
        return postCert(
            new Request.Builder().url(REFUND_API.newBuilder()
                .addPathSegment("refund")
                .build()), req, OrderRefundRes.class);
    }

    public RefundQueryRes refundQuery(RefundQueryReq req) throws WxException {

        return post(
            new Request.Builder().url(PAY_API.newBuilder()
                .addPathSegment("refundquery")
                .build()), req, RefundQueryRes.class);
    }


}
