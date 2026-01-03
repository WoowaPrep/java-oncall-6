package oncall.exception;

public class OncallException extends IllegalArgumentException {

    private OncallException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public static OncallException from(ErrorMessage errorMessage) {
        return new OncallException(errorMessage);
    }
}
