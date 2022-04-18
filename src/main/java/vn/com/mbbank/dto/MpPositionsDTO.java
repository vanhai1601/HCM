/*
 * Copyright (C) 2022 EcoIT. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import vn.com.mbbank.utils.Constants;

/**
 *
 * @author Admin
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MpPositionsDTO {
    private Long posId;

    private String posCode;

    private String posName;

    private Long pgrId;

    @NotNull
    private Long orgId;

    private Long jobId;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    private Date fromDate;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    private Date toDate;

    private Long headCount;
}
