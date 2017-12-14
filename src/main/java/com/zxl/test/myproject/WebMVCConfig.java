package com.zxl.test.myproject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.File;
import java.util.Locale;

/**
 * Created by Alex on 17/6/2 下午9:05.
 */
//@Configuration
//public class WebMVCConfig{
//
//    @Bean
//    public InternalResourceViewResolver getInternalResourceViewResolver2() {
//        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//        internalResourceViewResolver.setViewClass(HtmlResourceView.class);
//        internalResourceViewResolver.setPrefix("/app/");
//        internalResourceViewResolver.setSuffix(".html");
//        internalResourceViewResolver.setOrder(0);
//        return internalResourceViewResolver;
//    }
//
//    @Bean
//    public InternalResourceViewResolver getInternalResourceViewResolver() {
//        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//        internalResourceViewResolver.setPrefix("/");
//        internalResourceViewResolver.setOrder(1);
//        internalResourceViewResolver.setSuffix(".jsp");
//        return internalResourceViewResolver;
//    }
//
//    public static class HtmlResourceView extends InternalResourceView {
//        @Override
//        public boolean checkResource(Locale locale) {
//            File file = new File(this.getServletContext().getRealPath("/") + getUrl());
//            return file.exists();// 判断该页面是否存在
//        }
//    }
//}
