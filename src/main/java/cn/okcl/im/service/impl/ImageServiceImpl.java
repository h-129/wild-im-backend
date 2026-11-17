package cn.okcl.im.service.impl;

import cn.okcl.im.enums.ResultCodeEnum;
import cn.okcl.im.exception.BusinessException;
import cn.okcl.im.model.vo.UploadVo;
import cn.okcl.im.properties.FileProperties;
import cn.okcl.im.service.ImageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * 公共上传服务实现类
 */
@Service
public class ImageServiceImpl implements ImageService {
    //允许上传的文件类型
    private static final Set<String> WHITE_IMAGE_TYPES = Set.of(".jpg", ".jpeg", ".png", ".gif");

    // 最大文件大小：10MB
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 10;

    @Resource
    private FileProperties fileProperties;

    @Override
    public UploadVo uploadCommonImage(MultipartFile file) {
        // 公共上传：根路径 + 相对路径前缀
        String uploadBasePath = fileProperties.getCommonPath();
        String apiPrefix = fileProperties.getCommonApiPrefix();
        // 调用通用底层保存方法（不需要缩略图）
        return executeUpload(file, uploadBasePath, apiPrefix, false);
    }

    @Override
    public UploadVo uploadPrivateImage(MultipartFile file) {
        // 私密上传：根路径 + 隐私路径前缀
        String uploadBasePath = fileProperties.getPrivatePath();
        String apiPrefix = fileProperties.getPrivateApiPrefix();
        // 调用通用底层保存方法（需要生成缩略图）
        return executeUpload(file, uploadBasePath, apiPrefix, true);
    }

    /**
     * 核心统一上传处理逻辑（消除重复代码）
     *
     * @param file           上传的文件
     * @param uploadBasePath 基础存储路径（来自配置类）
     * @param apiPrefix      接口访问前缀（来自配置类）
     * @param needThumb      是否需要生成缩略图
     * @return UploadVo
     */
    private UploadVo executeUpload(MultipartFile file, String uploadBasePath, String apiPrefix, boolean needThumb) {
        // 1. 基础校验
        validateFile(file);

        String extension = getExtension(Objects.requireNonNull(file.getOriginalFilename()));
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        // 2. 构建物理存储目录路径 (项目根目录 + 基础路径 + 年/月/日)
        String projectDir = System.getProperty("user.dir").replace("\\", "/");
        String folderPath = String.format("%s%s/%d/%d/%d", projectDir, uploadBasePath, year, month, day);

        File folder = new File(folderPath);
        if (!folder.exists() && !folder.mkdirs()) {
            throw new BusinessException(ResultCodeEnum.FAILED, "创建上传目录失败");
        }

        // 3. 生成主文件并保存
        String newFileName = UUID.randomUUID() + extension;
        File destFile = new File(folderPath + "/" + newFileName);
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            throw new BusinessException(ResultCodeEnum.FAILED, "文件保存失败");
        }

        // 4. 组装返回结果
        UploadVo uploadVo = new UploadVo();
        String fileUrl = String.format("%s/%d/%d/%d/%s", apiPrefix, year, month, day, newFileName);
        uploadVo.setFileUrl(fileUrl);

        // 5. 如果需要生成缩略图
        if (needThumb) {
            String thumbFileName = UUID.randomUUID() + extension;
            String thumbFilePath = folderPath + "/" + thumbFileName;
            try {
                // 传入去掉点号的格式名称，例如 "png" 或 "jpg"
                compressImage(destFile, thumbFilePath, extension.substring(1));
            } catch (IOException e) {
                throw new BusinessException(ResultCodeEnum.FAILED, "缩略图生成失败");
            }
            String thumbUrl = String.format("%s/%d/%d/%d/%s", apiPrefix, year, month, day, thumbFileName);
            uploadVo.setThumbUrl(thumbUrl);
        }

        return uploadVo;
    }

    /**
     * 文件前置通用校验
     */
    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "上传文件不能为空");
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "文件大小不能超过10MB");
        }
        String fileName = file.getOriginalFilename();
        if (!StringUtils.hasText(fileName)) {
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "无效的文件名");
        }
        String extension = getExtension(fileName);
        if (!WHITE_IMAGE_TYPES.contains(extension.toLowerCase())) {
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "不支持的文件类型");
        }
    }

    /**
     * 获取文件扩展名（带点号，如 .png）
     */
    private String getExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        return fileName.substring(index);
    }

    /**
     * 压缩图片
     *
     * @param imgFile         原始图片文件
     * @param outputImagePath 输出图片路径
     * @param formatName      图片格式名称（如 png, jpg，不能带点号）
     * @throws IOException 异常信息
     */
    private void compressImage(File imgFile, String outputImagePath, String formatName) throws IOException {
        BufferedImage originImage = ImageIO.read(imgFile);
        if (originImage == null) {
            throw new IOException("无法读取图片文件");
        }

        int originWidth = originImage.getWidth();
        int originHeight = originImage.getHeight();
        double scale = Math.min(1.0, (double) 100 / Math.max(originWidth, originHeight));

        int newWidth = (int) (originWidth * scale);
        int newHeight = (int) (originHeight * scale);

        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Image scaledInstance = originImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        scaledImage.getGraphics().drawImage(scaledInstance, 0, 0, null);

        // 使用纯格式名（如 "png"）写入
        ImageIO.write(scaledImage, formatName, new File(outputImagePath));
    }
}