/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.mbbank.utils;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author author
 */
public class QueryUtils {

    /**
     * kiem tra 1 xau rong hay null khong
     *
     * @param str : xau
     * @param queryString
     * @param mapParam
     * @param field
     */
    public static void filter(String str, StringBuilder queryString, Map<String, Object> mapParam, String field) {
        if ((str != null) && !"".equals(str.trim())) {
            String key = Utils.convertSQLNameToJavaName(field);
            queryString.append(" AND LOWER(").append(field).append(") LIKE :").append(key).append(" ESCAPE '/'");
            str = str.trim().replaceAll(" +", " ");
            str = "%" + str.trim().toLowerCase().replace("/", "//").replace("_", "/_").replace("%", "/%") + "%";
            mapParam.put(key, str);
        }
    }

    /**
     *
     * @param n
     * @param queryString
     * @param mapParam
     * @param field
     */
//    public static void filterIn(List<?> n, StringBuilder queryString, Map<String, Object> mapParam, String field) {
//        if (n == null) {
//            return;
//        }
//        int length = n.size();
//        if (length > 0) {
//            queryString.append(" AND ").append(field).append(" IN ( ");
//            for (int i = 0; i < length; i++) {
//                String key = Utils.convertSQLNameToJavaName(field);
//                if (i == length - 1) {
//                    queryString.append(":").append(key).append(i).append(")");
//                } else {
//                    queryString.append(":").append(key).append(i).append(",");
//                }
//                mapParam.put(key + i, n.get(i));
//            }
//        }
//    }

    /**
     * kiem tra 1 xau rong hay null khong
     *
     * @param str : xau
     * @param queryString
     * @param mapParam
     * @param fields
     */
    public static void filter(String str, StringBuilder queryString, Map<String, Object> mapParam, String... fields) {
        if ((str != null) && !"".equals(str.trim())) {
            queryString.append(" AND ( 0 = 1 ");
            str = str.trim().replaceAll(" +", " ");
            str = "%" + str.trim().toLowerCase().replace("/", "//").replace("_", "/_").replace("%", "/%") + "%";
            int i = 0;
            for (String field : fields) {
                String key = Utils.convertSQLNameToJavaName(field);
                queryString.append(" OR LOWER(").append(field).append(") LIKE :").append(key).append(i).append(" ESCAPE '/'");
                mapParam.put(key + i, str);
                i++;
            }
            queryString.append(")");
        }
    }

    /**
     *
     * @param str
     * @param queryString
     * @param mapParam
     * @param field
     */
    public static void filterEq(String str, StringBuilder queryString, Map<String, Object> mapParam, String field) {
        if ((str != null) && !"".equals(str.trim())) {
            String key = Utils.convertSQLNameToJavaName(field);
            queryString.append(" AND LOWER(").append(field).append(") = :").append(key);
            str = str.trim().replaceAll(" +", " ");
            str = str.trim().toLowerCase();
            mapParam.put(key, str);
        }
    }

    /**
     * kiem tra 1 xau rong hay null khong
     *
     * @param n So
     * @param queryString
     * @param mapParam
     * @param field
     */
    public static void filter(Long n, StringBuilder queryString, Map<String, Object> mapParam, String field) {
        if ((n != null) && (n > 0L)) {
            String key = Utils.convertSQLNameToJavaName(field);
            queryString.append(" AND ").append(field).append(" = :").append(key);
            mapParam.put(key, n);
        }
    }

    /**
     *
     * @param n
     * @param queryString
     * @param mapParam
     * @param field
     */
    public static void filter(Long[] n, StringBuilder queryString, Map<String, Object> mapParam, String field) {
        if (n == null) {
            return;
        }
        int length = n.length;
        if (length > 0) {
            queryString.append(" AND ").append(field).append(" IN ( ");
            for (int i = 0; i < length; i++) {
                String key = Utils.convertSQLNameToJavaName(field);
                if (i == length - 1) {
                    queryString.append(":").append(key).append(i).append(")");
                } else {
                    queryString.append(":").append(key).append(i).append(",");
                }
                mapParam.put(key + i, n[i]);
            }
        }
    }

    /**
     *
     * @param n
     * @param queryString
     * @param mapParam
     * @param field
     */
    public static void filter(List<?> n, StringBuilder queryString, Map<String, Object> mapParam, String field) {
        if (n == null) {
            return;
        }
        int length = n.size();
        if (length > 0) {
            queryString.append(" AND ").append(field).append(" IN ( ");
            String key = Utils.convertSQLNameToJavaName(field);
            for (int i = 0; i < length; i++) {
                if (i == length - 1) {
                    queryString.append(":").append(key).append(i).append(")");
                } else {
                    queryString.append(":").append(key).append(i).append(",");
                }
                mapParam.put(key + i, n.get(i));
            }
        }
    }

    /**
     * kiem tra 1 xau rong hay null khong
     *
     * @param n So
     * @param queryString
     * @param mapParam
     * @param field
     */
    public static void filter(Integer n, StringBuilder queryString, Map<String, Object> mapParam, String field) {
        if ((n != null) && (n > 0)) {
            String key = Utils.convertSQLNameToJavaName(field);
            queryString.append(" AND ").append(field).append(" = :").append(key);
            mapParam.put(key, n);
        }
    }

    /**
     * kiem tra 1 xau rong hay null khong
     *
     * @param n So
     * @param queryString
     * @param mapParam
     * @param field
     */
    public static void filter(Double n, StringBuilder queryString, Map<String, Object> mapParam, String field) {
        if ((n != null) && (n > 0)) {
            String key = Utils.convertSQLNameToJavaName(field);
            queryString.append(" AND ").append(field).append(" = :").append(key);
            mapParam.put(key, n);
        }
    }

    /**
     * kiem tra 1 xau rong hay null khong
     *
     * @param date So
     * @param queryString
     * @param field
     * @param mapParam
     */
    public static void filter(Date date, StringBuilder queryString, Map<String, Object> mapParam, String field) {
        if ((date != null)) {
            String key = Utils.convertSQLNameToJavaName(field);
            queryString.append(" AND ").append(field).append(" = :").append(key);
            mapParam.put(key, date);
        }
    }

    /**
     * Kiem tra lon hon hoac bang.
     *
     * @param obj So
     * @param queryString
     * @param paramMap
     * @param field
     */
    public static void filterGe(Object obj, StringBuilder queryString, Map<String, Object> paramMap, String field) {
        if (obj != null && !"".equals(obj)) {
            String key = Utils.convertSQLNameToJavaName(field);
            queryString.append(" AND ").append(field).append(" >= :").append(key);
            paramMap.put(key, obj);
        }
    }

    /**
     * Kiem tra nho hon hoac bang.
     *
     * @param obj So
     * @param queryString
     * @param paramMap
     * @param field
     */
    public static void filterLe(Object obj, StringBuilder queryString, Map<String, Object> paramMap, String field) {
        if (obj != null && !"".equals(obj)) {
            String key = Utils.convertSQLNameToJavaName(field);
            queryString.append(" AND ").append(field).append(" <= :").append(key);
            paramMap.put(key, obj);
        }
    }

    /**
     * filter for inserting preparedStatement
     *
     * @param value
     * @param index
     * @param preparedStatement
     * @throws Exception
     */
    public static void filter(String value, PreparedStatement preparedStatement, int index)
            throws Exception {

        if (value != null) {
            preparedStatement.setString(index, value.trim());
        } else {
            preparedStatement.setNull(index, java.sql.Types.NULL);
        }
    }

    /**
     *
     * @param value
     * @param preparedStatement
     * @param index
     * @throws Exception
     */
    public static void filter(Double value, PreparedStatement preparedStatement, int index)
            throws Exception {

        if (value != null) {
            preparedStatement.setDouble(index, value);
        } else {
            preparedStatement.setNull(index, java.sql.Types.NULL);
        }
    }

    /**
     *
     * @param value
     * @param preparedStatement
     * @param index
     * @throws Exception
     */
    public static void filter(Long value, PreparedStatement preparedStatement, int index)
            throws Exception {
        if (value != null) {
            preparedStatement.setLong(index, value);
        } else {
            preparedStatement.setNull(index, java.sql.Types.NULL);
        }
    }

    /**
     *
     * @param value
     * @param preparedStatement
     * @param index
     * @throws Exception
     */
    public static void filter(BigDecimal value, PreparedStatement preparedStatement, int index)
            throws Exception {
        if (value != null) {
            preparedStatement.setBigDecimal(index, value);
        } else {
            preparedStatement.setNull(index, java.sql.Types.NULL);
        }
    }

    /**
     *
     * @param value
     * @param preparedStatement
     * @param index
     * @throws Exception
     */
    public static void filter(Object value, PreparedStatement preparedStatement, int index)
            throws Exception {
        if (value != null) {
            if (value instanceof Date) {
                Date temp = (Date) value;
                preparedStatement.setObject(index, new java.sql.Timestamp(temp.getTime()));
            } else {
                preparedStatement.setObject(index, value);
            }

        } else {
            preparedStatement.setNull(index, java.sql.Types.NULL);
        }
    }

    /**
     *
     * @param value
     * @param preparedStatement
     * @param index
     * @throws Exception
     */
    public static void filter(java.sql.Date value, PreparedStatement preparedStatement, int index)
            throws Exception {

        if (value != null) {
            preparedStatement.setDate(index, value);
        } else {
            preparedStatement.setNull(index, java.sql.Types.NULL);
        }
    }

}
