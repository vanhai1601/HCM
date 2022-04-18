/*
 * Copyright (C) 2022 EcoIT. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.entities;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * Lop entity ung voi bang HR_EMPLOYEES
 *
 * @author author
 * @since 1.0
 * @version 1.0
 */
@Data
@Entity
@Table(name = "HR_EMPLOYEES")
public class HrEmployeesEntity {

    @Id
    @GeneratedValue(generator = "HR_EMPLOYEES_SEQ")
    @SequenceGenerator(name = "HR_EMPLOYEES_SEQ", sequenceName = "HR_EMPLOYEES_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "EMPLOYEE_CODE")
    private String employeeCode;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "NICK_NAME")
    private String nickName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MIDILE_NAME")
    private String midileName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMP_TYPE_CODE")
    private String empTypeCode;

    @Column(name = "ORGANIZATION_ID")
    private Long organizationId;

    @Column(name = "POSITION_ID")
    private Long positionId;

    @Column(name = "JOB_ID")
    private Long jobId;

    @Column(name = "CONTRACT_TYPE_ID")
    private Long contractTypeId;

    @Column(name = "DATE_OF_BIRTH")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "GENDER_CODE")
    private String genderCode;

    @Column(name = "NATION_CODE")
    private String nationCode;

    @Column(name = "ETHNIC_CODE")
    private String ethnicCode;

    @Column(name = "RELIGION_CODE")
    private String religionCode;

    @Column(name = "PERSONAL_ID_TYPE_CODE")
    private String personalIdTypeCode;

    @Column(name = "PERSONAL_ID")
    private String personalId;

    @Column(name = "PERSONAL_ID_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date personalIdDate;

    @Column(name = "PERSONAL_ID_PLACE")
    private String personalIdPlace;

    @Column(name = "MARITAL_STATUS_CODE")
    private String maritalStatusCode;

    @Column(name = "JOIN_COMPANY_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date joinCompanyDate;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "COMPANY_PHONE")
    private String companyPhone;

    @Column(name = "PLACE_OF_BIRTH")
    private String placeOfBirth;

    @Column(name = "ORIGINAL_ADDRESS")
    private String originalAddress;

    @Column(name = "PERNAMENT_ADDRESS")
    private String pernamentAddress;

    @Column(name = "CURRENT_ADDRESS")
    private String currentAddress;

    @Column(name = "FAMILY_TYPE_CODE")
    private String familyTypeCode;

    @Column(name = "PERSONAL_TYPE_CODE")
    private String personalTypeCode;

    @Column(name = "BANK_ACCOUNT_NO")
    private String bankAccountNo;

    @Column(name = "BANK_CODE")
    private String bankCode;

    @Column(name = "TAX_NO")
    private String taxNo;

    @Column(name = "INSURANCE_NO")
    private String insuranceNo;

    @Column(name = "FROM_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fromDate;

    @Column(name = "TO_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date toDate;

    @Column(name = "FLAG_STATUS")
    private Long flagStatus;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createDate;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastUpdateDate;

    @Column(name = "PERSONAL_EMAIL")
    private String personalEmail;

    @Column(name = "IS_INSURANCE_MB")
    private Integer isInsuranceMb;

    @Column(name = "IMAGE_PATH")
    private String imagePath;
}
