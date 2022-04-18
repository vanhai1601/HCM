/*
 * Copyright (C) 2022 HRPLUS. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import vn.com.mbbank.core.dto.response.BaseResultSelect;
import vn.com.mbbank.dto.WorkProcessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.com.mbbank.core.config.I18n;
import vn.com.mbbank.dto.ManagerProcessDTO;
import vn.com.mbbank.entities.HrEmployeesEntity;
import vn.com.mbbank.entities.HrManagerProcessEntity;
import vn.com.mbbank.entities.HrWorkProcessEntity;
import vn.com.mbbank.entities.MpOrganizationsEntity;
import vn.com.mbbank.repositories.impl.WorkProcessRepositoryImpl;
import vn.com.mbbank.repositories.jpa.HrEmployeesRepositoryJPA;
import vn.com.mbbank.repositories.jpa.HrManagerProcessRepositoryJPA;
import vn.com.mbbank.repositories.jpa.HrWorkProcessRepositoryJPA;
import vn.com.mbbank.utils.Utils;
import vn.com.mbbank.services.WorkProcessService;
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
public class WorkProcessServiceImpl implements WorkProcessService {

    @Autowired
    private WorkProcessRepositoryImpl workProcessRepository;

    @Autowired
    private HrWorkProcessRepositoryJPA workProcessRepositoryJPA;

    @Autowired
    private HrManagerProcessRepositoryJPA managerProcessRepositoryJPA;

    @Autowired
    private HrEmployeesRepositoryJPA employeesRepositoryJPA;

    @Override
    public Object getListDataByEmpId(WorkProcessDTO workProcessDTO, Authentication authentication) {
        BaseResultSelect dataResult = workProcessRepository.getWorkProcess(workProcessDTO);
        return dataResult;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> saveData(WorkProcessDTO dto, Authentication authentication) throws Exception {
        // validate conflic qua trinh cong tac
        boolean isConflicProcess = workProcessRepository.isConflictProcess(dto);
        if (isConflicProcess) {
            String message = I18n.getMessage("workProcess.validate.process");
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_PARAM.getCode(), message);
        }

        // validate qua trinh quan lý truc tiep
        int sizeManager = dto.getListManager().size();
        for (int i = 0; i < sizeManager - 1; i++) {
            ManagerProcessDTO managerProcessDTO = dto.getListManager().get(i);
            if (managerProcessDTO.getFromDate().before(dto.getFromDate())) {
                String message = I18n.getMessage("workProcess.validate.process");
                return ResponseUtils.getResponseEntity(ErrorApp.INVALID_PARAM.getCode(), message);
            }

            for (int j = i + 1; j < sizeManager; j++) {
                if (dto.getListManager().get(i).getFromDate().compareTo(dto.getListManager().get(j).getFromDate()) == 0) {
                    String message = I18n.getMessage("managerProcess.validate.process");
                    return ResponseUtils.getResponseEntity(ErrorApp.INVALID_PARAM.getCode(), message);
                }
            }
        }

        // validate bat buoc phai chon nut la nho nhat
        List<MpOrganizationsEntity> listOrg = workProcessRepository.findByProperties(MpOrganizationsEntity.class, "parentId", dto.getOrganizationId(), "flagStatus", Constants.STATUS.IS_ACTIVE);
        if(listOrg != null && !listOrg.isEmpty()){
            String message = I18n.getMessage("workProcess.validate.id.isSmallest");
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_PARAM.getCode(), message);
        }

        // remove
        workProcessRepository.deleteManagerProcessByEmpId(dto.getEmployeeId(), Utils.getUserNameLogin(authentication));
        for (ManagerProcessDTO managerProcessDTO : dto.getListManager()) {
            HrManagerProcessEntity entity = new HrManagerProcessEntity();
            Utils.copyProperties(entity, managerProcessDTO);
            entity.setEmployeeId(dto.getEmployeeId());
            entity.setFlagStatus(Constants.STATUS.IS_ACTIVE);
            managerProcessRepositoryJPA.save(entity);
        }

        HrWorkProcessEntity entity;
        if (dto.getWorkProcessId() != null && dto.getWorkProcessId() > 0L) {
            entity = workProcessRepositoryJPA.getById(dto.getWorkProcessId());
            if (entity == null || Constants.STATUS.IS_NOT_ACTIVE.equals(entity.getFlagStatus())) {
                String message = I18n.getMessage("error.data.row.not.exists");
                return ResponseUtils.getResponseEntity(ErrorApp.INVALID_PARAM.getCode(), message);
            }
        } else {
            entity = new HrWorkProcessEntity();
        }
        Date currentDate = new Date();
        Utils.copyProperties(entity, dto);
        if (dto.getWorkProcessId() != null && dto.getWorkProcessId() > 0L) {
            entity.setLastUpdateDate(currentDate);
            entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        } else {
            entity.setCreateDate(currentDate);
            entity.setCreatedBy(Utils.getUserNameLogin(authentication));
        }
        entity.setFlagStatus(Constants.STATUS.IS_ACTIVE);
        workProcessRepositoryJPA.save(entity);

        // update employee neu qua trinh co hieu luc so voi ngay hien tai
        Date toDate = Utils.NVL(entity.getToDate(), currentDate);
        if(Utils.compareDate(entity.getFromDate(), currentDate, true) && Utils.compareDate(currentDate, toDate, true)){
            HrEmployeesEntity employeesEntity = workProcessRepository.get(HrEmployeesEntity.class, entity.getEmployeeId());
            employeesEntity.setOrganizationId(entity.getOrganizationId());
            employeesEntity.setPositionId(entity.getPositionId());
            employeesEntity.setEmpTypeCode(entity.getEmpTypeCode());
            employeesEntity.setLastUpdateDate(currentDate);
            employeesEntity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
            employeesRepositoryJPA.save(employeesEntity);
        }
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    public ResponseEntity<Object> deleteData(Long id, Authentication authentication) {
        Optional<HrWorkProcessEntity> optional = workProcessRepositoryJPA.findById(id);
        if (!optional.isPresent()) {
            return ResponseUtils.getResponseEntity(ErrorApp.BAD_REQUEST);
        }

        HrWorkProcessEntity entity = optional.get();
        entity.setFlagStatus(Constants.STATUS.IS_NOT_ACTIVE);
        entity.setLastUpdateDate(new Date());
        entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        workProcessRepositoryJPA.save(entity);
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    public WorkProcessDTO getDataById(Long id, Authentication authentication) throws Exception {
        WorkProcessDTO workProcess = (WorkProcessDTO) workProcessRepository.getWorkProcessById(id);
        if (workProcess == null) {
            return null;
        }
        WorkProcessDTO workProcessDTO = new WorkProcessDTO();
        Utils.copyProperties(workProcessDTO, workProcess);
        workProcessDTO.setListManager(workProcessRepository.getManagerProcess(workProcessDTO.getEmployeeId(), workProcessDTO.getFromDate(), workProcessDTO.getToDate()));
        return workProcessDTO;
    }
}
