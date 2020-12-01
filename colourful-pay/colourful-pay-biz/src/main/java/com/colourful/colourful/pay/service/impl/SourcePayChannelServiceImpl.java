package com.colourful.colourful.pay.service.impl;

import com.colourful.colourful.pay.dto.res.SourcePayChannelInfoResDto;
import com.colourful.colourful.pay.entity.SourcePayChannel;
import com.colourful.colourful.pay.mapper.SourcePayChannelMapper;
import com.colourful.colourful.pay.service.SourcePayChannelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 终端渠道配置支付方式关系表 服务实现类
 * </p>
 *
 * @author json
 * @since 2020-07-30
 */
@Service
public class SourcePayChannelServiceImpl extends ServiceImpl<SourcePayChannelMapper, SourcePayChannel> implements SourcePayChannelService {

	@Autowired
	private SourcePayChannelMapper sourcePayChannelMapper;



	@Override
	public List<SourcePayChannelInfoResDto> getPayChannelInfoList(String source) {
		return sourcePayChannelMapper.selectPayChannelInfoList(source);
	}
}
