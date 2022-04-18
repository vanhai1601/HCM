/*
 * Copyright (C) 2022 HRPLUS. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import vn.com.mbbank.dto.DependentPersonsDTO;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.dto.FamilyRelationshipsDTO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Lop interface service
 *
 * @author author
 * @since 1.0
 * @version 1.0
 */
public interface DependentPersonsService {

    Object getListDataByEmpId(DependentPersonsDTO dto, Authentication authentication);

    ResponseEntity<Object> saveData(DependentPersonsDTO dto, Authentication authentication) throws Exception;

    ResponseEntity<Object> deleteData(Long id, Authentication authentication);

    Object getDataById(Long id, Authentication authentication) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;

    List<FamilyRelationshipsDTO> getListFamilyRelationshipsByEmpId(Long empId);

    ResponseEntity<Object> searchDependentPersons(EmployeesDTO dto) ;

}
