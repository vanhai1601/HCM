package vn.com.mbbank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.com.mbbank.config.FileStorageConfig;
import vn.com.mbbank.core.config.I18n;
import vn.com.mbbank.core.dto.response.BaseResultSelect;
import vn.com.mbbank.core.utils.DynamicExport;
import vn.com.mbbank.core.utils.ImportExcel;
import vn.com.mbbank.dto.EducationProcessDTO;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.dto.ImportResultDTO;
import vn.com.mbbank.entities.HrAllowanceProcessEntity;
import vn.com.mbbank.entities.HrEducationProcessEntity;
import vn.com.mbbank.entities.HrEmployeesEntity;
import vn.com.mbbank.entities.VLookupEntity;
import vn.com.mbbank.repositories.impl.EducationProcessRepositoryImpl;
import vn.com.mbbank.repositories.impl.LookupValuesRepositoryImpl;
import vn.com.mbbank.repositories.jpa.HrEducationProcessRepositoryJPA;
import vn.com.mbbank.services.CommonUtilsService;
import vn.com.mbbank.services.EducationProcessService;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.ErrorApp;
import vn.com.mbbank.utils.ResponseUtils;
import vn.com.mbbank.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class EducationProcessServiceImpl implements EducationProcessService {
    @Autowired
    private HrEducationProcessRepositoryJPA educationProcessRepositoryJPA;

    @Autowired
    private EducationProcessRepositoryImpl educationProcessRepository;

    @Override
    public Object getListDataByEmpId(EducationProcessDTO dto, Authentication authentication) {
        return educationProcessRepository.getDataByEmpId(dto);
    }

    @Autowired
    private CommonUtilsService commonUtilsService;

    @Autowired
    private FileStorageConfig fileStorageConfig;

    @Autowired
    private LookupValuesRepositoryImpl lookupValuesRepositoryImpl;

    @Override
    public ResponseEntity<Object> saveData(EducationProcessDTO dto, Authentication authentication) throws Exception {
        HrEducationProcessEntity hrEducationProcessEntity;

        if (dto.getFromDate() != null
                && dto.getToDate() != null
                && !Utils.compareDate(dto.getFromDate(), dto.getToDate(), false)) {
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_PARAM.getCode(), I18n.getMessage("msg.to.date.must.greater.from.date"));
        }

        if (dto.getEducationProcessId() != null && dto.getEducationProcessId() > 0L) {
            hrEducationProcessEntity = educationProcessRepositoryJPA.getById(dto.getEducationProcessId());
            if (hrEducationProcessEntity == null) {
                return ResponseUtils.getResponseEntity(ErrorApp.BAD_REQUEST);
            }

            hrEducationProcessEntity.setLastUpdateDate(new Date());
            hrEducationProcessEntity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        } else {
            hrEducationProcessEntity = new HrEducationProcessEntity();
            hrEducationProcessEntity.setCreateDate(new Date());
            hrEducationProcessEntity.setCreatedBy(Utils.getUserNameLogin(authentication));
        }
        Utils.copyProperties(hrEducationProcessEntity, dto);
        hrEducationProcessEntity.setFlagStatus(Constants.STATUS.IS_ACTIVE);
        educationProcessRepositoryJPA.save(hrEducationProcessEntity);
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    public void deleteData(Long id, Authentication authentication) {
        HrEducationProcessEntity educationProcessEntity = educationProcessRepositoryJPA.getById(id);
        educationProcessEntity.setFlagStatus(Constants.STATUS.IS_NOT_ACTIVE);
        educationProcessEntity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        educationProcessEntity.setLastUpdateDate(new Date());
        educationProcessRepositoryJPA.save(educationProcessEntity);
    }

    @Override
    public Object getDataById(Long id, Authentication authentication) throws Exception {
        Optional<HrEducationProcessEntity> optional = educationProcessRepositoryJPA.findById(id);
        if (!optional.isPresent() || Constants.STATUS.IS_NOT_ACTIVE.equals(optional.get().getFlagStatus())) {
            return null;
        }
        EducationProcessDTO dto = new EducationProcessDTO();
        Utils.copyProperties(dto, optional.get());
        return dto;
    }

    @Override
    public ResponseEntity<Object> searchEducationProcess(EmployeesDTO dto, Authentication authentication) {
        BaseResultSelect result = educationProcessRepository.searchEducationProcess(dto);
        return ResponseUtils.getResponseSucessEntity(result);
    }

    @Override
    public String exportTemplateImportAddEducationProcess(Authentication authentication) throws Exception {
        String pathTemplate = "template/import/educationProcess/BM_NhapMoi_DaoTao.xlsx";
        DynamicExport dynamicExport = new DynamicExport(pathTemplate, 2, true);
        int activeSheet = 1;
        String[] arrTypeCode = new String[]{
                Constants.LOOKUP_CODES.HINHTHUC_DAOTAO
        };
        for (String typeCode : arrTypeCode) {
            dynamicExport.setActiveSheet(activeSheet++);
            List<VLookupEntity> listVLookup = educationProcessRepository.findByProperty(VLookupEntity.class, "typeCode", typeCode, "code");
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
        dynamicExport.setActiveSheet(activeSheet);
        List<HrEmployeesEntity> listEmployee = educationProcessRepository.findAll(HrEmployeesEntity.class);
        for(HrEmployeesEntity employeesEntity:listEmployee) {
            col = 0;
            dynamicExport.setText(String.valueOf(row), col++, row);
            dynamicExport.setText(employeesEntity.getEmployeeCode(), col++, row);
            dynamicExport.setText(employeesEntity.getFullName(), col, row++);
        }
        dynamicExport.setCellFormat(0, 0, row - 1, col, DynamicExport.BORDER_FORMAT);

        dynamicExport.setActiveSheet(0);

        String fileName = commonUtilsService.getFilePathExport(authentication, "BM_NhapMoi_DaoTao.xlsx");
        dynamicExport.exportFile(fileName);
        return fileName;
    }

    @Override
    public ResponseEntity<Object> importAddEducationProcess(MultipartFile files, Authentication authentication, HttpServletRequest req) throws Exception {
        String pathTemplate = "template/import/educationProcess/BM_NhapMoi_DaoTao.xml";
        ImportExcel importExcel = new ImportExcel(pathTemplate, req);
        List<Object[]> dataList = new ArrayList();
        String filePath = fileStorageConfig.getExportFolder() + Utils.getUserNameLogin(authentication) + "_BM_NhapMoi_DaoTao";
        if (!importExcel.validateCommon(req, files.getBytes(), dataList, filePath)) {
            ImportResultDTO importResultDTO = new ImportResultDTO();
            importResultDTO.setErrorFile(importExcel.getFileErrorDescription(files, fileStorageConfig.getExportFolder(), authentication));
            importResultDTO.setErrorList(importExcel.getErrorList());

            Integer importResult = (Integer) req.getAttribute("importResult");
            String messageError = I18n.getMessage("import.importError" + importResult);
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_FILE.getCode(), messageError, importResultDTO);
        }

        int row = 0;
        Map<String, String> mapMethodType = lookupValuesRepositoryImpl.getMapLookupByType(Constants.LOOKUP_CODES.HINHTHUC_DAOTAO, null);

        List<HrEmployeesEntity> listEmployee = educationProcessRepository.findAll(HrEmployeesEntity.class);
        Map<String, Long> mapEmployee = new HashMap<>();
        for (HrEmployeesEntity employeesEntity: listEmployee) {
            mapEmployee.put(employeesEntity.getEmployeeCode(),employeesEntity.getEmployeeId());
        }


        List<HrEducationProcessEntity> listEducation = new ArrayList<>();
        Date currentDate = new Date();

        for(Object[] obj:dataList) {
            int col = 1;
            HrEducationProcessEntity educationProcessEntity = new HrEducationProcessEntity();

            String employeeCode = (String) obj[col];
            if(mapEmployee.get(employeeCode) == null) {
                importExcel.addError(row, col, I18n.getMessage("import.input.invalid.lookup"), employeeCode);
                col++;
            }  else {
                educationProcessEntity.setEmployeeId(mapEmployee.get(employeeCode));
                col++;
            }
            col++;
            educationProcessEntity.setCourseName((String) obj[col++]);
            String eduMethodTypeName = (String) obj[col];
            if(mapMethodType.get(eduMethodTypeName.toLowerCase()) == null) {
                importExcel.addError(row, col, I18n.getMessage("import.input.invalid.lookup"), eduMethodTypeName);
            } else {
                educationProcessEntity.setEduMethodTypeCode(mapMethodType.get(eduMethodTypeName.toLowerCase()));
            }
            col++;
            educationProcessEntity.setCourseContent((String) obj[col++]);
            educationProcessEntity.setFromDate((Date) obj[col++]);
            educationProcessEntity.setToDate((Date) obj[col++]);
            educationProcessEntity.setNote((String) obj[col]);
            educationProcessEntity.setFlagStatus(1);
            educationProcessEntity.setCreatedBy(Utils.getUserNameLogin(authentication));
            educationProcessEntity.setCreateDate(currentDate);
            listEducation.add(educationProcessEntity);
            row++;
        }
        if (importExcel.hasError()) {// co loi xay ra
            ImportResultDTO importResultBean = new ImportResultDTO();
            importResultBean.setErrorFile(importExcel.getFileErrorDescription(files, fileStorageConfig.getExportFolder(), authentication));
            importResultBean.setErrorList(importExcel.getErrorList());
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_FILE, importResultBean);
        } else {// thuc hien insert vao DB
            educationProcessRepositoryJPA.saveAll(listEducation);
            return ResponseUtils.getResponseEntity(ErrorApp.SUCCESS);
        }
    }
}
