/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.mbbank.apidoc;

/**
 *
 * @author HuyPC
 */
public class CommonDoc {
    
    /**
     * @api {GET} /api/v1/search-item 1. Lấy danh sách điều kiện tìm kiếm
     * @apiVersion 1.0.0
     * @apiName get-search-item
     * @apiGroup I. COMMON
     * @apiPermission ROLE_EMPLOYEE
     *
     * @apiHeader {String} Content-Type=application/json;charset=UTF-8 ContentType
     * @apiHeader {String} Accept=application/json;charset=UTF-8 Accept
     * @apiHeader {String} Authorization <code>Bearer ${access_token}</code>
     * @apiHeaderExample {json} headerExample:
     * {
     * 	    "Content-Type":"application/json;charset=UTF-8",
     *      "Accept":"application/json;charset=UTF-8",
     *      "Authorization":"${access_token}"
     * }
     *
     * @apiDescription API được sử dụng để lấy danh sách điều kiện tìm kiếm
     *
     * @apiExample Example usage:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://14.225.7.172:8900/api/v1/search-item
	
     *
     * @apiParam {String} moduleCode Mã module ví dụ: THONG_TIN_CO_BAN
     *
     * @apiSuccess {Integer} code Mã lỗi trả về từ hệ thống
     * @apiSuccess {String} message Mô tả lỗi trả về từ hệ thống
     * @apiSuccess {Object[]} data Dữ liệu trả về từ hệ thống. Dữ liệu trả về dạng json array
     * @apiSuccess {String} data.code Mã trường dữ liệu
    * @apiSuccess {String=text, combobox, number, checkbox, radiobox, orgbox, date} data.inputType Loại input.
    * @apiSuccess {String} data.inputLabel Tên cột hiển thị
    * @apiSuccess {String} data.inputPlaceholder Gợi ý nhập liệu
    * @apiSuccess {String=REST_URL, MANUAL, STATIC} data.dataSourceType Nguồn dữ liệu.
    * @apiSuccess {String} data.dataSourceValue Giá trị dữ liệu. có thể là địa chỉ API, JSON
    * @apiSuccess {Long} data.minValue Validate giá trị tối thiểu
    * @apiSuccess {Long} data.maxValue Validate giá trị tối đa
     *
     * @apiError 200 Gọi API thành công
     * @apiError 400 Bad request
     * @apiError 401 Token không hợp lệ
     * @apiError 403 Không có quyền thao tác
     * @apiError 500 Lỗi hệ thống
     *
     * @apiSuccessExample Response (example): 200
     * {
            "code": 200,
            "message": "Thành công",
            "data": [
                {
                    "code": "employee",
                    "inputType": "employeebox",
                    "inputLabel": "Mã nhân viên",
                    "inputPlaceholder": "Nhập từ khóa tìm kiếm",
                    "dataSourceType": null,
                    "dataSourceValue": null,
                    "minValue": null,
                    "maxValue": null
                },
                {
                    "code": "org",
                    "inputType": "orgbox",
                    "inputLabel": "Đơn vị",
                    "inputPlaceholder": null,
                    "dataSourceType": null,
                    "dataSourceValue": null,
                    "minValue": null,
                    "maxValue": null
                },
                {
                    "code": "cccd",
                    "inputType": "text",
                    "inputLabel": "Số CCCD/CMND",
                    "inputPlaceholder": "Nhập từ khóa tìm kiếm",
                    "dataSourceType": "MANUAL",
                    "dataSourceValue": null,
                    "minValue": null,
                    "maxValue": null
                },
                {
                    "code": "marital-status",
                    "inputType": "multi-combobox",
                    "inputLabel": "Tình trạng hôn nhân",
                    "inputPlaceholder": "Nhập từ khóa tìm kiếm",
                    "dataSourceType": "REST_URL",
                    "dataSourceValue": "/api/v1/lookup-values?typeCode=TINH_TRANG_HON_NHAN",
                    "minValue": null,
                    "maxValue": null
                },
                {
                    "code": "gender",
                    "inputType": "combobox",
                    "inputLabel": "Giới tính",
                    "inputPlaceholder": null,
                    "dataSourceType": "REST_URL",
                    "dataSourceValue": "/api/v1/lookup-values?typeCode=GIOI_TINH",
                    "minValue": null,
                    "maxValue": null
                },
                {
                    "code": "emp-status",
                    "inputType": "combobox",
                    "inputLabel": "Trạng thái làm việc",
                    "inputPlaceholder": null,
                    "dataSourceType": "STATIC",
                    "dataSourceValue": "[{\"label\": \"Đang làm việc\", \"value\": \"1\"}, {\"label\": \"Đã nghỉ việc\", \"value\": \"0\"}]",
                    "minValue": null,
                    "maxValue": null
                },
                {
                    "code": "is-mb-employee",
                    "inputType": "checkbox",
                    "inputLabel": "Là nhân viên MB",
                    "inputPlaceholder": null,
                    "dataSourceType": "STATIC",
                    "dataSourceValue": "[{\"label\": \"Có\", \"value\": \"1\"}, {\"label\": \"Không\", \"value\": \"0\"}]",
                    "minValue": null,
                    "maxValue": null
                }
            ],
            "path": "/api/v1/search-item",
            "timestamp": "2022-04-06T17:42:10.155+07:00",
            "status": 200
        }
     * @apiErrorExample Response (example): 400 Bad Request
     * {"code": 400, "message": "Bad Request"}
     * @apiErrorExample Response (example): 403 Permission denied
     * {"code": 403, "message": "Permission denied"}
    */
    
    
    
    
    /**
     * @api {POST} /api/v1/user-bookmark 2. Lưu điều kiện tìm kiếm(Bookmark)
     * @apiVersion 1.0.0
     * @apiName post-user-bookmark
     * @apiGroup I. COMMON
     * @apiPermission ROLE_EMPLOYEE
     *
     * @apiHeader {String} Content-Type=application/json;charset=UTF-8 ContentType
     * @apiHeader {String} Accept=application/json;charset=UTF-8 Accept
     * @apiHeader {String} Authorization <code>Bearer ${access_token}</code>
     * @apiHeaderExample {json} headerExample:
     * {
     * 	    "Content-Type":"application/json;charset=UTF-8",
     *      "Accept":"application/json;charset=UTF-8",
     *      "Authorization":"${access_token}"
     * }
     *
     * @apiDescription API được sử dụng để lưu thông tin dấu trang
     *
     * @apiExample Example usage:
     * curl -i -X POST -H "Content-Type: application/json" http://14.225.7.172:8900/api/v1/user-bookmark -d 'partyDate=#partyDate'
	 
     * @apiParamExample {json} Request Body Param Example:
            {
                "userBookmarkId": 1,
                "userName": "MB001",
                "type": "THONG_TIN_CHUNG",
                "name": "Tìm kiếm thông tin chung",
                "options": [
                    {"code": "gioi-tinh", "values": ["01", "02"]},
                    {"code": "ma-nhan-vien", "values": ["Tu"]},
                    {"code": "nam-sinh", "values": [], "valueFrom": "02/03/1990", "valueTo": "24/12/1994"}
                ]
            }
     *
     * @apiBody {Long} userBookmarkId ID bản ghi
     * @apiBody {String} name Tên
	 * @apiBody {String} type Mã module ví dụ: THONG_TIN_CO_BAN
     * @apiBody {Object[]} options Thông tin điều kiện tìm kiếm
     * @apiBody {String} options.code Mã trường dữ liệu
     * @apiBody {String[]} options.values Giá trị người dùng đã nhập vào
     * @apiBody {String} options.valueFrom Giá trị từ
     * @apiBody {String} options.valueTo Giá trị đến

     
     *
     * @apiSuccess {Integer} code Mã lỗi trả về từ hệ thống
     * @apiSuccess {String} message Mô tả lỗi trả về từ hệ thống
     *
     * @apiError 200 Gọi API thành công
     * @apiError 400 Bad request
     * @apiError 401 Token không hợp lệ
     * @apiError 403 Không có quyền thao tác
     * @apiError 500 Lỗi hệ thống
     *
     * @apiSuccessExample Response (example): 200
     * {
            "code": 200,
            "message": "Thành công",
            "path": "/api/v1/user-bookmark",
            "timestamp": "2022-04-06T11:56:41.258+07:00",
            "status": 200
        }
     * @apiErrorExample Response (example): 400 Bad Request
     * {"code": 400, "message": "Bad Request"}
     * @apiErrorExample Response (example): 403 Permission denied
     * {"code": 403, "message": "Permission denied"}
    */
    
    
    
    /**
     * @api {GET} /api/v1/user-bookmark 3. Danh sách điều kiện tìm kiếm đã lưu(Bookmark)
     * @apiVersion 1.0.0
     * @apiName get-user-bookmark
     * @apiGroup I. COMMON
     * @apiPermission ROLE_EMPLOYEE
     *
     * @apiHeader {String} Content-Type=application/json;charset=UTF-8 ContentType
     * @apiHeader {String} Accept=application/json;charset=UTF-8 Accept
     * @apiHeader {String} Authorization <code>Bearer ${access_token}</code>
     * @apiHeaderExample {json} headerExample:
     * {
     * 	    "Content-Type":"application/json;charset=UTF-8",
     *      "Accept":"application/json;charset=UTF-8",
     *      "Authorization":"${access_token}"
     * }
     *
     * @apiDescription API được sử dụng để danh sách dấu trang
     *
     * @apiExample Example usage:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://14.225.7.172:8900/api/v1/user-bookmark
     *
     * @apiParam {String} type Mã module. ví dụ: THONG_TIN_CO_BAN
     
     *
     * @apiSuccess {Integer} code Mã lỗi trả về từ hệ thống
     * @apiSuccess {String} message Mô tả lỗi trả về từ hệ thống
     * @apiSuccess {Object[]} data Dữ liệu trả về từ hệ thống. Dữ liệu trả về dạng json array
     * @apiSuccess {Long} data.userBookmarkId ID bản ghi
     * @apiSuccess {String} data.name Tên
     * @apiSuccess {Object[]} data.options Thông tin điều kiện tìm kiếm
     * @apiSuccess {String} data.options.code Mã trường dữ liệu
     * @apiSuccess {String[]} data.options.values Giá trị người dùng đã nhập vào
     * @apiSuccess {String} data.options.valueFrom Giá trị từ
     * @apiSuccess {String} data.options.valueTo Giá trị đến
     *
     * @apiError 200 Gọi API thành công
     * @apiError 400 Bad request
     * @apiError 401 Token không hợp lệ
     * @apiError 403 Không có quyền thao tác
     * @apiError 500 Lỗi hệ thống
     *
     * @apiSuccessExample Response (example): 200
     * {
            "code": 200,
            "message": "Thành công",
            "data": [
                {
                    "userBookmarkId": 1,
                    "userName": null,
                    "type": "THONG_TIN_CHUNG",
                    "name": "Tìm kiếm thông tin chung",
                    "options": [
                        {
                            "code": "gioi-tinh",
                            "values": [
                                "01",
                                "02"
                            ],
                            "valueFrom": null,
                            "valueTo": null
                        },
                        {
                            "code": "ma-nhan-vien",
                            "values": [
                                "Tu"
                            ],
                            "valueFrom": null,
                            "valueTo": null
                        },
                        {
                            "code": "nam-sinh",
                            "values": [],
                            "valueFrom": "02/03/1990",
                            "valueTo": "24/12/1994"
                        }
                    ]
                }
            ],
            "path": "/api/v1/user-bookmark",
            "timestamp": "2022-04-06T11:56:41.258+07:00",
            "status": 200
        }
     * @apiErrorExample Response (example): 400 Bad Request
     * {"code": 400, "message": "Bad Request"}
     * @apiErrorExample Response (example): 403 Permission denied
     * {"code": 403, "message": "Permission denied"}
    */
    
    
    
	
	
	/**
     * @api {DELETE} /api/v1/user-bookmark/{id} 4. Xóa Bookmark
     * @apiVersion 1.0.0
     * @apiName del-user-bookmark
     * @apiGroup I. COMMON
     * @apiPermission ROLE_EMPLOYEE
     *
     * @apiHeader {String} Content-Type=application/json;charset=UTF-8 ContentType
     * @apiHeader {String} Accept=application/json;charset=UTF-8 Accept
     * @apiHeader {String} Authorization <code>Bearer ${access_token}</code>
     * @apiHeaderExample {json} headerExample:
     * {
     * 	    "Content-Type":"application/json;charset=UTF-8",
     *      "Accept":"application/json;charset=UTF-8",
     *      "Authorization":"${access_token}"
     * }
     *
     * @apiDescription API được sử dụng để xóa thông tin dấu trang
     *
     * @apiExample Example usage:
     * curl -i -X POST -H "Content-Type: application/json" http://14.225.7.172:8900/api/v1/user-bookmark/{id} -d 'partyDate=#partyDate'
     *
     * @apiSuccess {Integer} code Mã lỗi trả về từ hệ thống
     * @apiSuccess {String} message Mô tả lỗi trả về từ hệ thống
     *
     * @apiError 200 Gọi API thành công
     * @apiError 400 Bad request
     * @apiError 401 Token không hợp lệ
     * @apiError 403 Không có quyền thao tác
     * @apiError 500 Lỗi hệ thống
     *
     * @apiSuccessExample Response (example): 200
     * {
            "code": 200,
            "message": "Thành công",
            "path": "/api/v1/user-bookmark",
            "timestamp": "2022-04-06T11:56:41.258+07:00",
            "status": 200
        }
     * @apiErrorExample Response (example): 400 Bad Request
     * {"code": 400, "message": "Bad Request"}
     * @apiErrorExample Response (example): 403 Permission denied
     * {"code": 403, "message": "Permission denied"}
    */
	
    
    
    
    /**
     * @api {GET} /api/v1/download/temp-file 5. Download File xuất ra từ chức năng xuất báo cáo
     * @apiVersion 1.0.0
     * @apiName download-temp-file
     * @apiGroup I. COMMON
     * @apiPermission ROLE_EMPLOYEE
     *
     * @apiHeader {String} Content-Type=application/json;charset=UTF-8 ContentType
     * @apiHeader {String} Accept=application/json;charset=UTF-8 Accept
     * @apiHeader {String} Authorization <code>Bearer ${access_token}</code>
     * @apiHeaderExample {json} headerExample:
     * {
     * 	    "Content-Type":"application/json;charset=UTF-8",
     *      "Accept":"application/json;charset=UTF-8",
     *      "Authorization":"${access_token}"
     * }
     *
     * @apiDescription API được sử dụng để download file xuất ra từ chức năng báo cáo
     *
     * @apiParam {String} fileName Tên file người dùng muốn download
     *
     * @apiExample Example usage:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://14.225.7.172:8900/api/v1/download/temp-file
     *
     * @apiSuccess {File} file Trả về file và trình duyệt tự động download.
     *
     * @apiError 200 Gọi API thành công
     * @apiError 400 Bad request
     * @apiError 401 Token không hợp lệ
     * @apiError 403 Không có quyền thao tác
     * @apiError 500 Lỗi hệ thống
     *
     * @apiErrorExample Response (example): 400 Bad Request
     * "mess": {"code": 400, "description": "Bad Request"}
     * @apiErrorExample Response (example): 403 Permission denied
     * "mess": {"code": 403, "description": "Permission denied"}
    */
	
    
}
