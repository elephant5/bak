package com.colourful.colourful.pay.wechat.config;

import com.colourful.colourful.pay.wechat.api.common.WxPayApi;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties("pay.wechat")
@Component
public class WechatPayConfig {

    private String appid;

    private String appsecret;

    private String mchid;

    private String mchSecret;

    private String certpath;

    private String notifyUrl;

    private String wapName;

    private String wapUrl;

	@Bean
	public WxPayApi wxPayApi() {
		return new WxPayApi(appid, mchid, mchSecret, certpath);
	}
}
