package cn.okcl.im.service;

import cn.okcl.im.model.vo.UploadVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公共上传服务
 */
public interface ImageService {
    /**
     * 上传文件
     *
     * @param file 文件
     * @return 是否上传成功
     */
    UploadVo uploadCommonImage(MultipartFile file);

    /**
     * 上传私密图片
     *
     * @param file 图片文件
     * @return 上传结果
     */
    UploadVo uploadPrivateImage(MultipartFile file);
}
