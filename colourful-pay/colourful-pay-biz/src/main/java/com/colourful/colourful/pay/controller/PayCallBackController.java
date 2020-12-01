package com.colourful.colourful.pay.controller;


import com.alibaba.fastjson.JSON;
import com.colourful.colourful.pay.service.PayCallBackService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 支付回调
 * </p>
 *
 * @author json
 * @since 2020-08-04
 */
@Slf4j
@RestController
@RequestMapping("/pay/callback")
public class PayCallBackController {

	private static String SUCCESS_TXT = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";

	@Autowired
	private PayCallBackService payCallBackService;

	@ApiOperation("微信支付回调")
	@PostMapping(path = "/weixin/cb")
	@ResponseBody
	public String callback(@RequestBody String notifyData) throws Exception {
		//支付结果通知的xml格式数据
		log.info("微信支付回调参数:{}", notifyData);
		payCallBackService.wechatPayCallBack(notifyData);
		return SUCCESS_TXT;
	}
}
