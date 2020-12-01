package com.colourful.colourful.pay.wechat.api.common;


import com.colourful.colourful.pay.wechat.api.pay.domain.BasePayRes;
import com.colourful.colourful.pay.wechat.api.pay.req.BasePayReq;
import com.colourful.colourful.pay.wechat.common.WxException;
import com.colourful.colourful.pay.wechat.utils.WxSignUtils;
import com.colourful.colourful.pay.wechat.utils.WxXmlUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

abstract public class WxPayAbstractApi {
    protected static final Logger logger = LoggerFactory.getLogger(WxPayAbstractApi.class);

    protected static final OkHttpClient httpClient;

    static {
        httpClient = new OkHttpClient();
    }

    private String appId;
    private String mchId;
    private String secretKey;
    private String certPath;

    public WxPayAbstractApi(String appId, String mchId, String secretKey, String certPath) {
        this.appId = appId;
        this.mchId = mchId;
        this.secretKey = secretKey;
        this.certPath = certPath;
    }

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    protected <T extends BasePayRes> T post(Request.Builder request, BasePayReq reqBody,
											Class<T> clazz) throws WxException {
        return post(request, reqBody, clazz, 3);
    }

    protected <T extends BasePayRes> T postCert(Request.Builder request, BasePayReq reqBody, Class<T> clazz) throws WxException {
        return postCert(request, reqBody, clazz, 3);
    }

    protected <T extends BasePayRes> T post(Request.Builder request, BasePayReq reqBody,
                                            Class<T> clazz, int retry) throws WxException {
        try {
            reqBody.setAppid(appId);
            reqBody.setMchId(mchId);
            reqBody.genSign(secretKey);
            String xml = WxXmlUtils.toXml(reqBody);
            Request req = request
                    .post(RequestBody.create(MediaType.parse("application/xml;charset=UTF-8"), xml))
                    .build();
            logger.debug("POST: {}, DATA: {}", req.url(), xml);
            Response response = httpClient.newCall(req).execute();
            String resStr = Objects.requireNonNull(response.body()).string();
            TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
            };
            logger.debug("RES: {}", resStr);
            Map<String, Object> resMap = WxXmlUtils.fromXml(resStr, typeRef);
            if (!"SUCCESS".equals(resMap.getOrDefault("return_code", "FAIL"))) {
                throw new WxException(-1, (String) resMap.getOrDefault("return_msg", "未知错误！"));
            } else if(!"SUCCESS".equals(resMap.getOrDefault("result_code", "FAIL"))) {
                throw new WxException(-1, (String) resMap.getOrDefault("err_code_des", "未知错误！"));

            }

            String sign = WxSignUtils.genMd5Sign(resMap, secretKey);
            if (!sign.equals(resMap.get("sign"))) {
                throw new WxException(-1, "微信返回签名不匹配");
            }
            return WxXmlUtils.fromXml(resStr, clazz);
        } catch (IOException e) {
            if (retry > 0) {
                return post(request, reqBody, clazz, retry - 1);
            }
            logger.error(e.getMessage(), e);
            throw new WxException(-1, e.getMessage());
        }
    }

    protected <T extends BasePayRes> T postCert(Request.Builder request, BasePayReq reqBody,
                                                Class<T> clazz, int retry) throws WxException {
        try {
            reqBody.setAppid(appId);
            reqBody.setMchId(mchId);
            reqBody.genSign(secretKey);
            String xml = WxXmlUtils.toXml(reqBody);
            Request req = request
                    .post(RequestBody.create(MediaType.parse("application/xml;charset=UTF-8"), xml))
                    .build();
            logger.debug("POST: {}, DATA: {}", req.url(), xml);


            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream inputStream = new FileInputStream(new File(this.getCertPath()));
            try {
                keyStore.load(inputStream, this.getMchId().toCharArray());
            } catch(Exception e) {
                throw new WxException(-1, "加载退款证书失败");
            } finally {
                inputStream.close();
            }

            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(keyStore, this.getMchId().toCharArray());
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());
            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };

            httpClient.newBuilder().retryOnConnectionFailure(true).sslSocketFactory(sslContext.getSocketFactory(), trustManager).build();

            Response response = httpClient.newBuilder()
                    .retryOnConnectionFailure(true)
                    .sslSocketFactory(sslContext.getSocketFactory(), trustManager)
                    .build().newCall(req).execute();
            String resStr = Objects.requireNonNull(response.body()).string();
            TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
            };
            logger.debug("RES: {}", resStr);
            Map<String, Object> resMap = WxXmlUtils.fromXml(resStr, typeRef);
            if (!"SUCCESS".equals(resMap.getOrDefault("return_code", "FAIL"))) {
                throw new WxException(-1, (String) resMap.getOrDefault("return_msg", "未知错误！"));
            } else if(!"SUCCESS".equals(resMap.getOrDefault("result_code", "FAIL"))) {
                throw new WxException(-1, (String) resMap.getOrDefault("err_code_des", "未知错误！"));

            }

            String sign = WxSignUtils.genMd5Sign(resMap, secretKey);
            if (!sign.equals(resMap.get("sign"))) {
                throw new WxException(-1, "微信返回签名不匹配");
            }
            return WxXmlUtils.fromXml(resStr, clazz);
        } catch (KeyStoreException e) {
            throw new WxException(-1, "退款证书失败：" + e.getMessage() );
        } catch (FileNotFoundException e) {
            throw new WxException(-1, "退款证书不存在" + e.getMessage());
        } catch (IOException e) {
            if (retry > 0) {
                return postCert(request, reqBody, clazz, retry - 1);
            }
            logger.error(e.getMessage(), e);
            throw new WxException(-1, e.getMessage());
        } catch (Exception e) {
            throw new WxException(-1, e.getMessage());
        }
    }
}
