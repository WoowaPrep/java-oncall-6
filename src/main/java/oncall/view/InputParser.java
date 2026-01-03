package oncall.view;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import oncall.domain.work.HolidayWorkers;
import oncall.domain.date.OncallDate;
import oncall.domain.work.WeekdayWorkers;
import oncall.exception.ErrorMessage;
import oncall.exception.OncallException;

public class InputParser {

    private static final String DELIMITER = ",";
    private static final String NUMERIC_REGEX = "\\d+";

    private static final int MONTH_DAY_PAIR_COUNT = 2;
    private static final int MONTH_START_THRESHOLD = 1;
    private static final int MONTH_END_THRESHOLD = 12;

    private static final int MIN_WORKER_THRESHOLD = 5;
    private static final int MAX_WORKER_THRESHOLD = 35;

    private static final int NAME_LENGTH_THRESHOLD = 5;

    public static OncallDate parseMonthStartDay(String input) {
        String[] monthDay = input.split(DELIMITER);
        validateMonthStartDay(monthDay);

        String monthInput = monthDay[0];
        String dayOfWeekInput = monthDay[1];
        int monthValue = Integer.parseInt(monthInput);
        int startDay = 1;
        DayOfWeek dayOfWeek = OncallDate.createDayOfWeek(dayOfWeekInput);
        return OncallDate.of(monthValue, startDay, dayOfWeek);
    }

    public static WeekdayWorkers parseWeekdayWorkers(String input) {
        String[] names = input.split(DELIMITER);
        validateWorkerName(names);
        validateWorkerCount(names);
        validateDuplicate(names);

        return new WeekdayWorkers(List.of(names));
    }

    public static HolidayWorkers parseHolidayWorkers(String input) {
        String[] names = input.split(DELIMITER);
        validateWorkerName(names);
        validateWorkerCount(names);
        validateDuplicate(names);

        return new HolidayWorkers(List.of(names));
    }

    private static void validateDuplicate(String[] input) {
        if (input.length != (int) Arrays.stream(input).distinct().count()) {
            throw OncallException.from(ErrorMessage.INVALID_WORKERS);
        }
    }

    private static void validateWorkerCount(String[] input) {
        if (input.length < MIN_WORKER_THRESHOLD || input.length > MAX_WORKER_THRESHOLD) {
            throw OncallException.from(ErrorMessage.INVALID_WORKERS);
        }
    }

    private static void validateWorkerName(String[] input) {
        if (Arrays.stream(input)
                .anyMatch(name -> name.trim().isEmpty() ||
                        name.trim().length() > NAME_LENGTH_THRESHOLD)) {
            throw OncallException.from(ErrorMessage.INVALID_WORKERS);
        }
    }

    private static void validateMonthStartDay(String[] input) {
        validateMonthDayPair(input);
        String monthInput = input[0];
        String startDayInput = input[1];

        validateMonth(monthInput);
        validateStartDay(startDayInput);
    }

    private static void validateMonthDayPair(String[] input) {
        if (input.length != MONTH_DAY_PAIR_COUNT) {
            throw OncallException.from(ErrorMessage.INVALID_MONTH_DAY_PAIR);
        }
    }

    private static void validateMonth(String input) {
        validateMonthNumeric(input);
        Integer monthValue = Integer.parseInt(input);
        validateMonthRange(monthValue);
    }

    private static void validateStartDay(String input) {
        OncallDate.validateStartDay(input);
    }

    private static void validateMonthNumeric(String input) {
        if (!input.matches(NUMERIC_REGEX)) {
            throw OncallException.from(ErrorMessage.INVALID_MONTH);
        }
    }

    private static void validateMonthRange(Integer input) {
        if (input < MONTH_START_THRESHOLD || input > MONTH_END_THRESHOLD) {
            throw OncallException.from(ErrorMessage.INVALID_MONTH);
        }
    }
}
