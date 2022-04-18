/*
 * Copyright (C) 2022 HRPLUS. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.dto.WorkedOutsidesDTO;

/**
 * Lop interface service
 *
 * @author author
 * @since 1.0
 * @version 1.0
 */
public interface WorkedOutsidesService {

    Object getListDataByEmpId(WorkedOutsidesDTO dto, Authentication authentication);

    void saveData(WorkedOutsidesDTO dto, Authentication authentication) throws Exception;

    ResponseEntity<Object> deleteData(Long id, Authentication authentication);

    Object getDataById(Long id, Authentication authentication);

    ResponseEntity<Object> searchWorkedOutsides(EmployeesDTO dto) ;

}
