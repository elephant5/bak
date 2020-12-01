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
public class OrderRefundRes extends BasePayRes {
    @JacksonXmlCData
    private String transactionId;
    @JacksonXmlCData
    private String outTradeNo;
    @JacksonXmlCData
    private String outRefundNo;
    @JacksonXmlCData
    private String refundId;
    @JacksonXmlCData
    private Integer refundFee;
    @JacksonXmlCData
    private Integer settlementRefundFee;
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
    private Integer cashRefundFee;
    @JacksonXmlCData
    private Integer couponRefundFee;
    @JacksonXmlCData
    private Integer couponRefundCount;


    /**
     * TODO
     * coupon_type_$n
     * coupon_refund_id_$n
     * coupon_refund_fee_$n
     */
}
