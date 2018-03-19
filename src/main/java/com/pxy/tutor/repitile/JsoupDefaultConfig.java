package com.pxy.tutor.repitile;

public class JsoupDefaultConfig extends JsoupConfig {

    public JsoupDefaultConfig() {
        this.cookie = new Cookie("", "");
        this.data = new Data("query", "Java");
        this.method = "GET";
        this.timeOut = 5000;
        this.userAgent = "Mozilla";
        this.tryTime = 3;
    }
}
