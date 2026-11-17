package cn.okcl.im.controller;

import cn.okcl.im.common.Result;
import cn.okcl.im.model.vo.UploadVo;
import cn.okcl.im.service.ImageService;
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
@RequestMapping("/upload/images")
public class ImageController {
    @Resource
    private ImageService imageService;

    /**
     * 上传公共图片
     *
     * @param file 图片文件
     * @return 上传结果结果
     */
    @PostMapping("/common")
    public Result<UploadVo> uploadCommonImage(@RequestPart("file") MultipartFile file) {
        UploadVo uploadVo = imageService.uploadCommonImage(file);
        return Result.success(uploadVo);
    }

    /**
     * 上传私密图片
     *
     * @param file 图片文件
     * @return 上传结果
     */
    @PostMapping("/private")
    public Result<UploadVo> uploadPrivateImage(@RequestPart("file") MultipartFile file) {
        UploadVo uploadVo = imageService.uploadPrivateImage(file);
        return Result.success(uploadVo);
    }
}
