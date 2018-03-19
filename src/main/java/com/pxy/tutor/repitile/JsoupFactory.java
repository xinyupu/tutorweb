package com.pxy.tutor.repitile;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

public class JsoupFactory {

    public static Document connect(String url, JsoupConfig config) {
        return connect(url, config, 0);
    }

    public static Document connect(String url, JsoupConfig config, int tryTime) {
        Document document;
        try {
            if (config.getMethod().equals("GET")) {
                Map<String, String> cookies = Jsoup.connect(url).method(Connection.Method.GET).followRedirects(false).execute().cookies();
                document = Jsoup.connect(url).data(config.data.getName(), config.data.getValue())
                        .userAgent(config.userAgent)
                        .timeout(config.getTimeOut()).cookies(cookies)
                        .get();
                return document;
            } else if (config.getMethod().equals("POST")) {
                document = Jsoup.connect(url).data(config.data.getName(), config.data.getValue())
                        .userAgent(config.userAgent)
                        .cookie(config.getCookie().getName(), config.getCookie().getValue())
                        .timeout(config.getTimeOut())
                        .post();
                return document;
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

}
