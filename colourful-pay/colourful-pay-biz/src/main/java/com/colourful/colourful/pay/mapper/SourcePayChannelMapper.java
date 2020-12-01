package com.colourful.colourful.pay.mapper;

import com.colourful.colourful.pay.dto.res.SourcePayChannelInfoResDto;
import com.colourful.colourful.pay.entity.SourcePayChannel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 终端渠道配置支付方式关系表 Mapper 接口
 * </p>
 *
 * @author json
 * @since 2020-07-30
 */
@Mapper
public interface SourcePayChannelMapper extends BaseMapper<SourcePayChannel> {

	/**
	 * 查询渠道下支持的支付方式
	 * @param source
	 * @return
	 */
	List<SourcePayChannelInfoResDto> selectPayChannelInfoList(@Param(value = "source") String source);
}
