package com.steven.util.wx;

import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


public class HttpKit {
    
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final Logger LOGGER = Logger.getLogger(HttpKit.class);
    /**
     * 发送Get请求
     * @param url
     * @return
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws IOException 
     * @throws KeyManagementException 
     */
    public static String get(String url) throws NoSuchAlgorithmException, NoSuchProviderException, IOException, KeyManagementException {
        StringBuffer bufferRes = null;
        TrustManager[] tm = { new MyX509TrustManager() };
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
        sslContext.init(null, tm, new java.security.SecureRandom());  
        // 从上述SSLContext对象中得到SSLSocketFactory对象  
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        
        URL urlGet = new URL(url);
        HttpsURLConnection http = (HttpsURLConnection) urlGet.openConnection();
        // 连接超时
        http.setConnectTimeout(25000);
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout(25000);
        http.setRequestMethod("GET");
        http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        http.setSSLSocketFactory(ssf);
        http.setDoOutput(true);
        http.setDoInput(true);
        http.connect();
        
        InputStream in = http.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
        String valueString = null;
        bufferRes = new StringBuffer();
        while ((valueString = read.readLine()) != null){
            bufferRes.append(valueString);
        }
        in.close();
        if (http != null) {
            // 关闭连接
            http.disconnect();
        }
        return bufferRes.toString();
    }
    
    /**
     * 发送Get请求
     * @param url
     * @return
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws IOException 
     * @throws KeyManagementException 
     */
    public static String get(String url,Boolean https) throws NoSuchAlgorithmException, NoSuchProviderException, IOException, KeyManagementException {
     if(https != null && https){
      return get(url);
     }else{
      StringBuffer bufferRes = null;
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            // 连接超时
            http.setConnectTimeout(25000);
            // 读取超时 --服务器响应比较慢，增大时间
            http.setReadTimeout(25000);
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.connect();
            
            InputStream in = http.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
            String valueString = null;
            bufferRes = new StringBuffer();
            while ((valueString = read.readLine()) != null){
                bufferRes.append(valueString);
            }
            in.close();
            if (http != null) {
                // 关闭连接
                http.disconnect();
            }
            return bufferRes.toString();
     }
    }

    /**
     *  发送Post请求
     * @param url
     * @param params
     * @return
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public static String post(String url, String params) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
     StringBuffer bufferRes = null;
        TrustManager[] tm = { new MyX509TrustManager() };
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象  
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        URL urlGet = new URL(url);
        HttpsURLConnection http = (HttpsURLConnection) urlGet.openConnection();
        // 连接超时
        http.setConnectTimeout(25000);
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout(25000);
        http.setRequestMethod("POST");
        http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        http.setSSLSocketFactory(ssf);
        http.setDoOutput(true);
        http.setDoInput(true);
        http.connect();
        OutputStream out = http.getOutputStream();
        out.write(params.getBytes("UTF-8"));
        out.flush();
        out.close();
        InputStream in = http.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
        String valueString = null;
        bufferRes = new StringBuffer();
        while ((valueString = read.readLine()) != null){
            bufferRes.append(valueString);
        }
        in.close();
        if (http != null) {
            // 关闭连接
            http.disconnect();
        }
        return bufferRes.toString();
    }

    /**
     * 发送https请求
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return 返回微信服务器响应的信息
     */
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            return buffer.toString();
        } catch (Exception e) {
            LOGGER.error("https请求异常：{}", e);
        }
        return null;
    }
    
}
