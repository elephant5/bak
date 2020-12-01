package com.colourful.colourful.pay.wechat.api.sns.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by jill on 2018/5/2.
 */
@Data
public class WxSnsOAuthUserInfo {
    @JsonProperty("openid")
    private String openId;
    private String nickname;
    private Integer sex;
    private String city;
    private String province;
    private String country;
    @JsonProperty("headimgurl")
    private String headImgUrl;
    @JsonProperty("unionid")
    private String unionId;
}
