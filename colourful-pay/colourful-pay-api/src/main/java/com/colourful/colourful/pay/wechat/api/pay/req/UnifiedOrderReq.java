package com.colourful.colourful.pay.wechat.api.pay.req;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by jill on 2018/5/4.
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class UnifiedOrderReq extends BasePayReq{
    @JacksonXmlCData
    private String deviceInfo;
    @JacksonXmlCData
    private String body;
    @JacksonXmlCData
    private String detail;
    @JacksonXmlCData
    private String attach;
    @JacksonXmlCData
    private String outTradeNo;
    @JacksonXmlCData
    private String feeType;
    private Long totalFee;
    @JacksonXmlCData
    private String spbillCreateIp;
    @JacksonXmlCData
    private String timeStart;
    @JacksonXmlCData
    private String timeExpire;
    @JacksonXmlCData
    private String goodsTag;
    @JacksonXmlCData
    private String notifyUrl;
    @JacksonXmlCData
    private String tradeType;
    @JacksonXmlCData
    private String productId;
    @JacksonXmlCData
    private String limitPay;
    @JacksonXmlCData
    private String openid;

    @JacksonXmlCData
    private String sceneInfo; //场景信息
}
