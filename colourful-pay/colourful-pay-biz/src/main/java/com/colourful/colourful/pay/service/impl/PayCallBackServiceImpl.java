package com.colourful.colourful.pay.service.impl;

import com.colourful.colourful.pay.service.PayCallBackService;
import com.colourful.colourful.pay.service.PayOrderService;
import com.colourful.colourful.pay.wechat.api.pay.domain.OrderQueryRes;
import com.colourful.colourful.pay.wechat.config.WechatPayConfig;
import com.colourful.colourful.pay.wechat.utils.WxSignUtils;
import com.colourful.colourful.pay.wechat.utils.WxXmlUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class PayCallBackServiceImpl implements PayCallBackService {

	@Autowired
	private WechatPayConfig wechatPayConfig;

	@Autowired
	private PayOrderService payOrderService;

	/**
	 * 位置心智回调
	 * @param notifyData
	 */
	@Override
	public void wechatPayCallBack(String notifyData) throws Exception {
		OrderQueryRes orderQueryRes = WxXmlUtils.fromXml(notifyData, OrderQueryRes.class);
		validateSign(notifyData);
		validateResponse(orderQueryRes);

		//校验通过
		Map<String, Object> paramMap = new HashMap<>();
		//渠道信息
		paramMap.put("channel", orderQueryRes.getAttach());
		//支付单号
		paramMap.put("payOrderId", orderQueryRes.getOutTradeNo());
		//支付金额
		paramMap.put("amount", new BigDecimal(orderQueryRes.getTotalFee()).divide(new BigDecimal(100)));
		//第三方支付单号
		paramMap.put("payNumber", orderQueryRes.getTransactionId());
		paramMap.put("payTime", orderQueryRes.getTimeEnd());

		//更新聚合支付系统订单,并通知调用者
		payOrderService.payCallBack(paramMap);

	}

	/**
	 * 微信支付回调验签
	 * @param notifyData
	 * @throws Exception
	 */
	private void validateSign(String notifyData) throws Exception {
		TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
		};
		Map<String, Object> resMap = WxXmlUtils.fromXml(notifyData, typeRef);
		String sign = (String) resMap.remove("sign");
		if(StringUtils.isEmpty(sign)){
			throw new Exception("微信支付回调:签名为空");
		}
		String checkedSign = WxSignUtils.genMd5Sign(resMap, wechatPayConfig.getMchSecret());
		if (!sign.contentEquals(checkedSign)) {
			throw new Exception("微信支付回调:签名验证失败");
		}
	}

	/**
	 * 微信支付结果校验
	 * @param res
	 */
	private void validateResponse(OrderQueryRes res) {
		if (res == null || res.getReturnCode() == null) {
			throw new IllegalArgumentException("illegal arguments");
		}
		if ("FAIL".equals(res.getReturnCode())) {
			throw new IllegalArgumentException("Not accept return code" + res.getReturnMsg());
		}
		if ("FAIL".equals(res.getResultCode())) {
			throw new IllegalArgumentException("Result is illegal state " + res.getErrCode());
		}
	}

}
