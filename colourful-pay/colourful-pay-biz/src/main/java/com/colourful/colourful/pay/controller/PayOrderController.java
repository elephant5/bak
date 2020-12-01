package com.colourful.colourful.pay.controller;


import com.colourful.colourful.common.core.util.R;
import com.colourful.colourful.common.security.annotation.Inner;
import com.colourful.colourful.pay.constant.ConstantMsg;
import com.colourful.colourful.pay.dto.req.UnifiedorderReqDto;
import com.colourful.colourful.pay.dto.res.UnifiedorderResDto;
import com.colourful.colourful.pay.service.PayOrderService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 支付平台订单表 前端控制器
 * </p>
 *
 * @author json
 * @since 2020-07-30
 */
@Inner(value = false)
@RestController
@RequestMapping(value = "/pay",consumes = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8", produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
@Slf4j
public class PayOrderController {

	@Autowired
	private PayOrderService payOrderService;

	@ApiOperation(value = "支付系统统一下单")
	@PostMapping("/unifiedOrder")
	public R<UnifiedorderResDto> unifiedOrder(@RequestBody UnifiedorderReqDto reqDto) {
		try {
			UnifiedorderResDto resDto = payOrderService.unifiedOrder(reqDto);
			return R.ok(resDto);
		}catch (Exception e){
			log.error("统一下单失败{}", e);
			return R.failed(null, ConstantMsg.ErrorMsg);
		}
	}

}

