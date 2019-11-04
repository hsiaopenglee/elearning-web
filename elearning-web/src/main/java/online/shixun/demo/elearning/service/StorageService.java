package online.shixun.demo.elearning.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import online.shixun.demo.elearning.dto.Student;

//保存文件Service
@Service

//使用ConfigurationProperties注解，Spring会自动根据变量名+prefix 去application.properties中查找配置值
@ConfigurationProperties(prefix = "file.upload")
public class StorageService  extends BaseService {
	private String avatarPath;

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
    
    // 保存文件
    public void storeStudentImage(MultipartFile file, Student student) {
        String fileName = file.getOriginalFilename();
        String imageSuffix =  fileName.substring(fileName.lastIndexOf("."));
        String completeFilePath = avatarPath + "/student/student-" + student.getId() + imageSuffix;
        try {
            Files.copy(file.getInputStream(), Paths.get(completeFilePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.debug(e.getMessage());
        }
        student.setImageSuffix(imageSuffix);
    }
	
}
