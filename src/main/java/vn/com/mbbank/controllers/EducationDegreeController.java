package vn.com.mbbank.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.com.mbbank.dto.EducationDegreeDTO;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.services.EducationDegreeService;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.ResponseUtils;

/**
 * Lop controller
 *
 * @author hieuhc
 * @since 1.0
 * @version 1.0
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_PREFIX)
public class EducationDegreeController {

    @Autowired
    private EducationDegreeService educationDegreeService;

    @GetMapping(value = "/v1/education-degree/employees/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getListDataByEmpId(Authentication authentication,
            @PathVariable Long employeeId,
            EducationDegreeDTO educationDegreeDTO) {
        educationDegreeDTO.setEmployeeId(employeeId);
        return ResponseUtils.getResponseSucessEntity(educationDegreeService.getListDataByEmpId(educationDegreeDTO, authentication));
    }

    @PostMapping(value = "/v1/education-degree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveData(Authentication authentication,
            @Valid @RequestBody EducationDegreeDTO educationDegreeDTO) throws Exception {
        return educationDegreeService.saveData(educationDegreeDTO, authentication);
    }

    @PutMapping(value = "/v1/education-degree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateData(Authentication authentication,
            @RequestBody EducationDegreeDTO educationDegreeDTO) throws Exception {
        return educationDegreeService.saveData(educationDegreeDTO, authentication);
    }

    @DeleteMapping(value = "/v1/education-degree/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteData(Authentication authentication,
            @PathVariable Long id) {
        return educationDegreeService.deleteData(id, authentication);
    }

    @GetMapping(value = "/v1/education-degree/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDataById(Authentication authentication,
            @PathVariable Long id) throws Exception {
        return ResponseUtils.getResponseSucessEntity(educationDegreeService.getDataById(id, authentication));
    }

    @GetMapping(value = "/v1/education-degree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> searchEducationDegree(Authentication authentication,
            EmployeesDTO dto) throws Exception {
        return educationDegreeService.searchEducationDegree(dto, authentication);
    }

    @RequestMapping(value = "/v1/education-degree/export-template-add", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> exportTemplateImportAddEducationDegree(Authentication authentication) throws Exception {
        return ResponseUtils.getResponseFileEntity(educationDegreeService.exportTemplateImportAddEducationDegree(authentication));
    }

    @RequestMapping(value = "/v1/education-degree/import-add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> importAddEducationDegree(Authentication authentication, HttpServletRequest req,
                                                            @RequestPart(value = "file", required = true) MultipartFile file) throws Exception {
        return educationDegreeService.importAddEducationDegree(file, authentication, req);
    }

}
