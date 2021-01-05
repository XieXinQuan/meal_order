package com.quan.controller;

import com.quan.Enum.ResultEnum;
import com.quan.exception.GlobalException;
import com.quan.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/21
 */
@RequestMapping("/file")
@RestController
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping("/upload")
    public Object upload(MultipartFile file, HttpServletRequest request){
        if (file == null){
            throw new GlobalException(ResultEnum.CustomException.getKey(), "请选择文件");
        }

        return fileService.fileUpload(file);
    }
}
