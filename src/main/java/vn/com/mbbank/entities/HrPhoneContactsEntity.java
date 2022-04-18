/* * Copyright (C) 2022 EcoIT. All rights reserved. * EcoIT. Use is subject to license terms. */package vn.com.mbbank.entities;import javax.persistence.Basic;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.Id;import javax.persistence.SequenceGenerator;import javax.persistence.Table;import lombok.Data;import javax.validation.constraints.NotNull;import java.util.Date;import javax.persistence.Temporal;/** * Lop entity ung voi bang HR_PHONE_CONTACTS * @author author * @since 1.0 * @version 1.0 */@Data@Entity@Table(name = "HR_PHONE_CONTACTS")public class HrPhoneContactsEntity {    @Id    @GeneratedValue(generator = "HR_PHONE_CONTACTS_SEQ")    @SequenceGenerator(name = "HR_PHONE_CONTACTS_SEQ", sequenceName = "HR_PHONE_CONTACTS_SEQ", allocationSize = 1)    @Basic(optional = false)    @NotNull    @Column(name = "PHONE_CONTACT_ID")    private Long phoneContactId;    @Column(name = "EMPLOYEE_ID")    private Long employeeId;    @Column(name = "PHONE_NUMBER")    private String phoneNumber;    @Column(name = "PHONE_AREA_CODE")    private String phoneAreaCode;    @Column(name = "IS_MAIN")    private Long isMain;    @Column(name = "FROM_DATE")    @Temporal(javax.persistence.TemporalType.DATE)    private Date fromDate;    @Column(name = "TO_DATE")    @Temporal(javax.persistence.TemporalType.DATE)    private Date toDate;    @Column(name = "FLAG_STATUS")    private Integer flagStatus;    @Column(name = "CREATED_BY")    private String createdBy;    @Column(name = "CREATE_DATE")    @Temporal(javax.persistence.TemporalType.DATE)    private Date createDate;    @Column(name = "LAST_UPDATED_BY")    private String lastUpdatedBy;    @Column(name = "LAST_UPDATE_DATE")    @Temporal(javax.persistence.TemporalType.DATE)    private Date lastUpdateDate;}