package vn.com.mbbank.utils;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import vn.com.mbbank.core.dto.response.BaseResponse;

/**
 *
 * @author Admin
 */
public class ResponseUtils {

    public static ResponseEntity<Object> getResponseSucessEntity(Object itemObject) {
        BaseResponse baseResponse = new BaseResponse();
        if (itemObject != null) {
            baseResponse.setData(itemObject);
        }
        baseResponse.setCode(ErrorApp.SUCCESS.getCode());
        baseResponse.setMessage(ErrorApp.SUCCESS.getDescription());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    
    public static ResponseEntity<Object> getResponseEntity(ErrorApp errorApp, Object itemObject) {
        BaseResponse baseResponse = new BaseResponse();
        if (itemObject != null) {
            baseResponse.setData(itemObject);
        }
        baseResponse.setCode(errorApp.getCode());
        baseResponse.setMessage(errorApp.getDescription());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    public static ResponseEntity<Object> getResponseEntity(int code, String description, Object itemObject) {
        BaseResponse baseResponse = new BaseResponse();
        if (itemObject != null) {
            baseResponse.setData(itemObject);
        }
        baseResponse.setCode(code);
        baseResponse.setMessage(description);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    public static ResponseEntity<Object> getResponseEntity(int code, String description) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(code);
        baseResponse.setMessage(description);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    public static ResponseEntity<Object> getResponseEntity(ErrorApp errorApp) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(errorApp.getCode());
        baseResponse.setMessage(errorApp.getDescription());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    public static ResponseEntity<Object> getResponseEntity(ErrorApp errorApp, Object itemObject, HttpStatus httpStatus) {
        BaseResponse baseResponse = new BaseResponse();
        if (itemObject != null) {
            baseResponse.setData(itemObject);
        }
        baseResponse.setCode(errorApp.getCode());
        baseResponse.setMessage(errorApp.getDescription());
        return new ResponseEntity<>(baseResponse, httpStatus);
    }

    public static ResponseEntity<Object> getResponseEntity(int code, String description, Object itemObject, HttpStatus httpStatus) {
        BaseResponse baseResponse = new BaseResponse();
        if (itemObject != null) {
            baseResponse.setData(itemObject);
        }
        baseResponse.setCode(code);
        baseResponse.setMessage(description);
        return new ResponseEntity<>(baseResponse, httpStatus);
    }

    public static ResponseEntity<Object> getResponseEntity(int code, String description, HttpStatus httpStatus) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(code);
        baseResponse.setMessage(description);
        return new ResponseEntity<>(baseResponse, httpStatus);
    }

    public static ResponseEntity<Object> getResponseEntity(ErrorApp errorApp, HttpStatus httpStatus) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(errorApp.getCode());
        baseResponse.setMessage(errorApp.getDescription());
        return new ResponseEntity<>(baseResponse, httpStatus);
    }
    
    public static ResponseEntity<Object> getResponseFileEntity(String filePath) throws IOException {
        File file = new File(filePath);
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        HttpHeaders headers = new HttpHeaders();
        String encodedFileName = URLEncoder.encode(filePath, StandardCharsets.UTF_8.name()).replace("+", "%20");
        headers.add("Content-Disposition", String.format("attachment; inline; filename*=UTF-8''%1$s; filename=%1$s", encodedFileName));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
    
    public static ResponseEntity<Object> getResponseFileEntity(byte[] fileBytes, String fileName) throws IOException {
        ByteArrayResource resource = new ByteArrayResource(fileBytes);
        HttpHeaders headers = new HttpHeaders();
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()).replace("+", "%20");
        headers.add("Content-Disposition", String.format("attachment; inline; filename*=UTF-8''%1$s; filename=%1$s", encodedFileName));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
