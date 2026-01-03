package oncall;

import java.util.function.Supplier;
import oncall.domain.HolidayWorkers;
import oncall.domain.OncallDate;
import oncall.domain.WeekdayWorkers;
import oncall.domain.Workers;
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
        Workers workers = readWorkers();
    }

    private OncallDate readMonthStartDay() {
        return retry(() -> {
            String monthStartDayInput = inputView.readMonthStartDay();
            return InputParser.parseMonthStartDay(monthStartDayInput);
        });
    }

    private Workers readWorkers() {
        return retry(() -> {
            String weekdayWorkersInput = inputView.readWeekdayWorkers();
            String holidayWorkersInput = inputView.readHolidayWorkers();
            WeekdayWorkers weekdayWorkers = InputParser.parseWeekdayWorkers(weekdayWorkersInput);
            HolidayWorkers holidayWorkers = InputParser.parseHolidayWorkers(holidayWorkersInput);
            return new Workers(weekdayWorkers, holidayWorkers);
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
