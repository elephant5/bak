package com.colourful.colourful.pay.wechat.api.sns.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by jill on 2018/5/2.
 */
@Data
public class WxSnsUnionIdUserInfo {
    private Integer subscribe;
    private String openid;
    private String nickname;
    private Integer sex;
    private String language;
    private String city;
    private String province;
    private String country;
    @JsonProperty("headimgurl")
    private String headImgUrl;
    @JsonProperty("subscribe_time")
    private Long subscribeTime;
    private String unionid;
    private String remark;
    private Integer groupid;
    @JsonProperty("tagid_list")
    private Integer[] tagIdList;
    @JsonProperty("subscribe_scene")
    private String subscribeScene;
    @JsonProperty("qr_scene")
    private Integer qrScene;
    @JsonProperty("qr_scene_str")
    private String qrSceneStr;
}
