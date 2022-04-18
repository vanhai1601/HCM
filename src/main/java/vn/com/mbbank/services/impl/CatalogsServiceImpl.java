
package vn.com.mbbank.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.mbbank.dto.LookupValuesDTO;
import vn.com.mbbank.dto.MpJobDTO;
import vn.com.mbbank.entities.*;
import vn.com.mbbank.repositories.impl.LookupValuesRepositoryImpl;
import vn.com.mbbank.services.CatalogsService;

/**
 *
 * @author Admin
 */
@Service
public class CatalogsServiceImpl implements CatalogsService {
    
    @Autowired
    private LookupValuesRepositoryImpl lookupValuesRepository;

//    @Autowired
//    private Lookupvalu

    @Override
    public List<LookupValuesDTO> getListDataByType(String typeCode, String parentCode) {
        return lookupValuesRepository.getListDataByType(typeCode, parentCode);
    }

    @Override
    public List<CatBanksEntity> getCatBanks() {
        return lookupValuesRepository.findAll(CatBanksEntity.class);
    }

    @Override
    public List<HrContractTypesEntity> getContractTypes() {
        return lookupValuesRepository.findAll(HrContractTypesEntity.class);
    }

    @Override
    public List<MpJobsEntity> getMpJobs(MpJobDTO inputParam) {
        return lookupValuesRepository.findByProperties(MpJobsEntity.class, "jobType", inputParam.getJobType());
    }

    @Override
    public List<MpProjectsEntity> getMpProjects() {
        return lookupValuesRepository.findAll(MpProjectsEntity.class);
    }

    @Override
    public List<PrSsmSalarygradesEntity> getSalaryGrades() {
        return lookupValuesRepository.findAll(PrSsmSalarygradesEntity.class);
    }

    @Override
    public List<PrSsmSalaryranksEntity> getSalaryRanks() {
        return lookupValuesRepository.findAll(PrSsmSalaryranksEntity.class);
    }

    @Override
    public List<HrDocumentTypesEntity> getDocumentTypes() {
        return lookupValuesRepository.findAll(HrDocumentTypesEntity.class);
    }

    @Override
    public List<HrSchoolsEntity> getSchools() {
        return lookupValuesRepository.findAll(HrSchoolsEntity.class);
    }

    @Override
    public List<HrFacultiesEntity> getFaculties() {
        return lookupValuesRepository.findAll(HrFacultiesEntity.class);
    }

    @Override
    public List<HrMajorLevelsEntity> getMajorLevels() {
        return lookupValuesRepository.findAll(HrMajorLevelsEntity.class);
    }

    @Override
    public List<MpPositionsEntity> getMpPositions() {
        return lookupValuesRepository.findAll(MpPositionsEntity.class);
    }

}
