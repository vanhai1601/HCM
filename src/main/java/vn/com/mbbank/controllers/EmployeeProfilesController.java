package vn.com.mbbank.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vn.com.mbbank.services.EmployeeProfilesService;
import vn.com.mbbank.utils.Constants;

import javax.validation.Valid;
import org.springframework.web.multipart.MultipartFile;
import vn.com.mbbank.dto.EmployeeProfilesDTO;
import vn.com.mbbank.utils.ResponseUtils;

@RestController
@RequestMapping(Constants.REQUEST_MAPPING_PREFIX)
public class EmployeeProfilesController {

    @Autowired
    private EmployeeProfilesService employeeProfilesService;

    @GetMapping(value = "/v1/employee-profiles/employees/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getListDataByEmpId(Authentication authentication,
         @PathVariable Long employeeId,
         EmployeeProfilesDTO dto
    ) {
        dto.setEmployeeId(employeeId);
        Object resultObj = employeeProfilesService.getListDataByEmpId(dto, authentication);
        return ResponseUtils.getResponseSucessEntity(resultObj);
    }

    @PostMapping(value = "/v1/employee-profiles", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> uploadFile(Authentication authentication,
            @RequestPart(value = "files", required = false) List<MultipartFile> files,
            @Valid @RequestPart(value = "data") EmployeeProfilesDTO dto) throws Exception {
        return employeeProfilesService.saveProfiles(dto, authentication);
    }

    @DeleteMapping(value = "/v1/employee-profiles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteProfiles(Authentication authentication,
            @PathVariable Long id) throws Exception {
        return employeeProfilesService.deleteProfiles(id, authentication);
    }
}
