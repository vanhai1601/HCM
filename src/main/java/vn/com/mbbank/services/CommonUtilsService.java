
package vn.com.mbbank.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import vn.com.mbbank.dto.EmployeesDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 * @author author
 */
public interface CommonUtilsService {
    
    String getFilePathExport(Authentication authentication, String fileName);

    ResponseEntity<Object> responsePreviewXlsx(Authentication authentication, String templateFile) throws IOException, Exception;

    ResponseEntity<Object> responsePreviewDocx(Authentication authentication, String templateFile, HttpServletRequest req) throws IOException, Exception;

}
