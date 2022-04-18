package vn.com.mbbank.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageConfig {

    @Value("${minio.baseUrl}")
    private String minioBaseUrl;

    @Value("${minio.accessKey}")
    private String minioAccessKey;

    @Value("${minio.secretKey}")
    private String minioSecretKey;

    @Value("${report.exportFolder}")
    private String exportFolder;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder().endpoint(minioBaseUrl).credentials(minioAccessKey, minioSecretKey).build();
    }

    public String getExportFolder() {
        return exportFolder;
    }

    public void setExportFolder(String exportFolder) {
        this.exportFolder = exportFolder;
    }
}
