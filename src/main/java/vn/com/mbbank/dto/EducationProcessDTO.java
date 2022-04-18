package vn.com.mbbank.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import vn.com.mbbank.utils.Constants;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EducationProcessDTO {

    private Long educationProcessId;

    private Long employeeId;

    @Size(max = 200)
    private String courseName;

    private String eduMethodTypeName;

    @Size(max = 20)
    private String eduMethodTypeCode;

    @Size(max = 1000)
    private String courseContent;

    @Size(max = 1000)
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

    private String fullName;
    private String employeeCode;
    private Integer flagStatus;
    private String orgName;
}
