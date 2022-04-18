package vn.com.mbbank.controllers;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import vn.com.mbbank.config.FileStorageConfig;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.ErrorApp;
import vn.com.mbbank.utils.ResponseUtils;
import vn.com.mbbank.utils.Utils;

/**
 *
 * @author author
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_PREFIX)
public class DownloadFileController {

    @Autowired
    private FileStorageConfig fileStorageConfig;

    @RequestMapping(value = "/v1/download/temp-file", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> downloadTempFile(Authentication authentication,
            @RequestParam(value = "fileName", required = true) String fileName) throws IOException {
        String[] str = fileName.split("_");
        String userName = Utils.getUserNameLogin(authentication);
        if (userName.equals(str[1])) {
            String filePath = fileStorageConfig.getExportFolder() + fileName;
            return ResponseUtils.getResponseFileEntity(filePath);
        } else {
            return ResponseUtils.getResponseEntity(ErrorApp.ACCESS_DENIED);
        }

    }

}
