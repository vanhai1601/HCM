package vn.com.mbbank.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Lop entity ung voi bang HR_EDUCATION_PROCESS
 * @author hieuhc
 * @since 1.0
 * @version 1.0
 */
@Data
@Entity(name = "HR_EDUCATION_PROCESS")
public class HrEducationProcessEntity {

    @Id
    @GeneratedValue(generator = "EDUCATION_PRROCESS_SEQ")
    @SequenceGenerator(name = "EDUCATION_PRROCESS_SEQ", sequenceName = "EDUCATION_PRROCESS_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "EDUCATION_PROCESS_ID")
    private Long educationProcessId;

    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "COURSE_NAME")
    private String courseName;

    @Column(name = "EDU_METHOD_TYPE_CODE")
    private String eduMethodTypeCode;

    @Column(name = "COURSE_CONTENT")
    private String courseContent;

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
}
