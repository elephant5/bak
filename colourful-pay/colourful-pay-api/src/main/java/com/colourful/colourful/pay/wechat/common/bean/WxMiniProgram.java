package com.colourful.colourful.pay.wechat.common.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by jill on 2018/5/2.
 */
@Data
public class WxMiniProgram {

    @JsonProperty("appid")
    private String appId;
    @JsonProperty("pagepath")
    private String pagePath;
}
