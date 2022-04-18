package vn.com.mbbank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDTO {
    @NotEmpty
    String fileName;

    @NotEmpty
    String base64Data;
}
