/*
 * Copyright (C) 2022 HRPLUS. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import vn.com.mbbank.dto.DecplineRecordsDTO;
import vn.com.mbbank.dto.EmployeesDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * Lop interface service
 * @author author
 * @since 1.0
 * @version 1.0
 */
public interface DecplineRecordsService {
    Object getListDataByEmpId(DecplineRecordsDTO dto, Authentication authentication);
    void saveData(DecplineRecordsDTO dto, Authentication authentication) throws Exception;
    ResponseEntity<Object> deleteData(Long id, Authentication authentication);
    Object getDataById(Long id, Authentication authentication);

    ResponseEntity<Object> searchDecplineRecords(EmployeesDTO dto, Authentication authentication);

    String exportTemplateImportAddDecplineRecords(Authentication authentication) throws Exception;

    ResponseEntity<Object> importAddDecplineRecords(MultipartFile files, Authentication authentication, HttpServletRequest req) throws Exception;
}
