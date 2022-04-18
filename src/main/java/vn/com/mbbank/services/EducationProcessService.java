package vn.com.mbbank.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import vn.com.mbbank.dto.EducationProcessDTO;
import vn.com.mbbank.dto.EmployeesDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * Lop interface service
 * @author hieuhc
 * @since 1.0
 * @version 1.0
 */
public interface EducationProcessService {

    Object getListDataByEmpId(EducationProcessDTO dto, Authentication authentication);

    /**
     * save data
     * @param dto
     * @param authentication
     * @return
     * @throws Exception
     */
    ResponseEntity<Object> saveData(EducationProcessDTO dto, Authentication authentication) throws Exception;

    /**
     * Delete EducationDegree
     * @param id
     * @param authentication
     */
    void deleteData(Long id, Authentication authentication);

    /**
     * Get data by id
     * @param id
     * @param authentication
     * @return
     */
    Object getDataById(Long id, Authentication authentication) throws Exception;

    ResponseEntity<Object> searchEducationProcess(EmployeesDTO dto, Authentication authentication);

    String exportTemplateImportAddEducationProcess(Authentication authentication) throws Exception;

    ResponseEntity<Object> importAddEducationProcess(MultipartFile files, Authentication authentication, HttpServletRequest req) throws Exception;
}
