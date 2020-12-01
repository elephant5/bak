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
public class OrderRefundReq  extends BasePayReq {
    @JacksonXmlCData
    private String transactionId;

    @JacksonXmlCData
    private String outTradeNo;

    @JacksonXmlCData
    private String outRefundNo;
    @JacksonXmlCData
    private Integer totalFee;
    @JacksonXmlCData
    private Integer refundFee;
    @JacksonXmlCData
    private String refundFeeType;
    @JacksonXmlCData
    private String refundDesc;
    @JacksonXmlCData
    private String refundAccount;
    @JacksonXmlCData
    private String notifyUrl;
}
