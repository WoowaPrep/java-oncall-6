package oncall.exception;

public enum ErrorMessage {

    INVALID_MONTH_DAY_PAIR("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    INVALID_MONTH("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    INVALID_DAY("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),

    INVALID_WORKERS("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
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
