package vn.com.mbbank.repositories.impl;

import vn.com.mbbank.core.repositories.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import vn.com.mbbank.dto.MpPositionsDTO;
import vn.com.mbbank.utils.Constants;


@Repository
public class MpPositionsRepositoryImpl extends BaseRepositoryImpl {

    
    public List<MpPositionsDTO> getMpPositionsByOrgId(MpPositionsDTO dto) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.pos_id posId, p.pos_code posCode, p.pos_name posName"
                + " FROM mp_positions p, mp_organizations o, mp_organizations i"
                + " WHERE p.org_id = o.org_id"
                + " AND i.org_id = :inputOrgId"
                + " AND i.path_id like o.path_id || '%'"
                + "AND NVL(p.flag_status, " + Constants.STATUS.IS_ACTIVE + ") = :flagStatus");
        HashMap<String, Object> params = new HashMap<>();
        params.put("inputOrgId", dto.getOrgId());
        params.put("flagStatus", Constants.STATUS.IS_ACTIVE);
        return (List<MpPositionsDTO>) getListData(sql, params, null, null, MpPositionsDTO.class);
    }

}
