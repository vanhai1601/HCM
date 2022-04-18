package vn.com.mbbank.services.impl;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import vn.com.mbbank.config.FileStorageConfig;
import vn.com.mbbank.core.utils.AposeCellExport;
import vn.com.mbbank.core.utils.FileUtils;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.dto.UserBookmarkOptionsDTO;
import vn.com.mbbank.services.CommonUtilsService;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.Utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author author
 */
@Service
public class CommonUtilsServiceImpl implements CommonUtilsService {

    @Autowired
    private FileStorageConfig fileStorageConfig;

    @Override
    public String getFilePathExport(Authentication authentication, String fileName) {
        String userName = Utils.getUserNameLogin(authentication);
        return fileStorageConfig.getExportFolder() + Utils.convertDateTimeToString(new Date()) + "_" + userName + "_" + fileName;
    }

    @Override
    public ResponseEntity<Object> responsePreviewXlsx(Authentication authentication, String templateFile) throws IOException, Exception {
        AposeCellExport export = new AposeCellExport(templateFile);
        String tempFile = getFilePathExport(authentication, "_temp.html");
        export.saveHtmlFile(tempFile);

        File file = new File(tempFile);
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        HttpHeaders headers = new HttpHeaders();
        String encodedFileName = URLEncoder.encode(tempFile, StandardCharsets.UTF_8.name()).replace("+", "%20");
        headers.add("Content-Disposition", String.format("attachment; inline; filename*=UTF-8''%1$s; filename=%1$s", encodedFileName));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @Override
    public ResponseEntity<Object> responsePreviewDocx(Authentication authentication, String templateFile, HttpServletRequest req) throws IOException, Exception {
        String tempFile = getFilePathExport(authentication, "_temp.pdf");
        FileUtils.convertDocToPDF(templateFile, tempFile, req);
        File file = new File(tempFile);
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        HttpHeaders headers = new HttpHeaders();
        String encodedFileName = URLEncoder.encode(tempFile, StandardCharsets.UTF_8.name()).replace("+", "%20");
        headers.add("Content-Disposition", String.format("attachment; inline; filename*=UTF-8''%1$s; filename=%1$s", encodedFileName));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
