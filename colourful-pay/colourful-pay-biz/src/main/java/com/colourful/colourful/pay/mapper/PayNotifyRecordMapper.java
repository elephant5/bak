package com.colourful.colourful.pay.mapper;

import com.colourful.colourful.pay.entity.PayNotifyRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 支付成功回调通知记录日志表 Mapper 接口
 * </p>
 *
 * @author json
 * @since 2020-07-30
 */
@Mapper
public interface PayNotifyRecordMapper extends BaseMapper<PayNotifyRecord> {

}
