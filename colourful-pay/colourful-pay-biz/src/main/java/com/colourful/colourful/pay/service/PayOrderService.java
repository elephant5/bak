package com.colourful.colourful.pay.service;

import com.colourful.colourful.pay.dto.req.UnifiedorderReqDto;
import com.colourful.colourful.pay.dto.res.UnifiedorderResDto;
import com.colourful.colourful.pay.entity.PayOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付平台订单表 服务类
 * </p>
 *
 * @author json
 * @since 2020-07-30
 */
public interface PayOrderService extends IService<PayOrder> {

	/**
	 * 统一下单
	 * @param reqDto
	 * @return
	 */
	UnifiedorderResDto unifiedOrder(UnifiedorderReqDto reqDto) throws Exception;

	/**
	 * 支付成功,回调逻辑处理
	 * @param paramMap
	 */
	void payCallBack(Map<String, Object> paramMap) throws Exception;
}
