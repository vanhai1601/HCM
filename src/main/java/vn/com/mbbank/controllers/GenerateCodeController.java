/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.mbbank.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.com.mbbank.core.repositories.impl.BaseRepositoryImpl;
import vn.com.mbbank.dto.AllTabColumns;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.CoreDAO;
import vn.com.mbbank.utils.ResponseUtils;

/**
 * @author HuyPC
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_PREFIX)
public class GenerateCodeController extends BaseRepositoryImpl {

    @GetMapping(value = "/v1/generate/entity", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> genEntity(@RequestParam(value = "table", required = false, defaultValue = "") String table
    ) throws FileNotFoundException {
        String arrTableName[] = table.split(",");
        for (String singleTable : arrTableName) {

            String currentTable = null;
            StringBuffer currentDeclare = new StringBuffer("");
            String boType = null;
            String folder = "D:\\PROJECT\\MBBank\\HCM\\output/";
            PrintWriter boWriter = new PrintWriter(new File(folder + "0.java"));

            // <editor-fold defaultstate="collapsed" desc="Lay du lieu tu DB">
            StringBuilder sql = new StringBuilder(" SELECT utc.table_name tableName, " +
                    "utc.column_name columnName, " +
                    "utc.data_type dataType, " +
                    "utc.data_length dataLength," +
                    "utc.data_scale dataScale," +
                    "utc.DATA_PRECISION dataPrecision ");
            sql.append(" FROM all_tab_columns utc "
                    + " WHERE 1 = 1 ");
            HashMap<String, Object> hashMapParams = new HashMap<>();
            if (singleTable != null && !singleTable.trim().isEmpty()) {
                sql.append(" AND utc.table_name = :singleTable");
                hashMapParams.put("singleTable", singleTable);
            }
//            String arr[] = url.split("/");
            sql.append(" AND utc.owner = 'HCM'");
            sql.append(" ORDER BY utc.table_name, utc.column_id  ");

            System.out.println(sql);

            List<AllTabColumns> resultData = (List<AllTabColumns>) getListData(sql, hashMapParams, null, null, AllTabColumns.class);

            // </editor-fold>
            for (AllTabColumns allTabColumns : resultData) {
                StringBuilder sqlPrimary = new StringBuilder("SELECT column_name columnName FROM all_cons_columns WHERE constraint_name = ("
                        + "  SELECT constraint_name FROM user_constraints "
                        + "  WHERE UPPER(table_name) = :tableName AND CONSTRAINT_TYPE = 'P'"
                        + ")");
                HashMap<String, Object> params = new HashMap<>();
                params.put("tableName", allTabColumns.getTableName().toUpperCase());
                List<AllTabColumns> listPrimaryKey = (List<AllTabColumns>) getListData(sqlPrimary, params, null, null, AllTabColumns.class);
                String columnPrimary = listPrimaryKey == null || listPrimaryKey.isEmpty() ? "" : listPrimaryKey.get(0).getColumnName();
                // <editor-fold defaultstate="collapsed" desc="Lay du lieu dau vao, chuan hoa">
                String tableName1 = allTabColumns.getTableName();
                String tableName2 = CoreDAO.columnName(tableName1);
                String tableName3 = CoreDAO.tableName(tableName2);

                String columnName1 = allTabColumns.getColumnName();
                String columnName2 = CoreDAO.columnName(columnName1);
//                String columnName3 = CoreDAO.tableName(columnName2);

                String dataType = allTabColumns.getDataType();
                Integer dataScale = allTabColumns.getDataScale();
                // </editor-fold>

                // Neu bat dau mot bang moi
                if (!tableName3.equals(currentTable)) {

                    // <editor-fold defaultstate="collapsed" desc="File BO">
                    boWriter.println(currentDeclare);
                    boWriter.println();
                    if (singleTable == null || singleTable.isEmpty()) {
                        boWriter.println("}");
                    }
                    boWriter.close();

                    currentTable = tableName3;

                    boWriter = new PrintWriter(new File(folder + tableName3 + "Entity.java"));
                    boWriter.println("/*");
                    boWriter.println(" * Copyright (C) 2022 EcoIT. All rights reserved.");
                    boWriter.println(" * EcoIT. Use is subject to license terms.");
                    boWriter.println(" */");
                    boWriter.println("package vn.com.mbbank.entities;");
                    boWriter.println();
                    boWriter.println("import javax.persistence.Basic;");
                    boWriter.println("import javax.persistence.Column;");
                    boWriter.println("import javax.persistence.Entity;");
                    boWriter.println("import javax.persistence.GeneratedValue;");
                    boWriter.println("import javax.persistence.Id;");
                    boWriter.println("import javax.persistence.SequenceGenerator;");
                    boWriter.println("import javax.persistence.Table;");
                    boWriter.println("import lombok.Data;");
                    boWriter.println("import javax.validation.constraints.NotNull;");
                    boWriter.println("import java.util.Date;");
                    boWriter.println("import javax.persistence.Temporal;");
                    boWriter.println("");
                    boWriter.println();
                    boWriter.println("/**");
                    boWriter.println(" * Lop entity ung voi bang " + tableName1);
                    boWriter.println(" * @author author");
                    boWriter.println(" * @since 1.0");
                    boWriter.println(" * @version 1.0");
                    boWriter.println(" */");
                    boWriter.println();
                    boWriter.println("@Data");
                    boWriter.println("@Entity");
                    boWriter.println("@Table(name = \"" + allTabColumns.getTableName() + "\")");
                    boWriter.println("public class " + tableName3 + "Entity {");
                    boWriter.println();

                    boType = "Long";
                    if (dataType.equals("date")) {
                        boType = "Date";
                    } else if (dataType.equals("varchar") || dataType.equals("char") || dataType.equals("text") || dataType.equals("nvarchar")) {
                        boType = "String";
                    }

                    String sequenceName = tableName1.toUpperCase() + "_SEQ";
                    currentDeclare = new StringBuffer("");
                    currentDeclare.append("    @Id\r");
                    currentDeclare.append("    @GeneratedValue(generator = \"").append(sequenceName).append("\")\r");
                    currentDeclare.append("    @SequenceGenerator(name = \"").append(sequenceName).append("\", sequenceName = \"").append(sequenceName).append("\", allocationSize = 1)\r");
                    currentDeclare.append("    @Basic(optional = false)\r");
                    currentDeclare.append("    @NotNull\r");
                    currentDeclare.append("    @Column(name = \"").append(columnPrimary.toUpperCase()).append("\")\r");
                    currentDeclare.append("    private Long ").append(CoreDAO.columnName(columnPrimary)).append(";\r");
                    // </editor-fold>

                }

                // Bo qua cac cot la ID
                if (!columnName2.equalsIgnoreCase(CoreDAO.columnName(columnPrimary))) {
                    // <editor-fold defaultstate="collapsed" desc="Xu ly tung cot">
                    if (dataType.toUpperCase().equals("DATE")) {
                        boType = "Date";
                    } else if (dataType.toUpperCase().equals("NUMBER")) {
                        if (dataScale != null && dataScale > 0) {
                            boType = "Double";
                        } else if (allTabColumns.getDataPrecision() != null && allTabColumns.getDataPrecision() < 10) {
                            boType = "Integer";
                        } else {
                            boType = "Long";
                        }
                    } else if (dataType.toUpperCase().equals("NVARCHAR2") || dataType.toUpperCase().equals("VARCHAR2") || dataType.toUpperCase().equals("VARCHAR")) {
                        boType = "String";
                        if (dataType.equals("VARCHAR2")) {
                            System.out.println(tableName3 + ", " + columnName2 + ", " + dataType);
                        }
                    } else if (dataType.toUpperCase().equals("INT")) {
                        boType = "Long";
                    } else if (dataType.toUpperCase().equals("FLOAT")) {
                        boType = "Float";
                    } else {
                        System.out.println(tableName3 + ", " + columnName2 + ", " + dataType);
                    }

                    currentDeclare.append("\r");
                    currentDeclare.append("    @Column(name = \"").append(columnName1).append("\")\r");
                    if (boType.equals("Date")) {
                        currentDeclare.append("    @Temporal(javax.persistence.TemporalType.DATE)\r");
                    }
                    currentDeclare.append("    private ").append(boType).append(" ").append(columnName2).append(";\r");

                    // </editor-fold>
                }
            }

            boWriter.println(currentDeclare);
            boWriter.println();
            if (singleTable != null && !singleTable.trim().isEmpty()) {
                boWriter.println("}");
            }
            boWriter.close();
        }
        return ResponseUtils.getResponseSucessEntity(null);
    }
}
