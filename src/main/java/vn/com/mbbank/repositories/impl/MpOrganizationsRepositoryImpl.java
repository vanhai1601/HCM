package vn.com.mbbank.repositories.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import vn.com.mbbank.core.dto.response.BaseResultSelect;
import vn.com.mbbank.core.repositories.impl.BaseRepositoryImpl;
import vn.com.mbbank.dto.OrganizationDTO;
import vn.com.mbbank.dto.TreeDTO;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.QueryUtils;
import vn.com.mbbank.utils.Utils;

import java.util.HashMap;
import java.util.List;

@Repository
public class MpOrganizationsRepositoryImpl extends BaseRepositoryImpl {

    public List<TreeDTO> getRootNode() {
        StringBuilder sql = new StringBuilder(" SELECT "
                + " org_id nodeId,"
                + " org_name name,"
                + " org_code code,"
                + " parent_id parentId,"
                + " path_id path"
                + " FROM mp_organizations"
                + " WHERE parent_id IS NULL");
        HashMap<String, Object> params = new HashMap<>();
        sql.append(" ORDER BY path_order, path_id");
        return (List<TreeDTO>) getListData(sql, params, null, null, TreeDTO.class);
    }

    public List<TreeDTO> getNodeChildren(Long parentId) {
        StringBuilder sql = new StringBuilder(" SELECT "
                + " org_id nodeId,"
                + " org_name name,"
                + " org_code code,"
                + " parent_id parentId,"
                + " path_order path"
                + " FROM mp_organizations"
                + " WHERE parent_id = :parentId");
        HashMap<String, Object> params = new HashMap<>();
        params.put("parentId", parentId);
        sql.append(" ORDER BY path_order, path_id");
        return (List<TreeDTO>) getListData(sql, params, null, null, TreeDTO.class);
    }

    public List<TreeDTO> searchOrg(Long parentId, String keyword) {
        StringBuilder sql = new StringBuilder(" SELECT "
                + " org_id nodeId,"
                + " org_name name,"
                + " org_code code,"
                + " parent_id parentId,"
                + " path_id path"
                + " FROM mp_organizations"
                + " WHERE 1 = 1 ");
        HashMap<String, Object> params = new HashMap<>();

        if (parentId != null && parentId > 0L) {
            sql.append(" AND path_id LIKE :path");
            params.put("path", "%/" + parentId + "/%");
        }
        if (!Utils.isNullOrEmpty(keyword)) {
            sql.append(" AND (LOWER(org_code) LIKE :keyword OR LOWER(org_name) LIKE :keyword)");
            params.put("keyword", "%" + Utils.filterLikeKeyword(keyword) + "%");
        }
        sql.append(" ORDER BY path_order, path_id");

        return (List<TreeDTO>) getListData(sql, params, null, null, TreeDTO.class);
    }

    public List<TreeDTO> searchOrgByPath(List<String> listPath) {
        if (listPath == null || listPath.isEmpty()) {
            return null;
        }
        StringBuilder sql = new StringBuilder(" SELECT "
                + " org_id nodeId,"
                + " org_name name,"
                + " org_code code,"
                + " parent_id parentId"
                + " FROM mp_organizations"
                + " WHERE ( 1 != 1 ");
        HashMap<String, Object> params = new HashMap<>();
        for (int i = 0; i < listPath.size(); i++) {
            sql.append(" OR :path").append(i).append(" LIKE '%/' || org_id || '/%'");
            params.put("path" + i, listPath.get(i));
        }
        sql.append(" ) ORDER BY path_order");

        return (List<TreeDTO>) getListData(sql, params, null, null, TreeDTO.class);
    }

    public List<TreeDTO> getListTreeNodes() {
        StringBuilder sql = new StringBuilder(" SELECT "
                + " org_id nodeId,"
                + " org_name name,"
                + " org_code code,"
                + " parent_id parentId"
                + " FROM mp_organizations WHERE NVL(flag_status, " + Constants.STATUS.IS_ACTIVE + ") = :flagStatus");
        HashMap<String, Object> params = new HashMap<>();
        params.put("flagStatus", Constants.STATUS.IS_ACTIVE);
        sql.append(" ORDER BY path_order");
        return (List<TreeDTO>) getListData(sql, params, null, null, TreeDTO.class);
    }

    public BaseResultSelect getByParent(OrganizationDTO dto) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> params = new HashMap<String, Object>();
        sql.append(" SELECT org.org_id orgId, org.org_name orgName, org.path_id path, "
                + "       org.parent_id parentId, parent.org_name parentName "
                + " FROM mp_organizations org LEFT JOIN mp_organizations parent ON org.parent_id = parent.org_id "
                + "  WHERE  org.path_id like :path AND NVL(org.flag_status, :flagStatus) = :flagStatus ");
        QueryUtils.filter(dto.getOrgName(), sql, params, "org.org_name", "orgName");

        params.put("path", "%/" + dto.getParentId() + "/%");
        params.put("flagStatus", Constants.STATUS.IS_ACTIVE);
        sql.append(" ORDER BY org.path_order ");
        return getListDataAndCount(sql, params, Utils.NVL(dto.getStartRecord()), Utils.NVL(dto.getPageSize(), 10), OrganizationDTO.class);
    }
}
