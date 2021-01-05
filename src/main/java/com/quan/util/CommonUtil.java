package com.quan.util;

import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/11/15
 */
@Slf4j
public class CommonUtil {

    public static String getServerAddress(HttpServletRequest request){
        String result = "http";
        StringBuffer requestURL = request.getRequestURL();
        String requestUrl = requestURL.toString();
        boolean isHttp = StringUtils.startsWith(requestUrl, "http://");
        result += isHttp ? "" : "s";
        result += "://";
        requestUrl = requestUrl.replace(result, "");
        return result + requestUrl.substring(0, requestUrl.indexOf("/") == -1 ? requestUrl.length() : requestUrl.indexOf("/"));
    }

    public static String getUserDir(){
        return System.getProperty("user.dir");
    }

    public static String joinFilePath(String ... str){
        if (ArrayUtil.isEmpty(str)){
            return StringUtils.EMPTY;
        }
        return StringUtils.join(str, File.separator);
    }
}
