package oncall;

import java.util.function.Supplier;
import oncall.domain.work.HolidayWorkers;
import oncall.domain.date.OncallDate;
import oncall.domain.work.WeekdayWorkers;
import oncall.domain.work.Workers;
import oncall.view.InputParser;
import oncall.view.InputView;
import oncall.view.OutputView;

public class EmergencySchedule {

    private InputView inputView;
    private OutputView outputView;
    private InputParser inputParser;

    public EmergencySchedule() {
        this(new InputView(), new OutputView(), new InputParser());
    }

    public EmergencySchedule(InputView inputView, OutputView outputView, InputParser inputParser) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputParser = inputParser;
    }

    public void assign() {
        OncallDate date = readMonthStartDay();
        Workers workers = readWorkers();
        printWorkSchedule(date, workers);
    }

    private OncallDate readMonthStartDay() {
        return retry(() -> {
            String monthStartDayInput = inputView.readMonthStartDay();
            outputView.printNewLine();
            return inputParser.parseMonthStartDay(monthStartDayInput);
        });
    }

    private Workers readWorkers() {
        return retry(() -> {
            String weekdayWorkersInput = inputView.readWeekdayWorkers();
            outputView.printNewLine();
            String holidayWorkersInput = inputView.readHolidayWorkers();
            outputView.printNewLine();

            WeekdayWorkers weekdayWorkers = inputParser.parseWeekdayWorkers(weekdayWorkersInput);
            HolidayWorkers holidayWorkers = inputParser.parseHolidayWorkers(holidayWorkersInput);
            return new Workers(weekdayWorkers, holidayWorkers);
        });
    }

    private void printWorkSchedule(OncallDate date, Workers workers) {
        outputView.printWorkSchedule(date, workers);
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
