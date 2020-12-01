package com.colourful.colourful.pay.wechat.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by jill on 2018/3/29.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends Exception {
    private int errorCode = -1;

    public BizException(int errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }
}
