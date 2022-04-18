package vn.com.mbbank.repositories.impl;import vn.com.mbbank.core.dto.response.BaseResultSelect;import vn.com.mbbank.core.repositories.impl.BaseRepositoryImpl;import vn.com.mbbank.dto.SalaryProcessDTO;import org.springframework.stereotype.Repository;import java.util.List;import java.util.HashMap;import vn.com.mbbank.utils.Constants;import vn.com.mbbank.utils.Utils;/** * Autogen class Repository Impl: Lớp thao tác danh sach dien bien luong * * @author ToolGen * @date Sun Mar 20 21:35:25 ICT 2022 */@Repositorypublic class SalaryProcessRepositoryImpl extends BaseRepositoryImpl  {    /**     * Lay danh sach dien bien luong     *     * @param salaryProcessDTO: params client truyen len     * @return     */    public BaseResultSelect getSalaryProcess(SalaryProcessDTO salaryProcessDTO) {        StringBuilder sql = new StringBuilder();        sql.append("SELECT s.salary_process_id salaryProcessId,"                + "    s.employee_id employeeId,"                + "    (select g.salarygrade_name from pr_ssm_salarygrades g where g.salarygrade_id = s.salary_grade_id) salaryGradeName,"                + "    (select r.salaryrank_name from pr_ssm_salaryranks r where r.salaryrank_id = s.salary_rank_id) salaryRankName,"                + "    s.salary_grade_id salaryGradeId,"                + "    s.salary_rank_id salaryRankId,"// thiếu                + "    s.salary_amount salaryAmount,"                + "    s.salary_percent salaryPercent,"                + "    s.salary_factor salaryFactor,"                + "    s.from_date fromDate,"                + "    s.to_date toDate,   "                + "    s.note note   "                + " FROM hr_salary_process s"                + " WHERE s.employee_id = :employeeId AND NVL(s.flag_status, " + Constants.STATUS.IS_ACTIVE + ") = :flagStatus"                + " ORDER BY s.from_date DESC");        HashMap<String, Object> params = new HashMap<>();        params.put("employeeId", salaryProcessDTO.getEmployeeId());        params.put("flagStatus", Constants.STATUS.IS_ACTIVE);        return getListDataAndCount(sql, params, Utils.NVL(salaryProcessDTO.getStartRecord()), Utils.NVL(salaryProcessDTO.getPageSize(), 10), SalaryProcessDTO.class);    }    /**     * Kiem tra su lien tuc cua qua trinh.     *     * @param salaryProcessDTO param     * @return true neu co loi     */    public boolean isConflictProcess(SalaryProcessDTO salaryProcessDTO) {        StringBuilder sql = new StringBuilder("  SELECT sp.from_date fromDate, sp.to_date toDate "                + " FROM hr_salary_process sp "                + " WHERE sp.employee_id = :employeeId "                + " AND NVL(sp.flag_status, :flagStatus) = :flagStatus "                + " AND sp.salary_process_id != :salaryProcessId "                + " AND (sp.to_date IS NULL OR :fromDate <= sp.to_date) "                + (salaryProcessDTO.getToDate() == null ? "" : " AND sp.from_date <= :toDate"));        HashMap<String, Object> params = new HashMap<>();        params.put("employeeId", salaryProcessDTO.getEmployeeId());        params.put("flagStatus", Constants.STATUS.IS_ACTIVE);        params.put("salaryProcessId", Utils.NVL(salaryProcessDTO.getSalaryProcessId(), 0L));        params.put("fromDate", salaryProcessDTO.getFromDate());        if (salaryProcessDTO.getToDate() != null) {            params.put("toDate", salaryProcessDTO.getToDate());        }        List<SalaryProcessDTO> lst = (List<SalaryProcessDTO>) getListData(sql, params, null, null, SalaryProcessDTO.class);        if (lst == null || lst.isEmpty()) {            return false;        } else if (lst.size() > 1) {            return true;        } else if (lst.size() == 1) {            SalaryProcessDTO dto = lst.get(0);            if (dto.getToDate() != null || !dto.getFromDate().before(salaryProcessDTO.getFromDate())) {                return true;            }        }        return false;    }}