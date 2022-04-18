package vn.com.mbbank.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import vn.com.mbbank.entities.HrPhoneContactsEntity;
import vn.com.mbbank.utils.Constants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Autogen class DTO: Lớp thao tác danh sach nhan vien
 *
 * @author ToolGen
 * @date Sun Mar 20 21:28:46 ICT 2022
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonalInfoDTO {
    private Long employeeId;

    private String employeeCode;

    @NotBlank
    @Size(min = 0, max = 200)
    private String fullName;

    @NotNull
    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    private Date dateOfBirth;

    @NotBlank
    private String genderCode;

    private String genderName;

    @NotBlank
    private String nationCode;
    private String nationName;

    @NotBlank
    private String ethnicCode;
    private String ethnicName;

    @NotBlank
    private String religionCode;
    private String religionName;

    @NotBlank
    private String maritalStatusCode;
    private String maritalStatusName;

    private String email;
    private String personalEmail;

    @Size(min = 0, max = 10)
    private String mobileNumber;

    @Size(min = 0, max = 50)
    private String companyPhone;

    @Size(min = 0, max = 20)
    private String taxNo;

    @Size(min = 0, max = 20)
    private String insuranceNo;

    private Integer isInsuranceMb;

    private List<HrPhoneContactsEntity> listMobileNumber;
    private String imagePath;
}
