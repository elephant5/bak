package com.colourful.colourful.pay.dto.req;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统一下单入参
 */
@Data
public class UnifiedorderReqDto {

	@ApiModelProperty(value = "订单来源")
	private String source;

	@ApiModelProperty(value = "支付方式id")
	private Integer payChannelId;

	@ApiModelProperty(value = "用户id")
	private Long userId;

	@ApiModelProperty(value = "商户订单id")
	private String orderId;

	@ApiModelProperty(value = "用户openId")
	private String openId;

	@ApiModelProperty(value = "实付金额")
	private String realAmount;
}
