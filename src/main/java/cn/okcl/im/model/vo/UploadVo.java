package cn.okcl.im.model.vo;

import lombok.Data;

/**
 * 文件上传VO
 */
@Data
public class UploadVo {
    /**
     * 文件访问路径
     */
    private String fileUrl;
    /**
     * 缩略图访问路径
     */
    private String thumbUrl;
}
