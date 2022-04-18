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
 * Lop entity ung voi bang HR_REWARD_RECORDS
 * @author author
 * @since 1.0
 * @version 1.0
 */

@Data
@Entity
@Table(name = "HR_REWARD_RECORDS")
public class HrRewardRecordsEntity {

    @Id
    @GeneratedValue(generator = "HR_REWARD_RECORDS_SEQ")
    @SequenceGenerator(name = "HR_REWARD_RECORDS_SEQ", sequenceName = "HR_REWARD_RECORDS_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "REWARD_RECORD_ID")
    private Long rewardRecordId;

    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "AMOUNT_MONEY")
    private Long amountMoney;

    @Column(name = "METHOD_CODE")
    private String methodCode;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "FROM_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fromDate;

    @Column(name = "TO_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date toDate;

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

    @Column(name = "SIGNED_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date signedDate;

    @Column(name = "DECISION_LEVEL_CODE")
    private String decisionLevelCode;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "REASON")
    private String reason;


}
