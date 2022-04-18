package vn.com.mbbank.repositories.impl;

import vn.com.mbbank.core.dto.response.BaseResultSelect;
import vn.com.mbbank.core.repositories.impl.BaseRepositoryImpl;
import vn.com.mbbank.dto.EmployeesDTO;
import vn.com.mbbank.dto.ProjectMembersDTO;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;

import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.QueryUtils;
import vn.com.mbbank.utils.Utils;

/**
 * Autogen class Repository Impl: Lớp thao tác danh sach qua tham gia du an nhan
 * vien
 *
 * @author ToolGen
 * @date Sun Mar 20 22:27:27 ICT 2022
 */
@Repository
public class ProjectMembersRepositoryImpl extends BaseRepositoryImpl {

    /**
     * Lay danh sach qua trinh tham gia du an nhan vien
     *
     * @param projectMembersDTO: params client truyen len
     * @return
     */
    public BaseResultSelect getProjectMember(ProjectMembersDTO projectMembersDTO) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.project_member_id projectMemberId,"
                + "    a.from_date fromDate,"
                + "    a.to_date toDate,"
                + "    b.prj_name projectName,"
                + "    a.project_id projectId,"
                + "    c.job_name jobName,"
                + "    c.job_id jobId,"
                + "    a.note note"
                + " FROM hr_project_members a"
                + " LEFT JOIN mp_projects b ON a.project_id = b.prj_id "
                + " LEFT JOIN mp_jobs c ON a.job_id = c.job_id "
                + " WHERE a.employee_id = :employeeId AND NVL(a.flag_status, " + Constants.STATUS.IS_ACTIVE + ") = :flagStatus"
                + " ORDER BY a.from_date DESC");
        HashMap<String, Object> params = new HashMap<>();
        params.put("employeeId", projectMembersDTO.getEmployeeId());
        params.put("flagStatus", Constants.STATUS.IS_ACTIVE);
        return getListDataAndCount(sql, params, Utils.NVL(projectMembersDTO.getStartRecord()), Utils.NVL(projectMembersDTO.getPageSize(), 10), ProjectMembersDTO.class);
    }
    
    /**
     * Kiem tra su lien tuc cua qua trinh.
     *
     * @param projectMembersDTO param
     * @return true neu co loi
     */
    public boolean isConflictProcess(ProjectMembersDTO projectMembersDTO) {
        StringBuilder sql = new StringBuilder("  SELECT sp.from_date fromDate, sp.to_date toDate "
                + " FROM hr_project_members sp "
                + " WHERE sp.employee_id = :employeeId "
                + " AND sp.project_member_id != :projectMemberId"
                + " AND sp.project_id = :projectId "
                + " AND (sp.to_date IS NULL OR :fromDate <= sp.to_date) "
                + (projectMembersDTO.getToDate() == null ? "" : " AND sp.from_date <= :toDate"));

        HashMap<String, Object> params = new HashMap<>();
        params.put("employeeId", projectMembersDTO.getEmployeeId());
        params.put("projectId", projectMembersDTO.getProjectId());
        params.put("projectMemberId", Utils.NVL(projectMembersDTO.getProjectMemberId(), 0L));
        params.put("fromDate", projectMembersDTO.getFromDate());
        if (projectMembersDTO.getToDate() != null) {
            params.put("toDate", projectMembersDTO.getToDate());
        }
        List<ProjectMembersDTO> lst = (List<ProjectMembersDTO>) getListData(sql, params, null, null, ProjectMembersDTO.class);
        if (lst == null || lst.isEmpty()) {
            return false;
        } else if (lst.size() > 1) {
            return true;
        } else if (lst.size() == 1) {
            ProjectMembersDTO dto = lst.get(0);
            if (dto.getToDate() != null || !dto.getFromDate().before(projectMembersDTO.getFromDate())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tiem kiem qua trinh tham gia du an
     * @param employeesDTO
     * @return
     */
    public BaseResultSelect searchProjectMembers(EmployeesDTO employeesDTO) {
        StringBuilder sql = new StringBuilder(" SELECT e.employee_id,"
                + " e.employee_code,"
                + " e.full_name,"
                + " e.flag_status flagStatus,"
                + " NVL(mo.full_name, mo.org_name) orgName,"
                + " hpm.from_date fromDate,"
                + " hpm.to_date toDate,"
                + " mj.job_name jobName,"
                + " hpm.note note,"
                + " mp.prj_name projectName"
                + " FROM hr_employees e"
                + " LEFT JOIN mp_organizations mo ON mo.org_id = e.organization_id"
                + " JOIN hr_project_members hpm ON e.employee_id = hpm.employee_id"
                + " JOIN mp_projects mp ON hpm.project_id = mp.prj_id"
                + " LEFT JOIN mp_jobs mj ON hpm.job_id = mj.job_id"
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
        return getListPagination(sql.toString(), params, employeesDTO.getStartRecord(), employeesDTO.getPageSize(), ProjectMembersDTO.class);
    }
}
