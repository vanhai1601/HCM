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
 * Lop entity ung voi bang HR_WORKED_OUTSIDES
 * @author author
 * @since 1.0
 * @version 1.0
 */

@Data
@Entity
@Table(name = "HR_WORKED_OUTSIDES")
public class HrWorkedOutsidesEntity {

    @Id
    @GeneratedValue(generator = "HR_WORKED_OUTSIDES_SEQ")
    @SequenceGenerator(name = "HR_WORKED_OUTSIDES_SEQ", sequenceName = "HR_WORKED_OUTSIDES_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "WORKED_OUTSIDE_ID")
    private Long workedOutsideId;

    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;

    @Column(name = "POSITION_NAME")
    private String positionName;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "AMOUNT_SALARY")
    private Long amountSalary;

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

    @Column(name = "REFER_PERSON")
    private String referPerson;

    @Column(name = "REFER_PERSON_POSITION")
    private String referPersonPosition;

    @Column(name = "MISSION")
    private String mission;

    @Column(name = "REASON_LEAVE")
    private String reasonLeave;

    @Column(name = "REWARD_INFO")
    private String rewardInfo;

    @Column(name = "DECPLINE_INFO")
    private String decplineInfo;


}
