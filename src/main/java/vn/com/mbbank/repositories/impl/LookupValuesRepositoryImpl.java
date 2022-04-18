package vn.com.mbbank.repositories.impl;

import org.springframework.stereotype.Repository;
import vn.com.mbbank.core.repositories.impl.BaseRepositoryImpl;
import vn.com.mbbank.dto.LookupValuesDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.Utils;

@Repository
public class LookupValuesRepositoryImpl extends BaseRepositoryImpl {

    public List<LookupValuesDTO> getListDataByType(String typeCode, String parentCode) {
        StringBuilder sql = new StringBuilder("SELECT lv.lva_value value, "
                + " lv.lva_mean label"
                + " FROM lookup_values lv"
                + " JOIN lookup_codes lc on lc.lco_id = lv.lco_id"
                + " WHERE lc.lco_name = :typeCode ");
        HashMap<String, Object> params = new HashMap<>();
        if (!Utils.isNullObject(parentCode)) {
            sql.append(" AND lv.lva_value LIKE :parentCode");
            params.put("parentCode", parentCode + "%");
        }
        sql.append(" AND NVL(lv.flag_status, :flagStatus) = :flagStatus");
        params.put("flagStatus", Constants.STATUS.IS_ACTIVE);
        params.put("typeCode", typeCode);
        return (List<LookupValuesDTO>) getListData(sql, params, null, null, LookupValuesDTO.class);
    }

    public Map<String, String> getMapLookupByType(String typeCode, String parentCode) {
        StringBuilder sql = new StringBuilder("SELECT lv.lva_value value, "
                + " lv.lva_mean label"
                + " FROM lookup_values lv"
                + " JOIN lookup_codes lc on lc.lco_id = lv.lco_id"
                + " WHERE lc.lco_name = :typeCode ");
        HashMap<String, Object> params = new HashMap<>();
        if (!Utils.isNullObject(parentCode)) {
            sql.append(" AND lv.lva_value LIKE :parentCode");
            params.put("parentCode", parentCode + "%");
        }
        sql.append(" AND NVL(lv.flag_status, :flagStatus) = :flagStatus");
        params.put("flagStatus", Constants.STATUS.IS_ACTIVE);
        params.put("typeCode", typeCode);
        List<LookupValuesDTO> listResult = getListData(sql.toString(), params, LookupValuesDTO.class);
        Map<String, String> mapResult = new HashMap<>();
        for (LookupValuesDTO lookupValuesDTO : listResult) {
            mapResult.put(lookupValuesDTO.getLabel().toLowerCase(), lookupValuesDTO.getValue());
        }
        return mapResult;
    }

    public LookupValuesDTO getLookupValue(String typeCode, String code) {
        StringBuilder sql = new StringBuilder("SELECT lv.lva_value value, "
                + " lv.lva_mean label"
                + " FROM lookup_values lv"
                + " JOIN lookup_codes lc on lc.lco_id = lv.lco_id"
                + " WHERE lc.lco_name = :typeCode ");
        HashMap<String, Object> params = new HashMap<>();
        sql.append(" AND lv.lva_value LIKE :code");
        params.put("code", code);
        sql.append(" AND NVL(lv.flag_status, :flagStatus) = :flagStatus");
        params.put("flagStatus", Constants.STATUS.IS_ACTIVE);
        params.put("typeCode", typeCode);
        return getFirstData(sql.toString(), params, LookupValuesDTO.class);
    }

    public List<LookupValuesDTO> getListFullProvinceDistrictWard() {
        StringBuilder sql = new StringBuilder("SELECT"
                + " prov.code provinceCode,"
                + " prov.label provinceLabel,"
                + " dis.code districtCode, "
                + " dis.label districtLabel,"
                + " ward.code wardCode, "
                + " ward.label wardLabel"
                + " FROM v_lookup ward, v_lookup dis, v_lookup prov"
                + " WHERE ward.type_code = :typeCodeWard"
                + " AND dis.type_code = :typeCodeDis"
                + " AND prov.type_code = :typeCodePro"
                + " AND ward.code LIKE dis.code || '%'"
                + " AND dis.code LIKE prov.code || '%'"
                + "ORDER BY prov.code, dis.code, ward.code ");
        HashMap<String, Object> params = new HashMap<>();
        params.put("typeCodeWard", Constants.LOOKUP_CODES.XA);
        params.put("typeCodeDis", Constants.LOOKUP_CODES.HUYEN);
        params.put("typeCodePro", Constants.LOOKUP_CODES.TINH);
        return (List<LookupValuesDTO>) getListData(sql, params, null, null, LookupValuesDTO.class);
    }
}
