/*
 * Copyright (C) 2022 HRPLUS. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.services.impl;

import java.util.*;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import vn.com.mbbank.config.FileStorageConfig;
import vn.com.mbbank.core.config.I18n;
import vn.com.mbbank.core.dto.response.BaseResultSelect;
import vn.com.mbbank.core.utils.DynamicExport;
import vn.com.mbbank.core.utils.ImportExcel;
import vn.com.mbbank.dto.DecplineRecordsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.dto.ImportResultDTO;
import vn.com.mbbank.entities.HrDecplineRecordsEntity;
import vn.com.mbbank.entities.HrEmployeesEntity;
import vn.com.mbbank.entities.VLookupEntity;
import vn.com.mbbank.repositories.impl.DecplineRecordsRepositoryImpl;
import vn.com.mbbank.repositories.impl.LookupValuesRepositoryImpl;
import vn.com.mbbank.repositories.jpa.HrDecplineRecordsRepositoryJPA;
import vn.com.mbbank.services.CommonUtilsService;
import vn.com.mbbank.utils.Utils;
import vn.com.mbbank.services.DecplineRecordsService;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.ErrorApp;
import vn.com.mbbank.utils.ResponseUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Lop impl service
 *
 * @author author
 * @since 1.0
 * @version 1.0
 */
@Service
public class DecplineRecordsServiceImpl implements DecplineRecordsService {

    @Autowired
    private DecplineRecordsRepositoryImpl decplineRecordsRepository;

    @Autowired
    private HrDecplineRecordsRepositoryJPA decplineRecordsRepositoryJPA;

    @Autowired
    private CommonUtilsService commonUtilsService;

    @Autowired
    private FileStorageConfig fileStorageConfig;

    @Autowired
    private LookupValuesRepositoryImpl lookupValuesRepositoryImpl;

    @Override
    public Object getListDataByEmpId(DecplineRecordsDTO decplineRecordsDTO, Authentication authentication) {
        BaseResultSelect dataResult = decplineRecordsRepository.getDecplineRecords(decplineRecordsDTO);
        return dataResult;
    }

    @Override
    public void saveData(DecplineRecordsDTO dto, Authentication authentication) throws Exception {
        HrDecplineRecordsEntity entity;
        if (dto.getDisciplineRecordId() != null && dto.getDisciplineRecordId() > 0L) {
            entity = decplineRecordsRepositoryJPA.getById(dto.getDisciplineRecordId());
        } else {
            entity = new HrDecplineRecordsEntity();
            entity.setCreateDate(new Date());
            entity.setCreatedBy(Utils.getUserNameLogin(authentication));
        }
        Utils.copyProperties(entity, dto);
        entity.setFlagStatus(Constants.STATUS.IS_ACTIVE);
        decplineRecordsRepositoryJPA.save(entity);
    }

    @Override
    public ResponseEntity<Object> deleteData(Long id, Authentication authentication) {
        Optional<HrDecplineRecordsEntity> optional = decplineRecordsRepositoryJPA.findById(id);
        if (!optional.isPresent()) {
            return ResponseUtils.getResponseEntity(ErrorApp.BAD_REQUEST);
        }

        HrDecplineRecordsEntity entity = optional.get();
        entity.setFlagStatus(Constants.STATUS.IS_NOT_ACTIVE);
        entity.setLastUpdateDate(new Date());
        entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        decplineRecordsRepositoryJPA.save(entity);
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    public Object getDataById(Long id, Authentication authentication) {
        Optional<HrDecplineRecordsEntity> optional = decplineRecordsRepositoryJPA.findById(id);
        if (!optional.isPresent() || Constants.STATUS.IS_NOT_ACTIVE.equals(optional.get().getFlagStatus())) {
            return null;
        }
        return optional.get();
    }

    @Override
    public ResponseEntity<Object> searchDecplineRecords(EmployeesDTO dto, Authentication authentication) {
        BaseResultSelect result = decplineRecordsRepository.searchDecplineRecords(dto);
        return ResponseUtils.getResponseSucessEntity(result);
    }

    @Override
    public String exportTemplateImportAddDecplineRecords(Authentication authentication) throws Exception {
        String pathTemplate = "template/import/decplineRecords/BM_NhapMoi_KyLuat.xlsx";
        DynamicExport dynamicExport = new DynamicExport(pathTemplate, 2, true);
        int activeSheet = 1;
        String[] arrTypeCode = new String[]{
                Constants.LOOKUP_CODES.CAP_QD_KYLUAT,
                Constants.LOOKUP_CODES.HINHTHUC_KYLUAT
        };
        for (String typeCode : arrTypeCode) {
            dynamicExport.setActiveSheet(activeSheet++);
            List<VLookupEntity> listVLookup = decplineRecordsRepository.findByProperty(VLookupEntity.class, "typeCode", typeCode, "code");
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
        List<HrEmployeesEntity> listEmployee = decplineRecordsRepository.findAll(HrEmployeesEntity.class);
        for(HrEmployeesEntity employeesEntity:listEmployee) {
            col = 0;
            dynamicExport.setText(String.valueOf(row), col++, row);
            dynamicExport.setText(employeesEntity.getEmployeeCode(), col++, row);
            dynamicExport.setText(employeesEntity.getFullName(), col, row++);
        }
        dynamicExport.setCellFormat(0, 0, row - 1, col, DynamicExport.BORDER_FORMAT);

        dynamicExport.setActiveSheet(0);

        String fileName = commonUtilsService.getFilePathExport(authentication, "BM_NhapMoi_KyLuat.xlsx");
        dynamicExport.exportFile(fileName);
        return fileName;
    }

    @Override
    public ResponseEntity<Object> importAddDecplineRecords(MultipartFile files, Authentication authentication, HttpServletRequest req) throws Exception {
        String pathTemplate = "template/import/decplineRecords/BM_NhapMoi_KyLuat.xml";
        ImportExcel importExcel = new ImportExcel(pathTemplate, req);
        List<Object[]> dataList = new ArrayList();
        String filePath = fileStorageConfig.getExportFolder() + Utils.getUserNameLogin(authentication) + "_BM_NhapMoi_KyLuat";
        if (!importExcel.validateCommon(req, files.getBytes(), dataList, filePath)) {
            ImportResultDTO importResultDTO = new ImportResultDTO();
            importResultDTO.setErrorFile(importExcel.getFileErrorDescription(files, fileStorageConfig.getExportFolder(), authentication));
            importResultDTO.setErrorList(importExcel.getErrorList());

            Integer importResult = (Integer) req.getAttribute("importResult");
            String messageError = I18n.getMessage("import.importError" + importResult);
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_FILE.getCode(), messageError, importResultDTO);
        }

        int row = 0;
        Map<String, String> mapDisciplineMethod= lookupValuesRepositoryImpl.getMapLookupByType(Constants.LOOKUP_CODES.HINHTHUC_KYLUAT, null);
        Map<String, String> mapDisciplineLevel= lookupValuesRepositoryImpl.getMapLookupByType(Constants.LOOKUP_CODES.CAP_QD_KYLUAT, null);

        List<HrEmployeesEntity> listEmployee = decplineRecordsRepository.findAll(HrEmployeesEntity.class);
        Map<String, Long> mapEmployee = new HashMap<>();
        for (HrEmployeesEntity employeesEntity: listEmployee) {
            mapEmployee.put(employeesEntity.getEmployeeCode(),employeesEntity.getEmployeeId());
        }

        List<HrDecplineRecordsEntity> listDecpline = new ArrayList<>();
        Date currentDate = new Date();

        for(Object[] obj:dataList) {
            int col = 1;
            HrDecplineRecordsEntity decplineRecordsEntity = new HrDecplineRecordsEntity();

            String employeeCode = (String) obj[col];
            if(mapEmployee.get(employeeCode) == null) {
                importExcel.addError(row, col, I18n.getMessage("import.input.invalid.lookup"), employeeCode);
                col++;
            }  else {
                decplineRecordsEntity.setEmployeeId(mapEmployee.get(employeeCode));
                col++;
            }
            col++;
            decplineRecordsEntity.setSignedDate((Date) obj[col++]);
            String disciplineLevelName = (String) obj[col];
            if(mapDisciplineLevel.get(disciplineLevelName.toLowerCase()) == null) {
                importExcel.addError(row, col, I18n.getMessage("import.input.invalid.lookup"), disciplineLevelName);
            } else {
                decplineRecordsEntity.setDisciplineLevelCode(mapDisciplineLevel.get(disciplineLevelName.toLowerCase()));
            }
            col++;
            String disciplineMethodName = (String) obj[col];
            if(mapDisciplineMethod.get(disciplineMethodName.toLowerCase()) == null) {
                importExcel.addError(row, col, I18n.getMessage("import.input.invalid.lookup"), disciplineMethodName);
            } else {
                decplineRecordsEntity.setDisciplineMethodCode(mapDisciplineMethod.get(disciplineMethodName.toLowerCase()));
            }
            col++;
            decplineRecordsEntity.setReason((String) obj[col++]);
            decplineRecordsEntity.setAmount((Long) obj[col++]);
            decplineRecordsEntity.setNote((String) obj[col]);
            decplineRecordsEntity.setFlagStatus(1);
            decplineRecordsEntity.setCreatedBy(Utils.getUserNameLogin(authentication));
            decplineRecordsEntity.setCreateDate(currentDate);
            listDecpline.add(decplineRecordsEntity);
            row++;
        }
        if (importExcel.hasError()) {// co loi xay ra
            ImportResultDTO importResultBean = new ImportResultDTO();
            importResultBean.setErrorFile(importExcel.getFileErrorDescription(files, fileStorageConfig.getExportFolder(), authentication));
            importResultBean.setErrorList(importExcel.getErrorList());
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_FILE, importResultBean);
        } else {// thuc hien insert vao DB
            decplineRecordsRepositoryJPA.saveAll(listDecpline);
            return ResponseUtils.getResponseEntity(ErrorApp.SUCCESS);
        }
    }
}
