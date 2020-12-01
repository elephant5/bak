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
package com.colourful.colourful.mp.service;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.colourful.colourful.common.core.util.R;
import com.colourful.colourful.mp.entity.WxMpMenu;

/**
 * 微信菜单业务
 *
 * @author colourful
 * @date 2019-03-27 20:45:18
 */
public interface WxMenuService extends IService<WxMpMenu> {

	/**
	 * 新增微信公众号按钮
	 * @param wxMenus json
	 * @param appId 公众号信息
	 * @return
	 */
	Boolean save(JSONObject wxMenus, String appId);

	/**
	 * 发布到微信
	 * @param appId 公众号信息
	 * @return
	 */
	R push(String appId);

	/**
	 * 通过appid 查询菜单信息
	 * @param appId
	 * @return
	 */
	R getByAppId(String appId);

	/**
	 * 通过appid 删除菜单
	 * @param appId
	 * @return
	 */
	R delete(String appId);

}
