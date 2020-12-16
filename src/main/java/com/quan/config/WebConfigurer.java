package com.quan.config;

import com.quan.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;

import java.io.File;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/11/14
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String projectDir = System.getProperty("user.dir");
        String resourcesDir = projectDir + File.separator + "files" + File.separator;
        File file = new File(resourcesDir);
        if (!file.exists()){
            file.mkdir();
        }
        resourcesDir = resourcesDir.replace("\\", "/");
        registry.addResourceHandler("/files/**").addResourceLocations("file:" + resourcesDir);

        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        //拦截所有
        registration.addPathPatterns("/**");
        //放行
        registration.excludePathPatterns(

                "/",
                "/user/login",
                "/user/register",
                "/user/code",

                "/files/**",
                "/**/*.html",
                "/**/*.js",
                "/**/*.css",
                "/**/*.woff",
                "/**/*.ttf",
                "/favicon.ico"
        );
    }

}
