package com.colourful.colourful.pay.wechat.api.pay.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by jill on 2018/5/29.
 */
@EqualsAndHashCode
@Data
@JacksonXmlRootElement(localName = "xml")
public class OrderRefundCbRes {
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
    private String refundAccount;
    @JacksonXmlCData
    private String refundRecvAccount;
    @JacksonXmlCData
    private String refundRequestSource;
    @JacksonXmlCData
    private String refundStatus;
    @JacksonXmlCData
    private Integer settlementRefundFee;
    @JacksonXmlCData
    private Integer totalFee;
    @JacksonXmlCData
    private Integer settlementTotalFee;
    @JacksonXmlCData
    private String successTime;


}
