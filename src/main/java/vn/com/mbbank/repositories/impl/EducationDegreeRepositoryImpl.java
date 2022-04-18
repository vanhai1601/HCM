package vn.com.mbbank.repositories.impl;

import org.springframework.stereotype.Repository;
import vn.com.mbbank.core.dto.response.BaseResultSelect;
import vn.com.mbbank.core.repositories.impl.BaseRepositoryImpl;
import vn.com.mbbank.dto.EducationDegreeDTO;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.QueryUtils;
import vn.com.mbbank.utils.Utils;

import java.util.HashMap;

@Repository
public class EducationDegreeRepositoryImpl extends BaseRepositoryImpl {

    public BaseResultSelect getDataByEmpId(EducationDegreeDTO dto) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT hed.EDUCATION_DEGREE_ID educationDegreeId, "
                + " hed.employee_id employeeId,"
                + " hed.degree_name degreeName,"
                + " hs.name schoolName, "
                + " hf.name facultyName, "
                + " hml.name majorLevelName, "
                + " vl1.label degreeTypeName, "
                + " vl2.label eduRankName"
                + " FROM HR_EDUCATION_DEGREES hed "
                + " LEFT JOIN HR_SCHOOLS hs ON hs.SCHOOL_ID = hed.SCHOOL_ID "
                + " LEFT JOIN HR_FACULTIES hf ON hf.FACULTY_ID = hed.FACULTY_ID "
                + " LEFT JOIN HR_MAJOR_LEVELS hml ON hml.MAJOR_LEVEL_ID = hed.MAJOR_LEVEL_ID "
                + " LEFT JOIN V_LOOKUP vl1 ON vl1.code = hed.DEGREE_TYPE_CODE AND vl1.type_code = :degreeTypeCode "
                + " LEFT JOIN V_LOOKUP vl2 ON vl2.code = hed.EDU_RANK_CODE AND vl2.type_code = :eduRankCode "
                + " WHERE hed.employee_id = :employeeId "
                + " AND hed.flag_status = :flagStatus "
                + " ORDER BY hed.from_date DESC");
        HashMap<String, Object> params = new HashMap<>();
        params.put("degreeTypeCode", Constants.LOOKUP_CODES.LOAI_HINH_DT);
        params.put("eduRankCode", Constants.LOOKUP_CODES.XEP_LOAI_DT);
        params.put("employeeId", dto.getEmployeeId());
        params.put("flagStatus", Constants.STATUS.IS_ACTIVE);
        return getListDataAndCount(sql, params, Utils.NVL(dto.getStartRecord()), Utils.NVL(dto.getPageSize(), 10), EducationDegreeDTO.class);
    }

    /**
     * Tiem kiem bang cap, chung chi
     * @param employeesDTO
     * @return
     */
    public BaseResultSelect searchEducationDegree(EmployeesDTO employeesDTO) {
        StringBuilder sql = new StringBuilder(" SELECT e.employee_id,"
                + " e.employee_code,"
                + " e.full_name,"
                + " e.flag_status,"
                + " NVL(mo.full_name, mo.org_name) orgName,"
                + " hml.name majorLevelName,"
                + " hf.name facultyName,"
                + " hs.name schoolName,"
                + " hed.issue_year issueYear"
                + " FROM hr_employees e"
                + " LEFT JOIN mp_organizations mo ON mo.org_id = e.organization_id"
                + " JOIN hr_education_degrees hed ON e.employee_id = hed.employee_id"
                + " LEFT JOIN hr_major_levels hml ON hml.major_level_id = hed.major_level_id"
                + " LEFT JOIN hr_faculties hf ON hf.faculty_id = hed.faculty_id"
                + " LEFT JOIN hr_schools hs ON hs.school_id = hed.school_id"
                + " WHERE 1 = 1");
        HashMap<String, Object> params = new HashMap<>();
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
            sql.append(" AND EXISTS(SELECT 1 FROM hr_employee_infos i WHERE i.employee_id = e.employee_id AND i.major_level_id IN (:listMajorLevelId))");
            params.put("listMajorLevelId", employeesDTO.getListMajorLevelId());
        }
        QueryUtils.filter(employeesDTO.getListPositionId(), sql, params, "e.position_id");
        sql.append(" ORDER BY e.employee_id");
        return getListPagination(sql.toString(), params, employeesDTO.getStartRecord(), employeesDTO.getPageSize(), EducationDegreeDTO.class);
    }
}
