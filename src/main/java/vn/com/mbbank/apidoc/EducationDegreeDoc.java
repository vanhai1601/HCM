package vn.com.mbbank.apidoc;

public class EducationDegreeDoc {
    /**
     * @api {GET} /api/v1/education-degree 1. Tra cứu thông tin bằng cấp/chứng chỉ
     * @apiVersion 1.0.0
     * @apiName get-education-degree
     * @apiGroup XII. Thông tin bằng cấp/chứng chỉ
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
     * @apiDescription API được sử dụng để tra cứu thông tin bằng cấp/chứng chỉ.
     *
     * @apiExample Example usage:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://14.225.7.172:8900/api/v1/education-degree
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
     * @apiSuccess {Integer} data.listData.issueYear Năm tốt nghiệp
     * @apiSuccess {String} data.listData.schoolName Tên trường đào tạo
     * @apiSuccess {String} data.listData.facultyName Chuyên ngành
     * @apiSuccess {String} data.listData.majorLevelName Trình độ
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
     *                 "issueYear": 2023,
     *                 "schoolName": "Đại học Quốc gia Hà Nội",
     *                 "facultyName": "An toàn thông tin",
     *                 "majorLevelName": "Đại học",
     *                 "flagStatus": 1,
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "issueYear": 2020,
     *                 "schoolName": "Đại học Quốc gia Hà Nội",
     *                 "facultyName": "Công nghệ truyền thông",
     *                 "majorLevelName": "Trung cấp",
     *                 "flagStatus": 1,
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "issueYear": 2023,
     *                 "schoolName": "Đại học Quốc gia Hà Nội",
     *                 "facultyName": "Truyền thông đa phương tiện",
     *                 "majorLevelName": "Cao đẳng",
     *                 "flagStatus": 1,
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "issueYear": 2023,
     *                 "schoolName": "Đại học Công nghệ và Quản lý Hữu nghị (*)",
     *                 "facultyName": "An toàn thông tin",
     *                 "majorLevelName": "Đại học",
     *                 "flagStatus": 1,
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "issueYear": 2020,
     *                 "schoolName": "Đại học Quốc gia Hà Nội",
     *                 "facultyName": "Truyền thông đa phương tiện",
     *                 "majorLevelName": "Trung cấp",
     *                 "flagStatus": 1,
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "issueYear": 2020,
     *                 "schoolName": "Đại học Bách khoa Hà Nội",
     *                 "facultyName": "Hệ thống thông tin",
     *                 "majorLevelName": "Thạc sĩ",
     *                 "flagStatus": 1,
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "issueYear": 2020,
     *                 "schoolName": "Đại học Công đoàn",
     *                 "facultyName": "Quản lý công nghiệp",
     *                 "majorLevelName": "Đại học",
     *                 "flagStatus": 1,
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001"
     *             },
     *             {
     *                 "employeeId": 1,
     *                 "issueYear": 2020,
     *                 "schoolName": "Đại học Quốc gia Hà Nội",
     *                 "facultyName": "Truyền thông đa phương tiện",
     *                 "majorLevelName": "Cao đẳng",
     *                 "flagStatus": 1,
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001"
     *             }
     *         ],
     *         "count": 8
     *     },
     *     "path": "/api/v1/education-degree",
     *     "timestamp": "2022-04-13T13:52:10.052+07:00",
     *     "status": 200
     * }
     * @apiErrorExample Response (example): 400 Bad Request
     * {"code": 400, "message": "Bad Request"}
     * @apiErrorExample Response (example): 403 Permission denied
     * {"code": 403, "message": "Permission denied"}
     */
}
