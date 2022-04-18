package vn.com.mbbank.utils;

import vn.com.mbbank.core.config.I18n;

public enum ErrorApp {
    SUCCESS(200, I18n.getMessage("msg.success")),
    BAD_REQUEST(400, I18n.getMessage("msg.bad.request")),
    UNAUTHORIZED(401, I18n.getMessage("msg.unauthorized")),
    ACCESS_DENIED(403, I18n.getMessage("msg.access.denied")),
    INTERNAL_SERVER(500, I18n.getMessage("msg.internal.server")),
    USER_EXISTS(1, I18n.getMessage("msg.user.exists")),
    USER_NOT_EXISTS(2, I18n.getMessage("msg.user.not.exists")),
    GET_FILE_ERROR(3,I18n.getMessage("get.file.error")),
    INVALID_FILE(4, I18n.getMessage("msg.file.invalid")),
    INVALID_BASE64(5, I18n.getMessage("msg.base64.invalid")),
    INVALID_PARAM(201, "");

    private final int code;
    private final String description;

    ErrorApp(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }
}
