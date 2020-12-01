package com.colourful.colourful.pay.dto.res;

import com.colourful.colourful.pay.wechat.common.jsapibean.ChooseWxPay;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author json
 * @date 2020-07-31 14:47
 */

@Data
public class UnifiedorderResDto {

	@ApiModelProperty(value = "微信支付返回结果")
	private ChooseWxPay chooseWxPay;
}
