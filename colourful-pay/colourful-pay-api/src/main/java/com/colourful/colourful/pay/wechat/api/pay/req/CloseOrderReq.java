package com.colourful.colourful.pay.wechat.api.pay.req;

import com.colourful.colourful.pay.wechat.api.pay.req.BasePayReq;
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
public class CloseOrderReq extends BasePayReq {
    @JacksonXmlCData
    private String outTradeNo;
}