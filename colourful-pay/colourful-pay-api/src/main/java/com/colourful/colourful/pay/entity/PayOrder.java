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
 * 支付平台订单表
 * </p>
 *
 * @author json
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pay_order")
public class PayOrder extends Model<PayOrder> {

    private static final long serialVersionUID=1L;

    /**
     * 支付订单id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 商户订单ID
     */
    private String orderId;

    /**
     * 支付渠道
     */
    private Integer payChannelId;

    /**
     * 支付单来源
     */
    private String source;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 支付状态(1-待支付，2-已支付，3-退款，4-超时关闭订单)
     */
    private Integer status;

    /**
     * 实付金额
     */
    private BigDecimal realAmount;

    /**
     * 支付方式(1:免费兑换、2:纯积分、3:纯金额,4:积分+金额,5:分期,6:赠送)
     */
    private Integer payMethod;

    /**
     * 第三方订单号
     */
    private String payTransactionId;

    /**
     * 支付发起时间
     */
    private Date payStartTime;

	/**
	 * 支付完成时间
	 */
	private Date payEndTime;

    /**
     * 退款发起时间
     */
    private Date payRefundStartTime;

	/**
	 * 退款完成时间
	 */
	private Date payRefundEndTime;

    /**
     * 请求支付入参
     */
    private String payReq;

    /**
     * 支付返回结果
     */
    private String payRes;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
