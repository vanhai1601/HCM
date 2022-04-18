
package vn.com.mbbank.services;

import java.util.List;

import vn.com.mbbank.dto.LookupValuesDTO;
import vn.com.mbbank.dto.MpJobDTO;
import vn.com.mbbank.entities.*;

/**
 *
 * @author Admin
 */
public interface CatalogsService {
    List<LookupValuesDTO> getListDataByType(String typeCode, String parentCode);
    List<CatBanksEntity> getCatBanks();
    List<HrContractTypesEntity> getContractTypes();
    List<MpJobsEntity> getMpJobs(MpJobDTO inputParam);
    List<MpProjectsEntity> getMpProjects();
    List<PrSsmSalarygradesEntity> getSalaryGrades();
    List<PrSsmSalaryranksEntity> getSalaryRanks();
    List<HrDocumentTypesEntity> getDocumentTypes();
    List<HrSchoolsEntity> getSchools();
    List<HrFacultiesEntity> getFaculties();
    List<HrMajorLevelsEntity> getMajorLevels();
    List<MpPositionsEntity> getMpPositions();
}
