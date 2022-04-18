/*
 * Copyright (C) 2022 HRPLUS. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import vn.com.mbbank.core.dto.response.BaseResultSelect;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.dto.WorkedOutsidesDTO;
import vn.com.mbbank.entities.HrWorkedOutsidesEntity;
import vn.com.mbbank.repositories.impl.WorkedOutsidesRepositoryImpl;
import vn.com.mbbank.repositories.jpa.HrWorkedOutsidesRepositoryJPA;
import vn.com.mbbank.services.WorkedOutsidesService;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.ErrorApp;
import vn.com.mbbank.utils.ResponseUtils;
import vn.com.mbbank.utils.Utils;

import java.util.Date;
import java.util.Optional;

/**
 * Lop impl service
 *
 * @author author
 * @since 1.0
 * @version 1.0
 */
@Service
public class WorkedOutsidesServiceImpl implements WorkedOutsidesService {

    @Autowired
    private WorkedOutsidesRepositoryImpl workedOutsidesRepository;

    @Autowired
    private HrWorkedOutsidesRepositoryJPA workedOutsidesRepositoryJPA;

    @Override
    public Object getListDataByEmpId(WorkedOutsidesDTO workedOutsidesDTO, Authentication authentication) {
        BaseResultSelect dataResult = workedOutsidesRepository.getWorkedOutsides(workedOutsidesDTO);
        return dataResult;
    }

    @Override
    public void saveData(WorkedOutsidesDTO dto, Authentication authentication) throws Exception {
        HrWorkedOutsidesEntity entity;
        if (dto.getWorkedOutsideId() != null && dto.getWorkedOutsideId() > 0L) {
            entity = workedOutsidesRepositoryJPA.getById(dto.getWorkedOutsideId());
            entity.setLastUpdateDate(new Date());
            entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        } else {
            entity = new HrWorkedOutsidesEntity();
            entity.setCreateDate(new Date());
            entity.setCreatedBy(Utils.getUserNameLogin(authentication));
        }
        Utils.copyProperties(entity, dto);
        entity.setFlagStatus(Constants.STATUS.IS_ACTIVE);
        workedOutsidesRepositoryJPA.save(entity);
    }

    @Override
    public ResponseEntity<Object> deleteData(Long id, Authentication authentication) {
        Optional<HrWorkedOutsidesEntity> optional = workedOutsidesRepositoryJPA.findById(id);
        if (!optional.isPresent()) {
            return ResponseUtils.getResponseEntity(ErrorApp.BAD_REQUEST);
        }

        HrWorkedOutsidesEntity entity = optional.get();
        entity.setFlagStatus(Constants.STATUS.IS_NOT_ACTIVE);
        entity.setLastUpdateDate(new Date());
        entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        workedOutsidesRepositoryJPA.save(entity);
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    public Object getDataById(Long id, Authentication authentication) {
        Optional<HrWorkedOutsidesEntity> optional = workedOutsidesRepositoryJPA.findById(id);
        if (!optional.isPresent() || Constants.STATUS.IS_NOT_ACTIVE.equals(optional.get().getFlagStatus())) {
            return null;
        }
        return optional.get();
    }

    @Override
    public ResponseEntity<Object> searchWorkedOutsides(EmployeesDTO dto) {
        BaseResultSelect result = workedOutsidesRepository.searchWorkedOutsides(dto);
        return ResponseUtils.getResponseSucessEntity(result);
    }
}
