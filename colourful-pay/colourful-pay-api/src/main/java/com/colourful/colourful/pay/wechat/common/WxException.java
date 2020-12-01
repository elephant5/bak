package com.colourful.colourful.pay.wechat.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by jill on 2018/5/2.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxException extends BizException {
    private int errorCode;

    public WxException(int errorCode, String message) {
        super(errorCode, message);
    }
}
