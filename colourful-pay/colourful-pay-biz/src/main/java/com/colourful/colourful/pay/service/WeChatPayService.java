package com.colourful.colourful.pay.service;


import com.colourful.colourful.pay.dto.req.WechatPayReq;
import com.colourful.colourful.pay.wechat.common.WxException;
import com.colourful.colourful.pay.wechat.common.jsapibean.ChooseWxPay;

/**
 * @author json
 * @date 2020-08-03 10:21
 */
public interface WeChatPayService {

	/**
	 * 微信公众号支付
	 * @param wechatPayReq
	 * @return
	 */
	ChooseWxPay weChatJSAPIPayOrder(WechatPayReq wechatPayReq) throws WxException;

	/**
	 * 微信H5支付
	 * @param wechatPayReq
	 * @return
	 * @throws WxException
	 */
	ChooseWxPay weChatH5PayOrder(WechatPayReq wechatPayReq) throws WxException;
}
