package com.sdau.news.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import java.security.KeyManagementException;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import java.util.Map;


import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import javax.net.ssl.TrustManager;

import javax.net.ssl.X509TrustManager;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;

import org.apache.http.NameValuePair;

import org.apache.http.client.HttpClient;

import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.apache.http.client.methods.HttpDelete;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.client.methods.HttpPut;

import org.apache.http.conn.ClientConnectionManager;

import org.apache.http.conn.scheme.Scheme;

import org.apache.http.conn.scheme.SchemeRegistry;

import org.apache.http.conn.ssl.SSLSocketFactory;

import org.apache.http.entity.ByteArrayEntity;

import org.apache.http.entity.StringEntity;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
    /* public static  void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client= new OkHttpClient();
         Request request=new Request.Builder().url(address).build();
         client.newCall(request).enqueue(callback);
     }
    private static final String TAG = HttpUtil.class.getSimpleName();


    private static HttpsURLConnection connection = null;

    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";

    public static final String ERROR_TIMEOUT = "TIMEOUT";

    public static final String charsetName = "UTF-8";

    private static final int connectTimeOut = 8000;
    private static final int readTimeOut = 8000;


    /**
     * 建立链接，设置超时时间为8秒
     * <p>
     * path 链接地址
     * return 已经建立完成的链接
     */

    /*private static HttpsURLConnection getConnection(String path) {
        Log.d(TAG, "getConnection.......");
        URL url;
        try {
            url = new URL(path);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod(GET_METHOD);
            connection.setConnectTimeout(connectTimeOut);
            connection.setReadTimeout(readTimeOut);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                Log.d(TAG, "connected");
                return connection;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向服务器发送请求，默认编码为UTF-8
     * <p>
     * path 请求地址
     * return 从服务器获取的json数据包，
     */
    /*public static String sendRequest(String path) {

        StringBuilder response = new StringBuilder();
        BufferedReader reader = null;
        InputStream in = null;


        try {
            connection = getConnection(path);
            Log.d(TAG, "waiting response");

            //报错
            in = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(in, charsetName));
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            Log.d(TAG, "got response" + response);


        } catch (IOException e) {
            Log.e(TAG, "IO Exception");
            e.printStackTrace();
            return ERROR_TIMEOUT;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

            try {
                if (in != null)
                    in.close();
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return response.toString();
    }*/

    public static String getALiYun(String type) throws IOException {
        String host = "http://toutiao-ali.juheapi.com";
        String path = "/toutiao/index";
        String method = "GET";
        String appcode = "0c2314d2d0794f05860b9199aed5a313";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("type", type);
        HttpResponse response = null;

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
             response = doGet(host, path, method, headers, querys);
           
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }

       return  EntityUtils.toString(response.getEntity());
    }


    public static String getJson(String path) {
        //字符串拼接类
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(path);
            Log.d("TAG", "getJson: 得到地址url");
            //网络连接的对象
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            //连接服务器的最大时长
            Log.d("TAG", "getJson: 打开链接：opernConnection执行");
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            //从服务器读取数据的最大时长
            conn.setReadTimeout(10000);
            //如果在上面的时长内完成，则可以执行下面的操作
            conn.setRequestProperty("Authorization", "0c2314d2d0794f05860b9199aed5a313");
            //获得网络输入流
            InputStream is = conn.getInputStream();
            //字节流转字符流
            InputStreamReader isr = new InputStreamReader(is);
            //套缓冲流
            BufferedReader br = new BufferedReader(isr);

            //缓冲字符串
            String buffer;



            //循环读取
            while ((buffer = br.readLine()) != null) {
                //拼接

                sb.append(buffer);
            }

            //关闭流
            br.close();

           // Log.d("TAG", sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }



    /**

     * get

     *

     * @param host

     * @param path

     * @param method

     * @param headers

     * @param querys

     * @return

     * @throws Exception

     */

    public static HttpResponse doGet(String host, String path, String method,

                                     Map<String, String> headers,

                                     Map<String, String> querys)

            throws Exception {

        HttpClient httpClient = wrapClient(host);



        HttpGet request = new HttpGet(buildUrl(host, path, querys));

        for (Map.Entry<String, String> e : headers.entrySet()) {

            request.addHeader(e.getKey(), e.getValue());

        }



        return httpClient.execute(request);

    }



    /**

     * post form

     *

     * @param host

     * @param path

     * @param method

     * @param headers

     * @param querys

     * @param bodys

     * @return

     * @throws Exception

     */

    public static HttpResponse doPost(String host, String path, String method,

                                      Map<String, String> headers,

                                      Map<String, String> querys,

                                      Map<String, String> bodys)

            throws Exception {

        HttpClient httpClient = wrapClient(host);



        HttpPost request = new HttpPost(buildUrl(host, path, querys));

        for (Map.Entry<String, String> e : headers.entrySet()) {

            request.addHeader(e.getKey(), e.getValue());

        }



        if (bodys != null) {

            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();



            for (String key : bodys.keySet()) {

                nameValuePairList.add(new BasicNameValuePair(key, bodys.get(key)));

            }

            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");

            formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");

            request.setEntity(formEntity);

        }



        return httpClient.execute(request);

    }



    /**

     * Post String

     *

     * @param host

     * @param path

     * @param method

     * @param headers

     * @param querys

     * @param body

     * @return

     * @throws Exception

     */

    public static HttpResponse doPost(String host, String path, String method,

                                      Map<String, String> headers,

                                      Map<String, String> querys,

                                      String body)

            throws Exception {

        HttpClient httpClient = wrapClient(host);



        HttpPost request = new HttpPost(buildUrl(host, path, querys));

        for (Map.Entry<String, String> e : headers.entrySet()) {

            request.addHeader(e.getKey(), e.getValue());

        }



        if (StringUtils.isNotBlank(body)) {

            request.setEntity(new StringEntity(body, "utf-8"));

        }



        return httpClient.execute(request);

    }



    /**

     * Post stream

     *

     * @param host

     * @param path

     * @param method

     * @param headers

     * @param querys

     * @param body

     * @return

     * @throws Exception

     */

    public static HttpResponse doPost(String host, String path, String method,

                                      Map<String, String> headers,

                                      Map<String, String> querys,

                                      byte[] body)

            throws Exception {

        HttpClient httpClient = wrapClient(host);



        HttpPost request = new HttpPost(buildUrl(host, path, querys));

        for (Map.Entry<String, String> e : headers.entrySet()) {

            request.addHeader(e.getKey(), e.getValue());

        }



        if (body != null) {

            request.setEntity(new ByteArrayEntity(body));

        }



        return httpClient.execute(request);

    }



    /**

     * Put String

     * @param host

     * @param path

     * @param method

     * @param headers

     * @param querys

     * @param body

     * @return

     * @throws Exception

     */

    public static HttpResponse doPut(String host, String path, String method,

                                     Map<String, String> headers,

                                     Map<String, String> querys,

                                     String body)

            throws Exception {

        HttpClient httpClient = wrapClient(host);



        HttpPut request = new HttpPut(buildUrl(host, path, querys));

        for (Map.Entry<String, String> e : headers.entrySet()) {

            request.addHeader(e.getKey(), e.getValue());

        }



        if (StringUtils.isNotBlank(body)) {

            request.setEntity(new StringEntity(body, "utf-8"));

        }



        return httpClient.execute(request);

    }



    /**

     * Put stream

     * @param host

     * @param path

     * @param method

     * @param headers

     * @param querys

     * @param body

     * @return

     * @throws Exception

     */

    public static HttpResponse doPut(String host, String path, String method,

                                     Map<String, String> headers,

                                     Map<String, String> querys,

                                     byte[] body)

            throws Exception {

        HttpClient httpClient = wrapClient(host);



        HttpPut request = new HttpPut(buildUrl(host, path, querys));

        for (Map.Entry<String, String> e : headers.entrySet()) {

            request.addHeader(e.getKey(), e.getValue());

        }



        if (body != null) {

            request.setEntity(new ByteArrayEntity(body));

        }



        return httpClient.execute(request);

    }



    /**

     * Delete

     *

     * @param host

     * @param path

     * @param method

     * @param headers

     * @param querys

     * @return

     * @throws Exception

     */

    public static HttpResponse doDelete(String host, String path, String method,

                                        Map<String, String> headers,

                                        Map<String, String> querys)

            throws Exception {

        HttpClient httpClient = wrapClient(host);



        HttpDelete request = new HttpDelete(buildUrl(host, path, querys));

        for (Map.Entry<String, String> e : headers.entrySet()) {

            request.addHeader(e.getKey(), e.getValue());

        }



        return httpClient.execute(request);

    }



    private static String buildUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {

        StringBuilder sbUrl = new StringBuilder();

        sbUrl.append(host);

        if (!StringUtils.isBlank(path)) {

            sbUrl.append(path);

        }

        if (null != querys) {

            StringBuilder sbQuery = new StringBuilder();

            for (Map.Entry<String, String> query : querys.entrySet()) {

                if (0 < sbQuery.length()) {

                    sbQuery.append("&");

                }

                if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {

                    sbQuery.append(query.getValue());

                }

                if (!StringUtils.isBlank(query.getKey())) {

                    sbQuery.append(query.getKey());

                    if (!StringUtils.isBlank(query.getValue())) {

                        sbQuery.append("=");

                        sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));

                    }

                }

            }

            if (0 < sbQuery.length()) {

                sbUrl.append("?").append(sbQuery);

            }

        }



        return sbUrl.toString();

    }



    private static HttpClient wrapClient(String host) {

        HttpClient httpClient = new DefaultHttpClient();

        if (host.startsWith("https://")) {

            sslClient(httpClient);

        }



        return httpClient;

    }



    private static void sslClient(HttpClient httpClient) {

        try {

            KeyStore ctx = KeyStore.getInstance("TLS");

            X509TrustManager tm = new X509TrustManager() {

                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                public java.security.cert.X509Certificate[] getAcceptedIssuers() {

                    return null;

                }



            };

            ctx.load(null,null);

            SSLSocketFactory ssf = new SSLSocketFactory(ctx);

            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            ClientConnectionManager ccm = httpClient.getConnectionManager();

            SchemeRegistry registry = ccm.getSchemeRegistry();

            registry.register(new Scheme("https", ssf, 443));

        } catch (KeyManagementException ex) {

            throw new RuntimeException(ex);

        } catch (NoSuchAlgorithmException ex) {

            throw new RuntimeException(ex);

        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





}
