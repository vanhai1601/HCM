package vn.com.mbbank.repositories.impl;import org.springframework.stereotype.Repository;import vn.com.mbbank.core.dto.response.BaseResultSelect;import vn.com.mbbank.core.repositories.impl.BaseRepositoryImpl;import vn.com.mbbank.dto.EmployeesDTO;import vn.com.mbbank.dto.WorkedOutsidesDTO;import vn.com.mbbank.utils.Constants;import vn.com.mbbank.utils.QueryUtils;import vn.com.mbbank.utils.Utils;import java.util.HashMap;/** * Autogen class Repository Impl: Lớp thao tác danh sach qua trinh cong tac * truoc MB * * @author ToolGen * @date Sun Mar 20 21:38:55 ICT 2022 */@Repositorypublic class WorkedOutsidesRepositoryImpl extends BaseRepositoryImpl {    /**     * Lay danh sach qua trinh cong tac truoc MB     *     * @param dto: params client truyen len     * @return     */    public BaseResultSelect getWorkedOutsides(WorkedOutsidesDTO dto) {        StringBuilder sql = new StringBuilder();        sql.append("SELECT a.worked_outside_id workedOutSideId,"                + "    a.from_date fromDate,"                + "    a.to_date toDate,"                + "    a.organization_name organizationName,"                + "    a.position_name positionName,"                + "    a.refer_person referPerson,"                + "    a.refer_person_position referPersonPosition,"                + "    a.mission,"                + "    a.reason_leave reasonLeave,"                + "    a.reward_info rewardInfo,"                + "    a.decpline_info decplineInfo"                + " FROM hr_worked_outsides a"                + " WHERE a.employee_id = :employeeId AND NVL(a.flag_status, " + Constants.STATUS.IS_ACTIVE + ") = :flagStatus"                + " ORDER BY a.from_date DESC");        HashMap<String, Object> hashMapParams = new HashMap<>();        hashMapParams.put("employeeId", dto.getEmployeeId());        hashMapParams.put("flagStatus", Constants.STATUS.IS_ACTIVE);        return getListDataAndCount(sql, hashMapParams, Utils.NVL(dto.getStartRecord()), Utils.NVL(dto.getPageSize(), 10), WorkedOutsidesDTO.class);    }    public BaseResultSelect searchWorkedOutsides(EmployeesDTO employeesDTO) {        StringBuilder sql = new StringBuilder("SELECT e.employee_code, " +                "       e.full_name employeeName, " +                "       mo.org_name, " +                "       hwo.from_date, " +                "       hwo.to_date, " +                "       hwo.organization_name, " +                "       hwo.position_name, " +                "       e.flag_status " +                "FROM hr_employees e " +                "JOIN mp_organizations mo ON e.organization_id = mo.org_id " +                "JOIN hr_worked_outsides hwo ON hwo.employee_id = e.employee_id " +                "WHERE 1 = 1 ");        HashMap<String, Object> params = new HashMap<String, Object>();        QueryUtils.filter(employeesDTO.getEmployeeCode(), sql, params, "e.employee_code");        QueryUtils.filter(employeesDTO.getFullName(), sql, params, "e.full_name");        QueryUtils.filterEq(employeesDTO.getEmpTypeCode(), sql, params, "e.emp_type_code");        QueryUtils.filter(employeesDTO.getFlagStatus(), sql, params, "e.flag_status");        QueryUtils.filter(employeesDTO.getListStatus(), sql, params, "e.flag_status");        if (!Utils.isNullObject(employeesDTO.getOrganizationId())) {            sql.append(" AND mo.path_id LIKE :orgId");            params.put("orgId", "%/" + employeesDTO.getOrganizationId() + "/%");        }        if (employeesDTO.getListDateOfBirth() != null && !employeesDTO.getListDateOfBirth().isEmpty()) {            QueryUtils.filterGe(Utils.convertStringToDate(employeesDTO.getListDateOfBirth().get(0)), sql, params, "e.date_of_birth");            QueryUtils.filterLe(employeesDTO.getListDateOfBirth().size() > 1 ? Utils.convertStringToDate(employeesDTO.getListDateOfBirth().get(1)) : null, sql, params, "e.date_of_birth");        }        if (employeesDTO.getListMonthOfBirth() != null && !employeesDTO.getListMonthOfBirth().isEmpty()) {            QueryUtils.filterGe(employeesDTO.getListMonthOfBirth().get(0), sql, params, "MONTH(e.date_of_birth)");            QueryUtils.filterLe(employeesDTO.getListMonthOfBirth().size() > 1 ? employeesDTO.getListMonthOfBirth().get(1) : null, sql, params, "MONTH(e.date_of_birth)");        }        if (employeesDTO.getListYearOfBirth() != null && !employeesDTO.getListYearOfBirth().isEmpty()) {            QueryUtils.filterGe(employeesDTO.getListYearOfBirth().get(0), sql, params, "YEAR(e.date_of_birth)");            QueryUtils.filterLe(employeesDTO.getListYearOfBirth().size() > 1 ? employeesDTO.getListYearOfBirth().get(1) : null, sql, params, "YEAR(e.date_of_birth)");        }        QueryUtils.filter(employeesDTO.getListGender(), sql, params, "e.gender_code");        QueryUtils.filter(employeesDTO.getListReligionCode(), sql, params, "e.religion_code");        QueryUtils.filter(employeesDTO.getListEthnicCode(), sql, params, "e.ethnic_code");        QueryUtils.filter(employeesDTO.getListMaritalStatusCode(), sql, params, "e.marital_status_code");        QueryUtils.filter(employeesDTO.getPersonalId(), sql, params, "e.personal_id");        QueryUtils.filter(employeesDTO.getTaxNo(), sql, params, "e.tax_no");        QueryUtils.filter(employeesDTO.getInsuranceNo(), sql, params, "e.insurance_no");        QueryUtils.filter(employeesDTO.getMobileNumber(), sql, params, "e.mobile_number");        QueryUtils.filter(employeesDTO.getEmail(), sql, params, "e.email");        if(employeesDTO.getListMajorLevelId() != null && !employeesDTO.getListMajorLevelId().isEmpty()){            sql.append(" AND EXISTS(select 1 from hr_employee_infos i where i.employee_id = e.employee_id and i.major_level_id IN (:listMajorLevelId))");            params.put("listMajorLevelId", employeesDTO.getListMajorLevelId());        }        QueryUtils.filter(employeesDTO.getListPositionId(), sql, params, "e.position_id");        sql.append(" ORDER BY e.employee_id");        return getListPagination(sql.toString(), params, employeesDTO.getStartRecord(), employeesDTO.getPageSize(), WorkedOutsidesDTO.class);    }}