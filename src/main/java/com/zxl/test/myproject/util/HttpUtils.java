package com.zxl.test.myproject.util;

import com.zxl.test.myproject.exception.InvokeFailException;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by Alex on 16/9/21 上午10:11.
 */
@Component
public class HttpUtils {
    @Autowired
    private RestTemplate restTemplate;

    public  String doPost(String url, Map<String, String> formParams) {
        if (formParams == null) {
            return doPost(url);
        }

        try {
            MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
            formParams.keySet().stream().forEach(e -> requestEntity.add(e, MapUtils.getString(formParams, e, "")));
            return restTemplate.postForObject(url, requestEntity, String.class);
        } catch (Exception e) {
            throw new InvokeFailException("请求异常");
        }
    }

    public  String doPost(String url) {
        try {
            return restTemplate.postForObject(url, HttpEntity.EMPTY, String.class);
        } catch (Exception e) {
            throw new InvokeFailException("请求异常");
        }

    }

    public String doGet(String url) {
        try {
           return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            throw new InvokeFailException("请求异常");
        }
    }
}
