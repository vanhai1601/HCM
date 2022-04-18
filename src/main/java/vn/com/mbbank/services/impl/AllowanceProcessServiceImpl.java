/*
 * Copyright (C) 2022 HRPLUS. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.services.impl;

import java.util.*;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import vn.com.mbbank.config.FileStorageConfig;
import vn.com.mbbank.core.dto.response.BaseResultSelect;
import vn.com.mbbank.core.utils.DynamicExport;
import vn.com.mbbank.core.utils.ImportExcel;
import vn.com.mbbank.dto.AllowanceProcessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.com.mbbank.core.config.I18n;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.dto.ImportResultDTO;
import vn.com.mbbank.entities.*;
import vn.com.mbbank.repositories.impl.AllowanceProcessRepositoryImpl;
import vn.com.mbbank.repositories.impl.LookupValuesRepositoryImpl;
import vn.com.mbbank.repositories.jpa.HrAllowanceProcessRepositoryJPA;
import vn.com.mbbank.services.CommonUtilsService;
import vn.com.mbbank.utils.Utils;
import vn.com.mbbank.services.AllowanceProcessService;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.ErrorApp;
import vn.com.mbbank.utils.ResponseUtils;

import javax.servlet.http.HttpServletRequest;


@Service
public class AllowanceProcessServiceImpl implements AllowanceProcessService {

    @Autowired
    private AllowanceProcessRepositoryImpl allowanceProcessRepository;

    @Autowired
    private HrAllowanceProcessRepositoryJPA allowanceProcessRepositoryJPA;

    @Autowired
    private CommonUtilsService commonUtilsService;

    @Autowired
    private FileStorageConfig fileStorageConfig;

    @Autowired
    private LookupValuesRepositoryImpl lookupValuesRepositoryImpl;


    @Override
    public Object getListDataByEmpId(AllowanceProcessDTO allowanceProcessDTO, Authentication authentication) {
        BaseResultSelect dataResult = allowanceProcessRepository.getAllowanceProcess(allowanceProcessDTO);
        return dataResult;
    }

    @Override
    public ResponseEntity<Object> saveData(AllowanceProcessDTO dto, Authentication authentication) throws Exception {
        // validate conflic qua trinh
        boolean isConflicProcess = allowanceProcessRepository.isConflictProcess(dto);
        if (isConflicProcess) {
            String message = I18n.getMessage("workProcess.validate.process");
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_PARAM.getCode(), message);
        }

        HrAllowanceProcessEntity entity;
        Date currentDate = new Date();
        if (dto.getAllowanceProcessId() != null && dto.getAllowanceProcessId() > 0L) {
            entity = allowanceProcessRepositoryJPA.getById(dto.getAllowanceProcessId());
            entity.setLastUpdateDate(currentDate);
            entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        } else {
            entity = new HrAllowanceProcessEntity();
            entity.setCreateDate(currentDate);
            entity.setCreatedBy(Utils.getUserNameLogin(authentication));
        }
        Utils.copyProperties(entity, dto);
        entity.setFlagStatus(Constants.STATUS.IS_ACTIVE);
        allowanceProcessRepositoryJPA.save(entity);
        // ket thuc qua trinh truoc do
        HrAllowanceProcessEntity allowanceProcessEntityPrev = allowanceProcessRepository.getAllowanceProcessByDate(dto);
        if(allowanceProcessEntityPrev != null){
            allowanceProcessEntityPrev.setToDate(Utils.addDate(dto.getFromDate(), -1));
            allowanceProcessEntityPrev.setLastUpdateDate(currentDate);
            allowanceProcessEntityPrev.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
            allowanceProcessRepositoryJPA.save(allowanceProcessEntityPrev);
        }
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    public ResponseEntity<Object> deleteData(Long id, Authentication authentication) {
//        Optional<HrAllowanceProcessEntity> optional = allowanceProcessRepositoryJPA.findById(id);
//        if (!optional.isPresent()) {
//            return ResponseUtils.getResponseEntity(ErrorApp.BAD_REQUEST);
//        }
//
//        HrAllowanceProcessEntity entity = optional.get();
//        entity.setFlagStatus(Constants.STATUS.IS_NOT_ACTIVE);
//        entity.setLastUpdateDate(new Date());
//        entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
//        allowanceProcessRepositoryJPA.save(entity);
//        int recordDelete = allowanceProcessRepository.updateStatus(HrAllowanceProcessEntity.class, id, authentication);
//        if(recordDelete == 0){
//            return ResponseUtils.getResponseEntity(ErrorApp.BAD_REQUEST);
//        } else {
//            return ResponseUtils.getResponseSucessEntity(null);
//        }
        return null;
    }

    @Override
    public Object getDataById(Long id, Authentication authentication) {
        Optional<HrAllowanceProcessEntity> optional = allowanceProcessRepositoryJPA.findById(id);
        if (!optional.isPresent() || Constants.STATUS.IS_NOT_ACTIVE.equals(optional.get().getFlagStatus())) {
            return null;
        }

        return optional.get();
    }

    @Override
    public ResponseEntity<Object> searchAllowanceProcess(EmployeesDTO dto, Authentication authentication) {
        BaseResultSelect result = allowanceProcessRepository.searchAllowanceProcess(dto);
        return ResponseUtils.getResponseSucessEntity(result);
    }

    @Override
    public String exportTemplateImportAddAllowanceProcess(Authentication authentication) throws Exception {
        String pathTemplate = "template/import/allowanceProcess/BM_NhapMoi_PhuCap.xlsx";
        DynamicExport dynamicExport = new DynamicExport(pathTemplate, 2, true);
        int activeSheet = 1;
        String[] arrTypeCode = new String[]{
                Constants.LOOKUP_CODES.LOAI_PHU_CAP
        };
        for (String typeCode : arrTypeCode) {
            dynamicExport.setActiveSheet(activeSheet++);
            List<VLookupEntity> listVLookup = allowanceProcessRepository.findByProperty(VLookupEntity.class, "typeCode", typeCode, "code");
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
        List<HrEmployeesEntity> listEmployee = allowanceProcessRepository.findAll(HrEmployeesEntity.class);
        for(HrEmployeesEntity employeesEntity:listEmployee) {
            col = 0;
            dynamicExport.setText(String.valueOf(row), col++, row);
            dynamicExport.setText(employeesEntity.getEmployeeCode(), col++, row);
            dynamicExport.setText(employeesEntity.getFullName(), col, row++);
        }
        dynamicExport.setCellFormat(0, 0, row - 1, col, DynamicExport.BORDER_FORMAT);

        dynamicExport.setActiveSheet(0);

        String fileName = commonUtilsService.getFilePathExport(authentication, "BM_NhapMoi_PhuCap.xlsx");
        dynamicExport.exportFile(fileName);
        return fileName;
    }

    @Override
    public ResponseEntity<Object> importAddAllowanceProcess(MultipartFile files, Authentication authentication, HttpServletRequest req) throws Exception {
        String pathTemplate = "template/import/allowanceProcess/BM_NhapMoi_PhuCap.xml";
        ImportExcel importExcel = new ImportExcel(pathTemplate, req);
        List<Object[]> dataList = new ArrayList();
        String filePath = fileStorageConfig.getExportFolder() + Utils.getUserNameLogin(authentication) + "_BM_NhapMoi_PhuCap";
        if (!importExcel.validateCommon(req, files.getBytes(), dataList, filePath)) {
            ImportResultDTO importResultDTO = new ImportResultDTO();
            importResultDTO.setErrorFile(importExcel.getFileErrorDescription(files, fileStorageConfig.getExportFolder(), authentication));
            importResultDTO.setErrorList(importExcel.getErrorList());

            Integer importResult = (Integer) req.getAttribute("importResult");
            String messageError = I18n.getMessage("import.importError" + importResult);
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_FILE.getCode(), messageError, importResultDTO);
        }

        int row = 0;
        Map<String, String> mapAllowance = lookupValuesRepositoryImpl.getMapLookupByType(Constants.LOOKUP_CODES.LOAI_PHU_CAP, null);

        List<HrEmployeesEntity> listEmployee = allowanceProcessRepository.findAll(HrEmployeesEntity.class);
        Map<String, Long> mapEmployee = new HashMap<>();
        for (HrEmployeesEntity employeesEntity: listEmployee) {
            mapEmployee.put(employeesEntity.getEmployeeCode(),employeesEntity.getEmployeeId());
        }


        List<HrAllowanceProcessEntity> listAllowance = new ArrayList<>();
        Date currentDate = new Date();

        for(Object[] obj:dataList) {
            int col = 1;
            HrAllowanceProcessEntity allowanceProcessEntity = new HrAllowanceProcessEntity();

            String employeeCode = (String) obj[col];
            if(mapEmployee.get(employeeCode) == null) {
                importExcel.addError(row, col, I18n.getMessage("import.input.invalid.lookup"), employeeCode);
                col++;
            }  else {
                allowanceProcessEntity.setEmployeeId(mapEmployee.get(employeeCode));
                col++;
            }
            col++;
            allowanceProcessEntity.setFromDate((Date) obj[col++]);
            allowanceProcessEntity.setToDate((Date) obj[col++]);
            String allowanceTypeName = (String) obj[col];
            if(mapAllowance.get(allowanceTypeName.toLowerCase()) == null) {
                importExcel.addError(row, col, I18n.getMessage("import.input.invalid.lookup"), allowanceTypeName);
            } else {
                allowanceProcessEntity.setAllowanceTypeCode(mapAllowance.get(allowanceTypeName.toLowerCase()));
            }
            col++;
            allowanceProcessEntity.setAmountMoney((Long) obj[col++]);
            allowanceProcessEntity.setNote((String) obj[col]);
            allowanceProcessEntity.setFlagStatus(1);
            allowanceProcessEntity.setCreatedBy(Utils.getUserNameLogin(authentication));
            allowanceProcessEntity.setCreateDate(currentDate);
            listAllowance.add(allowanceProcessEntity);
            row++;
        }
        if (importExcel.hasError()) {// co loi xay ra
            ImportResultDTO importResultBean = new ImportResultDTO();
            importResultBean.setErrorFile(importExcel.getFileErrorDescription(files, fileStorageConfig.getExportFolder(), authentication));
            importResultBean.setErrorList(importExcel.getErrorList());
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_FILE, importResultBean);
        } else {// thuc hien insert vao DB
            allowanceProcessRepositoryJPA.saveAll(listAllowance);
            return ResponseUtils.getResponseEntity(ErrorApp.SUCCESS);
        }
    }
}
