package com.colourful.colourful.pay.wechat.api.base.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by jill on 2018/5/2.
 */
@Data
public class WxIpList {
    @JsonProperty("ip_list")
    private List<String> ipList;
}
