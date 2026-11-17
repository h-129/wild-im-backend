package cn.okcl.im.service;

import cn.okcl.im.model.vo.UploadVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公共上传服务
 */
public interface CommonUploadService {
    /**
     * 上传文件
     *
     * @param file 文件
     * @return 是否上传成功
     */
    UploadVo upload(MultipartFile file);
}
