package vn.com.mbbank.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import vn.com.mbbank.utils.Constants;

/**
 * Autogen class DTO: Lớp thao tác danh sach qua tham gia du an nhan vien
 * 
 * @author ToolGen
 * @date Sun Mar 20 22:27:26 ICT 2022
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ProjectMembersDTO {

    @Min(0)
    private Integer startRecord;

    @Min(1)
    private Integer pageSize;


    private Long projectMemberId;


    @NotNull
    private Long employeeId;


    @NotNull
    private Long projectId;

    @NotNull
    private Long jobId;


    @NotNull
    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    private Date fromDate;


    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    private Date toDate;


    private Long flagStatus;

    private String note;
    private String projectName;
    private String jobName;

    @NotBlank
    @Size(min = 0, max = 200)
    private String fullName;
    private String employeeCode;
    private String orgName;

}