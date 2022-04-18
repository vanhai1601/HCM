package vn.com.mbbank.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import vn.com.mbbank.utils.Constants;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * Autogen class DTO: Lớp thao tác danh sach than nhan
 *
 * @author ToolGen
 * @date Sun Mar 20 21:42:06 ICT 2022
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FamilyRelationshipsDTO {

    private Long familyRelationshipId;

    @NotNull
    private Long employeeId;

    @NotBlank
    @Size(min = 5, max = 200)
    private String fullName;

    @NotNull
    @Size(min = 1, max = 200)
    private String relationTypeCode;

    private String relationTypeName;

    private Long dayOfBirth;

    @Min(1)
    @Max(12)
    private Long monthOfBirth;

    @Min(1900)
    @Max(9999)
    private Long yearOfBirth;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    private Date dateOfBirth;

    @Size(min = 0, max = 200)
    private String job;

    @Size(min = 0, max = 20)
    private String workOrganization;

    @Size(min = 0, max = 500)
    private String note;

    private String policyTypeCode;

    @Size(min = 0, max = 200)
    private String currentAddress;

    private Long isInCompany;

    private Long referenceEmployeeId;

    @Size(min = 0, max = 20)
    private String personalIdNumber;

    private String taxNumber;

    private String provinceCode;

    private String districtCode;

    private String wardCode;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    private Date fromDate;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    private Date toDate;

    private Integer flagStatus;

    @Min(0)
    private Integer startRecord;

    @Min(1)
    private Integer pageSize;

    private Boolean resultSqlExecute;

    @NotNull
    private String relationStatusCode;

    private String relationStatusName;

    private Integer isHouseholdOwner;

    private String employeeCode;

    private String employeeName;

    private Long orgId;

    // Tên đơn vị
    private String orgName;

    // Đối tượng
    private String empTypeCode;

}
