package com.colourful.colourful.pay.wechat.api.pay.req;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class OrderQueryReq extends BasePayReq {
    @JacksonXmlCData
    private String outTradeNo;
    @JacksonXmlCData
    private String transactionId;
}
