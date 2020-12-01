/*
 *
 *      Copyright (c) 2018-2025, colourful All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: colourful
 *
 */

package com.colourful.colourful.auth;

import com.colourful.colourful.common.feign.annotation.EnableColourfulFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author colourful
 * @date 2020-02-08 认证授权中心
 */
@SpringCloudApplication
@EnableColourfulFeignClients
public class ColourfulAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColourfulAuthApplication.class, args);
	}

}
