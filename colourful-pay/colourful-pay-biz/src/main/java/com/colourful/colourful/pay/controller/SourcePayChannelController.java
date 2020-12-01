package com.colourful.colourful.pay.controller;


import com.colourful.colourful.common.security.annotation.Inner;
import com.colourful.colourful.pay.constant.ConstantMsg;
import com.colourful.colourful.pay.dto.res.SourcePayChannelInfoResDto;
import com.colourful.colourful.pay.service.SourcePayChannelService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.colourful.colourful.common.core.util.R;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 终端渠道配置支付方式关系 前端控制器
 * </p>
 *
 * @author json
 * @since 2020-07-30
 */
@RestController
@Slf4j
@RequestMapping(value = "/source",consumes = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8", produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
//application/json;charset=UTF-8
public class SourcePayChannelController {

	@Autowired
	private SourcePayChannelService sourcePayChannelService;

	@Inner(value = false)
	@ApiOperation(value = "查询渠道下支持的支付方式")
	@GetMapping(value = "/getPayChannels/{source}")
	public R<List<SourcePayChannelInfoResDto>> sendSmsCode(@PathVariable String source) {
		try {
			Assert.notNull(source, "参数不能为空");
			List<SourcePayChannelInfoResDto> list = sourcePayChannelService.getPayChannelInfoList(source);
			return R.ok(list, "ok");
		}catch (Exception e){
			log.error("查询渠道下支持的支付方式失败{}", e);
			return R.failed(null, ConstantMsg.ErrorMsg);
		}
	}

}

