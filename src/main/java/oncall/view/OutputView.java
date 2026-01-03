package oncall.view;

import oncall.domain.date.OncallDate;
import oncall.domain.work.Workers;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String WORK_SCHEDULE_WEEKDAY_FORMAT = "%d월 %d일 %s %s%n";
    private static final String WORK_SCHEDULE_HOLIDAY_FORMAT = "%d월 %d일 %s(휴일) %s%n";

    public void printWorkSchedule(OncallDate date, Workers workers) {
        int workingMonth = date.getMonth();
        while (date.getMonth() == workingMonth) {
            printWorkScheduleLine(date, workers);
            date = date.plusOneDay();
        }
    }

    private void printWorkScheduleLine(OncallDate date, Workers workers) {
        String worker = workers.work(date);

        if (date.isWeekday() && date.isHoliday()) {
            System.out.printf(WORK_SCHEDULE_HOLIDAY_FORMAT,
                    date.getMonth(), date.getDay(), date.getDayOfWeekFirstString(), worker);
            return;
        }

        System.out.printf(WORK_SCHEDULE_WEEKDAY_FORMAT,
                date.getMonth(), date.getDay(), date.getDayOfWeekFirstString(), worker);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printNewLine() {
        System.out.printf(NEW_LINE);
    }
}
