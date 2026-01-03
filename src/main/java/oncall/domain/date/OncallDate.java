package oncall.domain.date;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Map;
import oncall.exception.ErrorMessage;
import oncall.exception.OncallException;

public class OncallDate {

    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;
    private static final int MIN_DAY = 1;

    private static final int WEEK_LENGTH = 7;
    private static final int DAY_OFFSET = 2;
    private static final int DAY_ADJUSTMENT = 1;

    private static final Map<Integer, Integer> DAYS_IN_MONTH = Map.ofEntries(
            Map.entry(1, 31), Map.entry(2, 28),
            Map.entry(3, 31), Map.entry(4, 30),
            Map.entry(5, 31), Map.entry(6, 30),
            Map.entry(7, 31), Map.entry(8, 31),
            Map.entry(9, 30), Map.entry(10, 31),
            Map.entry(11, 30), Map.entry(12, 31)
    );

    private final int month;
    private final int day;
    private final DayOfWeek firstDayOfWeek;

    private OncallDate(int month, int day, DayOfWeek firstDayOfWeek) {
        this.month = month;
        this.day = day;
        this.firstDayOfWeek = firstDayOfWeek;
    }

    public static OncallDate of(int month, int day, DayOfWeek firstDayOfWeek) {
        validateMonth(month);
        validateDay(month, day);
        return new OncallDate(month, day, firstDayOfWeek);
    }

    public OncallDate plusOneDay() {
        int maxDay = DAYS_IN_MONTH.get(month);
        if (maxDay < day + 1) {
            return OncallDate.of(month + 1, 1, firstDayOfWeek);
        }

        return OncallDate.of(month, day + 1, firstDayOfWeek);
    }

    public OncallDate minusOneDay() {
        if (day == 1) {
            return OncallDate.of(month - 1, DAYS_IN_MONTH.get(month -1), firstDayOfWeek);
        }
        return OncallDate.of(month, day - 1, firstDayOfWeek);
    }

    public String getDayOfWeekFirstString() {
        if (getDayOfWeek() == DayOfWeek.MONDAY) return "월";
        if (getDayOfWeek() == DayOfWeek.TUESDAY) return "화";
        if (getDayOfWeek() == DayOfWeek.WEDNESDAY) return "수";
        if (getDayOfWeek() == DayOfWeek.THURSDAY) return "목";
        if (getDayOfWeek() == DayOfWeek.FRIDAY) return "금";
        if (getDayOfWeek() == DayOfWeek.SATURDAY) return "토";
        if (getDayOfWeek() == DayOfWeek.SUNDAY) return "일";

        throw OncallException.from(ErrorMessage.INVALID_DAY);
    }

    public static DayOfWeek createDayOfWeek(String input) {
        if (input.equals("월")) return DayOfWeek.MONDAY;
        if (input.equals("화")) return DayOfWeek.TUESDAY;
        if (input.equals("수")) return DayOfWeek.WEDNESDAY;
        if (input.equals("목")) return DayOfWeek.THURSDAY;
        if (input.equals("금")) return DayOfWeek.FRIDAY;
        if (input.equals("토")) return DayOfWeek.SATURDAY;
        if (input.equals("일")) return DayOfWeek.SUNDAY;

        throw OncallException.from(ErrorMessage.INVALID_DAY);
    }

    public static void validateStartDay(String input) {
        createDayOfWeek(input);
    }

    public DayOfWeek getDayOfWeek() {
        int startValue = firstDayOfWeek.getValue();
        int dayValue = (startValue + day - DAY_OFFSET) % WEEK_LENGTH + DAY_ADJUSTMENT;
        return DayOfWeek.of(dayValue);
    }

    public boolean isWeekday() {
        DayOfWeek dayOfWeek = getDayOfWeek();
        return dayOfWeek != DayOfWeek.SATURDAY
                && dayOfWeek != DayOfWeek.SUNDAY;
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY
                || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public boolean isHoliday() {
        return Arrays.stream(Holiday.values())
                .anyMatch(holiday -> holiday.isHoliday(month, day));
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    private static void validateMonth(int month) {
        if (month < MIN_MONTH || month > MAX_MONTH) {
            throw OncallException.from(ErrorMessage.INVALID_MONTH);
        }
    }

    private static void validateDay(int month, int day) {
        int maxDay = DAYS_IN_MONTH.get(month);
        if (day < MIN_DAY || day > maxDay) {
            throw OncallException.from(ErrorMessage.INVALID_DAY);
        }
    }
}
