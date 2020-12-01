package com.colourful.colourful.pay.service.impl;

import com.colourful.colourful.pay.dto.req.WechatPayReq;
import com.colourful.colourful.pay.service.WeChatPayService;
import com.colourful.colourful.pay.wechat.api.common.WxPayApi;
import com.colourful.colourful.pay.wechat.api.pay.domain.UnifiedOrderRes;
import com.colourful.colourful.pay.wechat.api.pay.req.UnifiedOrderReq;
import com.colourful.colourful.pay.wechat.common.WxException;
import com.colourful.colourful.pay.wechat.common.jsapibean.ChooseWxPay;
import com.colourful.colourful.pay.wechat.config.WechatPayConfig;
import com.colourful.colourful.pay.wechat.utils.HttpUtils;
import com.colourful.colourful.pay.wechat.utils.WxSignUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author json
 * @date 2020-08-03 10:21
 */
@Service
public class WeChatPayServiceImpl implements WeChatPayService {

	@Autowired
	private WechatPayConfig wechatPayConfig;

	@Autowired(required = false)
	protected HttpServletRequest request;

	@Autowired(required = false)
	private WxPayApi wxPayApi;

	/**
	 * 微信公众号支付
	 * @param wechatPayReq
	 * @return
	 * @throws WxException
	 */
	@Override
	public ChooseWxPay weChatJSAPIPayOrder(WechatPayReq wechatPayReq) throws WxException {
		Assert.notNull(wechatPayReq, "参数不能为空");

		UnifiedOrderReq unifiedOrderReq = UnifiedOrderReq.builder()
				.deviceInfo("WEB")
				.body(wechatPayReq.getProjectName()) //商家名称-销售商品类目:腾讯-游戏
				.notifyUrl(wechatPayConfig.getNotifyUrl()) //通知地址
				.outTradeNo(wechatPayReq.getPayOrderId()) //订单号
				.spbillCreateIp(HttpUtils.getRemoteIp(request)) //终端ip
				.totalFee(wechatPayReq.getTotalFee()) //订单总金额
				.tradeType("JSAPI")  //公众号支付
				.openid(wechatPayReq.getOpenId())
				.build();

		return this.wechatPay(unifiedOrderReq);
	}

	/**
	 * 微信H5支付
	 * @param wechatPayReq
	 * @return
	 * @throws WxException
	 */
	@Override
	public ChooseWxPay weChatH5PayOrder(WechatPayReq wechatPayReq) throws WxException {
		StringBuffer sceneInfo = new StringBuffer();
		sceneInfo.append("{\"h5_info\":");
		sceneInfo.append("{\"type\":").append("\"Wap\",");
		sceneInfo.append("\"wap_url\":").append(wechatPayConfig.getWapUrl()).append(","); //WAP网站URL地址
		sceneInfo.append("\"wap_name\":").append(wechatPayConfig.getWapName()).append("}}");

		UnifiedOrderReq unifiedOrderReq = UnifiedOrderReq.builder()
				.deviceInfo("WEB")
				.body(wechatPayReq.getProjectName()) //浏览器打开的移动网页的主页title名-商品概述:腾讯充值中心-QQ会员充值
				.notifyUrl(wechatPayConfig.getNotifyUrl()) //通知地址
				.outTradeNo(wechatPayReq.getPayOrderId()) //订单号
				.spbillCreateIp(HttpUtils.getRemoteIp(request)) //终端ip
				.totalFee(wechatPayReq.getTotalFee()) //订单总金额
				.tradeType("MWEB")  //H5支付
				.openid(wechatPayReq.getOpenId())
				.sceneInfo(sceneInfo.toString())
				.build();

		return this.wechatPay(unifiedOrderReq);
	}


	/**
	 * 调用微信统一下单接口,并处理返回结果
	 * @param unifiedOrderReq
	 * @return
	 * @throws WxException
	 */
	public ChooseWxPay wechatPay(UnifiedOrderReq unifiedOrderReq) throws WxException {
		ChooseWxPay chooseWxPay = new ChooseWxPay();
		//统一下单
		Map<String, Object> params = new HashMap<>();
		UnifiedOrderRes unifiedOrderRes = wxPayApi.unifiedOrder(unifiedOrderReq);
		chooseWxPay.setNonceStr(UUID.randomUUID().toString().replace("-", "").substring(0, 32).toUpperCase());
		chooseWxPay.setPackage0("prepay_id=" + unifiedOrderRes.getPrepayId());
		chooseWxPay.setTimestamp(String.valueOf(Instant.now().getEpochSecond()));
		chooseWxPay.setSignType("MD5");
		if(!StringUtils.isEmpty(unifiedOrderRes.getMwebUrl())){
			//H5支付:支付跳转链接
			chooseWxPay.setMwebUrl(unifiedOrderRes.getMwebUrl());
			params.put("mwebUrl",chooseWxPay.getMwebUrl());
		}
		params.put("timeStamp", chooseWxPay.getTimestamp());
		params.put("nonceStr", chooseWxPay.getNonceStr());
		params.put("package", chooseWxPay.getPackage0());
		params.put("signType", chooseWxPay.getSignType());
		params.put("appId", unifiedOrderRes.getAppid());
		chooseWxPay.setPaySign(WxSignUtils.genMd5Sign(params, wechatPayConfig.getMchSecret()));
		return chooseWxPay;
	}
}
