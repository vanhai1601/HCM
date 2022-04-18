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
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.dto.ImportResultDTO;
import vn.com.mbbank.dto.ProjectMembersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.com.mbbank.core.config.I18n;
import vn.com.mbbank.entities.HrEmployeesEntity;
import vn.com.mbbank.entities.HrProjectMembersEntity;
import vn.com.mbbank.entities.MpJobsEntity;
import vn.com.mbbank.entities.MpProjectsEntity;
import vn.com.mbbank.repositories.impl.ProjectMembersRepositoryImpl;
import vn.com.mbbank.repositories.jpa.HrProjectMembersRepositoryJPA;
import vn.com.mbbank.services.CommonUtilsService;
import vn.com.mbbank.utils.Utils;
import vn.com.mbbank.services.ProjectMembersService;
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
public class ProjectMembersServiceImpl implements ProjectMembersService {

    @Autowired
    private ProjectMembersRepositoryImpl projectMemberRepository;

    @Autowired
    private HrProjectMembersRepositoryJPA projectMemberRepositoryJPA;

    @Autowired
    private CommonUtilsService commonUtilsService;

    @Autowired
    private FileStorageConfig fileStorageConfig;

    @Override
    public Object getListDataByEmpId(ProjectMembersDTO projectMemberDTO, Authentication authentication) {
        BaseResultSelect dataResult = projectMemberRepository.getProjectMember(projectMemberDTO);
        return dataResult;
    }

    @Override
    public ResponseEntity<Object> saveData(ProjectMembersDTO dto, Authentication authentication) throws Exception {
        // validate conflic qua trinh cong tac
        boolean isConflicProcess = projectMemberRepository.isConflictProcess(dto);
        if (isConflicProcess) {
            String message = I18n.getMessage("projectMemebers.validate.process");
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_PARAM.getCode(), message);
        }

        HrProjectMembersEntity entity;
        if (dto.getProjectMemberId() != null && dto.getProjectMemberId() > 0L) {
            entity = projectMemberRepositoryJPA.getById(dto.getProjectMemberId());
            entity.setLastUpdateDate(new Date());
            entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        } else {
            entity = new HrProjectMembersEntity();
            entity.setCreateDate(new Date());
            entity.setCreatedBy(Utils.getUserNameLogin(authentication));
        }
        Utils.copyProperties(entity, dto);
        entity.setFlagStatus(Constants.STATUS.IS_ACTIVE);
        projectMemberRepositoryJPA.save(entity);
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    public ResponseEntity<Object> deleteData(Long id, Authentication authentication) {
        Optional<HrProjectMembersEntity> optional = projectMemberRepositoryJPA.findById(id);
        if (!optional.isPresent()) {
            return ResponseUtils.getResponseEntity(ErrorApp.BAD_REQUEST);
        }

        HrProjectMembersEntity entity = optional.get();
        entity.setFlagStatus(Constants.STATUS.IS_NOT_ACTIVE);
        entity.setLastUpdateDate(new Date());
        entity.setLastUpdatedBy(Utils.getUserNameLogin(authentication));
        projectMemberRepositoryJPA.save(entity);
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    public Object getDataById(Long id, Authentication authentication) {
        Optional<HrProjectMembersEntity> optional = projectMemberRepositoryJPA.findById(id);
        if (!optional.isPresent() || Constants.STATUS.IS_NOT_ACTIVE.equals(optional.get().getFlagStatus())) {
            return null;
        }
        return optional.get();
    }

    @Override
    public ResponseEntity<Object> searchProjectMembers(EmployeesDTO dto, Authentication authentication) {
        BaseResultSelect result = projectMemberRepository.searchProjectMembers(dto);
        return ResponseUtils.getResponseSucessEntity(result);
    }

    @Override
    public String exportTemplateImportAddProjectMembers(Authentication authentication) throws Exception {
        String pathTemplate = "template/import/projectMembers/BM_NhapMoi_QuaTrinhThamGiaDuAn.xlsx";
        DynamicExport dynamicExport = new DynamicExport(pathTemplate, 2, true);
        int activeSheet = 1;
        dynamicExport.setActiveSheet(activeSheet++);
        List<MpJobsEntity> listJob = projectMemberRepository.findAll(MpJobsEntity.class);
        int row = 1;
        int col = 0;
        for(MpJobsEntity entity: listJob) {
            col = 0;
            dynamicExport.setText(String.valueOf(row), col++, row);
            dynamicExport.setText(entity.getJobName(), col, row++);
        }
        dynamicExport.setCellFormat(0, 0, row - 1, col, DynamicExport.BORDER_FORMAT);

        dynamicExport.setActiveSheet(activeSheet++);
        List<MpProjectsEntity> listProject = projectMemberRepository.findAll(MpProjectsEntity.class);
        row = 1;
        for(MpProjectsEntity projectsEntity:listProject) {
            col = 0;
            dynamicExport.setText(String.valueOf(row), col++, row);
            dynamicExport.setText(projectsEntity.getPrjName(), col, row++);
        }
        dynamicExport.setCellFormat(0, 0, row - 1, col, DynamicExport.BORDER_FORMAT);

        dynamicExport.setActiveSheet(activeSheet);
        List<HrEmployeesEntity> listEmployee = projectMemberRepository.findAll(HrEmployeesEntity.class);
        row = 1;
        for(HrEmployeesEntity employeesEntity:listEmployee) {
            col = 0;
            dynamicExport.setText(String.valueOf(row), col++, row);
            dynamicExport.setText(employeesEntity.getEmployeeCode(), col++, row);
            dynamicExport.setText(employeesEntity.getFullName(), col, row++);
        }
        dynamicExport.setCellFormat(0, 0, row - 1, col, DynamicExport.BORDER_FORMAT);

        dynamicExport.setActiveSheet(0);

        String fileName = commonUtilsService.getFilePathExport(authentication, "BM_NhapMoi_QuaTrinhThamGiaDuAn.xlsx");
        dynamicExport.exportFile(fileName);
        return fileName;
    }

    @Override
    public ResponseEntity<Object> importAddProjectMembers(MultipartFile files, Authentication authentication, HttpServletRequest req) throws Exception {
        String pathTemplate = "template/import/projectMembers/BM_NhapMoi_QuaTrinhThamGiaDuAn.xml";
        ImportExcel importExcel = new ImportExcel(pathTemplate, req);
        List<Object[]> dataList = new ArrayList();
        String filePath = fileStorageConfig.getExportFolder() + Utils.getUserNameLogin(authentication) + "_BM_NhapMoi_QuaTrinhThamGiaDuAn";
        if (!importExcel.validateCommon(req, files.getBytes(), dataList, filePath)) {
            ImportResultDTO importResultDTO = new ImportResultDTO();
            importResultDTO.setErrorFile(importExcel.getFileErrorDescription(files, fileStorageConfig.getExportFolder(), authentication));
            importResultDTO.setErrorList(importExcel.getErrorList());

            Integer importResult = (Integer) req.getAttribute("importResult");
            String messageError = I18n.getMessage("import.importError" + importResult);
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_FILE.getCode(), messageError, importResultDTO);
        }

        int row = 0;
        List<HrEmployeesEntity> listEmployee =projectMemberRepository.findAll(HrEmployeesEntity.class);
        Map<String, Long> mapEmployee = new HashMap<>();
        for (HrEmployeesEntity employeesEntity: listEmployee) {
            mapEmployee.put(employeesEntity.getEmployeeCode(),employeesEntity.getEmployeeId());
        }

        List<MpJobsEntity> listJob = projectMemberRepository.findAll(MpJobsEntity.class);
        Map<String, Long> mapJob = new HashMap<>();
        for(MpJobsEntity mpJobsEntity : listJob) {
            mapJob.put(mpJobsEntity.getJobName(),mpJobsEntity.getJobId());
        }

        List<MpProjectsEntity> listProject = projectMemberRepository.findAll(MpProjectsEntity.class);
        Map<String, Long> mapProject = new HashMap<>();
        for(MpProjectsEntity projectsEntity : listProject) {
            mapProject.put(projectsEntity.getPrjName(), projectsEntity.getPrjId());
        }

        List<HrProjectMembersEntity> listProjectMember = new ArrayList<>();
        Date currentDate = new Date();

        for(Object[] obj:dataList) {
            int col = 1;
            HrProjectMembersEntity projectMembersEntity = new HrProjectMembersEntity();

            String employeeCode = (String) obj[col];
            if(mapEmployee.get(employeeCode) == null) {
                importExcel.addError(row, col, I18n.getMessage("import.input.invalid.lookup"), employeeCode);
                col++;
            }  else {
                projectMembersEntity.setEmployeeId(mapEmployee.get(employeeCode));
                col++;
            }

            col++;
            projectMembersEntity.setFromDate((Date) obj[col++]);
            projectMembersEntity.setToDate((Date) obj[col++]);
            String prjName =(String) obj[col];
            if(mapProject.get(prjName) == null) {
                importExcel.addError(row, col, I18n.getMessage("import.input.invalid.lookup"), prjName);
            } else {
                projectMembersEntity.setProjectId(mapProject.get(prjName));
            }
            col++;
            String jobName =(String) obj[col];

            if(mapJob.get(jobName) == null) {
                importExcel.addError(row, col, I18n.getMessage("import.input.invalid.lookup"), jobName);
            } else {
                projectMembersEntity.setJobId(mapJob.get(jobName));
            }
            col++;
            projectMembersEntity.setNote((String) obj[col]);
            projectMembersEntity.setFlagStatus(1);
            projectMembersEntity.setCreatedBy(Utils.getUserNameLogin(authentication));
            projectMembersEntity.setCreateDate(currentDate);
            listProjectMember.add(projectMembersEntity);
            row++;
        }
        if (importExcel.hasError()) {// co loi xay ra
            ImportResultDTO importResultBean = new ImportResultDTO();
            importResultBean.setErrorFile(importExcel.getFileErrorDescription(files, fileStorageConfig.getExportFolder(), authentication));
            importResultBean.setErrorList(importExcel.getErrorList());
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_FILE, importResultBean);
        } else {// thuc hien insert vao DB
            projectMemberRepositoryJPA.saveAll(listProjectMember);
            return ResponseUtils.getResponseEntity(ErrorApp.SUCCESS);
        }
    }
}
