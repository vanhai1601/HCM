package vn.com.mbbank.apidoc;

public class EducationProcessDoc {
    /**
     * @api {GET} /api/v1/education-process 1. Tra cứu quá trình đào tạo
     * @apiVersion 1.0.0
     * @apiName get-education-process
     * @apiGroup XIII. Quá trình đào tạo
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
     * @apiDescription API được sử dụng để tra cứu quá trình đào tạo.
     *
     * @apiExample Example usage:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://14.225.7.172:8900/api/v1/education-process
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
     * @apiSuccess {String} data.listData.courseName Tên khóa học
     * @apiSuccess {String} data.listData.eduMethodTypeName Hình thức đào tạo
     * @apiSuccess {String} data.listData.courseContent Nội dung đào tạo
     * @apiSuccess {Date} data.listData.fromDate Thời gian đào tạo từ ngày
     * @apiSuccess {Date} data.listData.toDate Thời gian đào tạo đến ngày
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
     *                 "courseName": "Khóa học - test",
     *                 "eduMethodTypeName": "Trực tuyến",
     *                 "courseContent": "Nội dung",
     *                 "fromDate": "09/02/2011",
     *                 "toDate": "08/04/2015",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "flagStatus": 1,
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "courseName": "Đào tạo quản lý cấp 1",
     *                 "eduMethodTypeName": "Tập trung",
     *                 "courseContent": "Đạo tạo quản lý cấp 1, dự bị quản lý cấp 1",
     *                 "fromDate": "01/04/2022",
     *                 "toDate": "08/04/2022",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "flagStatus": 1,
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "courseName": "Khóa học test",
     *                 "eduMethodTypeName": "Trực tuyến",
     *                 "courseContent": "Nội dung đào tạo",
     *                 "fromDate": "21/02/2010",
     *                 "toDate": "28/02/2010",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "flagStatus": 1,
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "courseName": "Khóa học",
     *                 "eduMethodTypeName": "Trực tuyến",
     *                 "courseContent": "Nội dung ttt",
     *                 "fromDate": "18/02/2010",
     *                 "toDate": "19/02/2010",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "flagStatus": 1,
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "courseName": "12345678912345678912345678912345678ddddddddddddddddd",
     *                 "eduMethodTypeName": "Tập trung",
     *                 "courseContent": "Nội dung",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "flagStatus": 1,
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "courseName": "123123",
     *                 "eduMethodTypeName": "Tập trung",
     *                 "courseContent": "123123",
     *                 "fromDate": "01/04/2022",
     *                 "toDate": "23/04/2022",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "flagStatus": 1,
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "courseName": "Nâng cao ",
     *                 "eduMethodTypeName": "Tập trung",
     *                 "courseContent": "abccckkskjsj                     djdhsjsdhjsdokokok",
     *                 "fromDate": "08/04/2022",
     *                 "toDate": "09/04/2022",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "flagStatus": 1,
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "employeeId": 2,
     *                 "courseName": "Đạo tạo quản lý cấp 1",
     *                 "eduMethodTypeName": "Tập trung",
     *                 "courseContent": "Đạo tạo cán bộ quản lý cấp 1, dự bị quản lý cấp 1",
     *                 "fromDate": "01/04/2022",
     *                 "toDate": "08/04/2022",
     *                 "fullName": "Đặng Đình Tứ",
     *                 "employeeCode": "MB0002",
     *                 "orgName": "Phòng Quan hệ khách hàng"
     *             },
     *             {
     *                 "employeeId": 2,
     *                 "courseName": "Đạo tạo quản lý cấp 1`",
     *                 "eduMethodTypeName": "Tập trung",
     *                 "courseContent": "Đạo tạo nhân sự quản lý cấp 1, dự bị lên quản lý",
     *                 "fromDate": "01/04/2022",
     *                 "toDate": "07/04/2022",
     *                 "fullName": "Đặng Đình Tứ",
     *                 "employeeCode": "MB0002",
     *                 "orgName": "Phòng Quan hệ khách hàng"
     *             }
     *         ],
     *         "count": 9
     *     },
     *     "path": "/api/v1/education-process",
     *     "timestamp": "2022-04-13T14:10:19.513+07:00",
     *     "status": 200
     * }
     * @apiErrorExample Response (example): 400 Bad Request
     * {"code": 400, "message": "Bad Request"}
     * @apiErrorExample Response (example): 403 Permission denied
     * {"code": 403, "message": "Permission denied"}
     */
}
