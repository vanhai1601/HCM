/*
 * Copyright (C) 2022 HRPLUS. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import vn.com.mbbank.dto.AllowanceProcessDTO;
import vn.com.mbbank.dto.EmployeesDTO;

import javax.servlet.http.HttpServletRequest;

public interface AllowanceProcessService {

    Object getListDataByEmpId(AllowanceProcessDTO dto, Authentication authentication);

    ResponseEntity<Object> saveData(AllowanceProcessDTO dto, Authentication authentication) throws Exception;

    ResponseEntity<Object> deleteData(Long id, Authentication authentication);

    Object getDataById(Long id, Authentication authentication);

    ResponseEntity<Object> searchAllowanceProcess(EmployeesDTO dto, Authentication authentication);

    String exportTemplateImportAddAllowanceProcess(Authentication authentication) throws Exception;

    ResponseEntity<Object> importAddAllowanceProcess(MultipartFile files, Authentication authentication, HttpServletRequest req) throws Exception;

}
