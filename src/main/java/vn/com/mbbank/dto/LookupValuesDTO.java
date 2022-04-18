package vn.com.mbbank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class LookupValuesDTO {
    private String value;
    private String label;
    private Long id;
    
    private String provinceCode;
    private String provinceLabel;
    private String districtCode;
    private String districtLabel;
    private String wardCode;
    private String wardLabel;
}
