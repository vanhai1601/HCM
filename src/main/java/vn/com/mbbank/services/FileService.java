package vn.com.mbbank.services;

import vn.com.mbbank.utils.exceptions.CustomException;

public interface FileService {
    void uploadFile(String filePath, byte[] file);

    void removeFile(String filePath);

    byte[] getFile(String filePath) throws CustomException;

    String getRootFolder();

    boolean copyFile(String filePath, String targetBucketName);
}
