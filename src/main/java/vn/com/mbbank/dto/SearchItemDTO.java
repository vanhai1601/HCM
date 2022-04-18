package vn.com.mbbank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchItemDTO {

    private String code;

    private String inputType;

    private String inputLabel;

    private String inputPlaceholder;

    private String dataSourceType;

    private String dataSourceValue;

    private Long minValue;

    private Long maxValue;

}
