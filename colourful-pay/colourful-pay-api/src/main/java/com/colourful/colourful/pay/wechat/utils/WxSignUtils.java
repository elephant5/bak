package com.colourful.colourful.pay.wechat.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Comparator;
import java.util.Map;

/**
 * Created by jill on 2018/5/2.
 */
public class WxSignUtils {
    public static String jsapiSign(String nonceStr, String jsapiTicket, String timestamp,
                                   String url) {
        StringBuilder sb = new StringBuilder();
        sb.append("jsapi_ticket=").append(jsapiTicket).append("&");
        sb.append("noncestr=").append(nonceStr).append("&");
        sb.append("timestamp=").append(timestamp).append("&");
        sb.append("url=").append(url);
        return DigestUtils.sha1Hex(sb.toString());
    }

    public static String genMd5Sign(Map<String, Object> params, String key) {
        StringBuilder sb = new StringBuilder();
        params.keySet().stream()
                .sorted(Comparator.naturalOrder())
                .forEachOrdered(k -> {
                    if (k.equals("sign")) {
                        return;
                    }
                    Object value = params.get(k);
                    if (null == value) {
                        return;
                    }
                    String strValue = String.valueOf(value);
                    if (strValue.isEmpty()) {
                        return;
                    }
                    sb.append("&").append(k).append("=").append(strValue);
                });
        sb.append("&key=").append(key);
        return DigestUtils.md5Hex(sb.substring(1)).toUpperCase();
    }

    public static String decryptSign(String info, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
        String decKey = DigestUtils.md5Hex(key).toLowerCase();

        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decKey.getBytes(), "AES"));

        byte[] result = cipher.doFinal(Base64.decodeBase64(info));

        return new String(result, "utf-8");
    }
}
