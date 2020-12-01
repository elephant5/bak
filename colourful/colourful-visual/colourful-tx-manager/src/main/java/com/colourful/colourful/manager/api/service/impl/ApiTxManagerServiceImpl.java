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

package com.colourful.colourful.manager.api.service.impl;

import com.colourful.colourful.manager.api.service.ApiTxManagerService;
import com.colourful.colourful.manager.compensate.model.TransactionCompensateMsg;
import com.colourful.colourful.manager.compensate.service.CompensateService;
import com.colourful.colourful.manager.config.ConfigReader;
import com.colourful.colourful.manager.manager.service.MicroService;
import com.colourful.colourful.manager.manager.service.TxManagerSenderService;
import com.colourful.colourful.manager.manager.service.TxManagerService;
import com.colourful.colourful.manager.model.TxServer;
import com.colourful.colourful.manager.model.TxState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author LCN on 2017/7/1.
 * @author LCN
 * @author colourful
 */
@Service
@AllArgsConstructor
public class ApiTxManagerServiceImpl implements ApiTxManagerService {

	private final TxManagerService managerService;

	private final MicroService eurekaService;

	private final CompensateService compensateService;

	private final TxManagerSenderService txManagerSenderService;

	private final ConfigReader configReader;

	@Override
	public TxServer getServer() {
		return eurekaService.getServer();
	}

	@Override
	public int cleanNotifyTransaction(String groupId, String taskId) {
		return managerService.cleanNotifyTransaction(groupId, taskId);
	}

	@Override
	public boolean sendCompensateMsg(long currentTime, String groupId, String model, String address, String uniqueKey,
			String className, String methodStr, String data, int time, int startError) {
		TransactionCompensateMsg transactionCompensateMsg = new TransactionCompensateMsg(currentTime, groupId, model,
				address, uniqueKey, className, methodStr, data, time, 0, startError);
		return compensateService.saveCompensateMsg(transactionCompensateMsg);
	}

	@Override
	public String sendMsg(String model, String msg) {
		return txManagerSenderService.sendMsg(model, msg, configReader.getTransactionNettyDelayTime());
	}

	@Override
	public TxState getState() {
		return eurekaService.getState();
	}

}
