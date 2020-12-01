package com.colourful.colourful.pay.dto.req;

import lombok.Data;

@Data
public class WechatPayReq {

    //订单号
    private String payOrderId;

    //项目名称
    private String projectName;

    //支付金额
    private Long totalFee;

    //微信OpenId
    private String openId;
}
