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
 * Lop entity ung voi bang HR_EMPLOYEE_INFOS
 * @author author
 * @since 1.0
 * @version 1.0
 */

@Data
@Entity
@Table(name = "HR_EMPLOYEE_INFOS")
public class HrEmployeeInfosEntity {

    @Id
    @GeneratedValue(generator = "HR_EMPLOYEE_INFOS_SEQ")
    @SequenceGenerator(name = "HR_EMPLOYEE_INFOS_SEQ", sequenceName = "HR_EMPLOYEE_INFOS_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "NICK_NAME")
    private String nickName;

    @Column(name = "PARTY_NUMBER")
    private String partyNumber;

    @Column(name = "PARTY_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date partyDate;

    @Column(name = "PARTY_OFFICIAL_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date partyOfficialDate;

    @Column(name = "PARTY_PLACE")
    private String partyPlace;

    @Column(name = "MAJOR_LEVEL_ID")
    private String majorLevelId;

    @Column(name = "EDU_SUBJECT_CODE")
    private String eduSubjectCode;

    @Column(name = "EDU_PLACE_CODE")
    private String eduPlaceCode;

    @Column(name = "EDU_RANK_CODE")
    private String eduRankCode;

    @Column(name = "EDU_GRADUATE_YEAR")
    private Integer eduGraduateYear;

    @Column(name = "PASSPORT_NUMBER")
    private String passportNumber;

    @Column(name = "PASSPORT_ISSUE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date passportIssueDate;

    @Column(name = "PASSPORT_ISSUE_PLACE")
    private String passportIssuePlace;

    @Column(name = "PASSPORT_EXPIRED_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date passportExpiredDate;

    @Column(name = "IS_ARMY")
    private Long isArmy;

    @Column(name = "ARMY_LEVEL_CODE")
    private String armyLevelCode;

    @Column(name = "ARMY_JOIN_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date armyJoinDate;

    @Column(name = "BANK_ACCOUNT_NO")
    private String bankAccountNo;

    @Column(name = "BANK_ID")
    private Long bankId;

    @Column(name = "PERNAMENT_PROVINCE_CODE")
    private String pernamentProvinceCode;

    @Column(name = "PERNAMENT_DISTRICT_CODE")
    private String pernamentDistrictCode;

    @Column(name = "PERNAMENT_WARD_CODE")
    private String pernamentWardCode;

    @Column(name = "PERNAMENT_DETAIL")
    private String pernamentDetail;

    @Column(name = "CURRENT_PROVINCE_CODE")
    private String currentProvinceCode;

    @Column(name = "CURRENT_DISTRICT_CODE")
    private String currentDistrictCode;

    @Column(name = "CURRENT_WARD_CODE")
    private String currentWardCode;

    @Column(name = "CURRENT_DETAIL")
    private String currentDetail;


}
