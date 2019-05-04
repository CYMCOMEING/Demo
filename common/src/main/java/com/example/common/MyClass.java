package com.example.common;

import com.example.common.spider.ygo.CardManager;
import com.example.common.spider.ygo.GetHtml;
import com.example.common.utils.L;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MyClass {
    public static void main(String[] args) {
        try {
            GetHtml.getInstance()
                    .setHead("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")
                    .setCallBackLinsener(new GetHtml.ICallBackListener() {
                        @Override
                        public void responseSuccess(String htmlurl) {
                            new CardManager().extractCardData(htmlurl);
                        }

                        @Override
                        public void responseFailed(int statusCode) {

                        }
                    })
                    .request("https://www.ourocg.cn/card/list-5/1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
