package vn.com.mbbank.utils;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

import java.lang.reflect.Type;
import java.util.Map;

@Data
public class InfoChangeBean {
    private String dataType;
    private String createdBy;
    private String jsonDataBefore;
    private String jsonDataAfter;
    private Long employeeId;

    public InfoChangeBean() {
    }

    public InfoChangeBean(Long employeeId, String dataType, String createdBy) {
        this.dataType = dataType;
        this.employeeId = employeeId;
        this.createdBy = createdBy;
    }

    public void setDataBefore(Object obj) {
        Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();
        this.jsonDataBefore = g.toJson(obj);
    }

    public void setDataAfter(Object obj) {
        Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();
        this.jsonDataAfter = g.toJson(obj);
    }

    public Map<String, MapDifference.ValueDifference<Object>> getDifference() {
        Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();
        Type mapType = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> firstMap = g.fromJson(jsonDataBefore, mapType);
        Map<String, Object> secondMap = g.fromJson(jsonDataAfter, mapType);
        return Maps.difference(firstMap, secondMap).entriesDiffering();
    }
}
