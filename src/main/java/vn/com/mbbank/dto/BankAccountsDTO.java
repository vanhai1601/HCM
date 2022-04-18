package vn.com.mbbank.dto;import com.fasterxml.jackson.annotation.JsonFormat;import com.fasterxml.jackson.annotation.JsonInclude;import com.fasterxml.jackson.annotation.JsonInclude.Include;import java.util.Date;import javax.validation.constraints.Min;import lombok.Data;import lombok.NoArgsConstructor;import javax.validation.constraints.Size;import org.springframework.format.annotation.DateTimeFormat;import vn.com.mbbank.utils.Constants;/** * Autogen class DTO: Lớp thao tác danh sach tai khoan ngan hang cua nhan vien * * @author ToolGen * @date Sun Mar 20 21:47:10 ICT 2022 */@Data@NoArgsConstructor@JsonInclude(Include.NON_NULL)public class BankAccountsDTO {    private Long bankAccountId;    private Long employeeId;    @Size(min = 0, max = 20)    private String accountNo;    private Long bankId;    private String bankName;    private String bankBranch;    private Integer isPaymentAccount;    private String accountTypeCode;    private String accountTypeName;    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)    @JsonFormat(pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)    private Date fromDate;    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)    @JsonFormat(pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)    private Date toDate;    @Min(0)    private Integer startRecord;    @Min(1)    private Integer pageSize;    private String fullName;    private String employeeCode;    private Long orgId;    // Tên đơn vị    private String orgName;    // Loại tài khoản    private String label;    // Đối tượng    private String empTypeCode;    private Integer flagStatus;}