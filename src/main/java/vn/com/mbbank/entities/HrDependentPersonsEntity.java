/*
 * Copyright (C) 2022 EcoIT. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.Date;
import javax.persistence.Temporal;


/**
 * Lop entity ung voi bang HR_DEPENDENT_PERSONS
 * @author author
 * @since 1.0
 * @version 1.0
 */

@Data
@Entity
@Table(name = "HR_DEPENDENT_PERSONS")
public class HrDependentPersonsEntity {

    @Id
    @GeneratedValue(generator = "HR_DEPENDENT_PERSONS_SEQ")
    @SequenceGenerator(name = "HR_DEPENDENT_PERSONS_SEQ", sequenceName = "HR_DEPENDENT_PERSONS_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "DEPENDENT_PERSON_ID")
    private Long dependentPersonId;

    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "FAMILY_RELATIONSHIP_ID")
    private Long familyRelationshipId;

    @Column(name = "FROM_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fromDate;

    @Column(name = "TO_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date toDate;

    @Column(name = "TAX_ORGANIZATION_ID")
    private Long taxOrganizationId;

    @Column(name = "PROVINCE_CODE")
    private String provinceCode;

    @Column(name = "DISTRICT_CODE")
    private String districtCode;

    @Column(name = "WARD_CODE")
    private String wardCode;

    @Column(name = "CODE_NO")
    private String codeNo;

    @Column(name = "BOOK_NO")
    private String bookNo;

    @Column(name = "FLAG_STATUS")
    private Integer flagStatus;

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

    @Column(name = "PERSONAL_ID")
    private String personalId;

    @Column(name = "PASSPORT_NO")
    private String passportNo;

    @Column(name = "NATION_CODE")
    private String nationCode;

    @Column(name = "NOTE")
    private String note;


}
