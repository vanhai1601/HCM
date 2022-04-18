
package vn.com.mbbank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Admin
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MpProjectsDTO {
    
    private Long prjId;
    private String prjCode;
    private String prjName;
    private String description;

  
}
