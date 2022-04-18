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
 * Lop entity ung voi bang HR_WORK_PROCESS
 * @author author
 * @since 1.0
 * @version 1.0
 */

@Data
@Entity
@Table(name = "HR_WORK_PROCESS")
public class HrWorkProcessEntity {

    @Id
    @GeneratedValue(generator = "HR_WORK_PROCESS_SEQ")
    @SequenceGenerator(name = "HR_WORK_PROCESS_SEQ", sequenceName = "HR_WORK_PROCESS_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "WORK_PROCESS_ID")
    private Long workProcessId;

    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "ORGANIZATION_ID")
    private Long organizationId;

    @Column(name = "POSITION_ID")
    private Long positionId;

    @Column(name = "JOB_ID")
    private Long jobId;

    @Column(name = "DOCUMENT_NO")
    private String documentNo;

    @Column(name = "POSITION_LEVEL")
    private String positionLevel;

    @Column(name = "DOCUMENT_TYPE_ID")
    private Long documentTypeId;

    @Column(name = "FROM_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fromDate;

    @Column(name = "TO_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date toDate;

    @Column(name = "EMP_TYPE_CODE")
    private String empTypeCode;

    @Column(name = "MANAGER_ID")
    private Long managerId;

    @Column(name = "OTHER_ORG_ID")
    private Long otherOrgId;

    @Column(name = "OTHER_POSITION_ID")
    private Long otherPositionId;

    @Column(name = "SIGNED_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date signedDate;

    @Column(name = "EXPIRATION_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expirationDate;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "FLAG_STATUS")
    private Integer flagStatus;
    
    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "CREATE_DATE")
    private Date createDate;
}
