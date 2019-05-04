package com.example.common.spider.ygo;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 获取html源码，包含js数据
 * 单例模式
 */
public class GetHtml {

    private String mHead;
    private CloseableHttpClient httpClient;
    private static GetHtml getHtml;
    private ICallBackListener listener;

    public interface ICallBackListener{
        void responseSuccess(String html);
        void responseFailed(int statusCode);
    }

    private GetHtml(){}

    public static GetHtml getInstance(){
        if (getHtml == null){
            getHtml = new GetHtml();
            getHtml.httpClient = HttpClients.createDefault();
        }
        return getHtml;
    }

    public GetHtml setHead(String head){
        mHead = head;
        return this;
    }

    public GetHtml setCallBackLinsener(ICallBackListener linsener){
        this.listener = linsener;
        return this;
    }

    public void request(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        //设置Header模拟浏览器行为
        httpGet.setHeader("User-Agent",mHead);
        //发送请求，收取响应
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        if (listener != null) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                //解析响应
                listener.responseSuccess(EntityUtils.toString(httpResponse.getEntity()));
            } else {
                listener.responseFailed(httpResponse.getStatusLine().getStatusCode());
            }
        }
        EntityUtils.consume(httpResponse.getEntity());
        httpResponse.close();
    }


}
