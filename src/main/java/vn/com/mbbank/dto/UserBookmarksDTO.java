package vn.com.mbbank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserBookmarksDTO {
    private Long userBookmarkId;

    private String userName;

    private String type;

    private String name;

    private List<UserBookmarkOptionsDTO> options;
}
