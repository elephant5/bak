package com.colourful.colourful.pay.wechat.common.jsapibean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jill on 2018/5/7.
 */
@Data
public class ChooseWxPay implements Serializable {

    private static final long serialVersionUID = -2998887404885641122L;

    private String timestamp;

    private String nonceStr;

//    最终返回转成json时，要修改为package，保留字
    private String package0;

    private String signType = "MD5";

    private String paySign;

    private String mwebUrl; //支付跳转链接
}
