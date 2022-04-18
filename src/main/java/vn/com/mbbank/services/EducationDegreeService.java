package vn.com.mbbank.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import vn.com.mbbank.dto.EducationDegreeDTO;
import vn.com.mbbank.dto.EmployeesDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * Lop interface service
 * @author hieuhc
 * @since 1.0
 * @version 1.0
 */

public interface EducationDegreeService {
    Object getListDataByEmpId(EducationDegreeDTO dto, Authentication authentication);

    /**
     * save data
     * @param dto
     * @param authentication
     * @return
     * @throws Exception
     */
    ResponseEntity<Object> saveData(EducationDegreeDTO dto, Authentication authentication) throws Exception;

    /**
     * Delete EducationDegree
     * @param id
     * @param authentication
     * @return 
     */
    ResponseEntity<Object> deleteData(Long id, Authentication authentication);

    /**
     * Get data by id
     * @param id
     * @param authentication
     * @return
     * @throws java.lang.Exception
     */
    Object getDataById(Long id, Authentication authentication) throws Exception;

    ResponseEntity<Object> searchEducationDegree(EmployeesDTO dto, Authentication authentication);

    String exportTemplateImportAddEducationDegree(Authentication authentication) throws Exception;

    ResponseEntity<Object> importAddEducationDegree(MultipartFile files, Authentication authentication, HttpServletRequest req) throws Exception;
}
