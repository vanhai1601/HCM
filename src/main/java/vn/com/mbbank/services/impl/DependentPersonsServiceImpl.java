/*
 * Copyright (C) 2022 HRPLUS. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import vn.com.mbbank.core.config.I18n;
import vn.com.mbbank.core.dto.response.BaseResultSelect;
import vn.com.mbbank.dto.DependentPersonsDTO;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.dto.FamilyRelationshipsDTO;
import vn.com.mbbank.entities.HrDependentPersonsEntity;
import vn.com.mbbank.entities.HrFamilyRelationshipsEntity;
import vn.com.mbbank.repositories.impl.DependentPersonsRepositoryImpl;
import vn.com.mbbank.repositories.impl.FamilyRelationshipsRepositoryImpl;
import vn.com.mbbank.repositories.jpa.HrDependentPersonsRepositoryJPA;
import vn.com.mbbank.repositories.jpa.HrFamilyRelationshipsRepositoryJPA;
import vn.com.mbbank.services.DependentPersonsService;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.ErrorApp;
import vn.com.mbbank.utils.ResponseUtils;
import vn.com.mbbank.utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Lop impl service
 *
 * @author author
 * @since 1.0
 * @version 1.0
 */
@Service
public class DependentPersonsServiceImpl implements DependentPersonsService {

    @Autowired
    private DependentPersonsRepositoryImpl dependentPersonsRepository;

    @Autowired
    private HrDependentPersonsRepositoryJPA dependentPersonsRepositoryJPA;

    @Autowired
    private FamilyRelationshipsRepositoryImpl familyRelationshipsRepositoryImpl;

    @Autowired
    private HrFamilyRelationshipsRepositoryJPA familyRelationshipsRepositoryJPA;

    @Override
    public Object getListDataByEmpId(DependentPersonsDTO dependentPersonsDTO, Authentication authentication) {
        BaseResultSelect dataResult = dependentPersonsRepository.getDependentPersons(dependentPersonsDTO);
        return dataResult;
    }

    @Override
    public ResponseEntity<Object> saveData(DependentPersonsDTO dto, Authentication authentication) throws Exception {
        boolean isConflicProcess = dependentPersonsRepository.isConflictProcess(dto);
        if (isConflicProcess) {
            String message = I18n.getMessage("dependentPersons.validate.process");
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_PARAM.getCode(), message);
        }

        HrDependentPersonsEntity entity;
        if (dto.getDependentPersonId() != null && dto.getDependentPersonId() > 0L) {
            entity = dependentPersonsRepositoryJPA.getById(dto.getDependentPersonId());
            entity.setLastUpdateDate(new Date());
            entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        } else {
            entity = new HrDependentPersonsEntity();
            entity.setCreateDate(new Date());
            entity.setCreatedBy(Utils.getUserNameLogin(authentication));
        }
        Utils.copyProperties(entity, dto);
//        if(!Utils.isNullOrEmpty(dto.getPersonalId())){
//            entity.setBookNo(null);
//            entity.setCodeNo(null);
//        } else if(!Utils.isNullOrEmpty(dto.getCodeNo())){
//            entity.setPersonalId(null);
//        }
        entity.setFlagStatus(Constants.STATUS.IS_ACTIVE);
        dependentPersonsRepositoryJPA.save(entity);
        // xu ly luu ma so thue vao thong tin than nhan
        HrFamilyRelationshipsEntity familyRelationshipsEntity = dependentPersonsRepository.get(HrFamilyRelationshipsEntity.class, entity.getFamilyRelationshipId());
        familyRelationshipsEntity.setTaxNumber(dto.getTaxNumber());
        familyRelationshipsRepositoryJPA.save(familyRelationshipsEntity);
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    public ResponseEntity<Object> deleteData(Long id, Authentication authentication) {
        Optional<HrDependentPersonsEntity> optional = dependentPersonsRepositoryJPA.findById(id);
        if (!optional.isPresent()) {
            return ResponseUtils.getResponseEntity(ErrorApp.BAD_REQUEST);
        }

        HrDependentPersonsEntity entity = optional.get();
        entity.setFlagStatus(Constants.STATUS.IS_NOT_ACTIVE);
        entity.setLastUpdateDate(new Date());
        entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        dependentPersonsRepositoryJPA.save(entity);
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    public Object getDataById(Long id, Authentication authentication) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Optional<HrDependentPersonsEntity> optional = dependentPersonsRepositoryJPA.findById(id);
        if (!optional.isPresent() || Constants.STATUS.IS_NOT_ACTIVE.equals(optional.get().getFlagStatus())) {
            return null;
        }

        DependentPersonsDTO dto = new DependentPersonsDTO();
        Utils.copyProperties(dto, optional.get());
        HrFamilyRelationshipsEntity familyRelationshipsEntity = dependentPersonsRepository.get(HrFamilyRelationshipsEntity.class, dto.getFamilyRelationshipId());
        dto.setTaxNumber(familyRelationshipsEntity.getTaxNumber());
        dto.setPersonalIdNumber(familyRelationshipsEntity.getPersonalIdNumber());
        return dto;
    }

    @Override
    public List<FamilyRelationshipsDTO> getListFamilyRelationshipsByEmpId(Long empId) {
        return familyRelationshipsRepositoryImpl.getListFamilyRelationshipsByEmpId(empId);
    }

    @Override
    public ResponseEntity<Object> searchDependentPersons(EmployeesDTO dto) {
        BaseResultSelect result = dependentPersonsRepository.searchDependentPersons(dto);
        return ResponseUtils.getResponseSucessEntity(result);
    }
}
