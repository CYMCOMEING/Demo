package com.example.common.spider.ygo;

import com.example.common.utils.L;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardManager {

    public static void main(String[] args) {
        String url = "https://www.ourocg.cn/card/list-5/1";
        try {
            GetHtml.getInstance()
                    .setHead("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")
                    .setCallBackLinsener(new GetHtml.ICallBackListener() {
                        @Override
                        public void responseSuccess(String html) {
                            CardManager cardManager = new CardManager();
                            String jsonData = cardManager.extractCardData(html);
                            List<Card> cards = cardManager.jsonToCardData(jsonData);
                            for (Card card : cards) {
                                L.log(card);
                            }
                        }

                        @Override
                        public void responseFailed(int statusCode) {

                        }
                    })
                    .request(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从thml提取json数据
     *
     * @param html
     * @return
     */
    public String extractCardData(String html) {
//        L.log(html);
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("script");
        for (Element element : elements) {
            String string = element.data();
            if (string.contains("window.__STORE__ = ")) {
                string = string.substring(string.indexOf("{") - 1);
                string = string.substring(0, string.lastIndexOf("}") + 1);
                return string;
            }
        }
        return "";
    }

    /**
     * 从json反序列化到java类
     *
     * @param json
     */
    public List<Card> jsonToCardData(String json) {
        L.log(json);
        JsonParser parser = new JsonParser();  //创建JSON解析器
        JsonObject object = (JsonObject) parser.parse(json);  //创建JsonObject对象
        JsonArray array = object.get("cards").getAsJsonArray();    //得到为json的数组

        List<Card> list = new ArrayList<>(array.size());
        for (int i = 0; i < array.size(); i++) {
            JsonObject subObject = array.get(i).getAsJsonObject();
            list.add(JsonObjectToCard(subObject));
        }
        return list;
    }

    public Card JsonObjectToCard(JsonObject subObject) {
        Card card = new Card();
        card.setId(getJElementLong(subObject,"id"));
        card.setHash_id(getJElementString(subObject,"hash_id"));
        card.setPassword(getJElementString(subObject,"password"));
        card.setName(getJElementString(subObject,"name"));
        card.setName_ja(getJElementString(subObject,"name_ja"));
        card.setName_en(getJElementString(subObject,"name_en"));
        card.setLocale(getJElementInt(subObject,"locale"));
        card.setType_st(getJElementString(subObject,"type_st"));
        card.setType_val(getJElementString(subObject,"type_val"));
        card.setImg_url(getJElementString(subObject,"img_url"));
        card.setLevel(getJElementInt(subObject,"level"));
        card.setAttribute(getJElementString(subObject,"attribute"));
        card.setRace(getJElementString(subObject,"race"));
        card.setAtk(getJElementInt(subObject,"atk"));
        card.setDef(getJElementInt(subObject,"def"));
        card.setPend_l(getJElementInt(subObject,"pend_l"));
        card.setPend_r(getJElementInt(subObject,"pend_r"));
        card.setLink(getJElementInt(subObject,"link"));
        card.setLink_arrow(getJElementInt(subObject,"link_arrow"));
        card.setName_nw(getJElementString(subObject,"name_nw"));
        card.setDesc(getJElementString(subObject,"desc"));
        card.setDesc_nw(getJElementString(subObject,"desc_nw"));
        card.setRare(getJElementString(subObject,"rare"));
        card.setPackages(getJElementString(subObject,"packages"));
        card.setHref(getJElementString(subObject,"href"));
        return card;
    }

    public String getJElementString(JsonObject subObject, String memberName) {
        if (subObject != null && memberName != null) {
            JsonElement jsonElement = subObject.get(memberName);
            if (jsonElement != null && !jsonElement.isJsonNull()) {
                return jsonElement.getAsString();
            }
        }
        return null;
    }

    public int getJElementInt(JsonObject subObject, String memberName) {
        if (subObject != null && memberName != null) {
            JsonElement jsonElement = subObject.get(memberName);
            if (jsonElement != null && !jsonElement.isJsonNull()) {
                return jsonElement.getAsInt();
            }
        }
        return 0;
    }

    public Long getJElementLong(JsonObject subObject, String memberName) {
        if (subObject != null && memberName != null) {
            JsonElement jsonElement = subObject.get(memberName);
            if (jsonElement != null && !jsonElement.isJsonNull()) {
                return jsonElement.getAsLong();
            }
        }
        return (long) 0;
    }
}
