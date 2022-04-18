/*
 * Copyright (C) 2022 HRPLUS. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.dto.ProjectMembersDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * Lop interface service
 *
 * @author author
 * @since 1.0
 * @version 1.0
 */
public interface ProjectMembersService {

    Object getListDataByEmpId(ProjectMembersDTO dto, Authentication authentication);

    ResponseEntity<Object> saveData(ProjectMembersDTO dto, Authentication authentication) throws Exception;

    ResponseEntity<Object> deleteData(Long id, Authentication authentication);

    Object getDataById(Long id, Authentication authentication);

    ResponseEntity<Object> searchProjectMembers(EmployeesDTO dto, Authentication authentication);

    String exportTemplateImportAddProjectMembers(Authentication authentication) throws Exception;

    ResponseEntity<Object> importAddProjectMembers(MultipartFile files, Authentication authentication, HttpServletRequest req) throws Exception;

}
