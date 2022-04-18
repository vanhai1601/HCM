package vn.com.mbbank.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Lop entity ung voi bang HR_EDUCATION_DEGREE
 * @author hieuhc
 * @since 1.0
 * @version 1.0
 */

@Data
@Entity
@Table(name = "HR_EDUCATION_DEGREES")
public class HrEducationDegreeEntity {
    @Id
    @GeneratedValue(generator = "HR_EDUCATION_DEGREE_SEQ")
    @SequenceGenerator(name = "HR_EDUCATION_DEGREE_SEQ", sequenceName = "HR_EDUCATION_DEGREE_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "EDUCATION_DEGREE_ID")
    private Long educationDegreeId;

    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;
    
    @Column(name = "DEGREE_NAME")
    private String degreeName;

    @Column(name = "DEGREE_TYPE_CODE")
    private String degreeTypeCode;
    
    @Column(name = "ISSUE_YEAR")
    private Integer issueYear;

    @Column(name = "SCHOOL_ID")
    private Long schoolId;

    @Column(name = "FACULTY_ID")
    private Long facultyId;

    @Column(name = "MAJOR_LEVEL_ID")
    private Long majorLevelId;

    @Column(name = "EDU_RANK_CODE")
    private String eduRankCode;

    @Column(name = "IS_RELATED_JOB")
    private Integer isRelatedJob;

    @Column(name = "IS_HIGHEST")
    private Integer isHighest;

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
