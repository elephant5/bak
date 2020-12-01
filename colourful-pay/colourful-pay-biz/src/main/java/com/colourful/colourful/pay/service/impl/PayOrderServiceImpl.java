package com.colourful.colourful.pay.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.colourful.colourful.pay.constant.PayChannel;
import com.colourful.colourful.pay.dto.req.UnifiedorderReqDto;
import com.colourful.colourful.pay.dto.req.WechatPayReq;
import com.colourful.colourful.pay.dto.res.UnifiedorderResDto;
import com.colourful.colourful.pay.entity.PayOrder;
import com.colourful.colourful.pay.enums.PayChannelEnum;
import com.colourful.colourful.pay.enums.PayOrderStatusEnum;
import com.colourful.colourful.pay.mapper.PayOrderMapper;
import com.colourful.colourful.pay.service.PayOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.colourful.colourful.pay.service.WeChatPayService;
import com.colourful.colourful.pay.utils.MySeqUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;


/**
 * <p>
 * 支付平台订单表 服务实现类
 * </p>
 *
 * @author json
 * @since 2020-07-30
 */
@Service
@Slf4j
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements PayOrderService {

	@Autowired
	private PayOrderMapper payOrderMapper;

	@Autowired
	private WeChatPayService weChatPayService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UnifiedorderResDto unifiedOrder(UnifiedorderReqDto reqDto) throws Exception {
		//TODO......根据商户订单号校验订单信息

		PayOrder payOrderInfo = new PayOrder();
		//根据商户订单查询支付单信息
		PayOrder payOrder = payOrderMapper.selectOne(new QueryWrapper<PayOrder>().lambda().eq(PayOrder::getOrderId, reqDto.getOrderId()));
		if(StringUtils.isEmpty(payOrder)){
			//创建支付订单
			PayOrder order = new PayOrder();
			BeanUtils.copyProperties(reqDto,order);
			order.setId(MySeqUtil.getPay());
			order.setStatus(PayOrderStatusEnum.UNPAID.getCode());
			order.setRealAmount(new BigDecimal(reqDto.getRealAmount()));
			order.setPayStartTime(new Date());
			payOrderMapper.insert(order);
			BeanUtils.copyProperties(order,payOrderInfo);
		}else {
			BeanUtils.copyProperties(payOrder,payOrderInfo);
		}
		UnifiedorderResDto resDto = new UnifiedorderResDto();

		//获取用户支付方式
		String payChannel = PayChannelEnum.getPayChannelValue(reqDto.getPayChannelId());
		try {
			switch (payChannel) {
				case PayChannel.WX_JSAPI :
					//微信公众号支付
					WechatPayReq jsapiWechatPayReq = new WechatPayReq();
					jsapiWechatPayReq.setPayOrderId(payOrderInfo.getId());
					jsapiWechatPayReq.setTotalFee(Long.valueOf(reqDto.getRealAmount()) * 100);
					//TODO...
					jsapiWechatPayReq.setProjectName("订单号-"+payOrderInfo.getId());
					jsapiWechatPayReq.setOpenId(reqDto.getOpenId());
					resDto.setChooseWxPay(weChatPayService.weChatJSAPIPayOrder(jsapiWechatPayReq));

					return resDto;
				case PayChannel.WX_H5 :
					//微信H5支付
					WechatPayReq h5WechatPayH5Req = new WechatPayReq();
					h5WechatPayH5Req.setPayOrderId(payOrderInfo.getId());
					h5WechatPayH5Req.setTotalFee(Long.valueOf(reqDto.getRealAmount()) * 100);
					//TODO...
					h5WechatPayH5Req.setProjectName("订单号-"+payOrderInfo.getId());
					resDto.setChooseWxPay(weChatPayService.weChatH5PayOrder(h5WechatPayH5Req));

					return resDto;

				default:
					throw new Exception("不支持的支付方式");
			}
		} catch (Exception e) {
			log.error("统一下单失败{}",e);
		}
		return null;

	}

	/**
	 * 支付成功,回调逻辑处理
	 * @param paramMap
	 */
	@Override
	@Transactional
	public void payCallBack(Map<String, Object> paramMap) throws Exception {
		String payOrderId = paramMap.get("payOrderId").toString();
		//校验订单信息
		PayOrder payOrder = payOrderMapper.selectById(payOrderId);
		if(StringUtils.isEmpty(payOrder)){
			log.error("支付订单不存在:"+payOrderId);
			throw new Exception("支付单信息不存在");
		}
		if(payOrder.getStatus().equals(PayOrderStatusEnum.UNPAID.getCode())){
			//只有代支付订单才需要处理回调数据
			//1.更新聚合支付订单

			//支付状态
			payOrder.setStatus(PayOrderStatusEnum.PREPAID.getCode());
			//支付单号
			payOrder.setPayTransactionId(String.valueOf(paramMap.get("payNumber")));
			//支付完成时间
			payOrder.setPayEndTime(new Date());
			//更改支付单信息
			payOrderMapper.updateById(payOrder);

			//2.通知调用方
			//TODO...


		}else {
			log.error("订单已支付成功:{}", JSON.toJSON(payOrder));
			throw new Exception("订单已支付成功");
		}
	}
}
