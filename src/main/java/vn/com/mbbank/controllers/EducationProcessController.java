package vn.com.mbbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.com.mbbank.dto.EducationProcessDTO;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.services.EducationProcessService;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.ResponseUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Lop controller
 *
 * @author hieuhc
 * @since 1.0
 * @version 1.0
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_PREFIX)
public class EducationProcessController {

    @Autowired
    private EducationProcessService educationProcessService;

    @GetMapping(value = "/v1/education-process/employees/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getListDataByEmpId(Authentication authentication,
            @PathVariable Long employeeId,
            EducationProcessDTO educationProcessDTO) {
        educationProcessDTO.setEmployeeId(employeeId);
        return ResponseUtils.getResponseSucessEntity(educationProcessService.getListDataByEmpId(educationProcessDTO, authentication));
    }

    @PostMapping(value = "/v1/education-process", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveData(Authentication authentication,
            @Valid @RequestBody EducationProcessDTO educationProcessDTO) throws Exception {
        return educationProcessService.saveData(educationProcessDTO, authentication);
    }

    @PutMapping(value = "/v1/education-process", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateData(Authentication authentication,
            @RequestBody EducationProcessDTO educationProcessDTO) throws Exception {
        return educationProcessService.saveData(educationProcessDTO, authentication);
    }

    @DeleteMapping(value = "/v1/education-process/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteData(Authentication authentication,
            @PathVariable Long id) {
        educationProcessService.deleteData(id, authentication);
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @GetMapping(value = "/v1/education-process/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDataById(Authentication authentication,
            @PathVariable Long id) throws Exception {
        return ResponseUtils.getResponseSucessEntity(educationProcessService.getDataById(id, authentication));
    }

    @GetMapping(value = "/v1/education-process", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> searchEducationProcess(Authentication authentication,
            EmployeesDTO dto) throws Exception {
        return educationProcessService.searchEducationProcess(dto, authentication);
    }

    @RequestMapping(value = "/v1/education-process/export-template-add", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> exportTemplateImportAddEducationProcess(Authentication authentication) throws Exception {
        return ResponseUtils.getResponseFileEntity(educationProcessService.exportTemplateImportAddEducationProcess(authentication));
    }

    @RequestMapping(value = "/v1/education-process/import-add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> importAddEducationProcess(Authentication authentication, HttpServletRequest req,
                                                            @RequestPart(value = "file", required = true) MultipartFile file) throws Exception {
        return educationProcessService.importAddEducationProcess(file, authentication, req);
    }
}
