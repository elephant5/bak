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
public class RefundQueryRes extends BasePayRes {
    @JacksonXmlCData
    private Integer totalRefundCount;

    @JacksonXmlCData
    private String transactionId;

    @JacksonXmlCData
    private String outTradeNo;
    @JacksonXmlCData
    private Integer totalFee;

    @JacksonXmlCData
    private Integer settlementTotalFee;

    @JacksonXmlCData
    private String feeType;

    @JacksonXmlCData
    private Integer cashFee;
    @JacksonXmlCData
    private Integer refundCount;

    /**
     * TODO
     * out_refund_no_$n
     * refund_id_$n
     * refund_channel_$n
     * refund_fee_$n
     * settlement_refund_fee_$n
     * coupon_type_$n_$m
     * coupon_refund_fee_$n
     * coupon_refund_count_$n
     * coupon_refund_id_$n_$m
     * coupon_refund_fee_$n_$m
     * refund_status_$n
     * refund_account_$n
     * refund_recv_accout_$n
     * refund_success_time_$n
     */
}
