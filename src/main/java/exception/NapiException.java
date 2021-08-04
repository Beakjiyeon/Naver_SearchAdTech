package exception;

import javax.swing.*;

/**
 * 네이버 라이브러리에서 발생하는 예외 처리 클래스
 * */
public class NapiException extends Exception {

    private ErrorCode errorCode;

    public NapiException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public NapiException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

}
/*

 */