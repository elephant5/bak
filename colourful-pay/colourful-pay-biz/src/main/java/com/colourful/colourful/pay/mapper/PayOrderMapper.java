package com.colourful.colourful.pay.mapper;

import com.colourful.colourful.pay.entity.PayOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 支付平台订单表 Mapper 接口
 * </p>
 *
 * @author json
 * @since 2020-07-30
 */
@Mapper
public interface PayOrderMapper extends BaseMapper<PayOrder> {

}
