package com.colourful.colourful.pay.enums;

public enum PayChannelEnum {


    PAY_CHANNEL_WX_JSAPI(1,"WX_JSAPI","微信公众号支付"),
	PAY_CHANNEL_WX_MWEB(2,"WX_MWEB","微信H5支付"),
	PAY_CHANNEL_WX_NATIVE(3,"WX_NATIVE"," 微信原生扫码支付"),
    PAY_CHANNEL_WX_APP(4,"WX_APP","微信APP支付"),
    PAY_CHANNEL_ALIPAY_MOBILE(5,"ALIPAY_MOBILE","支付宝移动支付"),
    PAY_CHANNEL_ALIPAY_PC(6,"ALIPAY_PC","支付宝PC支付"),
    PAY_CHANNEL_ALIPAY_WAP(7,"ALIPAY_WAP","支付宝WAP支付"),
    PAY_CHANNEL_ALIPAY_QR(8,"ALIPAY_QR","支付宝当面付之扫码支付"),
    PAY_CHANNEL_CCB_MOBILE(9,"CCB_MOBILE","建行移动端支付"),
    PAY_CHANNEL_SHBANK(10,"SH_BANK","上海银行支付"),
    PAY_CHANNEL_ICBC_MOBILE(11,"ICBC_MOBILE","工商银行支付"),
    PAY_CHANNEL_KLT_WAP(12,"KLT_WAP","开联通支付"),
    PAY_CHANNEL_CMB_NETPAY(13,"CMB_PAY","招行一网通支付");


    private Integer code;

    private String value;

    private String desc;

    PayChannelEnum(Integer code, String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getPayChannelValue(Integer payChannelId){
        String result = null;
        for (PayChannelEnum payChannelEnum : PayChannelEnum.values()) {
            if (payChannelId == payChannelEnum.getCode()){
                result =  payChannelEnum.getValue();
            }
        }
        return result;
    }

    public static Integer getPayChannelId(String payChannel){
        Integer result = null;
        for (PayChannelEnum payChannelEnum : PayChannelEnum.values()) {
            if (payChannel.equalsIgnoreCase(payChannelEnum.getValue())){
                result =  payChannelEnum.getCode();
            }
        }
        return result;
    }
}
