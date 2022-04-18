package vn.com.mbbank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vn.com.mbbank.config.FileStorageConfig;
import vn.com.mbbank.core.config.I18n;
import vn.com.mbbank.core.dto.response.BaseResultSelect;
import vn.com.mbbank.core.utils.DynamicExport;
import vn.com.mbbank.core.utils.ImportExcel;
import vn.com.mbbank.dto.EducationDegreeDTO;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.dto.ImportResultDTO;
import vn.com.mbbank.entities.*;
import vn.com.mbbank.repositories.impl.EducationDegreeRepositoryImpl;
import vn.com.mbbank.repositories.impl.LookupValuesRepositoryImpl;
import vn.com.mbbank.repositories.jpa.HrEducationDegreeRepositoryJPA;
import vn.com.mbbank.services.CommonUtilsService;
import vn.com.mbbank.services.EducationDegreeService;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.ErrorApp;
import vn.com.mbbank.utils.ResponseUtils;
import vn.com.mbbank.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Lop impl service
 * @author hieuhc
 * @since 1.0
 * @version 1.0
 */
@Service
public class EducationDegreeServiceImpl implements EducationDegreeService {
    @Autowired
    private HrEducationDegreeRepositoryJPA educationDegreeRepositoryJPA;

    @Autowired
    private EducationDegreeRepositoryImpl educationDegreeRepository;

    @Autowired
    private CommonUtilsService commonUtilsService;

    @Autowired
    private FileStorageConfig fileStorageConfig;

    @Autowired
    private LookupValuesRepositoryImpl lookupValuesRepositoryImpl;

    @Override
    public Object getListDataByEmpId(EducationDegreeDTO dto, Authentication authentication) {
        return educationDegreeRepository.getDataByEmpId(dto);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> saveData(EducationDegreeDTO dto, Authentication authentication) throws Exception {
        HrEducationDegreeEntity educationDegreeEntity;

        if (dto.getFromDate() != null
                && dto.getToDate() != null
                && !Utils.compareDate(dto.getFromDate(), dto.getToDate(), false)) {
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_PARAM.getCode(), I18n.getMessage("msg.to.date.must.greater.from.date"));
        }

        if (dto.getEducationDegreeId() != null && dto.getEducationDegreeId() > 0L) {
            educationDegreeEntity = educationDegreeRepositoryJPA.getById(dto.getEducationDegreeId());
            if (educationDegreeEntity == null) {
                return ResponseUtils.getResponseEntity(ErrorApp.BAD_REQUEST);
            }

            educationDegreeEntity.setLastUpdateDate(new Date());
            educationDegreeEntity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        } else {
            educationDegreeEntity = new HrEducationDegreeEntity();
            educationDegreeEntity.setCreateDate(new Date());
            educationDegreeEntity.setCreatedBy(Utils.getUserNameLogin(authentication));
        }
        Utils.copyProperties(educationDegreeEntity, dto);
        educationDegreeEntity.setFlagStatus(Constants.STATUS.IS_ACTIVE);
        educationDegreeRepositoryJPA.save(educationDegreeEntity);
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> deleteData(Long id, Authentication authentication) {
        
        Optional<HrEducationDegreeEntity> optional = educationDegreeRepositoryJPA.findById(id);
        if (!optional.isPresent()) {
            return ResponseUtils.getResponseEntity(ErrorApp.BAD_REQUEST);
        }
        
        HrEducationDegreeEntity educationDegreeEntity = optional.get();
        educationDegreeEntity.setFlagStatus(Constants.STATUS.IS_NOT_ACTIVE);
        educationDegreeEntity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        educationDegreeEntity.setLastUpdateDate(new Date());
        educationDegreeRepositoryJPA.save(educationDegreeEntity);
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    public Object getDataById(Long id, Authentication authentication) throws Exception {
        Optional<HrEducationDegreeEntity> optional = educationDegreeRepositoryJPA.findById(id);
        if (!optional.isPresent() || Constants.STATUS.IS_NOT_ACTIVE.equals(optional.get().getFlagStatus())) {
            return null;
        }
        EducationDegreeDTO dto = new EducationDegreeDTO();
        Utils.copyProperties(dto, optional.get());
        return dto;
    }

    @Override
    public ResponseEntity<Object> searchEducationDegree(EmployeesDTO dto, Authentication authentication) {
        BaseResultSelect result = educationDegreeRepository.searchEducationDegree(dto);
        return ResponseUtils.getResponseSucessEntity(result);
    }

    @Override
    public String exportTemplateImportAddEducationDegree(Authentication authentication) throws Exception {
        String pathTemplate = "template/import/educationDegree/BM_NhapMoi_BangCap.xlsx";
        DynamicExport dynamicExport = new DynamicExport(pathTemplate, 2, true);
        int activeSheet = 1;
        String[] arrTypeCode = new String[]{
                Constants.LOOKUP_CODES.LOAI_BANG_CAP,
                Constants.LOOKUP_CODES.XEP_LOAI_DT
        };
        for (String typeCode : arrTypeCode) {
            dynamicExport.setActiveSheet(activeSheet++);
            List<VLookupEntity> listVLookup = educationDegreeRepository.findByProperty(VLookupEntity.class, "typeCode", typeCode, "code");
            int row = 1;
            int col = 0;
            for (VLookupEntity entity : listVLookup) {
                col = 0;
                dynamicExport.setText(String.valueOf(row), col++, row);
                dynamicExport.setText(entity.getLabel(), col, row++);
            }
            dynamicExport.setCellFormat(0, 0, row - 1, col, DynamicExport.BORDER_FORMAT);
        }
        int row = 1;
        int col = 0;
        dynamicExport.setActiveSheet(activeSheet++);
        List<HrMajorLevelsEntity> listMajorLevel = educationDegreeRepository.findAll(HrMajorLevelsEntity.class);
        for (HrMajorLevelsEntity entity:listMajorLevel) {
            col = 0;
            dynamicExport.setText(String.valueOf(row), col++, row);
            dynamicExport.setText(entity.getName(), col, row++);
        }
        dynamicExport.setCellFormat(0, 0, row - 1, col, DynamicExport.BORDER_FORMAT);

        row = 1;
        dynamicExport.setActiveSheet(activeSheet++);
        List<HrSchoolsEntity> listSchool = educationDegreeRepository.findAll(HrSchoolsEntity.class);
        for(HrSchoolsEntity entity:listSchool) {
            col = 0;
            dynamicExport.setText(String.valueOf(row), col++, row);
            dynamicExport.setText(entity.getName(), col, row++);
        }
        dynamicExport.setCellFormat(0, 0, row - 1, col, DynamicExport.BORDER_FORMAT);

        row = 1;
        dynamicExport.setActiveSheet(activeSheet++);
        List<HrFacultiesEntity> listFaculty = educationDegreeRepository.findAll(HrFacultiesEntity.class);
        for(HrFacultiesEntity entity:listFaculty) {
            col = 0;
            dynamicExport.setText(String.valueOf(row), col++, row);
            dynamicExport.setText(entity.getName(), col, row++);
        }
        dynamicExport.setCellFormat(0, 0, row - 1, col, DynamicExport.BORDER_FORMAT);

        row = 1;
        dynamicExport.setActiveSheet(activeSheet++);
        Integer[] isRelatedJobList = new Integer[]{0,1};
        for(Integer isRelatedJob:isRelatedJobList) {
            col = 0;
            dynamicExport.setText(String.valueOf(row), col++, row);
            dynamicExport.setText(isRelatedJob.toString(), col++, row);
            if(isRelatedJob == 0) {
                dynamicExport.setText("Không liên quan đến công việc", col, row++);
            } else {
                dynamicExport.setText("Liên quan đến công việc", col, row++);
            }
        }
        dynamicExport.setCellFormat(0, 0, row - 1, col, DynamicExport.BORDER_FORMAT);

        row = 1;
        dynamicExport.setActiveSheet(activeSheet++);
        Integer[] isHighestList = new Integer[]{0,1};
        for(Integer isHighest:isHighestList) {
            col = 0;
            dynamicExport.setText(String.valueOf(row), col++, row);
            dynamicExport.setText(isHighest.toString(), col++, row);
            if(isHighest == 0) {
                dynamicExport.setText("Không phải là trình độ cao nhất", col, row++);
            } else {
                dynamicExport.setText("Là trình độ cao nhất", col, row++);
            }
        }
        dynamicExport.setCellFormat(0, 0, row - 1, col, DynamicExport.BORDER_FORMAT);

        row = 1;
        dynamicExport.setActiveSheet(activeSheet);
        List<HrEmployeesEntity> listEmployee = educationDegreeRepository.findAll(HrEmployeesEntity.class);
        for(HrEmployeesEntity employeesEntity:listEmployee) {
            col = 0;
            dynamicExport.setText(String.valueOf(row), col++, row);
            dynamicExport.setText(employeesEntity.getEmployeeCode(), col++, row);
            dynamicExport.setText(employeesEntity.getFullName(), col, row++);
        }
        dynamicExport.setCellFormat(0, 0, row - 1, col, DynamicExport.BORDER_FORMAT);

        dynamicExport.setActiveSheet(0);

        String fileName = commonUtilsService.getFilePathExport(authentication, "BM_NhapMoi_BangCap.xlsx");
        dynamicExport.exportFile(fileName);
        return fileName;
    }

    @Override
    public ResponseEntity<Object> importAddEducationDegree(MultipartFile files, Authentication authentication, HttpServletRequest req) throws Exception {
        String pathTemplate = "template/import/educationDegree/BM_NhapMoi_BangCap.xml";
        ImportExcel importExcel = new ImportExcel(pathTemplate, req);
        List<Object[]> dataList = new ArrayList();
        String filePath = fileStorageConfig.getExportFolder() + Utils.getUserNameLogin(authentication) + "_BM_NhapMoi_BangCap";
        if (!importExcel.validateCommon(req, files.getBytes(), dataList, filePath)) {
            ImportResultDTO importResultDTO = new ImportResultDTO();
            importResultDTO.setErrorFile(importExcel.getFileErrorDescription(files, fileStorageConfig.getExportFolder(), authentication));
            importResultDTO.setErrorList(importExcel.getErrorList());

            Integer importResult = (Integer) req.getAttribute("importResult");
            String messageError = I18n.getMessage("import.importError" + importResult);
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_FILE.getCode(), messageError, importResultDTO);
        }

        int row = 0;
        Map<String, String> mapdegreeType = lookupValuesRepositoryImpl.getMapLookupByType(Constants.LOOKUP_CODES.LOAI_BANG_CAP, null);
        Map<String, String> mapEduRank = lookupValuesRepositoryImpl.getMapLookupByType(Constants.LOOKUP_CODES.XEP_LOAI_DT, null);

        List<HrSchoolsEntity> listSchool = educationDegreeRepository.findAll(HrSchoolsEntity.class);
        Map<String, Long> mapSchool = new HashMap<>();
        for (HrSchoolsEntity entity: listSchool) {
            mapSchool.put(entity.getName(), entity.getSchoolId());
        }
        List<HrFacultiesEntity> listFaculty = educationDegreeRepository.findAll(HrFacultiesEntity.class);
        Map<String, Long> mapFaculty = new HashMap<>();
        for (HrFacultiesEntity entity: listFaculty) {
            mapFaculty.put(entity.getName(), entity.getFacultyId());
        }
        List<HrMajorLevelsEntity> listMajorLevel = educationDegreeRepository.findAll(HrMajorLevelsEntity.class);
        Map<String, Long> mapMajorLevel = new HashMap<>();
        for (HrMajorLevelsEntity entity: listMajorLevel) {
            mapMajorLevel.put(entity.getName(),entity.getMajorLevelId());
        }
        List<HrEmployeesEntity> listEmployee = educationDegreeRepository.findAll(HrEmployeesEntity.class);
        Map<String, Long> mapEmployee = new HashMap<>();
        for (HrEmployeesEntity entity: listEmployee) {
            mapEmployee.put(entity.getEmployeeCode(),entity.getEmployeeId());
        }

        Map<Integer, Integer> mapIsRelatedJob = new HashMap<>();
        mapIsRelatedJob.put(1, 1);
        mapIsRelatedJob.put(0, 0);

        Map<Integer, Integer> mapIsHighest = new HashMap<>();
        mapIsHighest.put(1, 1);
        mapIsHighest.put(0,0);

        List<HrEducationDegreeEntity> listDegree = new ArrayList<>();
        Date currentDate = new Date();

        for(Object[] obj:dataList) {
            int col = 1;
            HrEducationDegreeEntity educationDegreeEntity = new HrEducationDegreeEntity();

            String employeeCode = (String) obj[col];
            if(mapEmployee.get(employeeCode) == null) {
                importExcel.addError(row, col, I18n.getMessage("import.input.invalid.lookup"), employeeCode);
                col++;
            }  else {
                educationDegreeEntity.setEmployeeId(mapEmployee.get(employeeCode));
                col++;
            }
            col++;

            String degreeTypeName =(String) obj[col];
            if(mapdegreeType.get(degreeTypeName.toLowerCase()) == null) {
                importExcel.addError(row, col, I18n.getMessage("import.input.invalid.lookup"), degreeTypeName);
            } else {
                educationDegreeEntity.setDegreeTypeCode(degreeTypeName.toLowerCase());
            }
            col++;
            educationDegreeEntity.setIssueYear((Integer) obj[col++]);
            educationDegreeEntity.setDegreeName((String) obj[col++]);

            String schoolName = (String) obj[col];
            if(mapSchool.get(schoolName) == null) {
                importExcel.addError(row, col, I18n.getMessage("import.input.invalid.lookup"), schoolName);
            } else {
                educationDegreeEntity.setSchoolId(mapSchool.get(schoolName));
            }
            col++;
            String facultyName = (String) obj[col];
            if(mapFaculty.get(facultyName) == null) {
                importExcel.addError(row, col, I18n.getMessage("import.input.invalid.lookup"), facultyName);
            } else {
                educationDegreeEntity.setFacultyId(mapFaculty.get(facultyName));
            }
            col++;
            String majorLevelName = (String) obj[col++];
            if(mapMajorLevel.get(majorLevelName) != null) {
                educationDegreeEntity.setMajorLevelId(mapMajorLevel.get(majorLevelName));
            }
            String eduRankName = (String) obj[col++];
            if(mapEduRank.get(eduRankName.toLowerCase()) != null) {
                educationDegreeEntity.setEduRankCode(eduRankName.toLowerCase());
            }
            Integer isRelatedJob = (Integer) obj[col++];
            if(mapIsRelatedJob.get(isRelatedJob) != null) {
                educationDegreeEntity.setIsRelatedJob(isRelatedJob);
            }
            Integer isHighest = (Integer) obj[col++];
            if(mapIsHighest.get(isHighest) != null) {
                educationDegreeEntity.setIsHighest(mapIsHighest.get(isHighest));
            }
            educationDegreeEntity.setNote((String) obj[col]);

            educationDegreeEntity.setFlagStatus(1);
            educationDegreeEntity.setCreatedBy(Utils.getUserNameLogin(authentication));
            educationDegreeEntity.setCreateDate(currentDate);
            listDegree.add(educationDegreeEntity);
            row++;
        }
        if (importExcel.hasError()) {// co loi xay ra
            ImportResultDTO importResultBean = new ImportResultDTO();
            importResultBean.setErrorFile(importExcel.getFileErrorDescription(files, fileStorageConfig.getExportFolder(), authentication));
            importResultBean.setErrorList(importExcel.getErrorList());
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_FILE, importResultBean);
        } else {// thuc hien insert vao DB
            educationDegreeRepositoryJPA.saveAll(listDegree);
            return ResponseUtils.getResponseEntity(ErrorApp.SUCCESS);
        }
    }
}
