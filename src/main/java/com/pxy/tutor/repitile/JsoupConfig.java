package com.pxy.tutor.repitile;


public class JsoupConfig {

    protected Cookie cookie;
    protected int timeOut;
    protected String userAgent;
    protected Data data;
    protected String method;
    protected int tryTime;


    public int getTryTime() {
        return tryTime;
    }

    public void setTryTime(int tryTime) {
        this.tryTime = tryTime;
    }

    public Cookie getCookie() {
        return cookie;
    }

    public JsoupConfig setCookie(String name, String value) {
        this.cookie = new Cookie(name, value);
        return this;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public JsoupConfig setTimeOut(int timeOut) {
        this.timeOut = timeOut;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public JsoupConfig setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public Data getData() {
        return data;
    }

    public JsoupConfig setData(String name, String value) {
        this.data = new Data(name, value);
        return this;
    }

    public String getMethod() {
        return method;
    }

    public JsoupConfig setMethod(String method) {
        this.method = method;
        return this;
    }

    protected static class Cookie {


        public Cookie(String name, String value) {
            this.name = name;
            this.value = value;
        }

        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    protected static class Data {

        public Data(String name, String value) {
            this.name = name;
            this.value = value;
        }

        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
