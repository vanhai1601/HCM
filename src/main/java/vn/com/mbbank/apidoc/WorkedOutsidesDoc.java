package vn.com.mbbank.apidoc;

public class WorkedOutsidesDoc {

    /**
     * @api {GET} /api/v1/worked-outsides 1. Tìm kiếm thông tin quá trình công tác trước MB
     * @apiVersion 1.0.0
     * @apiName searchWorkedOutsides
     * @apiGroup VI. Thông tin quá trình công tác trước MB
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
     * @apiDescription API được sử dụng để tìm kiếm thông tin quá trình công tác trước MB
     *
     * @apiExample Example usage:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://14.225.7.172:8900/api/v1/worked-outsides
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
     * @apiSuccess {String}   data.listData.organizationName Đơn vị công tác trước MB
     * @apiSuccess {String}   data.listData.positionName Chức danh
     * @apiSuccess {Date}     data.listData.fromDate Từ ngày
     * @apiSuccess {Date}     data.listData.toDate Đến ngày
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
     *                 "organizationName": "123123",
     *                 "positionName": "3sdadsfsd",
     *                 "fromDate": "03/02/2022",
     *                 "toDate": "03/05/2022",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "organizationName": "1234312",
     *                 "positionName": "hê lố",
     *                 "fromDate": "01/01/2001",
     *                 "toDate": "01/01/2001",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "organizationName": "1234312",
     *                 "positionName": "42341234",
     *                 "fromDate": "03/02/2022",
     *                 "toDate": "03/12/2022",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "organizationName": "123123",
     *                 "positionName": "12312312",
     *                 "fromDate": "03/02/2022",
     *                 "toDate": "03/02/2022",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "organizationName": "1234312",
     *                 "positionName": "42341234",
     *                 "fromDate": "01/01/2001",
     *                 "toDate": "01/01/2001",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "organizationName": "Thêm mới quá trình công tác trước MBThêm m",
     *                 "positionName": "Chức danh Thêm mới quá trình công tác trước MB",
     *                 "fromDate": "01/01/2001",
     *                 "toDate": "01/01/2002",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "fromDate": "10/01/2022",
     *                 "toDate": "10/02/2022",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "organizationName": "1234312",
     *                 "positionName": "42341234",
     *                 "fromDate": "01/01/2003",
     *                 "toDate": "01/01/2003",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "organizationName": "1234312",
     *                 "positionName": "42341234",
     *                 "fromDate": "01/03/2002",
     *                 "toDate": "01/03/2012",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "organizationName": "FPT",
     *                 "positionName": "Nhân viên",
     *                 "fromDate": "01/01/2001",
     *                 "toDate": "01/01/2001",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "organizationName": "123123",
     *                 "positionName": "3sdadsfsd",
     *                 "fromDate": "01/01/2003",
     *                 "toDate": "01/01/2003",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "organizationName": "1234312",
     *                 "positionName": "42341234",
     *                 "fromDate": "01/03/2002",
     *                 "toDate": "01/03/2012",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "organizationName": "1234312",
     *                 "positionName": "42341234",
     *                 "fromDate": "01/01/2003",
     *                 "toDate": "01/01/2003",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "organizationName": "1234312",
     *                 "positionName": "42341234",
     *                 "fromDate": "01/01/2003",
     *                 "toDate": "01/01/2003",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "organizationName": "1234312",
     *                 "positionName": "42341234",
     *                 "fromDate": "01/03/2002",
     *                 "toDate": "01/03/2012",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "organizationName": "1234312",
     *                 "positionName": "42341234",
     *                 "fromDate": "01/03/2002",
     *                 "toDate": "01/03/2012",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "organizationName": "1234312",
     *                 "positionName": "42341234",
     *                 "fromDate": "01/03/2002",
     *                 "toDate": "01/03/2012",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "organizationName": "1234312",
     *                 "positionName": "42341234",
     *                 "fromDate": "01/01/2001",
     *                 "toDate": "01/01/2000",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "organizationName": "1234312",
     *                 "positionName": "42341234",
     *                 "fromDate": "01/01/2001",
     *                 "toDate": "01/01/2001",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "organizationName": "123123",
     *                 "positionName": "3sdadsfsd",
     *                 "fromDate": "01/03/2002",
     *                 "toDate": "01/03/2005",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "employeeCode": "MB0001",
     *                 "employeeName": "Lương Thị Phượng",
     *                 "flagStatus": 1
     *             }
     *         ],
     *         "count": 20
     *     },
     *     "path": "/api/v1/worked-outsides",
     *     "timestamp": "2022-04-13T11:59:36.259+07:00",
     *     "status": 200
     * }
     *
     * @apiErrorExample Response (example): 400 Bad Request
     * {"code": 400, "message": "Bad Request"}
     * @apiErrorExample Response (example): 403 Permission denied
     * {"code": 403, "message": "Permission denied"}
     */

}
