package com.tt.fly.common.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * @author molly
 * @Date 2021/11/05
 */
@Slf4j
public class HttpClientUtil {

    public static String doGet(String httpUrl) {

        if(StrUtil.isBlank(httpUrl)) {
            return null;
        }

        HttpURLConnection connection = null;
        InputStream inputStream = null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(15000);
            connection.connect();
            if(connection.getResponseCode() == 200) {
              inputStream = connection.getInputStream();
              if (null != inputStream) {
                  reader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));
                  String result = null;
                  while (null != (result = reader.readLine())) {
                      builder.append(result);
                  }
              }
            } else {
           log.error("http 请求错误 code={},message={}",connection.getResponseCode(), connection.getResponseMessage());
            }
        } catch (Exception e) {
            log.error("http 请求错误 ", e);
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != connection) {
                connection.disconnect();
            }

        }
        if (builder.length() > 0) {
            return builder.toString();
        }
        return null;
    }
}
