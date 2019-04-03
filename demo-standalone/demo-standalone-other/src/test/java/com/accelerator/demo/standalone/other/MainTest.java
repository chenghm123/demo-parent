package com.accelerator.demo.standalone.other;

import com.accelerator.demo.standalone.other.http.HttpUtils;

public class MainTest {

    public static void main(String[] args) throws Exception {
        String url = "/api/weather/city/101030100";
        String host = "t.weather.sojson.com";
        int port = 80;
        HttpUtils.doGet(host, port, url);
    }


}
