package com.colourful.colourful.pay.wechat.api.sns.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by jill on 2018/5/2.
 */
@Data
public class WxSnsSession {

    @JsonProperty("openid")
    private String openId;
    @JsonProperty("session_key")
    private String sessionKey;
}
