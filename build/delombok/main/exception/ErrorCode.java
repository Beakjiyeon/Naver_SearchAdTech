package exception;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 커스텀 예외 클래스
 */
public enum ErrorCode implements EnumModel {
    // COMMON
    INVALID_CODE(400, "900", "Invalid Code");
    private int status;
    private String code;
    private String message;
    private String detail;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    @Override
    public String getKey() {
        return this.code;
    }

    @Override
    public String getValue() {
        return this.message;
    }

    private ErrorCode() {
    }

    public int getStatus() {
        return this.status;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDetail() {
        return this.detail;
    }
}
