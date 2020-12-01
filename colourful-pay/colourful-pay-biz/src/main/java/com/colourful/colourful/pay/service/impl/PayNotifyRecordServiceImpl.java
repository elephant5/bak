package com.colourful.colourful.pay.service.impl;

import com.colourful.colourful.pay.entity.PayNotifyRecord;
import com.colourful.colourful.pay.mapper.PayNotifyRecordMapper;
import com.colourful.colourful.pay.service.PayNotifyRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付成功回调通知记录日志表 服务实现类
 * </p>
 *
 * @author json
 * @since 2020-07-30
 */
@Service
public class PayNotifyRecordServiceImpl extends ServiceImpl<PayNotifyRecordMapper, PayNotifyRecord> implements PayNotifyRecordService {

}
