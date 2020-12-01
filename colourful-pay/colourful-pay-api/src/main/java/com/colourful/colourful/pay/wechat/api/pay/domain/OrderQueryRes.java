package com.colourful.colourful.pay.wechat.api.pay.domain;

import com.colourful.colourful.pay.wechat.api.pay.domain.BasePayRes;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by jill on 2018/5/4.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderQueryRes extends BasePayRes {
    /**
     * tradeState为SUCCESS算成功
     */
    @JacksonXmlCData
    private String tradeState;
    @JacksonXmlCData
    private String deviceInfo;
    @JacksonXmlCData
    private String openid;
    @JacksonXmlCData
    private String isSubscribe;
    @JacksonXmlCData
    private String tradeType;
    @JacksonXmlCData
    private String bankType;
    @JacksonXmlCData
    private Integer totalFee;
    @JacksonXmlCData
    private Integer settlementTotalFee;
    @JacksonXmlCData
    private String feeType;
    @JacksonXmlCData
    private Integer cashFee;
    @JacksonXmlCData
    private String cashFeeType;
    @JacksonXmlCData
    private Integer couponFee;
    @JacksonXmlCData
    private Integer couponCount;
    @JacksonXmlCData
    private String transactionId;
    @JacksonXmlCData
    private String outTradeNo;
    @JacksonXmlCData
    private String attach;
    @JacksonXmlCData
    private String timeEnd;
    @JacksonXmlCData
    private String tradeStateDesc;

}
