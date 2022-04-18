/* * Copyright (C) 2022 EcoIT. All rights reserved. * EcoIT. Use is subject to license terms. */package vn.com.mbbank.entities;import javax.persistence.Basic;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.Id;import javax.persistence.SequenceGenerator;import javax.persistence.Table;import lombok.Data;import javax.validation.constraints.NotNull;import java.util.Date;import javax.persistence.Temporal;/** * Lop entity ung voi bang HR_ALLOWANCE_PROCESS * @author author * @since 1.0 * @version 1.0 */@Data@Entity@Table(name = "HR_ALLOWANCE_PROCESS")public class HrAllowanceProcessEntity {    @Id    @GeneratedValue(generator = "HR_ALLOWANCE_PROCESS_SEQ")    @SequenceGenerator(name = "HR_ALLOWANCE_PROCESS_SEQ", sequenceName = "HR_ALLOWANCE_PROCESS_SEQ", allocationSize = 1)    @Basic(optional = false)    @NotNull    @Column(name = "ALLOWANCE_PROCESS_ID")    private Long allowanceProcessId;    @Column(name = "EMPLOYEE_ID")    private Long employeeId;    @Column(name = "ALLOWANCE_TYPE_CODE")    private String allowanceTypeCode;    @Column(name = "AMOUNT_MONEY")    private Long amountMoney;    @Column(name = "FROM_DATE")    @Temporal(javax.persistence.TemporalType.DATE)    private Date fromDate;    @Column(name = "TO_DATE")    @Temporal(javax.persistence.TemporalType.DATE)    private Date toDate;    @Column(name = "FLAG_STATUS")    private Integer flagStatus;    @Column(name = "CREATED_BY")    private String createdBy;    @Column(name = "CREATE_DATE")    @Temporal(javax.persistence.TemporalType.DATE)    private Date createDate;    @Column(name = "LAST_UPDATED_BY")    private String lastUpdatedBy;    @Column(name = "LAST_UPDATE_DATE")    @Temporal(javax.persistence.TemporalType.DATE)    private Date lastUpdateDate;    @Column(name = "NOTE")    private String note;}