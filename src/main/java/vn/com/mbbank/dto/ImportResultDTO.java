package vn.com.mbbank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImportResultDTO {
    private String errorFile;
    private List errorList;
}
