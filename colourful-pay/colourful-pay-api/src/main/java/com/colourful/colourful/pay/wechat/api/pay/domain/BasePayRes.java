package com.colourful.colourful.pay.wechat.api.pay.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * Created by jill on 2018/5/4.
 */
@Data
@JacksonXmlRootElement(localName = "xml")
public class BasePayRes {

    @JacksonXmlCData
    private String appid;
    @JacksonXmlCData
    private String mchId;
    @JacksonXmlCData
    private String sign;
    @JacksonXmlCData
    private String nonceStr;
    @JacksonXmlCData
    private String returnCode;
    @JacksonXmlCData
    private String returnMsg;
    @JacksonXmlCData
    private String resultCode;
    @JacksonXmlCData
    private String errCode;
    @JacksonXmlCData
    private String errCodeDes;
}