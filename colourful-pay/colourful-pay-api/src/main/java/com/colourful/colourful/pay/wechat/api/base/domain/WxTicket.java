package com.colourful.colourful.pay.wechat.api.base.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by jill on 2018/5/2.
 */
@Data
public class WxTicket {

    private String ticket;

    @JsonProperty("expires_in")
    private Long expiresIn;
}
