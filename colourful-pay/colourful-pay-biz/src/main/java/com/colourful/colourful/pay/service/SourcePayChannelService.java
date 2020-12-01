package com.colourful.colourful.pay.service;

import com.colourful.colourful.pay.dto.res.SourcePayChannelInfoResDto;
import com.colourful.colourful.pay.entity.SourcePayChannel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 终端渠道配置支付方式关系表 服务类
 * </p>
 *
 * @author json
 * @since 2020-07-30
 */
public interface SourcePayChannelService extends IService<SourcePayChannel> {

	/**
	 * 查询渠道下支持的支付方式
	 * @param source
	 * @return
	 */
	List<SourcePayChannelInfoResDto> getPayChannelInfoList(String source);
}
