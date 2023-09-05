package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.*;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * User: hallen
 * Date: 2023/9/5
 * Time: 21:39
 */

/**
 * 通用接口
 */

@RestController
@RequestMapping("/admin/common")
@ApiOperation("通用接口")
@Slf4j
public class CommonController {
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传{}", file);
        /**
         * 视频中讲解的是将文件上传到阿里云中,
         * 这里把上传的文件存放到本地目录下
         * p38
         * 【黑马程序员2023最新Java项目实战《苍穹外卖》
         * https://www.bilibili.com/video/BV1TP411v7v6?p=38&vd_source=9754d787a7e94230ea564beefa400978
         */

        if (!file.isEmpty()) {
            try {
                // 获取文件名
                String originalFilename = file.getOriginalFilename();
                String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String objectName = UUID.randomUUID().toString() + extension;

                // 指定文件保存的目录，这里保存在项目根目录下的 "sky-server/src/main/java/com/sky/image" 文件夹中
                String uploadDir = System.getProperty("user.dir") + File.separator +
                        "sky-server/src/main/java/com/sky/image";
                // 创建目录
                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // 创建文件路径
                String filePath = uploadDir + File.separator + objectName;
                log.info("文件保存路径为: "+filePath);
                File dest = new File(filePath);

                // 保存文件
                file.transferTo(dest);

                // 文件保存成功
                return Result.success("上传成功");
            } catch (IOException e) {
                e.printStackTrace();
                // 处理文件保存失败的情况
                return Result.error(MessageConstant.UPLOAD_FAILED);
            }
        } else {
            // 处理文件为空的情况
            return Result.error(MessageConstant.UPLOAD_FAILED);
        }

    }
}
