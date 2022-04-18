package vn.com.mbbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Hr_family_relationships
 * 
 * @author ToolGen
 * @date Sun Mar 20 21:42:07 ICT 2022
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "HR_FAMILY_RELATIONSHIPS")
public class HrFamilyRelationshipsEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "HR_FAMILY_RELATIONSHIPS_SEQ")
    @SequenceGenerator(name = "HR_FAMILY_RELATIONSHIPS_SEQ", sequenceName = "HR_FAMILY_RELATIONSHIPS_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAMILY_RELATIONSHIP_ID")
    Long familyRelationshipId;

    @Column(name = "EMPLOYEE_ID")
    Long employeeId;

    @Column(name = "FULL_NAME")
    String fullName;

    @Column(name = "RELATION_TYPE_CODE")
    String relationTypeCode;

    @Column(name = "DAY_OF_BIRTH")
    Long dayOfBirth;

    @Column(name = "MONTH_OF_BIRTH")
    Long monthOfBirth;

    @Column(name = "YEAR_OF_BIRTH")
    Long yearOfBirth;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DATE_OF_BIRTH")
    Date dateOfBirth;

    @Column(name = "JOB")
    String job;

    @Column(name = "WORK_ORGANIZATION")
    String workOrganization;

    @Column(name = "NOTE")
    String note;

    @Column(name = "POLICY_TYPE_CODE")
    String policyTypeCode;

    @Column(name = "CURRENT_ADDRESS")
    String currentAddress;

    @Column(name = "IS_IN_COMPANY")
    Long isInCompany;

    @Column(name = "REFERENCE_EMPLOYEEE_ID")
    Long referenceEmployeeId;

    @Column(name = "PERSONAL_ID_NUMBER")
    String personalIdNumber;

    @Column(name = "TAX_NUMBER")
    String taxNumber;

    @Column(name = "PROVINCE_CODE")
    String provinceCode;

    @Column(name = "DISTRICT_CODE")
    String districtCode;

    @Column(name = "WARD_CODE")
    String wardCode;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "FROM_DATE")
    Date fromDate;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "TO_DATE")
    Date toDate;

    @Column(name = "FLAG_STATUS")
    Integer flagStatus;

    @Column(name = "CREATED_BY")
    String createdBy;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "LAST_UPDATED_BY")
    String lastUpdatedBy;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "LAST_UPDATE_DATE")
    Date lastUpdateDate;
    
    @Column(name = "RELATION_STATUS_CODE")
    private String relationStatusCode;
    
    @Column(name = "IS_HOUSEHOLD_OWNER")
    private Integer isHouseholdOwner;
}