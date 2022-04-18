/*
 * Copyright (C) 2022 HRPLUS. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.services.impl;

import java.util.Date;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import vn.com.mbbank.core.dto.response.BaseResultSelect;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.dto.RewardRecordsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.com.mbbank.entities.HrRewardRecordsEntity;
import vn.com.mbbank.repositories.impl.RewardRecordsRepositoryImpl;
import vn.com.mbbank.repositories.jpa.HrRewardRecordsRepositoryJPA;
import vn.com.mbbank.utils.Utils;
import vn.com.mbbank.services.RewardRecordsService;
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
public class RewardRecordsServiceImpl implements RewardRecordsService {

    @Autowired
    private RewardRecordsRepositoryImpl rewardRecordsRepository;

    @Autowired
    private HrRewardRecordsRepositoryJPA rewardRecordsRepositoryJPA;

    @Override
    public Object getListDataByEmpId(RewardRecordsDTO rewardRecordsDTO, Authentication authentication) {
        BaseResultSelect dataResult = rewardRecordsRepository.getRewardRecords(rewardRecordsDTO);
        return dataResult;
    }

    @Override
    public void saveData(RewardRecordsDTO dto, Authentication authentication) throws Exception {
        HrRewardRecordsEntity entity;
        if (dto.getRewardRecordId() != null && dto.getRewardRecordId() > 0L) {
            entity = rewardRecordsRepositoryJPA.getById(dto.getRewardRecordId());
            entity.setLastUpdateDate(new Date());
            entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        } else {
            entity = new HrRewardRecordsEntity();
            entity.setCreateDate(new Date());
            entity.setCreatedBy(Utils.getUserNameLogin(authentication));
        }
        Utils.copyProperties(entity, dto);
        entity.setFlagStatus(Constants.STATUS.IS_ACTIVE);
        rewardRecordsRepositoryJPA.save(entity);
    }

    @Override
    public ResponseEntity<Object> deleteData(Long id, Authentication authentication) {
        Optional<HrRewardRecordsEntity> optional = rewardRecordsRepositoryJPA.findById(id);
        if (!optional.isPresent()) {
            return ResponseUtils.getResponseEntity(ErrorApp.BAD_REQUEST);
        }

        HrRewardRecordsEntity entity = optional.get();
        entity.setFlagStatus(Constants.STATUS.IS_NOT_ACTIVE);
        entity.setLastUpdateDate(new Date());
        entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        rewardRecordsRepositoryJPA.save(entity);
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    public Object getDataById(Long id, Authentication authentication) {
        Optional<HrRewardRecordsEntity> optional = rewardRecordsRepositoryJPA.findById(id);
        if (!optional.isPresent() || Constants.STATUS.IS_NOT_ACTIVE.equals(optional.get().getFlagStatus())) {
            return null;
        }
        return optional.get();
    }

    @Override
    public ResponseEntity<Object> searchRewardRecords(EmployeesDTO dto, Authentication authentication) {
        BaseResultSelect result = rewardRecordsRepository.searchRewardRecords(dto);
        return ResponseUtils.getResponseSucessEntity(result);
    }
}
