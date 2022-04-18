package vn.com.mbbank.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.dto.FamilyRelationshipsDTO;

/**
 * Autogen class: Lớp thao tác danh sach than nhan
 * 
 * @author ToolGen
 * @date Sun Mar 20 21:42:07 ICT 2022
 */
public interface FamilyRelationshipsService {
    Object getListDataByEmpId(FamilyRelationshipsDTO familyRelationshipsDTO, Authentication authentication);
    ResponseEntity<Object> saveData(FamilyRelationshipsDTO dto, Authentication authentication) throws Exception;
    ResponseEntity<Object> deleteData(Long id, Authentication authentication);
    Object getDataById(Long id, Authentication authentication) throws Exception;
    ResponseEntity<Object> searchFamilyRelationships(EmployeesDTO dto) ;
}