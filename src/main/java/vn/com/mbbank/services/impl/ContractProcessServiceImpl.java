/*
 * Copyright (C) 2022 HRPLUS. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.services.impl;

import java.util.Date;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import vn.com.mbbank.core.dto.response.BaseResultSelect;
import vn.com.mbbank.dto.ContractProcessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.com.mbbank.core.config.I18n;
import vn.com.mbbank.entities.HrContractProcessEntity;
import vn.com.mbbank.repositories.impl.ContractProcessRepositoryImpl;
import vn.com.mbbank.repositories.jpa.HrContractProcessRepositoryJPA;
import vn.com.mbbank.utils.Utils;
import vn.com.mbbank.services.ContractProcessService;
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
public class ContractProcessServiceImpl implements ContractProcessService {

    @Autowired
    private ContractProcessRepositoryImpl contractProcessRepository;

    @Autowired
    private HrContractProcessRepositoryJPA contractProcessRepositoryJPA;

    @Override
    public Object getListDataByEmpId(ContractProcessDTO contractProcessDTO, Authentication authentication) {
        BaseResultSelect dataResult = contractProcessRepository.getContractProcess(contractProcessDTO);
        return dataResult;
    }

    @Override
    public ResponseEntity<Object> saveData(ContractProcessDTO dto, Authentication authentication) throws Exception {
        // validate conflic qua trinh cong tac
        boolean isConflicProcess = contractProcessRepository.isConflictProcess(dto);
        if (isConflicProcess) {
            String message = I18n.getMessage("contractProcess.validate.process");
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_PARAM.getCode(), message);
        }

        HrContractProcessEntity entity;
        if (dto.getContractProcessId() != null && dto.getContractProcessId() > 0L) {
            entity = contractProcessRepositoryJPA.getById(dto.getContractProcessId());
            entity.setLastUpdateDate(new Date());
            entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        } else {
            entity = new HrContractProcessEntity();
            entity.setCreateDate(new Date());
            entity.setCreatedBy(Utils.getUserNameLogin(authentication));
        }
        Utils.copyProperties(entity, dto);
        entity.setFlagStatus(Constants.STATUS.IS_ACTIVE);
        contractProcessRepositoryJPA.save(entity);
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    public ResponseEntity<Object> deleteData(Long id, Authentication authentication) {
        Optional<HrContractProcessEntity> optional = contractProcessRepositoryJPA.findById(id);
        if (!optional.isPresent()) {
            return ResponseUtils.getResponseEntity(ErrorApp.BAD_REQUEST);
        }

        HrContractProcessEntity entity = optional.get();
        entity.setFlagStatus(Constants.STATUS.IS_NOT_ACTIVE);
        entity.setLastUpdateDate(new Date());
        entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        contractProcessRepositoryJPA.save(entity);
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    public Object getDataById(Long id, Authentication authentication) {
        Optional<HrContractProcessEntity> optional = contractProcessRepositoryJPA.findById(id);
        if (!optional.isPresent() || Constants.STATUS.IS_NOT_ACTIVE.equals(optional.get().getFlagStatus())) {
            return null;
        }
        return optional.get();
    }
}
