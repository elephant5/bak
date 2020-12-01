package com.colourful.colourful.pay.dto.res;

import lombok.Data;


/**
 * @author json
 * @date 2020-07-31 14:51
 * 微信支付参数
 */
@Data
public class WXPayRes {

    private String timestamp;

    private String nonceStr;

    //最终返回转成json时，要修改为package，保留字
    private String package0;

    private String signType = "MD5";

    private String paySign;

    private String mwebUrl; //H5支付跳转链接
}
