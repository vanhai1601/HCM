package vn.com.mbbank.services.impl;

import java.util.Date;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import vn.com.mbbank.core.dto.response.BaseResultSelect;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.dto.FamilyRelationshipsDTO;
import vn.com.mbbank.services.FamilyRelationshipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import vn.com.mbbank.core.config.I18n;
import vn.com.mbbank.core.dto.response.BaseResultSelect;
import vn.com.mbbank.dto.FamilyRelationshipsDTO;
import vn.com.mbbank.entities.HrFamilyRelationshipsEntity;
import vn.com.mbbank.repositories.impl.FamilyRelationshipsRepositoryImpl;
import vn.com.mbbank.repositories.jpa.HrFamilyRelationshipsRepositoryJPA;
import vn.com.mbbank.services.FamilyRelationshipsService;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.ErrorApp;
import vn.com.mbbank.utils.ResponseUtils;
import vn.com.mbbank.utils.Utils;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class FamilyRelationshipsServiceImpl implements FamilyRelationshipsService {

    @Autowired
    private FamilyRelationshipsRepositoryImpl familyRelationshipsRepositoryImpl;

    @Autowired
    private HrFamilyRelationshipsRepositoryJPA familyRelationshipsRepositoryJPA;

    @Override
    public Object getListDataByEmpId(FamilyRelationshipsDTO familyRelationshipsDTO, Authentication authentication) {
        BaseResultSelect dataResult = familyRelationshipsRepositoryImpl.getListDataByEmpId(familyRelationshipsDTO);
        return dataResult;
    }

    @Override
    public ResponseEntity<Object> saveData(FamilyRelationshipsDTO dto, Authentication authentication) throws Exception {
        if(dto.getIsHouseholdOwner() != null && dto.getIsHouseholdOwner().equals(1L)){
            boolean isDuplicate = familyRelationshipsRepositoryImpl.duplicate(HrFamilyRelationshipsEntity.class, dto.getFamilyRelationshipId(), "employeeId", dto.getEmployeeId(), "isHouseholdOwner", 1);
            if(isDuplicate) {
                String message = I18n.getMessage("familyRelationship.validate.isHeadHouse.invalid");
                return ResponseUtils.getResponseEntity(ErrorApp.INVALID_PARAM.getCode(), message);
            }
        }

        HrFamilyRelationshipsEntity entity;
        if(dto.getFamilyRelationshipId() != null && dto.getFamilyRelationshipId() > 0L){
            entity = familyRelationshipsRepositoryJPA.getById(dto.getFamilyRelationshipId());
            entity.setLastUpdateDate(new Date());
            entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        } else {
            entity = new HrFamilyRelationshipsEntity();
            entity.setCreateDate(new Date());
            entity.setCreatedBy(Utils.getUserNameLogin(authentication));
        }
        Utils.copyProperties(entity, dto);
        if(entity.getDateOfBirth() != null){
            entity.setDayOfBirth(Long.valueOf(Utils.convertDateToString(entity.getDateOfBirth(), "dd")));
            entity.setMonthOfBirth(Long.valueOf(Utils.convertDateToString(entity.getDateOfBirth(), "MM")));
            entity.setYearOfBirth(Long.valueOf(Utils.convertDateToString(entity.getDateOfBirth(), "yyyy")));
        }
        entity.setFlagStatus(Constants.STATUS.IS_ACTIVE);
        familyRelationshipsRepositoryJPA.save(entity);
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    public ResponseEntity<Object> deleteData(Long id, Authentication authentication) {
        Optional<HrFamilyRelationshipsEntity> optional = familyRelationshipsRepositoryJPA.findById(id);
        if (!optional.isPresent()) {
            return ResponseUtils.getResponseEntity(ErrorApp.BAD_REQUEST);
        }

        HrFamilyRelationshipsEntity entity = optional.get();
        entity.setFlagStatus(Constants.STATUS.IS_NOT_ACTIVE);
        entity.setLastUpdateDate(new Date());
        entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        familyRelationshipsRepositoryJPA.save(entity);
        return ResponseUtils.getResponseSucessEntity(null);
    }
    
    @Override
    public Object getDataById(Long id, Authentication authentication) throws Exception {
        Optional<HrFamilyRelationshipsEntity> optional = familyRelationshipsRepositoryJPA.findById(id);
        if (!optional.isPresent() || Constants.STATUS.IS_NOT_ACTIVE.equals(optional.get().getFlagStatus())) {
            return null;
        }
        FamilyRelationshipsDTO dto = new FamilyRelationshipsDTO();
        Utils.copyProperties(dto, optional.get());
        return dto;
    }

    @Override
    public ResponseEntity<Object> searchFamilyRelationships(EmployeesDTO dto) {
        BaseResultSelect result = familyRelationshipsRepositoryImpl.searchFamilyRelationships(dto);
        return ResponseUtils.getResponseSucessEntity(result);
    }
}
