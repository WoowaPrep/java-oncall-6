package oncall;

import java.util.function.Supplier;
import oncall.domain.OncallDate;
import oncall.exception.ErrorMessage;
import oncall.exception.OncallException;
import oncall.view.InputParser;
import oncall.view.InputView;
import oncall.view.OutputView;

public class EmergencySchedule {

    private InputView inputView;
    private OutputView outputView;

    public EmergencySchedule() {
        this(new InputView(), new OutputView());
    }

    public EmergencySchedule(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void assign() {
        OncallDate date = readMonthStartDay();
    }

    private OncallDate readMonthStartDay() {
        return retry(() -> {
            String monthStartDayInput = inputView.readMonthStartDay();
            return InputParser.parseMonthStartDay(monthStartDayInput);
        });
    }

    private <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
