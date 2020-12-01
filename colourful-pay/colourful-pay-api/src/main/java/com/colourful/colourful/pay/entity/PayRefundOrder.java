package com.colourful.colourful.pay.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 退款单信息表
 * </p>
 *
 * @author json
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pay_refund_order")
public class PayRefundOrder extends Model<PayRefundOrder> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 支付订单ID
     */
    private String payOrderId;

    /**
     * 第三方退款订单号
     */
    private String refundRecordId;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 退款状态：0:退款中，1:退款失败,2:退款成功
     */
    private String status;

    /**
     * 退款理由
     */
    private String refundReason;

    /**
     * 退款失败原因
     */
    private String refuseRefundReason;

    /**
     * 退款发起时间
     */
    private Date refundStartTime;

    /**
     * 退款结束时间
     */
    private Date refundEndTime;

    /**
     * 创建时间，退款单创建时间
     */
    private Date createTime;

    /**
     * 修改时间，退款单更新时间
     */
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
