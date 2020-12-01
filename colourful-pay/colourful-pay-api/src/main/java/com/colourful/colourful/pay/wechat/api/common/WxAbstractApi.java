package com.colourful.colourful.pay.wechat.api.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.colourful.colourful.pay.wechat.common.WxException;
import com.colourful.colourful.pay.wechat.common.bean.WxAccessToken;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.val;
import okhttp3.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Data
abstract public class WxAbstractApi {
    protected static final Logger logger = LoggerFactory.getLogger(WxAbstractApi.class);

    protected static final OkHttpClient httpClient;
    protected static final ObjectMapper objectMapper;

    static {
        httpClient = new OkHttpClient();

        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        Security.addProvider(new BouncyCastleProvider());
    }

    @SuppressWarnings("rawtypes")
	@Autowired
    protected RedisTemplate redisTemplate;
    
    private String appId;
    private String appSecret;

    public WxAbstractApi(String appId, String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
    }

    protected WxAccessToken getWxAccessToken() throws WxException {
        return get(
                new Request.Builder().url(
                        Objects.requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/cgi-bin"))
                                .newBuilder()
                                .addPathSegment("token")
                                .addQueryParameter("grant_type", "client_credential")
                                .addQueryParameter("appid", appId)
                                .addQueryParameter("secret", appSecret)
                                .build()),
                WxAccessToken.class);
    }

    @SuppressWarnings("unchecked")
	public String getAccessToken() throws WxException {
        String key = appId + "_" + appSecret + "_access_token";
        String keyForExpire = key + "_expire";
        //先从缓存中获取token
        String accessToken = (String) redisTemplate.opsForValue().get(key);
        Long expiresIn = (Long) redisTemplate.opsForValue().get(keyForExpire);
        //缓存 存在
        if(accessToken!=null && expiresIn !=null) {
        	//缓存未过期
            if(expiresIn > new Date().getTime()) {
                return accessToken;
            }
        }
        ///缓存不存在或者已过期 重新获取AccessToken
        WxAccessToken wxAccessToken = this.getWxAccessToken();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, (int) (wxAccessToken.getExpiresIn()/2));//设置有效期 7200/2 秒 即 1个小时 
        wxAccessToken.setExpiresIn(cal.getTimeInMillis());
        redisTemplate.opsForValue().set(key, wxAccessToken.getAccessToken());
        redisTemplate.opsForValue().set(keyForExpire, wxAccessToken.getExpiresIn());
        
        return wxAccessToken.getAccessToken();
    }

    protected static void get(Request.Builder request) throws WxException {
        try {
            Request req = request.get().build();
            logger.debug("GET: {}", req.url());
            Response response = httpClient.newCall(request.get().build()).execute();
            String resStr = Objects.requireNonNull(response.body()).string();
            logger.debug("RES: {}", resStr);
            JSONObject resObject = JSON.parseObject(resStr);
            if (resObject.containsKey("errcode") && !resObject.getInteger("errcode").equals(0)) {
                throw new WxException(resObject.getInteger("errcode"), resObject.getString("errmsg"));
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new WxException(-1, e.getMessage());
        }
    }

    protected static <T> T get(Request.Builder request, Class<T> clazz)
            throws WxException {
        try {
            Request req = request.get().build();
            logger.debug("GET: {}", req.url());
            Response response = httpClient.newCall(request.get().build()).execute();
            String resStr = Objects.requireNonNull(response.body()).string();
            logger.debug("RES: {}", resStr);
            JSONObject resObject = JSON.parseObject(resStr);
            if (resObject.containsKey("errcode") && !resObject.getInteger("errcode").equals(0)) {
                throw new WxException(resObject.getInteger("errcode"), resObject.getString("errmsg"));
            }
            return objectMapper.readValue(resStr, clazz);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new WxException(-1, e.getMessage());
        }
    }

    protected static void post(Request.Builder request, Object body) throws WxException {
        try {
            String json = objectMapper.writeValueAsString(body);
            Request req = request
                    .post(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), json))
                    .build();
            logger.debug("POST: {}, DATA: {}", req.url(), json);
            Response response = httpClient.newCall(req).execute();
            String resStr = Objects.requireNonNull(response.body()).string();
            logger.debug("RES: {}", resStr);
            JSONObject resObject = JSON.parseObject(resStr);
            if (resObject.containsKey("errcode") && !resObject.getInteger("errcode").equals(0)) {
                throw new WxException(resObject.getInteger("errcode"), resObject.getString("errmsg"));
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new WxException(-1, e.getMessage());
        }
    }

    protected static byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) {
        try {
            val cipher = Cipher.getInstance("DES/CBC/PKCS7Padding");
            val sKeySpec = new SecretKeySpec(keyByte, "DES");
            val params = AlgorithmParameters.getInstance("DES");
            params.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, params);// 初始化
            return cipher.doFinal(content);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
 
}
