package vn.com.mbbank.apidoc;

public class DependentPersonsDoc {

    /**
     * @api {GET} /api/v1/dependent-persons 1. Tìm kiếm thông tin giảm trừ gia cảnh
     * @apiVersion 1.0.0
     * @apiName searchDependentPersons
     * @apiGroup V. Thông tin giảm trừ gia cảnh
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
     * @apiDescription API được sử dụng để tìm kiếm thông tin giảm trừ gia cảnh
     *
     * @apiExample Example usage:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://14.225.7.172:8900/api/v1/dependent-persons
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
     * @apiSuccess {Date}  data.listData.fromDate  Thời gian giảm trừ từ
     * @apiSuccess {Date}    data.listData.toDate    Đến tháng
     * @apiSuccess {String}  data.listData.relationTypeName Mối quan hệ
     * @apiSuccess {String} data.listData.fullName  Tên người giảm trừ gia cảnh
     * @apiSuccess {Date} data.listData.dateOfBirth  Ngày sinh
     * @apiSuccess {String}  data.listData.orgName  Đơn vị
     * @apiSuccess {String}  data.listData.employeeCode  Mã nhân viêṇ
     * @apiSuccess {String}  data.listData.employeeName  Tên nhân viên
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
     *                 "fromDate": "01/2022",
     *                 "toDate": "07/2022",
     *                 "relationTypeName": "Con trai",
     *                 "fullName": "Đỗ Văn Chiến",
     *                 "dateOfBirth": "08/12/1996",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "fromDate": "08/2022",
     *                 "toDate": "12/2022",
     *                 "relationTypeName": "Con trai",
     *                 "fullName": "Đỗ Văn Chiến",
     *                 "dateOfBirth": "08/12/1996",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "fromDate": "05/2022",
     *                 "toDate": "11/2022",
     *                 "relationTypeName": "Bố ",
     *                 "fullName": "21341234",
     *                 "dateOfBirth": "15/04/2022",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "fromDate": "08/2022",
     *                 "toDate": "12/2022",
     *                 "relationTypeName": "Bố ",
     *                 "fullName": "Đặng Văn C",
     *                 "taxNumber": "123456",
     *                 "dateOfBirth": "20/02/1960",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "fromDate": "08/2022",
     *                 "toDate": "05/2022",
     *                 "relationTypeName": "Con trai",
     *                 "fullName": "123123",
     *                 "dateOfBirth": "08/04/2022",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "fromDate": "08/2022",
     *                 "relationTypeName": "Mẹ",
     *                 "fullName": "Vũ Thị Dung",
     *                 "taxNumber": "Mã số thuế NPT",
     *                 "dateOfBirth": "31/03/1949",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "fromDate": "08/2022",
     *                 "toDate": "02/2022",
     *                 "relationTypeName": "Bố ",
     *                 "fullName": "Đặng Văn C",
     *                 "taxNumber": "123456",
     *                 "dateOfBirth": "20/02/1960",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "fromDate": "02/2023",
     *                 "toDate": "09/2023",
     *                 "relationTypeName": "Bố ",
     *                 "fullName": "Đặng Văn C",
     *                 "taxNumber": "123456",
     *                 "dateOfBirth": "20/02/1960",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "fromDate": "02/2021",
     *                 "toDate": "04/2022",
     *                 "relationTypeName": "Bố ",
     *                 "fullName": "Đặng Văn C",
     *                 "taxNumber": "123456",
     *                 "dateOfBirth": "20/02/1960",
     *                 "orgName": "Phòng Quan hệ khách hàng",
     *                 "employeeCode": "MB0002",
     *                 "employeeName": "Đặng Đình Tứ"
     *             }
     *         ],
     *         "count": 9
     *     },
     *     "path": "/api/v1/dependent-persons",
     *     "timestamp": "2022-04-13T11:15:31.156+07:00",
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
