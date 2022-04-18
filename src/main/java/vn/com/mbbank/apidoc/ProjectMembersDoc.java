package vn.com.mbbank.apidoc;

public class ProjectMembersDoc {
    /**
     * @api {GET} /api/v1/project-members 1. Tra cứu quá trình tham gia dự án
     * @apiVersion 1.0.0
     * @apiName get-project-members
     * @apiGroup VIII. Quá trình tham gia dự án
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
     * @apiDescription API được sử dụng để tra cứu quá trình tham gia dự án.
     *
     * @apiExample Example usage:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://14.225.7.172:8900/api/v1/project-members
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
     * @apiSuccess {String} data.listData.projectName Dự án tham gia
     * @apiSuccess {String} data.listData.jobName Vai trò
     * @apiSuccess {String} data.listData.note Mô tả
     * @apiSuccess {Date} data.listData.fromDate Từ ngày
     * @apiSuccess {Date} data.listData.toDate Đến ngày
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
     *                 "fromDate": "02/03/2022",
     *                 "toDate": "26/03/2022",
     *                 "note": "test",
     *                 "projectName": "Quản lý nhân sự",
     *                 "jobName": "Thư ký dự án",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "fromDate": "01/04/2023",
     *                 "toDate": "02/03/2022",
     *                 "note": "sdfsdf",
     *                 "projectName": "Quản lý nhân sự",
     *                 "jobName": "Thư ký dự án",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "fromDate": "01/05/2022",
     *                 "toDate": "14/05/2022",
     *                 "note": "123123",
     *                 "projectName": "Quản lý nhân sự",
     *                 "jobName": "Thư ký dự án",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "fromDate": "01/04/2022",
     *                 "toDate": "30/04/2022",
     *                 "note": "Ok",
     *                 "projectName": "Quản lý nhân sự",
     *                 "jobName": "Thư ký dự án",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 2,
     *                 "fromDate": "01/04/2022",
     *                 "toDate": "30/04/2022",
     *                 "note": "Ok",
     *                 "projectName": "Quản lý nhân sự",
     *                 "jobName": "Thư ký dự án",
     *                 "fullName": "Đặng Đình Tứ",
     *                 "employeeCode": "MB0002",
     *                 "orgName": "Phòng Quan hệ khách hàng"
     *             }
     *         ],
     *         "count": 5
     *     },
     *     "path": "/api/v1/project-members",
     *     "timestamp": "2022-04-13T14:20:08.782+07:00",
     *     "status": 200
     * }
     * @apiErrorExample Response (example): 400 Bad Request
     * {"code": 400, "message": "Bad Request"}
     * @apiErrorExample Response (example): 403 Permission denied
     * {"code": 403, "message": "Permission denied"}
     */
}
