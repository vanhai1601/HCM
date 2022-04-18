package vn.com.mbbank.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import vn.com.mbbank.dto.UserBookmarksDTO;

import java.util.List;

public interface UserBookmarksService {

    List getListDataByUser(UserBookmarksDTO dto, Authentication authentication);

    List getListSearchItem(String moduleCode);

    ResponseEntity<Object> saveData(UserBookmarksDTO dto, Authentication authentication);

    ResponseEntity<Object> deleteData(Long id, Authentication authentication);

}
