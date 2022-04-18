/*
 * Copyright (C) 2022 EcoIT. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import vn.com.mbbank.utils.Constants;


/**
 * Lop entity ung voi bang HR_EMPLOYEE_INFOS
 * @author author
 * @since 1.0
 * @version 1.0
 */

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HrEmployeeInfosDTO {

    private Long employeeId;

    private String firstName;

    private String lastName;

    private String middleName;

    private String nickName;

    private String partyNumber;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    private Date partyDate;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    private Date partyOfficialDate;

    @Size(min = 0, max = 200)
    private String partyPlace;

//    private String eduGradeCode;

    private String eduSubjectCode;

    private String eduPlaceCode;

    private String eduRankCode;

    private Integer eduGraduateYear;

    private String passportNumber;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    private Date passportIssueDate;

    private String passportIssuePlace;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    private Date passportExpiredDate;

    private Long isArmy;

    private String armyLevelCode;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    private Date armyJoinDate;

    private String bankAccountNo;

    private Long bankId;

    private String pernamentProvinceCode;

    private String pernamentProvinceName;

    private String pernamentDistrictCode;

    private String pernamentDistrictName;

    private String pernamentWardCode;

    private String pernamentWardName;

    private String pernamentDetail;

    private String currentProvinceCode;

    private String currentProvinceName;

    private String currentDistrictCode;

    private String currentDistrictName;

    private String currentWardCode;

    private String currentWardName;

    private String currentDetail;

    private String placeOfBirth;

    private String originalAddress;

    private String pernamentAddress;

    private String currentAddress;

    private String armyLevelName;
}
