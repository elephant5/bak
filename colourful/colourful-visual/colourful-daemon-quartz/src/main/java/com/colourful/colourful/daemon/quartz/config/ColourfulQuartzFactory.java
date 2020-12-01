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

package com.colourful.colourful.daemon.quartz.config;

import com.colourful.colourful.daemon.quartz.constants.ColourfulQuartzEnum;
import com.colourful.colourful.daemon.quartz.entity.SysJob;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 郑健楠
 *
 * <p>
 * 动态任务工厂
 */
@Slf4j
@DisallowConcurrentExecution
public class ColourfulQuartzFactory implements Job {

	@Autowired
	private ColourfulQuartzInvokeFactory colourfulQuartzInvokeFactory;

	@Override
	@SneakyThrows
	public void execute(JobExecutionContext jobExecutionContext) {
		SysJob sysJob = (SysJob) jobExecutionContext.getMergedJobDataMap()
				.get(ColourfulQuartzEnum.SCHEDULE_JOB_KEY.getType());
		colourfulQuartzInvokeFactory.init(sysJob, jobExecutionContext.getTrigger());
	}

}
