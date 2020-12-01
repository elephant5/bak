package com.colourful.colourful.pay.wechat.utils;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Slf4j
public class HttpUtils {

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url 发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }


    /*
    * java对象转换为 name1=value1&name2=value2 的形式。
    * */
    public static String parseURLPair(Object o) throws Exception{
        Class<? extends Object> c = o.getClass();
        Field[] fields = c.getDeclaredFields();
        Map<String, Object> map = new TreeMap<String, Object>();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = field.get(o);
            if(value != null)
                map.put(name, value);
        }
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        StringBuffer sb = new StringBuffer();
        while (it.hasNext()) {
            Map.Entry<String, Object> e = it.next();
            sb.append(e.getKey()).append("=").append(e.getValue()).append("&");
        }
        return sb.deleteCharAt(sb.length()-1).toString();
    }

    public static HttpClient getHttpClient(boolean pool, int httpsPort) {
        SSLSocketFactory sf = null;
        try {
            TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] certificate, String authType) {
                    return true;
                }
            };
            sf = new SSLSocketFactory(acceptingTrustStrategy, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        Scheme sch = new Scheme("https", httpsPort, sf);
        ClientConnectionManager ccm = null;
        if (pool) {
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(sch);
            ccm = new PoolingClientConnectionManager(registry);
            return new DefaultHttpClient(ccm);
        } else {
            DefaultHttpClient client = new DefaultHttpClient();
            client.getConnectionManager().getSchemeRegistry().register(sch);
            return client;
        }
    }

    /**
     * 获取终端IP
     * @param request
     * @return
     */
    public static String getRemoteIp(ServletRequest request) {
        String ip = null;
        if (request instanceof HttpServletRequest) {
            val httpReq = (HttpServletRequest) request;
            ip = httpReq.getHeader("g-remote-ip");
            if (null == ip || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = httpReq.getHeader("X-Forwarded-For");
            }
            if (null == ip || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = httpReq.getHeader("Proxy-Client-IP");
            }
            if (null == ip || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = httpReq.getHeader("WL-Proxy-Client-IP");
            }
            if (null == ip || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = httpReq.getHeader("HTTP_CLIENT_IP");
            }
            if (null == ip || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = httpReq.getHeader("HTTP_X_FORWARDED_FOR");
            }
        }
        if (null == ip || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if(!StringUtils.isEmpty(ip)){
            String[] split = ip.split(",");
            if(split.length >= 2){
                for(int i=0 ;i <split.length; i++){
                    if(!"127.0.0.1" .equals( split[i].trim())){
                        return split[i];
                    }
                }
            }else {
                return ip;
            }
        }
        return ip;
    }

}