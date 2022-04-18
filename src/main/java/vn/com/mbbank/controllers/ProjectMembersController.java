/*
 * Copyright (C) 2022 HRPLUS. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.dto.ProjectMembersDTO;
import vn.com.mbbank.entities.HrProjectMembersEntity;
import vn.com.mbbank.services.ProjectMembersService;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.ResponseUtils;
import vn.com.mbbank.utils.Utils;

@RestController
@RequestMapping(Constants.REQUEST_MAPPING_PREFIX)
public class ProjectMembersController {

    @Autowired
    private ProjectMembersService projectMembersService;

    @GetMapping(value = "/v1/project-members/employees/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getListDataByEmpId(Authentication authentication,
            @PathVariable Long employeeId,
            ProjectMembersDTO projectMembersDTO
    ) {
        projectMembersDTO.setEmployeeId(employeeId);
        Object resultObj = projectMembersService.getListDataByEmpId(projectMembersDTO, authentication);
        return ResponseUtils.getResponseSucessEntity(resultObj);
    }

    @PostMapping(value = "/v1/project-members", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> saveData(Authentication authentication,
            @RequestPart(value = "files", required = false) List<MultipartFile> files,
            @Valid @RequestPart(value = "data") ProjectMembersDTO projectMembersDTO
    ) throws Exception {
        return projectMembersService.saveData(projectMembersDTO, authentication);
    }

    @DeleteMapping(value = "/v1/project-members/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteData(Authentication authentication,
            @PathVariable Long id
    ) throws Exception {
        return projectMembersService.deleteData(id, authentication);
    }

    @GetMapping(value = "/v1/project-members/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDataById(Authentication authentication,
            @PathVariable Long id
    ) throws Exception {
        HrProjectMembersEntity entity = (HrProjectMembersEntity) projectMembersService.getDataById(id, authentication);
        if (entity == null) {
            return ResponseUtils.getResponseSucessEntity(null);
        }
        ProjectMembersDTO dto = new ProjectMembersDTO();
        Utils.copyProperties(dto, entity);
        return ResponseUtils.getResponseSucessEntity(dto);
    }

    @GetMapping(value = "/v1/project-members", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> searchProjectMembers(Authentication authentication,
            EmployeesDTO dto) throws Exception {
        return projectMembersService.searchProjectMembers(dto, authentication);
    }

    @RequestMapping(value = "/v1/project-members/export-template-add", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> exportTemplateImportAddProjectMembers(Authentication authentication) throws Exception {
        return ResponseUtils.getResponseFileEntity(projectMembersService.exportTemplateImportAddProjectMembers(authentication));
    }

    @RequestMapping(value = "/v1/project-members/import-add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> importAddProjectMembers(Authentication authentication, HttpServletRequest req,
                                                    @RequestPart(value = "file", required = true) MultipartFile file) throws Exception {
        return projectMembersService.importAddProjectMembers(file, authentication, req);
    }
}
