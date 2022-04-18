package vn.com.mbbank.apidoc;

public class DecplineRecordsDoc {
    /**
     * @api {GET} /api/v1/decpline-records 1. Tra cứu thông tin kỷ luật
     * @apiVersion 1.0.0
     * @apiName get-decpline-records
     * @apiGroup XV. THông tin kỷ luật
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
     * @apiDescription API được sử dụng để tra cứu thông tin kỷ luật.
     *
     * @apiExample Example usage:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://14.225.7.172:8900/api/v1/decpline-records
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
     * @apiSuccess {Date} data.listData.signedDate Ngày quyết định
     * @apiSuccess {Long} data.listData.amount Số tiền
     * @apiSuccess {String} data.listData.disciplineMethodName Hình thức kỷ luật
     * @apiSuccess {String} data.listData.disciplineLevelName Cấp quyết định
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
     *                 "disciplineMethodName": "Hạ bậc lương",
     *                 "disciplineLevelName": "Kỷ luật cấp trung tâm",
     *                 "amount": 123123123,
     *                 "reason": "3123123",
     *                 "signedDate": "06/04/2022",
     *                 "flagStatus": 1,
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "disciplineMethodName": "Sa thải",
     *                 "disciplineLevelName": "Kỷ luật cấp đơn vị",
     *                 "amount": 300000,
     *                 "reason": "abc    ,.,,.,   ,,,,                                                                      ....jhk",
     *                 "signedDate": "11/04/2022",
     *                 "flagStatus": 1,
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "disciplineMethodName": "Sa thải",
     *                 "disciplineLevelName": "Kỷ luật cấp đơn vị",
     *                 "amount": 9999999999,
     *                 "reason": "~!!s             ssss          343434",
     *                 "signedDate": "02/04/1998",
     *                 "flagStatus": 1,
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "disciplineMethodName": "Sa thải",
     *                 "disciplineLevelName": "Kỷ luật cấp đơn vị",
     *                 "amount": 9999999999,
     *                 "reason": "~!@ssss           33          4",
     *                 "signedDate": "10/04/2022",
     *                 "flagStatus": 1,
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "disciplineMethodName": "Hạ bậc lương",
     *                 "disciplineLevelName": "Kỷ luật cấp đơn vị",
     *                 "signedDate": "01/04/2010",
     *                 "flagStatus": 1,
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "disciplineMethodName": "Cảnh cáo",
     *                 "disciplineLevelName": "Kỷ luật cấp đơn vị",
     *                 "amount": 1000000,
     *                 "reason": "Triển khai hệ thống lỗi",
     *                 "signedDate": "04/03/2022",
     *                 "flagStatus": 1,
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "disciplineMethodName": "Khiển trách",
     *                 "disciplineLevelName": "Kỷ luật cấp trung tâm",
     *                 "amount": 123123123,
     *                 "reason": "123213",
     *                 "signedDate": "01/04/2022",
     *                 "flagStatus": 1,
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "disciplineMethodName": "Khiển trách",
     *                 "disciplineLevelName": "Kỷ luật cấp trung tâm",
     *                 "signedDate": "01/03/2022",
     *                 "flagStatus": 1,
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "disciplineMethodName": "Khiển trách",
     *                 "disciplineLevelName": "Kỷ luật cấp trung tâm",
     *                 "amount": 123123,
     *                 "reason": "ưqeqwe",
     *                 "signedDate": "02/04/2022",
     *                 "flagStatus": 1,
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "disciplineMethodName": "Cảnh cáo",
     *                 "disciplineLevelName": "Kỷ luật cấp đơn vị",
     *                 "amount": 10000,
     *                 "reason": "Test",
     *                 "signedDate": "05/03/2022",
     *                 "flagStatus": 1,
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 7,
     *                 "disciplineMethodName": "Cảnh cáo",
     *                 "disciplineLevelName": "Kỷ luật cấp đơn vị",
     *                 "reason": "Do triển khai dự án chậm tiến độ",
     *                 "signedDate": "05/04/2022",
     *                 "flagStatus": 1,
     *                 "fullName": "Nguyễn Quang Thời",
     *                 "employeeCode": "MB0014",
     *                 "orgName": "BP Quan hệ khách hàng"
     *             }
     *         ],
     *         "count": 11
     *     },
     *     "path": "/api/v1/decpline-records",
     *     "timestamp": "2022-04-13T13:42:33.656+07:00",
     *     "status": 200
     * }
     * @apiErrorExample Response (example): 400 Bad Request
     * {"code": 400, "message": "Bad Request"}
     * @apiErrorExample Response (example): 403 Permission denied
     * {"code": 403, "message": "Permission denied"}
     */
}
