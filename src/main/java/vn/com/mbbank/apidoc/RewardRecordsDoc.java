package vn.com.mbbank.apidoc;

public class RewardRecordsDoc {
    /**
     * @api {GET} /api/v1/reward-records 1. Tra cứu thông tin khen thưởng
     * @apiVersion 1.0.0
     * @apiName get-reward-records
     * @apiGroup XIV. Thông tin khen thưởng
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
     * @apiDescription API được sử dụng để tra cứu thông tin khen thưởng
     *
     * @apiExample Example usage:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://14.225.7.172:8900/api/v1/reward-records
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
     * @apiSuccess {Object[]} data.listData Dữ liệu trả về từ hệ thống. Dữ liệu trả về dạng json array
     * @apiSuccess {Object} data.count Tổng số lượng bản ghi
     *
     * @apiSuccess {Long} data.listData.employeeId ID bản ghi
     * @apiSuccess {String} data.listData.employeeCode Mã nhân viên
     * @apiSuccess {String} data.listData.fullName Tên nhân viên
     * @apiSuccess {String} data.listData.orgName Tên đơn vị
     * @apiSuccess {String} data.listData.methodName Hình thức/Danh hiệu khen thưởng
     * @apiSuccess {Date} data.listData.signedDate Ngày quyết định
     * @apiSuccess {Integer} data.listData.year Năm
     * @apiSuccess {String} data.listData.reason Lý do
     * @apiSuccess {Integer} data.listData.flagStatus Trạng thái làm việc. 1: Đang làm việc, Ngược lại là Đã nghỉ việc
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
     *                 "employeeId": 1,
     *                 "methodName": "Bằng khen",
     *                 "signedDate": "05/04/2022",
     *                 "year": 2020,
     *                 "reason": "abc",
     *                 "flagStatus": 1,
     *                 "employeeCode": "MB0001",
     *                 "fullName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "methodName": "Lao động tiên tiến",
     *                 "signedDate": "02/04/2019",
     *                 "year": 2020,
     *                 "reason": "123123",
     *                 "flagStatus": 1,
     *                 "employeeCode": "MB0001",
     *                 "fullName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "methodName": "Lao động tiên tiến",
     *                 "signedDate": "08/03/2022",
     *                 "year": 2020,
     *                 "reason": "Test",
     *                 "flagStatus": 1,
     *                 "employeeCode": "MB0001",
     *                 "fullName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "methodName": "Lao động tiên tiến",
     *                 "signedDate": "09/03/2022",
     *                 "year": 2020,
     *                 "reason": "Test",
     *                 "flagStatus": 1,
     *                 "employeeCode": "MB0001",
     *                 "fullName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 2,
     *                 "methodName": "Lao động tiên tiến",
     *                 "signedDate": "08/04/2022",
     *                 "year": 2020,
     *                 "reason": "Test",
     *                 "employeeCode": "MB0002",
     *                 "fullName": "Đặng Đình Tứ",
     *                 "orgName": "Phòng Quan hệ khách hàng"
     *             },
     *             {
     *                 "employeeId": 7,
     *                 "methodName": "Lao động tiên tiến",
     *                 "signedDate": "01/04/2022",
     *                 "year": 2020,
     *                 "reason": "Có thành tích tốt trong năm",
     *                 "flagStatus": 1,
     *                 "employeeCode": "MB0014",
     *                 "fullName": "Nguyễn Quang Thời",
     *                 "orgName": "BP Quan hệ khách hàng"
     *             }
     *         ],
     *         "count": 6
     *     },
     *     "path": "/api/v1/reward-records",
     *     "timestamp": "2022-04-13T14:29:26.163+07:00",
     *     "status": 200
     * }
     * @apiErrorExample Response (example): 400 Bad Request
     * {"code": 400, "message": "Bad Request"}
     * @apiErrorExample Response (example): 403 Permission denied
     * {"code": 403, "message": "Permission denied"}
     */
}
