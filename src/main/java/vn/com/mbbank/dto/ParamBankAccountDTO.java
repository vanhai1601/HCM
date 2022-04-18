
package vn.com.mbbank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @author Admin
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ParamBankAccountDTO {
    
    private List<Long> idDelete;

    private List<BankAccountsDTO> data;
}
