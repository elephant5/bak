package com.colourful.colourful.pay.wechat.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

/**
 * Created by jill on 2018/5/4.
 */
public class WxXmlUtils {
	private static final XmlMapper xmlMapper;

	static {
		xmlMapper = new XmlMapper();
		xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
	}

	public static String toXml(Object obj) throws JsonProcessingException {
		return xmlMapper.writeValueAsString(obj);
	}

	public static <T> T fromXml(String xml, Class<T> clazz) throws IOException {
		return xmlMapper.readValue(xml, clazz);
	}

	public static <T> T fromXml(String xml, TypeReference valueTypeRef) throws IOException {
		return (T) xmlMapper.readValue(xml, valueTypeRef);
	}
}