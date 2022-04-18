package vn.com.mbbank.apidoc;

public class FamilyRelationshipsDoc {

    /**
     * @api {GET} /api/v1/family-relationships 1. Tìm kiếm thông tin nhân thân
     * @apiVersion 1.0.0
     * @apiName searchFamilyRelationships
     * @apiGroup IV. Thông tin nhân thân
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
     * @apiDescription API được sử dụng để tìm kiếm thông tin nhân thân
     *
     * @apiExample Example usage:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://14.225.7.172:8900/api/v1/family-relationships
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
     *
     * @apiSuccess {String}   data.listData.fullName Họ tên nhân thân
     * @apiSuccess {String}   data.listData.relationTypeCode Mối quan hệ
     * @apiSuccess {Date}     data.listData.dateOfBirth Ngày sinh
     * @apiSuccess {String}   data.listData.workOrganization Đơn vị
     * @apiSuccess {Date}     data.listData.relationStatusCode Tình trạng
     * @apiSuccess {String}   data.listData.orgName  Đơn vị
     * @apiSuccess {String}   data.listData.employeeCode  Mã nhân viêṇ
     * @apiSuccess {String}   data.listData.employeeName  Tên nhân viên
     * @apiSuccess {Integer}  data.listData.flagStatus  Trạng thái làm việc. 1: Đang làm việc, Ngược lại là Đã nghỉ việc
     *
     *
     * @apiError 200 Gọi API thành công
     * @apiError 400 Bad request
     * @apiError 401 Token không hợp lệ
     * @apiError 403 Không có quyền thao tác
     * @apiError 500 Lỗi hệ thống
     *
     * @apiSuccessExample Response (example): 200
     *
     * {
     *     "code": 200,
     *     "message": "Thành công",
     *     "data": {
     *         "listData": [
     *             {
     *                 "fullName": "123123",
     *                 "relationTypeCode": "Con trai",
     *                 "dateOfBirth": "08/04/2022",
     *                 "workOrganization": "13123123",
     *                 "flagStatus": 1,
     *                 "relationStatusCode": "Còn nhỏ",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "fullName": "Đỗ Văn Chiến",
     *                 "relationTypeCode": "Bố ",
     *                 "dateOfBirth": "08/12/1996",
     *                 "flagStatus": 1,
     *                 "relationStatusCode": "Đang công tác",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "fullName": "Đỗ Văn Chiến",
     *                 "relationTypeCode": "Con trai",
     *                 "dateOfBirth": "08/12/1996",
     *                 "flagStatus": 1,
     *                 "relationStatusCode": "Còn nhỏ",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "fullName": "Đỗ Văn Chiến",
     *                 "relationTypeCode": "Con trai",
     *                 "dateOfBirth": "08/12/1996",
     *                 "flagStatus": 1,
     *                 "relationStatusCode": "Còn nhỏ",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "fullName": "Đỗ Văn Chiến",
     *                 "relationTypeCode": "Con trai",
     *                 "dateOfBirth": "08/12/1996",
     *                 "flagStatus": 1,
     *                 "relationStatusCode": "Còn nhỏ",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "fullName": "Nguyễn Trần Quang Anh",
     *                 "relationTypeCode": "Con trai",
     *                 "flagStatus": 1,
     *                 "relationStatusCode": "Còn nhỏ",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "fullName": "Vũ Thị Dung",
     *                 "relationTypeCode": "Mẹ",
     *                 "dateOfBirth": "31/03/1949",
     *                 "flagStatus": 1,
     *                 "relationStatusCode": "Đã qua đời",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "fullName": "Đặng Đình Tứ",
     *                 "relationTypeCode": "Mẹ",
     *                 "dateOfBirth": "29/04/2004",
     *                 "flagStatus": 1,
     *                 "relationStatusCode": "Đang công tác",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "fullName": "fsfsdfsdf",
     *                 "relationTypeCode": "Mẹ",
     *                 "dateOfBirth": "02/04/2022",
     *                 "workOrganization": "sdfsdf",
     *                 "flagStatus": 1,
     *                 "relationStatusCode": "Đang công tác",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "fullName": "Đỗ Văn Chiến",
     *                 "relationTypeCode": "Con trai",
     *                 "dateOfBirth": "08/12/1996",
     *                 "flagStatus": 1,
     *                 "relationStatusCode": "Còn nhỏ",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "fullName": "21341234",
     *                 "relationTypeCode": "Bố ",
     *                 "dateOfBirth": "15/04/2022",
     *                 "workOrganization": "234234",
     *                 "flagStatus": 1,
     *                 "relationStatusCode": "Còn nhỏ",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "fullName": "21341234",
     *                 "relationTypeCode": "Bố ",
     *                 "dateOfBirth": "15/04/2022",
     *                 "workOrganization": "234234",
     *                 "flagStatus": 1,
     *                 "relationStatusCode": "Còn nhỏ",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "fullName": "Hà Duy Khánh",
     *                 "relationTypeCode": "Bố ",
     *                 "dateOfBirth": "25/09/1996",
     *                 "workOrganization": "BP Quan hệ",
     *                 "flagStatus": 1,
     *                 "relationStatusCode": "Đang công tác",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "fullName": "Đặng Văn C",
     *                 "relationTypeCode": "Bố ",
     *                 "dateOfBirth": "20/02/1960",
     *                 "workOrganization": "TĐ Công nghệ ATT",
     *                 "flagStatus": 1,
     *                 "relationStatusCode": "Nghỉ hưu",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "fullName": "Đỗ Văn Chiến",
     *                 "relationTypeCode": "Bố ",
     *                 "dateOfBirth": "08/12/1996",
     *                 "flagStatus": 1,
     *                 "relationStatusCode": "Còn nhỏ",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "fullName": "Đặng Đình Tứ",
     *                 "relationTypeCode": "Bố ",
     *                 "dateOfBirth": "29/04/2004",
     *                 "flagStatus": 1,
     *                 "relationStatusCode": "Đang công tác",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "orgName": "BP. Dịch vụ Khách hàng"
     *             },
     *             {
     *                 "fullName": "Nguyên văn B",
     *                 "relationTypeCode": "Bố ",
     *                 "dateOfBirth": "01/04/2022",
     *                 "workOrganization": "32123123",
     *                 "relationStatusCode": "Còn nhỏ",
     *                 "employeeCode": "MB0002",
     *                 "employeeName": "Đặng Đình Tứ",
     *                 "orgName": "Phòng Quan hệ khách hàng"
     *             }
     *         ],
     *         "count": 17
     *     },
     *     "path": "/api/v1/family-relationships",
     *     "timestamp": "2022-04-13T11:48:54.680+07:00",
     *     "status": 200
     * }
     *
     * @apiErrorExample Response (example): 400 Bad Request
     * {"code": 400, "message": "Bad Request"}
     * @apiErrorExample Response (example): 403 Permission denied
     * {"code": 403, "message": "Permission denied"}
     */

}
