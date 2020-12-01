/*
 *    Copyright (c) 2018-2025, colourful All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: colourful
 */
package com.colourful.colourful.pay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.colourful.colourful.pay.entity.PayChannel;
import com.colourful.colourful.pay.mapper.PayChannelMapper;
import com.colourful.colourful.pay.service.PayChannelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 渠道
 *
 * @author colourful
 * @date 2019-05-28 23:57:58
 */
@Service
@AllArgsConstructor
public class PayChannelServiceImpl extends ServiceImpl<PayChannelMapper, PayChannel> implements PayChannelService {

	/**
	 * 新增支付渠道
	 * @param payChannel 支付渠道
	 * @return
	 */
	@Override
	public Boolean saveChannel(PayChannel payChannel) {
		return save(payChannel);
	}

}
