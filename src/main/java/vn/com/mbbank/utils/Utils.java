package vn.com.mbbank.utils;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.keycloak.KeycloakPrincipal;
import org.springframework.security.core.Authentication;
import java.util.regex.Pattern;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;

public class Utils {

    private static final Logger LOGGER = LogManager.getLogger(Utils.class);

    /**
     * Lay thong tin user
     *
     * @param authentication
     * @return
     */
    public static String getUserNameLogin(Authentication authentication) {
        try {
            KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
            String userName = principal.getKeycloakSecurityContext().getToken().getPreferredUsername().toUpperCase();
            return userName;
        } catch (Exception e) {
            LOGGER.error("Loi! getUserLogin: ", e);
            return null;
        }
    }

    /**
     * Lay thong tin user id
     *
     * @param authentication
     * @return
     */
//    public static String getUserLoginId(Authentication authentication) {
//        try {
//            KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
//            String userId = principal.getKeycloakSecurityContext().getToken().getId();
//            return "1";
////            return userId;
//        } catch (Exception e) {
//            LOGGER.error("Loi! getUserLoginId: ", e);
//            return null;
//        }
//    }
    /**
     * Get string token
     *
     * @param authentication
     * @return
     */
    public static String getStringToken(Authentication authentication) {
        try {
            KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
            String strToken = principal.getKeycloakSecurityContext().getTokenString();
            return strToken;
        } catch (Exception e) {
            LOGGER.error("Loi! getUserLogin: ", e);
            return null;
        }
    }

    /**
     * Lay is user login tu token
     *
     * @param authentication
     * @return
     */
//    public static String getUserNameLogin(Authentication authentication) {
//        try {
//            KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
//            String userId = principal.getKeycloakSecurityContext().getToken().getId();
//            return "1";
////            return userId;
//        } catch (Exception e) {
//            LOGGER.error("Loi! getUserNameLogin: ", e);
//            return null;
//        }
//    }
    /**
     * Lay role
     *
     * @param authentication
     * @return
     */
    public static Set<String> getRoleId(Authentication authentication) {
        try {
            KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
            Set<String> roleId = principal.getKeycloakSecurityContext().getToken().getResourceAccess().get(getClientId(authentication)).getRoles();
            return roleId;
        } catch (Exception e) {
            LOGGER.error("Loi! getUserLogin: ", e);
            return new HashSet<>();
        }
    }

    /**
     * Lay client id
     *
     * @param authentication
     * @return
     */
    public static String getClientId(Authentication authentication) {
        try {
            KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
            String clientId = principal.getKeycloakSecurityContext().getToken().getIssuedFor();
            return clientId;
        } catch (Exception e) {
            LOGGER.error("Loi! getUserLogin: ", e);
            return null;
        }
    }

    /**
     * Kiem tra object co null hay khong
     *
     * @param objects
     * @return
     */
    public static boolean isNullOrEmpty(final Object[] objects) {
        return objects == null || objects.length == 0;
    }

    /**
     * Kiem tra chuoi String null or rong
     *
     * @param toTest
     * @return
     */
    public static boolean isNullOrEmpty(String toTest) {
        return toTest == null || toTest.isEmpty();
    }

    /**
     * Kiem tra object co null hay khong
     *
     * @param obj
     * @return
     */
    public static boolean isNullObject(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            return isNullOrEmpty(obj.toString());
        }
        if (obj instanceof Long) {
            return Long.valueOf(obj.toString()) <= 0L;
        }
        return false;
    }

    /**
     * Kiem tra list null or empty
     *
     * @param collection
     * @return
     */
    public static boolean isNullOrEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Check valid file
     *
     * @param fileName
     * @param file
     * @param maxFileSizeMb
     * @return
     */
    public static boolean checkFileValid(String fileName, byte[] file, Integer maxFileSizeMb) {
        if (Objects.isNull(maxFileSizeMb)) {
            maxFileSizeMb = 15;
        }
        Objects.requireNonNull(file);
        long fileSizeMb = file.length / (1024 * 1024);
        return checkFileExtensionValid(fileName, ".JPG", ".PNG", ".TIFF", ".BMP", ".PDF", ".JPEG", ".WEBP", ".HEIC", ".DOCX", ".DOC", ".XLS", ".XLSX") && fileSizeMb <= maxFileSizeMb;
    }

    /**
     * Check valid extension file
     *
     * @param fileName
     * @param fileExtensions
     * @return
     */
    public static boolean checkFileExtensionValid(String fileName, String... fileExtensions) {
        Objects.requireNonNull(fileName);
        for (String fileExtension : fileExtensions) {
            if (fileName.toLowerCase().endsWith(fileExtension.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param value
     * @return
     */
    public static String NVL(String value) {
        return NVL(value, "");
    }

    /**
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static String NVL(String value, String defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static Integer NVL(Integer value, Integer defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static Integer NVL(Integer value) {
        return NVL(value, 0);
    }

    public static Long NVL(Long value, Long defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static Date NVL(Date value, Date defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static Long NVL(Long value) {
        return NVL(value, 0L);
    }

    public static Double NVL(Double value, Double defaultValue) {
        return value == null ? defaultValue : value;
    }

    /**
     * Format so.
     *
     * @param d So
     * @return Xau
     */
    public static String formatNumber(Long d) {
        if (d == null) {
            return "";
        } else {
            DecimalFormat format = new DecimalFormat("###,###");
            return format.format(d);
        }
    }

    /**
     * Format so.
     *
     * @param d So
     * @param pattern
     * @return Xau
     */
    public static String formatNumber(Object d, String pattern) {
        if (d == null) {
            return "";
        } else {
            DecimalFormat format = new DecimalFormat(pattern);
            return format.format(d);
        }
    }

    /**
     * Format so.
     *
     * @param d So
     * @return Xau
     */
    public static String formatNumber(Double d) {
        if (d == null) {
            return null;
        } else {
            DecimalFormat format = new DecimalFormat("###,###.#####");
            return format.format(d);
        }
    }

    /**
     * Chuyen doi tuong Date thanh doi tuong String.
     *
     * @param date Doi tuong Date
     * @return Xau ngay, co dang dd/MM/yyyy
     */
    public static String convertDateToString(Date date) {
        if (date == null) {
            return "";
        } else {
            return new SimpleDateFormat("dd/MM/yyyy").format(date);
        }
    }

    /**
     * Chuyen doi tuong Date thanh doi tuong String.
     *
     * @param date Doi tuong Date
     * @return Xau ngay, co dang dd/MM/yyyy
     */
    public static String convertDateTimeToString(Date date) {
        if (date == null) {
            return "";
        } else {
            return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        }
    }

    /**
     * Chuyen doi tuong Date thanh doi tuong String
     *
     * @param date Doi tuong Date
     * @param formatPattern Kieu format ngay thang
     * @return Xau ngay voi kieu format truyen vao
     */
    public static String convertDateToString(Date date, String formatPattern) {
        if (date == null) {
            return null;
        } else {
            formatPattern = formatPattern == null ? vn.com.mbbank.core.utils.constants.Constants.DATE_FORMAT : formatPattern;
            SimpleDateFormat dateFormat = getSimpleDateFormat(formatPattern);
            return dateFormat.format(date);
        }
    }

    public static Date convertStringToDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            return null;
        } else {
            String pattern = vn.com.mbbank.core.utils.constants.Constants.DATE_FORMAT;
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            dateFormat.setLenient(false);
            try {
                return dateFormat.parse(date);
            } catch (ParseException ex) {
                LOGGER.debug(ex.toString());
                return null;
            }
        }
    }

    /**
     * Chuyen doi tuong Date thanh doi tuong String
     *
     * @param date Doi tuong Date
     * @param formatPattern Kieu format ngay thang
     * @return Xau ngay voi kieu format truyen vao
     */
    public static Date convertStringToDate(String date, String formatPattern) {
        try {
            if (date == null || "".equals(date)) {
                return null;
            } else {
                String tg;
                tg = formatPattern == null ? vn.com.mbbank.core.utils.constants.Constants.DATE_FORMAT : formatPattern;
                SimpleDateFormat dateFormat = new SimpleDateFormat(tg);
                return dateFormat.parse(date);
            }
        } catch (ParseException ex) {
            LOGGER.info(ex.toString());
            return null;
        }
    }

    /**
     *
     * @param formatPattern
     * @return
     */
    public static SimpleDateFormat getSimpleDateFormat(String formatPattern) {
        return new SimpleDateFormat(formatPattern);
    }

    public static String removeHtml(String html) {
        return html.replaceAll("\\<[^>]*>", "");
    }

    /**
     * copy properties tu form sang BO va nguoc lai
     *
     * @param dest
     * @param orig
     */
    public static void copyProperties(Object dest, Object orig) {
        try {
            ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
            convertUtilsBean.register(new LongConverter(), Long.class);
            convertUtilsBean.register(new DoubleConverter(), Double.class);
            convertUtilsBean.register(new StringToDateConverter(), Date.class);
            convertUtilsBean.register(new DateToStringConverter(), String.class);
            BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean, new PropertyUtilsBean());
            beanUtilsBean.copyProperties(dest, orig);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOGGER.info(ex.toString());
        }
    }

    public static String filterLikeKeyword(String keyword) {
        return keyword.trim().toLowerCase().replace("/", "//").replace("_", "/_").replace("%", "/%");
    }

    public static String filterEmployeeName(String fullName) {

        fullName = fullName.trim().replaceAll(" +", " ");
        String result = "";
        boolean isUpperCase = false;
        for (int i = 0; i < fullName.length(); i++) {
            char c = fullName.charAt(i);
            if (isUpperCase || i == 0) {
                result += Character.toUpperCase(c);
                isUpperCase = false;
            } else {
                result += Character.toLowerCase(c);
            }

            if (Character.isSpaceChar(c)) {
                isUpperCase = true;
            }
        }
        return result;
    }

    public static boolean isValidEmail(String emailAddress) {
        if (isNullOrEmpty(emailAddress)) {
            return false;
        }
        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public static String getExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index + 1);
    }

    /**
     * So sánh ngày if equal == true if earlierDate <= laterDate return true
     * else return false if equal != true if earlierDate < laterDate return true
     * else return false @param earlierDate @param
     *
     * l
     * aterDate @param equal @return
     * @param earlierDate
     * @param laterDate
     * @param equal
     * @return 
     */
    public static Boolean compareDate(Date earlierDate, Date laterDate, Boolean equal) {
        /*
         * equal = true: Bang cung hop le false: endDate < startDate
         */
        Calendar earlierDateCal = Calendar.getInstance();
        Calendar laterDateCal = Calendar.getInstance();

        earlierDateCal.setTime(earlierDate);
        laterDateCal.setTime(laterDate);

        if (equal) {
            //Bang cung hop le
            return laterDateCal.after(earlierDateCal) || laterDateCal.equals(earlierDateCal);

        } else {
            return laterDateCal.after(earlierDateCal);
        }
    }

    /**
     * Tinh so ngay nam giua 2 ngay
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * Cong valueToAdd ngay vao ngay inputDate.
     *
     * @param inputDate
     * @param valueToAdd
     * @return
     * @throws Exception
     */
    public static Date addDate(Date inputDate, int valueToAdd) throws Exception {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(inputDate);
            c.add(Calendar.DATE, valueToAdd);
            return c.getTime();
        } catch (Exception ex) {
            LOGGER.info(ex.toString());
            return null;
        }
    }

    public static String convertSQLNameToJavaName(String str) {

        str = str.trim().replaceAll(" ", "");
        String result = "";
        boolean isUpperCase = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isAlphabetic(c)) {
                isUpperCase = true;
                continue;
            }
            if (isUpperCase) {
                result += Character.toUpperCase(c);
                isUpperCase = false;
            } else {
                result += Character.toLowerCase(c);
            }

        }
        return result;
    }

}
