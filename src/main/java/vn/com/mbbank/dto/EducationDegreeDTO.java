package vn.com.mbbank.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import vn.com.mbbank.utils.Constants;

/**
 * Lop DTO entity ung voi bang HR_EDUCATION_DEGREE
 *
 * @author hieuhc
 * @since 1.0
 * @version 1.0
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EducationDegreeDTO {

    private Long educationDegreeId;

    @NotBlank
    @Size(max = 200)
    private String degreeName;

    @NotNull
    private Long employeeId;

    @NotBlank
    private String degreeTypeCode;
    private String degreeTypeName;

    @Max(9999)
    @Min(1900)
    private Integer issueYear;

    @NotNull
    private Long schoolId;

    private String schoolName;

    @NotNull
    private Long facultyId;

    private String facultyName;

    @NotNull
    private Long majorLevelId;
    private String majorLevelName;

    private String eduRankCode;
    private String eduRankName;

    private Integer isRelatedJob;

    private Integer isHighest;

    @Size(max = 500)
    private String note;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    private Date fromDate;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    private Date toDate;

    @Min(0)
    private Integer startRecord;

    @Min(1)
    private Integer pageSize;

    private Integer flagStatus;
    private String orgName;
    private String fullName;
    private String employeeCode;
}
