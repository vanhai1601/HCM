/*
 * Copyright (C) 2022 HRPLUS. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import vn.com.mbbank.dto.ContractProcessDTO;

/**
 * Lop interface service
 * @author author
 * @since 1.0
 * @version 1.0
 */
public interface ContractProcessService {
    Object getListDataByEmpId(ContractProcessDTO dto, Authentication authentication);
    ResponseEntity<Object> saveData(ContractProcessDTO dto, Authentication authentication) throws Exception;
    ResponseEntity<Object> deleteData(Long id, Authentication authentication);
    Object getDataById(Long id, Authentication authentication);

}
