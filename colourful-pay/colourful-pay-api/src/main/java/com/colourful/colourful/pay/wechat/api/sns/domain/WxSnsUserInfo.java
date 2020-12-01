package com.colourful.colourful.pay.wechat.api.sns.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by jill on 2018/5/2.
 */

@Data
public class WxSnsUserInfo {
    private String openId;
    @JsonProperty("nickName")
    private String nickname;
    @JsonProperty("gender")
    private Integer sex;
    private String city;
    private String province;
    private String country;
    @JsonProperty("avatarUrl")
    private String headImgUrl;
    private String unionId;
}
