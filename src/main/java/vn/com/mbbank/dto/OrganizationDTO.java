package vn.com.mbbank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationDTO {
    private Long orgId;
    private String orgName;
    private String path;
    private Long parentId;
    private String parentName;

    @Min(0)
    private Integer startRecord;

    @Min(1)
    private Integer pageSize;
}
