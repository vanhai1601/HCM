package vn.com.mbbank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserBookmarkOptionsDTO {

    private String code;
    private List<String> values;
    private String valueFrom;
    private String valueTo;
}
