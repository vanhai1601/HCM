package vn.com.mbbank.controllers;import vn.com.mbbank.dto.InfoChangesDTO;import vn.com.mbbank.services.InfoChangesService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.MediaType;import org.springframework.security.core.Authentication;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;import vn.com.mbbank.utils.*;/** * Autogen class: Lớp thao tác danh sach lich su tac dong theo nhan vien *  * @author ToolGen * @date Sun Mar 20 22:23:53 ICT 2022 */@RestController@RequestMapping(Constants.REQUEST_MAPPING_PREFIX)public class InfoChangesController {//    @Autowired//    InfoChangesService infoChangesService;//////    /**//     * Lay danh sach lich su tac dong theo nhan vien//     *//     * @param authentication: thong tin nguoi dung//     * @param dataParams      params client//     * @return//     *///    @GetMapping(value = "/v1/employees/{id}/info-changes", produces = MediaType.APPLICATION_JSON_VALUE)//    public ResponseEntity<Object> getInfoChanges(Authentication authentication,//                                              @PathVariable Long id,                                              InfoChangesDTO dataParams) {//        /*//        ==========================================================//        authenEntity: user info and role//        dataParams: danh sach bien client co the truyen len//        ==========================================================//        *///        Object resultObj = infoChangesService.getInfoChanges(dataParams,id);//        return ResponseUtils.getResponseSucessEntity(resultObj);//    }}