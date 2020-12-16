package com.quan.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/11/15
 */
@Slf4j
public class CommonUtil {

    private static String localHost;
    static {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String hostAddress = inetAddress.getHostAddress();
            localHost = hostAddress;
        }catch (UnknownHostException e){}
    }

    public static String getServerIp(){
        return localHost;
    }
    public static String getServerWebFilePath(){
        return "http://" + localHost + "/files/";
    }
    public static String getProjectPath(){
        return System.getProperty("user.dir");
    }
    public static String getImagePath(){
        return getProjectPath() + File.separator + "files" + File.separator;
    }

}
