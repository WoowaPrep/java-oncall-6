package oncall.exception;

public enum ErrorMessage {

    INVALID_MONTH("유효한 월 범위가 아닙니다."),
    INVALID_DAY("유효한 일 범위가 아닙니다."),
    ;

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
