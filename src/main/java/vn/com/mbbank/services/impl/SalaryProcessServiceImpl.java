/*
 * Copyright (C) 2022 HRPLUS. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.services.impl;

import java.util.Date;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import vn.com.mbbank.core.dto.response.BaseResultSelect;
import vn.com.mbbank.dto.SalaryProcessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.com.mbbank.core.config.I18n;
import vn.com.mbbank.entities.HrSalaryProcessEntity;
import vn.com.mbbank.repositories.impl.SalaryProcessRepositoryImpl;
import vn.com.mbbank.repositories.jpa.HrSalaryProcessRepositoryJPA;
import vn.com.mbbank.utils.Utils;
import vn.com.mbbank.services.SalaryProcessService;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.ErrorApp;
import vn.com.mbbank.utils.ResponseUtils;

/**
 * Lop impl service
 *
 * @author author
 * @since 1.0
 * @version 1.0
 */
@Service
public class SalaryProcessServiceImpl implements SalaryProcessService {

    @Autowired
    private SalaryProcessRepositoryImpl salaryProcessRepositoryImpl;

    @Autowired
    private HrSalaryProcessRepositoryJPA salaryProcessRepositoryJPA;

    @Override
    public Object getListDataByEmpId(SalaryProcessDTO salaryProcessDTO, Authentication authentication) {
        BaseResultSelect dataResult = salaryProcessRepositoryImpl.getSalaryProcess(salaryProcessDTO);
        return dataResult;
    }

    @Override
    public ResponseEntity<Object> saveData(SalaryProcessDTO dto, Authentication authentication) throws Exception {
        boolean isConflicProcess = salaryProcessRepositoryImpl.isConflictProcess(dto);
        if (isConflicProcess) {
            String message = I18n.getMessage("salaryProcess.validate.process");
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_PARAM.getCode(), message);
        }

        HrSalaryProcessEntity entity;
        if (dto.getSalaryProcessId() != null && dto.getSalaryProcessId() > 0L) {
            entity = salaryProcessRepositoryJPA.getById(dto.getSalaryProcessId());
            entity.setLastUpdateDate(new Date());
            entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        } else {
            entity = new HrSalaryProcessEntity();
            entity.setCreateDate(new Date());
            entity.setCreatedBy(Utils.getUserNameLogin(authentication));
        }
        Utils.copyProperties(entity, dto);
        entity.setFlagStatus(Constants.STATUS.IS_ACTIVE);
        salaryProcessRepositoryJPA.save(entity);
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    public ResponseEntity<Object> deleteData(Long id, Authentication authentication) {
        Optional<HrSalaryProcessEntity> optional = salaryProcessRepositoryJPA.findById(id);
        if (!optional.isPresent()) {
            return ResponseUtils.getResponseEntity(ErrorApp.BAD_REQUEST);
        }

        HrSalaryProcessEntity entity = optional.get();
        entity.setFlagStatus(Constants.STATUS.IS_NOT_ACTIVE);
        entity.setLastUpdateDate(new Date());
        entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        salaryProcessRepositoryJPA.save(entity);
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    public Object getDataById(Long id, Authentication authentication) {
        Optional<HrSalaryProcessEntity> optional = salaryProcessRepositoryJPA.findById(id);
        if (!optional.isPresent() || Constants.STATUS.IS_NOT_ACTIVE.equals(optional.get().getFlagStatus())) {
            return null;
        }
        return optional.get();
    }
}
