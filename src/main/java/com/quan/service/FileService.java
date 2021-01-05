package com.quan.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import com.quan.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/21
 */
@Service
@Slf4j
public class FileService extends BaseService{

    public String fileUpload(MultipartFile file){
        String targetPath = CommonUtil.joinFilePath(CommonUtil.getUserDir(),
                "files", String.valueOf(this.getCurrentUserId()), file.getOriginalFilename());
        File targetFile = FileUtil.mkParentDirs(targetPath);
        while (targetFile.exists()){
            targetPath = targetPath.substring(0, targetPath.lastIndexOf(".")) + RandomUtil.randomInt(0, 10) + targetPath.substring(targetPath.lastIndexOf("."));
            targetFile = new File(targetPath);
        }
        try {
            file.transferTo(targetFile);
        }catch (IOException e){
            log.error("文件转换到本地错误", e);
        }
        String result = CommonUtil.getServerAddress(this.getHttpServletRequest()) +
                targetPath.replace(CommonUtil.getUserDir(), "");
        return result.replace("\\", "/");
    }
}
