package cn.okcl.im.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件上传配置属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "files")
public class FileProperties {
    private String uploadPath; // 基础上传路径
    private String privatePath; // 私密上传路径
    private String privateApiPrefix; // 私密上传接口访问路径
    private String commonPath; // 公共上传路径
    private String commonApiPrefix; // 公共上传接口访问路径
}
