package vn.com.mbbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.com.mbbank.dto.*;
import vn.com.mbbank.entities.*;
import vn.com.mbbank.services.CatalogsService;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.ResponseUtils;
import vn.com.mbbank.utils.Utils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Constants.REQUEST_MAPPING_PREFIX)
public class CatalogsController {

    @Autowired
    private CatalogsService catalogsService;

    @GetMapping(value = "/v1/lookup-values", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getContactInfo(Authentication authentication,
            @RequestParam(value = "typeCode") String typeCode,
            @RequestParam(value = "parentCode", required = false) String parentCode) {
        List list = catalogsService.getListDataByType(typeCode, parentCode);
        return ResponseUtils.getResponseSucessEntity(list);
    }

    @GetMapping(value = "/v1/cat-banks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCatBanks() {
        List list = catalogsService.getCatBanks();
        return ResponseUtils.getResponseSucessEntity(list);
    }

    @GetMapping(value = "/v1/contract-types", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getContractTypes() {
        List list = catalogsService.getContractTypes();
        return ResponseUtils.getResponseSucessEntity(list);
    }

    @GetMapping(value = "/v1/mp-jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMpJobs(@Valid MpJobDTO inputParam) throws Exception {
        List<MpJobsEntity> listResultEntity = catalogsService.getMpJobs(inputParam);
        List<MpJobDTO> listResultDTO = new ArrayList<>();
        for (MpJobsEntity entity : listResultEntity) {
            MpJobDTO dto = new MpJobDTO();
            Utils.copyProperties(dto, entity);
            listResultDTO.add(dto);
        }
        return ResponseUtils.getResponseSucessEntity(listResultDTO);
    }

    @GetMapping(value = "/v1/mp-projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMpProjects() throws Exception {
        List<MpProjectsEntity> listResultEntity = catalogsService.getMpProjects();
        List<MpProjectsDTO> listResultDTO = new ArrayList<>();
        for (MpProjectsEntity entity : listResultEntity) {
            MpProjectsDTO dto = new MpProjectsDTO();
            Utils.copyProperties(dto, entity);
            listResultDTO.add(dto);
        }
        return ResponseUtils.getResponseSucessEntity(listResultDTO);
    }

     @GetMapping(value = "/v1/salary-grades", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSalaryGrades() throws Exception {
        List<PrSsmSalarygradesEntity> listSalarygradesEntity = catalogsService.getSalaryGrades();
        List<SalaryGradesDTO> listResultDTO = new ArrayList<>();
        for(PrSsmSalarygradesEntity entity: listSalarygradesEntity){
            SalaryGradesDTO dto = new SalaryGradesDTO();
            dto.setSalaryGradeId(entity.getSalarygradeId());
            dto.setSalaryGradeCode(entity.getSalarygradeCode());
            dto.setSalaryGradeName(entity.getSalarygradeName());
            dto.setDisplaySeq(entity.getDisplaySeq());
            listResultDTO.add(dto);
        }
        return ResponseUtils.getResponseSucessEntity(listResultDTO);
    }

    @GetMapping(value = "/v1/salary-ranks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSalaryRanks() throws Exception {
        List<PrSsmSalaryranksEntity> listSalaryRanksEntity = catalogsService.getSalaryRanks();
        List<SalaryRanksDTO> listResultDTO = new ArrayList<>();
        for(PrSsmSalaryranksEntity entity: listSalaryRanksEntity){
            SalaryRanksDTO dto = new SalaryRanksDTO();
            dto.setSalaryRankId(entity.getSalaryrankId());
            dto.setSalaryRankCode(entity.getSalaryrankCode());
            dto.setSalaryRankName(entity.getSalaryrankName());
            dto.setDisplaySeq(entity.getDisplaySeq());
            listResultDTO.add(dto);
        }
        return ResponseUtils.getResponseSucessEntity(listResultDTO);
    }
    
    @GetMapping(value = "/v1/document-types", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDocumentTypes() {
        List list = catalogsService.getDocumentTypes();
        return ResponseUtils.getResponseSucessEntity(list);
    }
    
    /**
     * Noi cap van bang, truong dao tao
     * @return 
     */
    @GetMapping(value = "/v1/schools", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSchools() {
        List list = catalogsService.getSchools();
        return ResponseUtils.getResponseSucessEntity(list);
    }
    
    /**
     * Lay danh muc chuyen nganh
     * @return 
     */
    @GetMapping(value = "/v1/faculties", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getFaculties() {
        List list = catalogsService.getFaculties();
        return ResponseUtils.getResponseSucessEntity(list);
    }
    
    /**
     * Lay danh muc trinh do dao tao
     * @return 
     */
    @GetMapping(value = "/v1/major_levels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMajorLevels() {
        List list = catalogsService.getMajorLevels();
        return ResponseUtils.getResponseSucessEntity(list);
    }
    
     /**
     * Lay danh muc trinh do dao tao
     * @return 
     */
    @GetMapping(value = "/v2/major_levels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getV2MajorLevels() {
        List<HrMajorLevelsEntity> listMajor = catalogsService.getMajorLevels();
        List<LookupValuesDTO> listResult = new ArrayList<>();
        for (HrMajorLevelsEntity entity : listMajor) {
            LookupValuesDTO lookupDTO = new LookupValuesDTO();
            lookupDTO.setLabel(entity.getName());
            lookupDTO.setValue(entity.getMajorLevelId().toString());
            listResult.add(lookupDTO);
        }
        return ResponseUtils.getResponseSucessEntity(listResult);
    }
    
    /**
     * Lay danh muc trinh do dao tao
     * @return 
     */
    @GetMapping(value = "/v1/mp-positions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMpPositions() {
        List list = catalogsService.getMpPositions();
        return ResponseUtils.getResponseSucessEntity(list);
    }
    
    /**
     * Lay danh muc trinh do dao tao
     * @return 
     */
    @GetMapping(value = "/v2/mp-positions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getV2MpPositions() {
        List<MpPositionsEntity> listPosition = catalogsService.getMpPositions();
        List<LookupValuesDTO> listResult = new ArrayList<>();
        for (MpPositionsEntity mpPositionsEntity : listPosition) {
            LookupValuesDTO lookupDTO = new LookupValuesDTO();
            lookupDTO.setLabel(mpPositionsEntity.getPosName());
            lookupDTO.setValue(mpPositionsEntity.getPosId().toString());
            listResult.add(lookupDTO);
        }
        return ResponseUtils.getResponseSucessEntity(listResult);
    }
}
