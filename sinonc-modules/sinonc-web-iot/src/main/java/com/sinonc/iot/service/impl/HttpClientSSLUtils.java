package com.sinonc.iot.service.impl;


import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;

public class HttpClientSSLUtils {
    private static HttpClient client = null;
    protected static final Integer DEFAULT_CONNECTION_TIME_OUT = 100000;
    protected static final Integer DEFAULT_SOCKET_TIME_OUT = 200000;
    protected static final String DEFAULT_CHAR_SET = "UTF-8";

    public HttpClientSSLUtils() {
    }

    public static String doPost(String url, String jsonText) throws Exception {
        HttpClient client = null;
        HttpPost post = new HttpPost(url);

        String var6;
        try {
            if (jsonText != null && !jsonText.isEmpty()) {
                StringEntity entity = new StringEntity(jsonText, ContentType.APPLICATION_JSON);
                post.setEntity(entity);
            }

            Builder customReqConf = RequestConfig.custom();
            customReqConf.setConnectTimeout(DEFAULT_CONNECTION_TIME_OUT);
            customReqConf.setSocketTimeout(DEFAULT_CONNECTION_TIME_OUT);
            post.setConfig(customReqConf.build());
            HttpResponse res = null;
            if (url.startsWith("https")) {
                client = createSSLInsecureClient();
                res = ((HttpClient) client).execute(post);
            } else {
                client = HttpClientSSLUtils.client;
                res = ((HttpClient) client).execute(post);
            }

            var6 = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
        } finally {
            post.releaseConnection();
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }

        }

        return var6;
    }

    public static String doGet(String url) throws Exception {
        HttpClient client = null;
        HttpGet get = new HttpGet(url);
        String result = "";

        try {
            Builder customReqConf = RequestConfig.custom();
            customReqConf.setConnectTimeout(DEFAULT_CONNECTION_TIME_OUT);
            customReqConf.setSocketTimeout(DEFAULT_CONNECTION_TIME_OUT);
            get.setConfig(customReqConf.build());
            HttpResponse res = null;
            if (url.startsWith("https")) {
                client = createSSLInsecureClient();
                res = ((HttpClient) client).execute(get);
            } else {
                client = HttpClientSSLUtils.client;
                res = ((HttpClient) client).execute(get);
            }

            result = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
        } finally {
            get.releaseConnection();
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }

        }

        return result;
    }

    private static CloseableHttpClient createSSLInsecureClient() throws GeneralSecurityException {
        try {
            SSLContext sslContext = (new SSLContextBuilder()).loadTrustMaterial((KeyStore) null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (GeneralSecurityException var2) {
            throw var2;
        }
    }


    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(128);
        cm.setDefaultMaxPerRoute(128);
        client = HttpClients.custom().setConnectionManager(cm).build();
    }
}
