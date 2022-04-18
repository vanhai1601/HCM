package vn.com.mbbank.services.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import vn.com.mbbank.core.config.I18n;
import vn.com.mbbank.dto.UserBookmarkOptionsDTO;
import vn.com.mbbank.dto.UserBookmarksDTO;
import vn.com.mbbank.entities.HrUserBookmarksEntity;
import vn.com.mbbank.repositories.impl.UserBookmarksRepositoryImpl;
import vn.com.mbbank.repositories.jpa.HrUserBookmarksRepositoryJPA;
import vn.com.mbbank.services.UserBookmarksService;
import vn.com.mbbank.utils.Constants;
import vn.com.mbbank.utils.ErrorApp;
import vn.com.mbbank.utils.ResponseUtils;
import vn.com.mbbank.utils.Utils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserBookmarksServiceImpl implements UserBookmarksService {

    @Autowired
    private UserBookmarksRepositoryImpl userBookmarksRepository;

    @Autowired
    private HrUserBookmarksRepositoryJPA userBookmarksRepositoryJPA;

    private Gson gson;

    @PostConstruct
    public void init() {
        gson = new Gson();
    }

    @Override
    public List getListDataByUser(UserBookmarksDTO dto, Authentication authentication) {
        List<HrUserBookmarksEntity> listData = userBookmarksRepository.findByProperties(HrUserBookmarksEntity.class, "userName", Utils.getUserNameLogin(authentication), "type", dto.getType(), "name");
        List<UserBookmarksDTO> result = new ArrayList<>();
        for (HrUserBookmarksEntity entity : listData) {
            UserBookmarksDTO userBookmarksDTO = new UserBookmarksDTO();
            userBookmarksDTO.setName(entity.getName());
            userBookmarksDTO.setType(entity.getType());
            userBookmarksDTO.setUserBookmarkId(entity.getUserBookmarkId());
            List<UserBookmarkOptionsDTO> listOption = gson.fromJson(entity.getOptions(), new TypeToken<ArrayList<UserBookmarkOptionsDTO>>() {
            }.getType());
            userBookmarksDTO.setOptions(listOption);
            result.add(userBookmarksDTO);
        }
        return result;
    }

    @Override
    public List getListSearchItem(String moduleCode) {
        return userBookmarksRepository.getSearchItem(moduleCode);
    }

    @Override
    public ResponseEntity<Object> saveData(UserBookmarksDTO dto, Authentication authentication) {
        boolean isDuplicate = userBookmarksRepository.duplicate(HrUserBookmarksEntity.class, dto.getUserBookmarkId(), "userName", dto.getUserName(), "type", dto.getType());
        if (isDuplicate) {
            return ResponseUtils.getResponseEntity(ErrorApp.INVALID_PARAM.getCode(), I18n.getMessage("validate.userBookMark.duplicate"));
        }

        HrUserBookmarksEntity entity;
        String userName = Utils.getUserNameLogin(authentication);
        if (Utils.isNullObject(dto.getUserBookmarkId())) {
            entity = new HrUserBookmarksEntity();
            entity.setCreateDate(new Date());
        } else {
            entity = userBookmarksRepository.get(HrUserBookmarksEntity.class, dto.getUserBookmarkId());
            if (entity == null || !entity.getUserName().equals(userName)) {
                return ResponseUtils.getResponseEntity(ErrorApp.BAD_REQUEST);
            }
            entity.setLastUpdateDate(new Date());
        }
        entity.setUserName(userName);
        entity.setType(dto.getType());
        entity.setName(dto.getName());
        entity.setFlagStatus(Constants.STATUS.IS_ACTIVE);
        entity.setOptions(gson.toJson(dto.getOptions()));
        userBookmarksRepositoryJPA.save(entity);
        return ResponseUtils.getResponseSucessEntity(null);
    }

    @Override
    public ResponseEntity<Object> deleteData(Long id, Authentication authentication) {
        HrUserBookmarksEntity entity = userBookmarksRepository.get(HrUserBookmarksEntity.class, id);
        if (entity == null || !entity.getUserName().equals(Utils.getUserNameLogin(authentication))) {
            return ResponseUtils.getResponseEntity(ErrorApp.BAD_REQUEST);
        }
        userBookmarksRepository.deleteById(HrUserBookmarksEntity.class, id);
        return ResponseUtils.getResponseSucessEntity(null);
    }

}
