package vn.com.mbbank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeDTO {

    Long nodeId;
    String code;
    String name;
    Long parentId;
    private String path;
    List<TreeDTO> childrens = new ArrayList();

}
