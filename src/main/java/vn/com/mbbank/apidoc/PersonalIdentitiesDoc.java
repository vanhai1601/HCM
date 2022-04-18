package vn.com.mbbank.apidoc;

public class PersonalIdentitiesDoc {

    /**
     * @api {GET} /api/v1/identities 1. Tìm kiếm thông tin định danh
     * @apiVersion 1.0.0
     * @apiName searchIdentities
     * @apiGroup II. Thông tin định danh
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
     * @apiDescription API được sử dụng để tìm kiếm thông tin định danh
     *
     * @apiExample Example usage:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://14.225.7.172:8900/api/v1/identities
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
     * @apiSuccess {Object} data Dữ liệu trả về từ hệ thống. Dữ liệu trả về dạng json object
     * @apiSuccess {Object[]} listData Dữ liệu trả về từ hệ thống. Dữ liệu trả về dạng json array
     * @apiSuccess {Object} data.count Tổng số lượng bản ghi
     * @apiSuccess {String}  data.listData.idNo Số định danh
     * @apiSuccess {Date}    data.listData.idIssueDate Ngày cấp
     * @apiSuccess {String}  data.listData.idIssuePlace Nơi cấp
     * @apiSuccess {Integer} data.listData.isMain Là số định danh chính
     * @apiSuccess {Integer} data.listData.flagStatus Trạng thái làm việc. 1: Đang làm việc, Ngược lại là Đã nghỉ việc
     * @apiSuccess {String}  data.listData.label Loại giấy tờ
     * @apiSuccess {String}  data.listData.orgName Đơn vị
     * @apiSuccess {String}  data.listData.employeeCode Mã nhân viên
     * @apiSuccess {String}  data.listData.fullName Tên nhân viên
     *
     *
     * @apiError 200 Gọi API thành công
     * @apiError 400 Bad request
     * @apiError 401 Token không hợp lệ
     * @apiError 403 Không có quyền thao tác
     * @apiError 500 Lỗi hệ thống
     *
     * @apiSuccessExample Response (example): 200
     * {
     *     "code": 200,
     *     "message": "Thành công",
     *     "data": {
     *         "listData": [
     *             {
     *                 "idNo": "101290064",
     *                 "idIssueDate": "12/04/2022",
     *                 "idIssuePlace": "Hà Nội",
     *                 "isMain": 0,
     *                 "flagStatus": 1,
     *                 "label": "Giấy phép lao động",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "fullName": "Lương Thị Phượng"
     *             },
     *             {
     *                 "idNo": "101290064",
     *                 "idIssueDate": "12/04/2022",
     *                 "idIssuePlace": "thhhh",
     *                 "isMain": 0,
     *                 "flagStatus": 1,
     *                 "label": "Giấy phép lao động",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "fullName": "Lương Thị Phượng"
     *             },
     *             {
     *                 "idNo": "34324dfd2432",
     *                 "idIssueDate": "04/03/2022",
     *                 "idIssuePlace": "HNN",
     *                 "isMain": 1,
     *                 "flagStatus": 1,
     *                 "label": "Giấy phép lao động",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "fullName": "Lương Thị Phượng"
     *             },
     *             {
     *                 "idNo": "101290064",
     *                 "isMain": 0,
     *                 "flagStatus": 1,
     *                 "label": "Giấy phép lao động",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "fullName": "Lương Thị Phượng"
     *             },
     *             {
     *                 "idNo": "033082447271",
     *                 "idIssueDate": "27/03/2023",
     *                 "idIssuePlace": "HN",
     *                 "isMain": 0,
     *                 "flagStatus": 1,
     *                 "label": "Giấy phép lao động",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "fullName": "Lương Thị Phượng"
     *             },
     *             {
     *                 "idNo": "12233333",
     *                 "idIssueDate": "01/04/2022",
     *                 "idIssuePlace": "bnn",
     *                 "isMain": 1,
     *                 "label": "Hộ chiếu",
     *                 "orgName": "Phòng Quan hệ khách hàng",
     *                 "employeeCode": "MB0002",
     *                 "fullName": "Đặng Đình Tứ"
     *             },
     *             {
     *                 "idNo": "12233333",
     *                 "idIssueDate": "04/01/2022",
     *                 "idIssuePlace": "bnn",
     *                 "isMain": 1,
     *                 "label": "Hộ chiếu",
     *                 "orgName": "Phòng Quan hệ khách hàng",
     *                 "employeeCode": "MB0002",
     *                 "fullName": "Đặng Đình Tứ"
     *             },
     *             {
     *                 "idNo": "12224324234",
     *                 "idIssueDate": "04/04/2022",
     *                 "isMain": 1,
     *                 "flagStatus": 1,
     *                 "label": "Chứng minh thư",
     *                 "orgName": "BP Kế toán và Dịch vụ khách hàng",
     *                 "employeeCode": "MB0012",
     *                 "fullName": "Vũ Thị Ánh Liên"
     *             },
     *             {
     *                 "idNo": "2228777",
     *                 "idIssueDate": "01/04/2022",
     *                 "idIssuePlace": "nơi cấp",
     *                 "isMain": 1,
     *                 "flagStatus": 1,
     *                 "label": "Chứng minh thư",
     *                 "orgName": "BP Quan hệ khách hàng",
     *                 "employeeCode": "MB0014",
     *                 "fullName": "Nguyễn Quang Thời"
     *             },
     *             {
     *                 "idNo": "3773488",
     *                 "idIssueDate": "01/04/2022",
     *                 "idIssuePlace": "nơi cấp",
     *                 "isMain": 1,
     *                 "flagStatus": 1,
     *                 "label": "Chứng minh thư",
     *                 "orgName": "BP Quan hệ khách hàng",
     *                 "employeeCode": "MB0021",
     *                 "fullName": "Đinh Duy Anh"
     *             }
     *         ],
     *         "count": 10
     *     },
     *     "path": "/api/v1/identities",
     *     "timestamp": "2022-04-13T10:30:05.142+07:00",
     *     "status": 200
     * }
     *
     *
     * @apiErrorExample Response (example): 400 Bad Request
     * {"code": 400, "message": "Bad Request"}
     * @apiErrorExample Response (example): 403 Permission denied
     * {"code": 403, "message": "Permission denied"}
     */

}
