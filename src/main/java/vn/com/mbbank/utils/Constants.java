package vn.com.mbbank.utils;

public class Constants {

    public static final String REQUEST_MAPPING_PREFIX = "/api";
    public static final String VERSION_API_V1 = "/v1";
    public static final String COMMON_DATE_FORMAT = "dd/MM/yyyy";
    public static final String COMMON_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String LOCALE_VN = "vi_VN";
    public static final String TIMEZONE_VN = "Asia/Ho_Chi_Minh";

    public interface LOOKUP_CODES {

        public static final String GIOI_TINH = "GIOI_TINH";
        public static final String DAN_TOC = "DAN_TOC";
        public static final String TON_GIAO = "TON_GIAO";
        public static final String TINH_TRANG_HON_NHAN = "TINH_TRANG_HON_NHAN";
        public static final String TINH = "TINH";
        public static final String HUYEN = "HUYEN";
        public static final String XA = "XA";
        public static final String LOAI_TAI_KHOAN = "LOAI_TAI_KHOAN";
        public static final String LOAI_GIAY_TO = "LOAI_GIAY_TO";
        public static final String MA_VUNG_DIEN_THOAI = "MA_VUNG_DIEN_THOAI";
        public static final String QUOC_GIA = "QUOC_GIA";
        public static final String DOI_TUONG_CV = "DOI_TUONG_CV";
        public static final String CAP_BAC_QUAN_HAM = "CAP_BAC_QUAN_HAM";
        public static final String LEVEL_NV = "LEVEL_NV";
        public static final String LOAI_HOP_DONG = "LOAI_HOP_DONG";
        public static final String LOAI_HINH_DT = "LOAI_HINH_DT";
        public static final String HE_DT = "HE_DT";
        public static final String HINHTHUC_DAOTAO = "HINHTHUC_DAOTAO";
        public static final String XEP_LOAI_DT = "XEP_LOAI_DT";
        public static final String MOI_QUAN_HE_NT = "MOI_QUAN_HE_NT";
        public static final String TINH_TRANG_NT = "TINH_TRANG_NT";
        public static final String DOITUONG_CHINHSACH = "DOITUONG_CHINHSACH";
        public static final String CAP_QD_KHENTHUONG = "CAP_QD_KHENTHUONG";
        public static final String HINHTHUC_KHENTHUONG = "HINHTHUC_KHENTHUONG";
//        public static final String DANHHIEU_KHENTHUONG = "TEST";
        public static final String HINHTHUC_KYLUAT = "HINHTHUC_KYLUAT";
        public static final String CAP_QD_KYLUAT = "CAP_QD_KYLUAT";
        public static final String LOAI_HO_SO = "LOAI_HO_SO";
        public static final String LOAI_PHU_CAP = "LOAI_PHU_CAP";
        public static final String LOAI_BANG_CAP = "LOAI_BANG_CAP";
    }

    public interface STATUS{
        public static final Integer IS_ACTIVE = 1;
        public static final Integer IS_NOT_ACTIVE = 0;
    }

}
