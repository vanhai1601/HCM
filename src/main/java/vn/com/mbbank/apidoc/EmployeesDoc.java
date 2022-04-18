
package vn.com.mbbank.apidoc;

/**
 *
 * @author Admin
 */
public class EmployeesDoc {
    /**
     * @api {GET} /api/v1/employees/personal-information 1. Tìm kiếm thông tin cơ bản
     * @apiVersion 1.0.0
     * @apiName get-emp-personal-information
     * @apiGroup II. Thông tin cơ bản
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
     * @apiDescription API được sử dụng để tìm kiếm thông tin cơ bản.
     *
     * @apiExample Example usage:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://14.225.7.172:8900/api/v1/employees/personal-information
     *
     * @apiParam {String} [employeeCode] Mã nhân viên
     * @apiParam {String} [fullName] Tên nhân viên
     * @apiParam {Integer} [flagStatus] Trạng thái. 1: Đang làm việc. 0: Đã nghỉ việc
     * @apiParam {Long} [organizationId] ID đơn vị
     * @apiParam {String} [listEthnicCode] Ngoài các param cố định thì các trường generate động được mapping trường code trong API lấy danh sách điều kiện tìm kiếm thành  tên param. <br/>
     * Đối với dữ liệu combobox thì truyền lên mảng giá trị. <br/>Đối với Dữ liệu Từ ngày - Đến ngày thì truyền mảng giá trị: Phần tử thứ nhất là Từ ngày. Phần tử thứ 2 là đến ngày.<br/> Ví dụ: ["20/01/2022", "24/05/2022"] <br/>Nếu người dùng chỉ nhập 1 trong 2 phần từ thì phần tử còn lên đc truyền lên giá trị rỗng. <br/> Ví dụ: ["", "Giá trị đến ngày"] hoặc ["Giá trị từ ngày", ""]
     *
     * @apiSuccess {Integer} code Mã lỗi trả về từ hệ thống
     * @apiSuccess {String} message Mô tả lỗi trả về từ hệ thống
     * @apiSuccess {Object[]} data Dữ liệu trả về từ hệ thống. Dữ liệu trả về dạng json array
     * @apiSuccess {Long} data.employeeId ID bản ghi
     * @apiSuccess {String} data.employeeCode Mã nhân viên
     * @apiSuccess {String} data.fullName Tên nhân viên
     * @apiSuccess {String} data.empTypeName Đối tượng
     * @apiSuccess {String} data.positionName Chức danh
     * @apiSuccess {String} data.orgName Tên đơn vị
     * @apiSuccess {Integer} data.flagStatus Trạng thái làm việc. 1: Đang làm việc, Ngược lại là Đã nghỉ việc
     * @apiSuccess {String} data.email Email nhân viên
     * @apiSuccess {String} data.mobileNumber Số điện thoại
     * @apiSuccess {String} data.dateOfBirth Ngày sinh
     * @apiSuccess {String} data.genderName Giới tính
     * @apiSuccess {String} data.ethnicName Dân tộc
     * @apiSuccess {String} data.religionName Tôn giáo
     * @apiSuccess {String} data.personalId Số CCCD
     * @apiSuccess {String} data.insuranceNo Số BHXH
     * @apiSuccess {String} data.taxNo Mã số thuế
     * @apiSuccess {String} data.isInsuranceMb Có tham gia BHXH MB
     * @apiSuccess {String} data.placeOfBirth Nơi sinh
     * @apiSuccess {String} data.maritalStatusName Tình trạng hôn nhân
     * @apiSuccess {String} data.pernamentAddress Hộ khẩu thường trú
     * @apiSuccess {String} data.currentAddress Địa chỉ hiện tại
     * @apiSuccess {String} data.originalAddress Hộ khẩu thường trú
     * @apiSuccess {String} data.partyDate Ngày vào Đảng
     * @apiSuccess {String} data.partyOfficialDate Ngày vào Đảng chính thức
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
            "data": {
                "listData": [
                    {
                        "employeeId": 5,
                        "employeeCode": "MB0012",
                        "fullName": "Vũ Thị Ánh Liên",
                        "empTypeName": "Chính thức",
                        "dateOfBirth": "12/02/1993",
                        "genderName": "Nữ",
                        "ethnicName": "Kinh",
                        "religionName": "Không",
                        "personalId": "12224324234",
                        "maritalStatusName": "Độc thân",
                        "mobileNumber": "09721235",
                        "positionName": "Trưởng phòng quan hệ khách hàng",
                        "orgName": "BP Kế toán và Dịch vụ khách hàng",
                        "isInsuranceMb": 0,
                        "flagStatus": 1
                    },
                    {
                        "employeeId": 7,
                        "employeeCode": "MB0014",
                        "fullName": "Nguyễn Quang Thời",
                        "empTypeName": "Chính thức",
                        "dateOfBirth": "12/02/1995",
                        "genderName": "Nam",
                        "ethnicName": "Kinh",
                        "religionName": "Công giáo",
                        "personalId": "2228777",
                        "maritalStatusName": "Độc thân",
                        "mobileNumber": "09721237",
                        "placeOfBirth": "Hà nội",
                        "originalAddress": "Thái Bình",
                        "pernamentAddress": "null, Phường Phúc Xá, Quận Ba Đình, Thành phố Hà Nội",
                        "currentAddress": "xóm 7, Phường Quảng An, Quận Tây Hồ, Thành phố Hà Nội",
                        "positionName": "Trưởng phòng",
                        "orgName": "BP Quan hệ khách hàng",
                        "isInsuranceMb": 1,
                        "flagStatus": 1
                    }
                ],
                "count": 5
            },
            "path": "/api/v1/employees/personal-information",
            "timestamp": "2022-04-11T16:34:24.305+07:00",
            "status": 200
        }
     * @apiErrorExample Response (example): 400 Bad Request
     * {"code": 400, "message": "Bad Request"}
     * @apiErrorExample Response (example): 403 Permission denied
     * {"code": 403, "message": "Permission denied"}
    */
    
    
    
    /**
     * @api {GET} /api/v1/employees/export-template-add 2. Xuất Template Import THÊM MỚI nhân viên
     * @apiVersion 1.0.0
     * @apiName emp-export-template-add
     * @apiGroup II. Thông tin cơ bản
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
     * @apiDescription API được sử dụng để xuất biểu mẫu import.
     *
     * @apiExample Example usage:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://14.225.7.172:8900/api/v1/employees/export-template-add
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


    /**
     * @api {POST} /api/v1/employees/import-add 3. Xử lý import THÊM MỚI nhân viên
     * @apiVersion 1.0.0
     * @apiName employees-import-add
     * @apiGroup II. Thông tin cơ bản
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
     * @apiDescription API được sử dụng để xử lý dữ liệu import.
     *
     * @apiExample Example usage:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://14.225.7.172:8989/import/v1/import-emp
     *
     * @apiParam {MultipartFile} file File import người dùng upload
     *
     * @apiSuccess {Integer} code Mã lỗi trả về từ hệ thống
     * @apiSuccess {String} message Mô tả lỗi trả về từ hệ thống
     * @apiSuccess {JSON} data Mô tả chi tiết lỗi file import. Dữ liệu trả về dạng json array
     * @apiSuccess {String} data.errorFile Tên file chứa mô tả lỗi. Client thực hiện gọi api tại mục I.5 sau để download file
     * @apiSuccess {Object[]} data.errorList Mô tả lỗi dữ liệu đầu vào
     * @apiSuccess {Integer} data.errorList.row Dòng xảy ra lỗi
     * @apiSuccess {Integer} data.errorList.column Cột xảy ra lỗi
     * @apiSuccess {String} data.errorList.columnLabel Tên cột xảy ra lỗi
     * @apiSuccess {String} data.errorList.description Mô tả lỗi
     * @apiSuccess {String} data.errorList.content Nội dung người dùng nhập vào
     *
     * @apiError 200 Gọi API thành công
     * @apiError 4 File import có lỗi
     * @apiError 400 Bad request
     * @apiError 401 Token không hợp lệ
     * @apiError 403 Không có quyền thao tác
     * @apiError 500 Lỗi hệ thống
     *
     * @apiSuccessExample Response (example): 200
     * {
     *     "code": 200,
     *     "message": "Thành công"
     * }
     *
     * @apiErrorExample Response (example): 4 File invalid
     * {
     * 	    "code": 4,
     * 	    "message": "File import có lỗi",
     * 	    "data": {
     * 	        "errorFile": "errorFile.xlsx",
     * 	        "errorList": [
     *               {
     *                   "row": 5,
     *                   "column": 4,
     *                   "columnLabel": "D",
     *                   "description": "Mã chức danh không hợp lệ",
     *                   "content": "CD0011"
     *               }
     *           ]
     * 	    }
     * }
     *
     * @apiErrorExample Response (example): 400 Bad Request
     * "mess": {"code": 400, "description": "Bad Request"}
     * @apiErrorExample Response (example): 403 Permission denied
     * "mess": {"code": 403, "description": "Permission denied"}
    */
    
    
    
    
    
    
    /**
     * @api {GET} /api/v1/employees/export-template-update 4. Xuất Template Import CẬP NHẬT nhân viên
     * @apiVersion 1.0.0
     * @apiName emp-export-template-update
     * @apiGroup II. Thông tin cơ bản
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
     * @apiDescription API được sử dụng để xuất biểu mẫu import.
     *
     * @apiExample Example usage:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://14.225.7.172:8900/api/v1/employees/export-template-update
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


    /**
     * @api {POST} /api/v1/employees/import-update 5. Xử lý import CẬP NHẬT nhân viên
     * @apiVersion 1.0.0
     * @apiName employees-import-update
     * @apiGroup II. Thông tin cơ bản
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
     * @apiDescription API được sử dụng để xử lý dữ liệu import.
     *
     * @apiExample Example usage:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://14.225.7.172:8989/import/v1/import-update
     *
     * @apiParam {MultipartFile} file File import người dùng upload
     *
     * @apiSuccess {Integer} code Mã lỗi trả về từ hệ thống
     * @apiSuccess {String} message Mô tả lỗi trả về từ hệ thống
     * @apiSuccess {JSON} data Mô tả chi tiết lỗi file import. Dữ liệu trả về dạng json array
     * @apiSuccess {String} data.errorFile Tên file chứa mô tả lỗi. Client thực hiện gọi api tại mục I.5 sau để download file
     * @apiSuccess {Object[]} data.errorList Mô tả lỗi dữ liệu đầu vào
     * @apiSuccess {Integer} data.errorList.row Dòng xảy ra lỗi
     * @apiSuccess {Integer} data.errorList.column Cột xảy ra lỗi
     * @apiSuccess {String} data.errorList.columnLabel Tên cột xảy ra lỗi
     * @apiSuccess {String} data.errorList.description Mô tả lỗi
     * @apiSuccess {String} data.errorList.content Nội dung người dùng nhập vào
     *
     * @apiError 200 Gọi API thành công
     * @apiError 4 File import có lỗi
     * @apiError 400 Bad request
     * @apiError 401 Token không hợp lệ
     * @apiError 403 Không có quyền thao tác
     * @apiError 500 Lỗi hệ thống
     *
     * @apiSuccessExample Response (example): 200
     * {
     *     "code": 200,
     *     "message": "Thành công"
     * }
     *
     * @apiErrorExample Response (example): 4 File invalid
     * {
     * 	    "code": 4,
     * 	    "message": "File import có lỗi",
     * 	    "data": {
     * 	        "errorFile": "errorFile.xlsx",
     * 	        "errorList": [
     *               {
     *                   "row": 5,
     *                   "column": 4,
     *                   "columnLabel": "D",
     *                   "description": "Mã chức danh không hợp lệ",
     *                   "content": "CD0011"
     *               }
     *           ]
     * 	    }
     * }
     *
     * @apiErrorExample Response (example): 400 Bad Request
     * "mess": {"code": 400, "description": "Bad Request"}
     * @apiErrorExample Response (example): 403 Permission denied
     * "mess": {"code": 403, "description": "Permission denied"}
    */


    
}
