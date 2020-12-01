package com.colourful.colourful.pay.enums;

/**
 * 支付状态(1-待支付，2-已支付，3-退款，4-超时关闭订单)
 */
public enum PayOrderStatusEnum {

    UNPAID(1,"待支付"),
    PREPAID(2,"已支付"),
    REFUND(3,"已退款"),
    PAIDFAIL(4,"支付失败");

    private int code;
    private String name;

    PayOrderStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
