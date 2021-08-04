package exception;

import lombok.extern.slf4j.Slf4j;

/**
 * 커스텀 예외 클래스
 */
@Slf4j
public class CommonException extends Exception {

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
    public String getMessage(){
        return message;
    }
}
