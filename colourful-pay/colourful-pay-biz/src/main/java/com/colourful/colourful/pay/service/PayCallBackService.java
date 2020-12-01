package com.colourful.colourful.pay.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

public interface PayCallBackService {

	/**
	 * 微信支付回调
	 * @param notifyData
	 */
	void wechatPayCallBack(String notifyData) throws Exception;
}
