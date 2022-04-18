package vn.com.mbbank.repositories.impl;

import org.springframework.stereotype.Repository;
import vn.com.mbbank.core.dto.response.BaseResultSelect;
import vn.com.mbbank.core.repositories.impl.BaseRepositoryImpl;
import vn.com.mbbank.dto.SalaryProcessDTO;
import vn.com.mbbank.dto.SearchItemDTO;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.Utils;

import java.util.HashMap;
import java.util.List;

@Repository
public class UserBookmarksRepositoryImpl extends BaseRepositoryImpl {

    public List getSearchItem(String moduleCode) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT s.*  "
                + " FROM hr_search_item s"
                + " JOIN hr_module_item m ON m.search_item_id = s.search_item_id"
                + " WHERE NVL(s.flag_status, :flagStatus) = :flagStatus"
                + " AND m.module_code = :moduleCode"
                + " ORDER BY s.search_item_id");
        HashMap<String, Object> params = new HashMap<>();
        params.put("moduleCode", moduleCode);
        params.put("flagStatus", Constants.STATUS.IS_ACTIVE);
        return getListData(sql.toString(), params, SearchItemDTO.class);
    }
}
