package vn.com.mbbank.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Hr_bank_accounts
 * 
 * @author ToolGen
 * @date Sun Mar 20 21:44:29 ICT 2022
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "HR_BANK_ACCOUNTS")
public class HrBankAccountsEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "HR_BANK_ACCOUNTS_SEQ")
    @SequenceGenerator(name = "HR_BANK_ACCOUNTS_SEQ", sequenceName = "HR_BANK_ACCOUNTS_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "BANK_ACCOUNT_ID")
    Long bankAccountId;

    @Column(name = "EMPLOYEE_ID")
    Long employeeId;

    @Column(name = "ACCOUNT_NO")
    String accountNo;

    @Column(name = "BANK_ID")
    Long bankId;

    @Column(name = "BANK_BRANCH")
    String bankBranch;

    @Column(name = "IS_PAYMENT_ACCOUNT")
    Integer isPaymentAccount;

    @Column(name = "ACCOUNT_TYPE_CODE")
    String accountTypeCode;

    @Column(name = "FROM_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date fromDate;

    @Column(name = "TO_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date toDate;

    @Column(name = "FLAG_STATUS")
    Integer flagStatus;

    @Column(name = "CREATED_BY")
    String createdBy;

    @Column(name = "CREATE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date createDate;

    @Column(name = "LAST_UPDATED_BY")
    String lastUpdatedBy;

    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date lastUpdateDate;
}