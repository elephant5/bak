package com.colourful.colourful.pay.dto.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author json
 * @date 2020-07-31 10:07
 *
 * 渠道下配置支付方式返回对象
 */

@Data
public class SourcePayChannelInfoResDto {

	@ApiModelProperty(value = "终端渠道")
	private String source;

	@ApiModelProperty(value = "支付方式id")
	private Integer payChannelId;

	@ApiModelProperty(value = "支付方式名称")
	private String payChannelName;

	@ApiModelProperty(value = "支付方式简称")
	private String shortName;

	@ApiModelProperty(value = "支付方式图标")
	private String logo;

	@ApiModelProperty(value = "支付前端回调地址")
	private String frontendUrl;

}
