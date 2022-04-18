package vn.com.mbbank.services.impl;

import vn.com.mbbank.utils.Utils;
import vn.com.mbbank.services.FileService;
import vn.com.mbbank.utils.ErrorApp;
import vn.com.mbbank.utils.exceptions.CustomException;
import io.minio.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);

    private static final String ROOT_FOLDER = "yyyyMMdd";

    @Autowired
    MinioClient minioClient;

    @Value("${minio.bucket}")
    String bucket;

    @Value("${common.upload.max-file-size}")
    Integer maxFileSize;

    @Override
    public void uploadFile(String filePath, byte[] file) {
        try {
            if (!Utils.checkFileValid(filePath, file, maxFileSize)) {
                throw new CustomException(ErrorApp.INVALID_FILE);
            }

            if (filePath.startsWith("/")) {
                filePath = filePath.substring(1);
            }
            createBucketIfNotExist(bucket, false);
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucket).object(filePath).stream(new ByteArrayInputStream(file), file.length, -1).build());
        } catch (Exception e) {
            LOG.error("HAS ERROR UPLOAD FILE: ", e);
        }
    }

    @Override
    public void removeFile(String filePath) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket(bucket).object(filePath).build());
        } catch (Exception e) {
            LOG.error("HAS ERROR REMOVE FILE", e);
        }
    }

    @Override
    public byte[] getFile(String filePath) throws CustomException {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucket)
                        .object(filePath)
                        .build())) {
            return IOUtils.toByteArray(stream);
        } catch (Exception e) {
            LOG.error("HAS ERROR GET FILE", e);
            throw new CustomException(ErrorApp.GET_FILE_ERROR);
        }
    }

    @Override
    public boolean copyFile(String sourceFilePath, String targetFilePath) {
        try {
            minioClient.copyObject(
                    CopyObjectArgs.builder()
                            .bucket(bucket)
                            .object(targetFilePath)
                            .source(CopySource.builder()
                                    .bucket(bucket)
                                    .object(sourceFilePath)
                                    .build())
                            .build());
            return true;
        } catch (Exception e) {
            LOG.error("Co Loi Copy File", e);
            return false;
        }
    }

    @Override
    public String getRootFolder() {
        return new SimpleDateFormat(ROOT_FOLDER).format(new Date(System.currentTimeMillis()));
    }


    private void createBucketIfNotExist(String bucketName, boolean objectLock) {
        try {
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).objectLock(objectLock).build());
            }
        } catch (Exception e) {
            LOG.error("HAS ERROR CREATE BUCKET", e);
        }
    }
}
