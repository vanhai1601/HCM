package vn.com.mbbank.apidoc;

public class BankAccountsDoc {

    /**
     * @api {GET} /api/v1/bank-accounts 1. Tìm kiếm thông tin tài khoản
     * @apiVersion 1.0.0
     * @apiName searchBankAccounts
     * @apiGroup III. Thông tin tài khoản
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
     * @apiDescription API được sử dụng để tìm kiếm thông tin tài khoản
     *
     * @apiExample Example usage:
     * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://14.225.7.172:8900/api/v1/bank-accounts
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
     * @apiSuccess {String}   data.listData.accountNo Số tài khoản
     * @apiSuccess {String}   data.listData.bankName  Ngân hàng
     * @apiSuccess {String}   data.listData.bankBranch Chi nhánh
     * @apiSuccess {Integer}  data.listData.isPaymentAccount Là tài khoản chi lương
     * @apiSuccess {String}   data.listData.orgName  Đơn vị
     * @apiSuccess {String}   data.listData.employeeCode  Mã nhân viêṇ
     * @apiSuccess {String}   data.listData.fullName     Tên nhân viên
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
     *                 "accountNo": "0330902332",
     *                 "bankName": "BIDV",
     *                 "bankBranch": "Chi nhánh dsaMb BN",
     *                 "isPaymentAccount": 0,
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "label": "Tài khoản lương",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "accountNo": "0555828331",
     *                 "bankName": "MB Bank",
     *                 "bankBranch": "Chi nhánh MB Hà Nội",
     *                 "isPaymentAccount": 0,
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "label": "Tài khoản thanh toán",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "accountNo": "0330902333",
     *                 "bankName": "MB Bank",
     *                 "bankBranch": "Chi nhánh Mb tp HCM",
     *                 "isPaymentAccount": 1,
     *                 "fullName": "Lương Thị Phượng",
     *                 "employeeCode": "MB0001",
     *                 "orgName": "BP. Dịch vụ Khách hàng",
     *                 "label": "Tài khoản thanh toán",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "accountNo": "2233445",
     *                 "bankName": "MB Bank",
     *                 "bankBranch": "Chi nhánh trần duy hưng",
     *                 "isPaymentAccount": 1,
     *                 "fullName": "Đinh Duy Anh",
     *                 "employeeCode": "MB0021",
     *                 "orgName": "BP Quan hệ khách hàng",
     *                 "label": "Tài khoản lương",
     *                 "flagStatus": 1
     *             },
     *             {
     *                 "accountNo": "343543",
     *                 "bankName": "MB Bank",
     *                 "bankBranch": "dfd",
     *                 "isPaymentAccount": 1,
     *                 "fullName": "Đinh Duy Anh",
     *                 "employeeCode": "MB0021",
     *                 "orgName": "BP Quan hệ khách hàng",
     *                 "label": "Tài khoản lương",
     *                 "flagStatus": 1
     *             }
     *         ],
     *         "count": 5
     *     },
     *     "path": "/api/v1/bank-accounts",
     *     "timestamp": "2022-04-13T13:12:26.504+07:00",
     *     "status": 200
     * }
     *
     * @apiErrorExample Response (example): 400 Bad Request
     * {"code": 400, "message": "Bad Request"}
     * @apiErrorExample Response (example): 403 Permission denied
     * {"code": 403, "message": "Permission denied"}
     */

}
