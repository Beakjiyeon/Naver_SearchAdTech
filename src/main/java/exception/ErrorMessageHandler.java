package exception;

/**
 * 커스텀 예외 처리 클래스(파라미터)
 * */
public class ErrorMessageHandler {

    private CommonErrorMessage commonErrorMessage;

    public String getMessage(Exception e) {
        if (e instanceof CommonException) {
            int error_code = ((CommonException) e).getErrorCode();
            return commonErrorMessage.getMessage(error_code);
        } else {
            return commonErrorMessage.getMessage(-1);
        }
    }

}
