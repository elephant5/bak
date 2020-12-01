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
public class UnifiedOrderRes extends BasePayRes {
    @JacksonXmlCData
    private String deviceInfo;
    @JacksonXmlCData
    private String tradeType;
    @JacksonXmlCData
    private String prepayId;
    @JacksonXmlCData
    private String codeUrl;

    @JacksonXmlCData
    private String mwebUrl; //支付跳转链接
}
