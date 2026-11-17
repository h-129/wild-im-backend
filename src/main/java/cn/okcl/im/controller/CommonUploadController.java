package cn.okcl.im.controller;

import cn.okcl.im.common.Result;
import cn.okcl.im.model.vo.UploadVo;
import cn.okcl.im.service.CommonUploadService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公共上传控制器
 */
@RestController
@RequestMapping("/common/upload")
public class CommonUploadController {
    @Resource
    private CommonUploadService commonUploadService;

    /**
     * 上传图片
     *
     * @param file 图片文件
     * @return 上传结果结果
     */
    @PostMapping("/image")
    public Result<?> uploadImage(@RequestPart("file") MultipartFile file) {
        UploadVo uploadVo = commonUploadService.upload(file);
        return Result.success(uploadVo);
    }
}
