package com.colourful.colourful.pay.wechat.api.pay.req;

import com.colourful.colourful.pay.wechat.utils.WxSignUtils;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

@Data
@JacksonXmlRootElement(localName = "xml")
public class BasePayReq {
    private static Map<Class, List<Field>> classFieldMap = new HashMap<>();

    @Getter
    @Setter
    @JacksonXmlCData
    private String appid;
    @Getter
    @Setter
    @JacksonXmlCData
    private String mchId;
    @Getter
    private String nonceStr;
    @Getter
    private String sign;
    @Getter
    private String signType = "MD5";

    public void genSign(String key) {
//    this.signType = "MD5";
        this.nonceStr = UUID.randomUUID().toString().replace("-", "")
                .substring(0, 32).toUpperCase();
        Map<String, Object> params = new HashMap<>();
        getAllDeclaredFields(this.getClass()).stream()
                .filter(f -> !Modifier.isStatic(f.getModifiers()) && !f.getName().equals("sign"))
                .forEach(f -> {
                    Object value = valueOfField(this, f.getName());
                    if (!(null == value || (value instanceof String && ((String) value).isEmpty()))) {
                        params.put(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, f.getName()), value);
                    }
                });
        this.sign = WxSignUtils.genMd5Sign(params, key);
    }

    static List<Field> getAllDeclaredFields(Class clazz) {
        if (classFieldMap.containsKey(clazz)) {
            return classFieldMap.get(clazz);
        }
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length < 1) {
            return Collections.emptyList();
        }
        List<Field> fieldList = Lists.newArrayList(fields);
        if (null != clazz.getSuperclass()) {
            fieldList.addAll(getAllDeclaredFields(clazz.getSuperclass()));
        }
        classFieldMap.put(clazz, fieldList);
        return fieldList;
    }

    static Object valueOfField(Object obj, String fieldName) {
        try {
            return obj.getClass().getMethod("get" + StringUtils.capitalize(fieldName)).invoke(obj);
        } catch (Exception e) {
            return null;
        }
    }
}
