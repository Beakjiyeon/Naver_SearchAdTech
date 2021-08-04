package exception;

/**
 * 커스텀 예외 클래스 처리 클래스(네이버)
 */
public class ErrorResponse {
    private String message;
    private String code;
    private int status;
    private String detail;

    public ErrorResponse(ErrorCode code) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
        this.detail = code.getDetail();
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return this.code;
    }

    public int getStatus() {
        return this.status;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public void setDetail(final String detail) {
        this.detail = detail;
    }

    public ErrorResponse() {
    }
}
