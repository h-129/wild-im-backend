package cn.okcl.im.service.impl;

import cn.okcl.im.enums.ResultCodeEnum;
import cn.okcl.im.exception.BusinessException;
import cn.okcl.im.model.vo.UploadVo;
import cn.okcl.im.properties.FileProperties;
import cn.okcl.im.service.CommonUploadService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * 公共上传服务实现类
 */
@Service
public class CommonUploadServiceImpl implements CommonUploadService {
    //允许上传的文件类型
    private static final Set<String> WHITE_IMAGE_TYPES = Set.of(".jpg", ".jpeg", ".png", ".gif");
    @Resource
    private FileProperties fileProperties;

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 是否上传成功
     */
    @Override
    public UploadVo upload(MultipartFile file) {
        //检查文件大小
        if (!sizeCheck(file)) {
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "文件大小不能超过10MB");
        }
        String fileName = file.getOriginalFilename();
        if (!StringUtils.hasText(fileName)) {
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "无效的文件");
        }
        String extension = this.getExtension(fileName);
        if (!WHITE_IMAGE_TYPES.contains(extension)) {
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "不支持的文件类型");
        }
        LocalDateTime date = LocalDateTime.now();
        int year = date.getYear();
        int month = date.getMonthValue() + 1;
        int day = date.getDayOfMonth();
        //当前项目路径
        String projectDir = System.getProperty("user.dir");
        //上传路径
        String commonUploadPath = projectDir.replace("\\", "/") + fileProperties.getCommonUploadPath();
        //根据日期创建目录
        String folderPath = String.format("%s/%s/%s/%s", commonUploadPath, year, month, day);
        //创建目录 如果目录不存在则创建
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //生成文件名
        String uuid = UUID.randomUUID().toString();
        //新的文件名
        String newFileName = uuid + extension;
        //新文件
        File newFile = new File(folderPath + "/" + newFileName);
        //保存文件
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            throw new BusinessException(ResultCodeEnum.FAILED);
        }
        //返回文件访问路径
        String fileUrl = String.format("%s/%s/%s/%s/%s", fileProperties.getCommonUploadApiPrefix(), year, month, day, newFileName);
        UploadVo uploadVo = new UploadVo();
        uploadVo.setFileUrl(fileUrl);
        return uploadVo;
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName 文件名
     * @return 文件扩展名
     */
    private String getExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        return fileName.substring(index);
    }

    private boolean sizeCheck(MultipartFile file) {
        return file.getSize() <= 1024 * 1024 * 10;
    }
}
