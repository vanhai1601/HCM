package vn.com.mbbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vn.com.mbbank.dto.UserBookmarksDTO;
import vn.com.mbbank.services.UserBookmarksService;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.ResponseUtils;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constants.REQUEST_MAPPING_PREFIX)
public class UserBookmarksController {

    @Autowired
    private UserBookmarksService userBookmarksService;

    @GetMapping(value = "/v1/user-bookmark", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getListDataByEmpId(Authentication authentication,
            UserBookmarksDTO dto
    ) {
        List listData = userBookmarksService.getListDataByUser(dto, authentication);
        return ResponseUtils.getResponseSucessEntity(listData);
    }

    @DeleteMapping(value = "/v1/user-bookmark/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteUserBookmark(Authentication authentication,
            @PathVariable Long id
    ) {
        return userBookmarksService.deleteData(id, authentication);
    }

    @PostMapping(value = "/v1/user-bookmark", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveUserBookmark(Authentication authentication,
            @Valid @RequestBody UserBookmarksDTO dto
    ) {
        return userBookmarksService.saveData(dto, authentication);
    }

    @GetMapping(value = "/v1/search-item", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getListDataByEmpId(Authentication authentication,
            @RequestParam(value = "moduleCode", required = true) String moduleCode
    ) {
        List listData = userBookmarksService.getListSearchItem(moduleCode);
        return ResponseUtils.getResponseSucessEntity(listData);
    }
}
