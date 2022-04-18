
package vn.com.mbbank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Admin
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PersonalIDDTO {
    
    private List<Long> idDelete;

    private List<PersonalIdentitiesDTO> data;
}
