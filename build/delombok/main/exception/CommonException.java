package exception;

/**
 * 커스텀 예외 클래스
 */
public class CommonException extends Exception {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CommonException.class);
    private int errorCode;
    private ErrorMessageHandler errorMessageHandler;
    private CommonErrorMessage commonErrorMessage = new CommonErrorMessage();
    private String message;

    public CommonException(int errorCode) {
        this.errorCode = errorCode;
        this.message = commonErrorMessage.getMessage((errorCode));
        log.error(message);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
