package vn.com.mbbank.repositories.impl;

import org.springframework.stereotype.Service;
import vn.com.mbbank.core.dto.response.BaseResultSelect;
import vn.com.mbbank.core.repositories.impl.BaseRepositoryImpl;
import vn.com.mbbank.dto.EducationProcessDTO;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.QueryUtils;
import vn.com.mbbank.utils.Utils;

import java.util.HashMap;

@Service
public class EducationProcessRepositoryImpl extends BaseRepositoryImpl {

    public BaseResultSelect getDataByEmpId(EducationProcessDTO dto) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT hep.EDUCATION_PROCESS_ID educationProcessId, hep.EMPLOYEE_ID employeeId,"
                + "       hep.COURSE_NAME courseName, vl.label as eduMethodTypeName, hep.COURSE_CONTENT courseContent, "
                + "       hep.note note, hep.FROM_DATE fromDate, hep.TO_DATE toDate"
                + " FROM HR_EDUCATION_PROCESS hep "
                + " LEFT JOIN V_LOOKUP vl ON vl.code = hep.EDU_METHOD_TYPE_CODE AND vl.type_code = :eduMethodTypeCode "
                + " WHERE hep.employee_id = :employeeId "
                + " AND hep.flag_status = :flagStatus "
                + " ORDER BY hep.FROM_DATE DESC");
        HashMap<String, Object> params = new HashMap<>();
        params.put("eduMethodTypeCode", Constants.LOOKUP_CODES.HINHTHUC_DAOTAO);
        params.put("employeeId", dto.getEmployeeId());
        params.put("flagStatus", Constants.STATUS.IS_ACTIVE);
        return getListDataAndCount(sql, params, Utils.NVL(dto.getStartRecord()), Utils.NVL(dto.getPageSize(), 10), EducationProcessDTO.class);
    }

    /**
     * Tiem kiem qua trinh dao tao
     *
     * @param employeesDTO
     * @return
     */
    public BaseResultSelect searchEducationProcess(EmployeesDTO employeesDTO) {
        StringBuilder sql = new StringBuilder(" SELECT e.employee_id,"
                + " e.employee_code,"
                + " e.full_name,"
                + " e.flag_status,"
                + " NVL(mo.full_name, mo.org_name) orgName,"
                + " hep.course_name courseName,"
                + " vl.label as eduMethodTypeName,"
                + " hep.course_content courseContent,"
                + " hep.from_date fromDate,"
                + " hep.to_date toDate"
                + " FROM hr_employees e"
                + " LEFT JOIN mp_organizations mo ON mo.org_id = e.organization_id"
                + " JOIN hr_education_process hep ON e.employee_id = hep.employee_id"
                + " LEFT JOIN v_lookup vl ON vl.code = hep.edu_method_type_code AND vl.type_code = :eduMethodTypeCode "
                + " WHERE 1 = 1");
        HashMap<String, Object> params = new HashMap<>();
        params.put("eduMethodTypeCode", Constants.LOOKUP_CODES.HINHTHUC_DAOTAO);

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
        return getListPagination(sql.toString(), params, employeesDTO.getStartRecord(), employeesDTO.getPageSize(), EducationProcessDTO.class);
    }
}
