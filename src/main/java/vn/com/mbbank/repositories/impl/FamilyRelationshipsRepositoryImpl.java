package vn.com.mbbank.repositories.impl;

import org.springframework.stereotype.Repository;
import vn.com.mbbank.core.dto.response.BaseResultSelect;
import vn.com.mbbank.core.repositories.impl.BaseRepositoryImpl;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.dto.FamilyRelationshipsDTO;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.QueryUtils;
import vn.com.mbbank.utils.Utils;

import java.util.HashMap;
import java.util.List;

/**
 * Autogen class Repository Impl: Lớp thao tác danh sach than nhan
 *
 * @author ToolGen
 * @date Sun Mar 20 21:42:07 ICT 2022
 */
@Repository
public class FamilyRelationshipsRepositoryImpl extends BaseRepositoryImpl {

    public BaseResultSelect getListDataByEmpId(FamilyRelationshipsDTO familyRelationshipsDTO) {
        String sql = "SELECT hfr.family_relationship_id familyRelationshipId,"
                + "    vl1.label relationTypeName,"
                + "    hfr.full_name fullName,"
                + "    hfr.date_of_birth dateOfBirth,"
                + "    hfr.work_organization workOrganization,"
                + "    hfr.job job,"
                + "    hfr.current_address currentAddress,"
                + "    vl2.label relationStatusName"
                + " FROM hr_family_relationships hfr "
                + " LEFT JOIN v_lookup vl1 ON vl1.code = hfr.relation_type_code AND vl1.type_code = :typeCode"
                + " LEFT JOIN v_lookup vl2 ON vl2.code = hfr.relation_status_code AND vl2.type_code = :typeCodeStatus"
                + " WHERE hfr.employee_id = :employeeId AND NVL(hfr.flag_status, :flagStatus) = :flagStatus";
        HashMap<String, Object> params = new HashMap<>();
        params.put("employeeId", familyRelationshipsDTO.getEmployeeId());
        params.put("typeCode", Constants.LOOKUP_CODES.MOI_QUAN_HE_NT);
        params.put("typeCodeStatus", Constants.LOOKUP_CODES.TINH_TRANG_NT);
        params.put("flagStatus", Constants.STATUS.IS_ACTIVE);
        BaseResultSelect resultData = getListPagination(sql, params, familyRelationshipsDTO.getStartRecord(), familyRelationshipsDTO.getPageSize(), FamilyRelationshipsDTO.class);
        return resultData;
    }

    public List<FamilyRelationshipsDTO> getListFamilyRelationshipsByEmpId(Long employeeId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT hfr.family_relationship_id familyRelationshipId,"
                + "    vl1.label relationTypeName,"
                + "    hfr.full_name fullName,"
                + "    hfr.personal_id_number personalIdNumber,"
                + "    hfr.tax_number taxNumber"
                + " FROM hr_family_relationships hfr "
                + " LEFT JOIN v_lookup vl1 ON vl1.code = hfr.relation_type_code AND vl1.type_code = :typeCode"
                + " WHERE hfr.employee_id = :employeeId"
                + " AND NVL(hfr.flag_status, :flagStatus) = :flagStatus");
        HashMap<String, Object> params = new HashMap<>();
        params.put("employeeId", employeeId);
        params.put("flagStatus", Constants.STATUS.IS_ACTIVE);
        params.put("typeCode", Constants.LOOKUP_CODES.MOI_QUAN_HE_NT);
        return (List<FamilyRelationshipsDTO>) getListData(sql, params, null, null, FamilyRelationshipsDTO.class);
    }

    public BaseResultSelect searchFamilyRelationships(EmployeesDTO employeesDTO) {
        StringBuilder sql = new StringBuilder("SELECT e.employee_code, " +
                "       e.full_name employeeName, " +
                "       mo.org_name, " +
                "       vl1.label relationTypeCode, " +
                "       hfr.full_name, " +
                "       vl2.label relationStatusCode, " +
                "       hfr.date_of_birth, " +
                "       hfr.work_organization, " +
                "       e.flag_status " +
                "FROM hr_employees e " +
                "JOIN mp_organizations mo ON e.organization_id = mo.org_id " +
                "JOIN hr_family_relationships hfr ON e.employee_id = hfr.employee_id " +
                "JOIN v_lookup vl1 ON vl1.code = hfr.relation_type_code " +
                "AND vl1.type_code = :relationTypeCode " +
                "JOIN v_lookup vl2 ON vl2.code = hfr.relation_status_code " +
                "AND vl2.type_code = :relationStatusCode " +
                "WHERE 1 = 1 ");
        HashMap<String, Object> params = new HashMap<>();
        params.put("relationTypeCode", Constants.LOOKUP_CODES.MOI_QUAN_HE_NT);
        params.put("relationStatusCode", Constants.LOOKUP_CODES.TINH_TRANG_NT);
        QueryUtils.filter(employeesDTO.getEmployeeCode(), sql, params, "e.employee_code");
        QueryUtils.filter(employeesDTO.getFullName(), sql, params, "e.full_name");
        QueryUtils.filterEq(employeesDTO.getEmpTypeCode(), sql, params, "e.emp_type_code");
        QueryUtils.filter(employeesDTO.getFlagStatus(), sql, params, "e.flag_status");
        QueryUtils.filter(employeesDTO.getListStatus(), sql, params, "e.flag_status");
        if (!Utils.isNullObject(employeesDTO.getOrganizationId())) {
            sql.append(" AND mo.path_id LIKE :orgId");
            params.put("orgId", "%/" + employeesDTO.getOrganizationId() + "/%");
        }
        if (employeesDTO.getListDateOfBirth() != null && !employeesDTO.getListDateOfBirth().isEmpty()) {
            QueryUtils.filterGe(Utils.convertStringToDate(employeesDTO.getListDateOfBirth().get(0)), sql, params, "e.date_of_birth");
            QueryUtils.filterLe(employeesDTO.getListDateOfBirth().size() > 1 ? Utils.convertStringToDate(employeesDTO.getListDateOfBirth().get(1)) : null, sql, params, "e.date_of_birth");
        }

        if (employeesDTO.getListMonthOfBirth() != null && !employeesDTO.getListMonthOfBirth().isEmpty()) {
            QueryUtils.filterGe(employeesDTO.getListMonthOfBirth().get(0), sql, params, "MONTH(e.date_of_birth)");
            QueryUtils.filterLe(employeesDTO.getListMonthOfBirth().size() > 1 ? employeesDTO.getListMonthOfBirth().get(1) : null, sql, params, "MONTH(e.date_of_birth)");
        }

        if (employeesDTO.getListYearOfBirth() != null && !employeesDTO.getListYearOfBirth().isEmpty()) {
            QueryUtils.filterGe(employeesDTO.getListYearOfBirth().get(0), sql, params, "YEAR(e.date_of_birth)");
            QueryUtils.filterLe(employeesDTO.getListYearOfBirth().size() > 1 ? employeesDTO.getListYearOfBirth().get(1) : null, sql, params, "YEAR(e.date_of_birth)");
        }

        QueryUtils.filter(employeesDTO.getListGender(), sql, params, "e.gender_code");
        QueryUtils.filter(employeesDTO.getListReligionCode(), sql, params, "e.religion_code");
        QueryUtils.filter(employeesDTO.getListEthnicCode(), sql, params, "e.ethnic_code");
        QueryUtils.filter(employeesDTO.getListMaritalStatusCode(), sql, params, "e.marital_status_code");
        QueryUtils.filter(employeesDTO.getPersonalId(), sql, params, "e.personal_id");
        QueryUtils.filter(employeesDTO.getTaxNo(), sql, params, "e.tax_no");
        QueryUtils.filter(employeesDTO.getInsuranceNo(), sql, params, "e.insurance_no");
        QueryUtils.filter(employeesDTO.getMobileNumber(), sql, params, "e.mobile_number");
        QueryUtils.filter(employeesDTO.getEmail(), sql, params, "e.email");
        if(employeesDTO.getListMajorLevelId() != null && !employeesDTO.getListMajorLevelId().isEmpty()){
            sql.append(" AND EXISTS(select 1 from hr_employee_infos i where i.employee_id = e.employee_id and i.major_level_id IN (:listMajorLevelId))");
            params.put("listMajorLevelId", employeesDTO.getListMajorLevelId());
        }
        QueryUtils.filter(employeesDTO.getListPositionId(), sql, params, "e.position_id");
        sql.append(" ORDER BY e.employee_id");
        return getListPagination(sql.toString(), params, employeesDTO.getStartRecord(), employeesDTO.getPageSize(), FamilyRelationshipsDTO.class);
    }
}
