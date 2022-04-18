package vn.com.mbbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.dto.FamilyRelationshipsDTO;
import vn.com.mbbank.services.FamilyRelationshipsService;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.ResponseUtils;
import vn.com.mbbank.utils.Utils;

import javax.validation.Valid;

/**
 * Autogen class: Lớp thao tác danh sach than nhan
 *
 * @author ToolGen
 * @date Sun Mar 20 21:42:06 ICT 2022
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_PREFIX)
public class FamilyRelationshipsController {

    @Autowired
    private FamilyRelationshipsService familyRelationshipsService;

    @GetMapping(value = "/v1/family-relationships/employees/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getListDataByEmpId(Authentication authentication,
            @PathVariable Long employeeId,
            FamilyRelationshipsDTO familyRelationshipsDTO
    ) {
        familyRelationshipsDTO.setEmployeeId(employeeId);
        Object resultObj = familyRelationshipsService.getListDataByEmpId(familyRelationshipsDTO, authentication);
        return ResponseUtils.getResponseSucessEntity(resultObj);
    }

    @PostMapping(value = "/v1/family-relationships", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveData(Authentication authentication,
            @Valid @RequestBody FamilyRelationshipsDTO familyRelationshipsDTO
    ) throws Exception {
        return familyRelationshipsService.saveData(familyRelationshipsDTO, authentication);
    }

    @DeleteMapping(value = "/v1/family-relationships/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteData(Authentication authentication,
            @PathVariable Long id
    ) throws Exception {
        return familyRelationshipsService.deleteData(id, authentication);
    }

    @GetMapping(value = "/v1/family-relationships/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDataById(Authentication authentication,
            @PathVariable Long id
    ) throws Exception {
        FamilyRelationshipsDTO entity = (FamilyRelationshipsDTO) familyRelationshipsService.getDataById(id, authentication);
        if(entity == null) {
            return ResponseUtils.getResponseSucessEntity(null);
        }
        FamilyRelationshipsDTO familyRelationshipsDTO = new FamilyRelationshipsDTO();
        Utils.copyProperties(familyRelationshipsDTO, entity);
        return ResponseUtils.getResponseSucessEntity(familyRelationshipsDTO);
    }

    @GetMapping(value = "/v1/family-relationships", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> searchFamilyRelationships(Authentication authentication, EmployeesDTO employeesDTO) {
        return familyRelationshipsService.searchFamilyRelationships(employeesDTO);
    }
}
