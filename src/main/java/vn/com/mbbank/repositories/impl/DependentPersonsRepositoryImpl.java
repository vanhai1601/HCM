package vn.com.mbbank.repositories.impl;import org.springframework.stereotype.Repository;import vn.com.mbbank.core.dto.response.BaseResultSelect;import vn.com.mbbank.core.repositories.impl.BaseRepositoryImpl;import vn.com.mbbank.dto.DependentPersonsDTO;import vn.com.mbbank.dto.EmployeesDTO;import vn.com.mbbank.utils.Constants;import vn.com.mbbank.utils.QueryUtils;import vn.com.mbbank.utils.Utils;import java.util.HashMap;import java.util.List;/** * Autogen class Repository Impl: Lớp thao tác danh sach giam tru gia canh * * @author ToolGen * @date Sun Mar 20 21:40:57 ICT 2022 */@Repositorypublic class DependentPersonsRepositoryImpl extends BaseRepositoryImpl {    /**     * Lay danh sach giam tru gia canh     *     * @param dependentPersonsDTO: params client truyen len     * @return     */    public BaseResultSelect getDependentPersons(DependentPersonsDTO dependentPersonsDTO) {        String sql = "SELECT dp.dependent_person_id dependentPersonId,"                + "    dp.family_relationship_id familyRelationshipId,"                + "    vl1.label relationTypeName,"                + "    hfr.full_name fullName,"                + "    hfr.date_of_birth dateOfBirth,"                + "    hfr.tax_number taxNumber,"                + "    hfr.personal_id_number personalIdNumber,"                + "    dp.personal_id personalId,"                + "    dp.code_no codeNo,"                + "    dp.book_no bookNo,"                + "    dp.nation_code nationCode,"                + "    dp.province_code provinceCode,"                + "    dp.district_code districtCode,"                + "    dp.ward_code wardCode,"                + "    dp.from_date fromDate,"                + "    dp.to_date toDate,"                + "    dp.note note"                + " FROM hr_dependent_persons dp"                + " LEFT JOIN hr_family_relationships hfr ON dp.family_relationship_id = hfr.family_relationship_id"                + " LEFT JOIN v_lookup vl1 ON vl1.code = hfr.relation_type_code AND vl1.type_code = :typeCode"                + " WHERE dp.employee_id = :employeeId AND NVL(dp.flag_status, " + Constants.STATUS.IS_ACTIVE + ") = :flagStatus";        HashMap<String, Object> params = new HashMap<>();        params.put("typeCode", Constants.LOOKUP_CODES.MOI_QUAN_HE_NT);        params.put("employeeId", dependentPersonsDTO.getEmployeeId());        params.put("flagStatus", Constants.STATUS.IS_ACTIVE);        BaseResultSelect resultData = getListPagination(sql, params, dependentPersonsDTO.getStartRecord(), dependentPersonsDTO.getPageSize(), DependentPersonsDTO.class);        return resultData;    }    /**     * Kiem tra su lien tuc cua qua trinh.     *     * @param dto param     * @return true neu co loi     */    public boolean isConflictProcess(DependentPersonsDTO dto) {        StringBuilder sql = new StringBuilder("  SELECT sp.from_date fromDate, sp.to_date toDate "                + " FROM hr_dependent_persons sp "                + " WHERE sp.employee_id = :employeeId "                + " AND NVL(sp.flag_status, :flagStatus) = :flagStatus"                + " AND sp.dependent_person_id != :dependentPersonId"                + " AND sp.family_relationship_id = :familyRelationshipsId "                + " AND (sp.to_date IS NULL OR :fromDate <= sp.to_date) "                + (dto.getToDate() == null ? "" : " AND sp.from_date <= :toDate"));        HashMap<String, Object> params = new HashMap<>();        params.put("employeeId", dto.getEmployeeId());        params.put("flagStatus", Constants.STATUS.IS_ACTIVE);        params.put("dependentPersonId", Utils.NVL(dto.getDependentPersonId(), 0L));        params.put("familyRelationshipsId", dto.getFamilyRelationshipId());        params.put("fromDate", dto.getFromDate());        if (dto.getToDate() != null) {            params.put("toDate", dto.getToDate());        }        List<DependentPersonsDTO> lst = (List<DependentPersonsDTO>) getListData(sql, params, null, null, DependentPersonsDTO.class);        if (lst == null || lst.isEmpty()) {            return false;        } else if (lst.size() > 1) {            return true;        } else if (lst.size() == 1) {            DependentPersonsDTO dependentPersonsDTO = lst.get(0);            if (dependentPersonsDTO.getToDate() != null || !dependentPersonsDTO.getFromDate().before(dto.getFromDate())) {                return true;            }        }        return false;    }    public BaseResultSelect searchDependentPersons(EmployeesDTO employeesDTO) {        StringBuilder sql = new StringBuilder("SELECT e.employee_code, " +                "       e.full_name employeeName, " +                "       mo.org_name, " +                "       vl.label relationTypeName, " +                "       hfr.full_name, " +                "       hfr.tax_number, " +                "       hfr.date_of_birth, " +                "       hdp.from_date, " +                "       hdp.to_date, " +                "       e.flag_status " +                "FROM hr_employees e " +                "JOIN mp_organizations mo ON e.organization_id = mo.org_id " +                "JOIN hr_dependent_persons hdp ON e.employee_id = hdp.employee_id " +                "JOIN hr_family_relationships hfr ON hfr.family_relationship_id = hdp.family_relationship_id " +                "JOIN v_lookup vl ON vl.code = hfr.relation_type_code " +                "AND vl.type_code = :relationTypeName " +                "WHERE 1 = 1 ");        HashMap<String, Object> params = new HashMap<>();        params.put("relationTypeName", Constants.LOOKUP_CODES.MOI_QUAN_HE_NT);        QueryUtils.filter(employeesDTO.getEmployeeCode(), sql, params, "e.employee_code");        QueryUtils.filter(employeesDTO.getFullName(), sql, params, "e.full_name");        QueryUtils.filterEq(employeesDTO.getEmpTypeCode(), sql, params, "e.emp_type_code");        QueryUtils.filter(employeesDTO.getFlagStatus(), sql, params, "e.flag_status");        QueryUtils.filter(employeesDTO.getListStatus(), sql, params, "e.flag_status");        if (!Utils.isNullObject(employeesDTO.getOrganizationId())) {            sql.append(" AND mo.path_id LIKE :orgId");            params.put("orgId", "%/" + employeesDTO.getOrganizationId() + "/%");        }        if (employeesDTO.getListDateOfBirth() != null && !employeesDTO.getListDateOfBirth().isEmpty()) {            QueryUtils.filterGe(Utils.convertStringToDate(employeesDTO.getListDateOfBirth().get(0)), sql, params, "e.date_of_birth");            QueryUtils.filterLe(employeesDTO.getListDateOfBirth().size() > 1 ? Utils.convertStringToDate(employeesDTO.getListDateOfBirth().get(1)) : null, sql, params, "e.date_of_birth");        }        if (employeesDTO.getListMonthOfBirth() != null && !employeesDTO.getListMonthOfBirth().isEmpty()) {            QueryUtils.filterGe(employeesDTO.getListMonthOfBirth().get(0), sql, params, "MONTH(e.date_of_birth)");            QueryUtils.filterLe(employeesDTO.getListMonthOfBirth().size() > 1 ? employeesDTO.getListMonthOfBirth().get(1) : null, sql, params, "MONTH(e.date_of_birth)");        }        if (employeesDTO.getListYearOfBirth() != null && !employeesDTO.getListYearOfBirth().isEmpty()) {            QueryUtils.filterGe(employeesDTO.getListYearOfBirth().get(0), sql, params, "YEAR(e.date_of_birth)");            QueryUtils.filterLe(employeesDTO.getListYearOfBirth().size() > 1 ? employeesDTO.getListYearOfBirth().get(1) : null, sql, params, "YEAR(e.date_of_birth)");        }        QueryUtils.filter(employeesDTO.getListGender(), sql, params, "e.gender_code");        QueryUtils.filter(employeesDTO.getListReligionCode(), sql, params, "e.religion_code");        QueryUtils.filter(employeesDTO.getListEthnicCode(), sql, params, "e.ethnic_code");        QueryUtils.filter(employeesDTO.getListMaritalStatusCode(), sql, params, "e.marital_status_code");        QueryUtils.filter(employeesDTO.getPersonalId(), sql, params, "e.personal_id");        QueryUtils.filter(employeesDTO.getTaxNo(), sql, params, "e.tax_no");        QueryUtils.filter(employeesDTO.getInsuranceNo(), sql, params, "e.insurance_no");        QueryUtils.filter(employeesDTO.getMobileNumber(), sql, params, "e.mobile_number");        QueryUtils.filter(employeesDTO.getEmail(), sql, params, "e.email");        if(employeesDTO.getListMajorLevelId() != null && !employeesDTO.getListMajorLevelId().isEmpty()){            sql.append(" AND EXISTS(select 1 from hr_employee_infos i where i.employee_id = e.employee_id and i.major_level_id IN (:listMajorLevelId))");            params.put("listMajorLevelId", employeesDTO.getListMajorLevelId());        }        QueryUtils.filter(employeesDTO.getListPositionId(), sql, params, "e.position_id");        sql.append(" ORDER BY e.employee_id");        return getListPagination(sql.toString(), params, employeesDTO.getStartRecord(), employeesDTO.getPageSize(), DependentPersonsDTO.class);    }}