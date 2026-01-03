package oncall.view;

import oncall.domain.date.OncallDate;
import oncall.domain.work.Workers;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String WORK_SCHEDULE_WEEKDAY_FORMAT = "%d월 %d일 %s %s%n";
    private static final String WORK_SCHEDULE_HOLIDAY_FORMAT = "%d월 %d일 %s(휴일) %s%n";

    public void printWorkSchedule(OncallDate date, Workers workers) {
        int workingMonth = date.getMonth();
        while (true) {
            if (date.getMonth() != workingMonth) break;

            printWorkScheduleLine(date, workers);
            date = date.plusOneDay();
        }
    }

    private void printWorkScheduleLine(OncallDate date, Workers workers) {
        if (date.isWeekend() && date.isHoliday()) {
            System.out.printf(WORK_SCHEDULE_HOLIDAY_FORMAT,
                    date.getMonth(), date.getDay(), date.getDayOfWeekFirstString(), workers.work(date));
        }
        System.out.printf(WORK_SCHEDULE_WEEKDAY_FORMAT,
                date.getMonth(), date.getDay(), date.getDayOfWeekFirstString(), workers.work(date));

    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printNewLine() {
        System.out.printf(NEW_LINE);
    }
}
