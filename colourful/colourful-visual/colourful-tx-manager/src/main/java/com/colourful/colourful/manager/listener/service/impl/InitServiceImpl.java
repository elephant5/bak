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

package com.colourful.colourful.manager.listener.service.impl;

import com.colourful.colourful.manager.config.ConfigReader;
import com.colourful.colourful.manager.framework.utils.Constants;
import com.colourful.colourful.manager.listener.service.InitService;
import com.colourful.colourful.manager.netty.service.NettyServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LCN on 2017/7/4.
 */
@Service
public class InitServiceImpl implements InitService {

	@Autowired
	private NettyServerService nettyServerService;

	@Autowired
	private ConfigReader configReader;

	@Override
	public void start() {
		/** 加载本地服务信息 **/

		Constants.socketPort = configReader.getSocketPort();
		Constants.maxConnection = configReader.getSocketMaxConnection();
		nettyServerService.start();
	}

	@Override
	public void close() {
		nettyServerService.close();
	}

}
