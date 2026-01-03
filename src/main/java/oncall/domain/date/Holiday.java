package oncall.domain.date;

import java.time.MonthDay;

public enum Holiday {

    NEW_YEAR_DAY(MonthDay.of(1, 1), "신정"),
    INDEPENDENCE_MOVEMENT_DAY(MonthDay.of(3, 1), "삼일절"),
    CHILDREN_DAY(MonthDay.of(5, 5), "어린이날"),
    MEMORIAL_DAY(MonthDay.of(6, 6), "현충일"),
    NATIONAL_LIBERATION_DAY(MonthDay.of(8, 15), "광복절"),
    NATIONAL_FOUNDATION_DAY_OF_KOREA(MonthDay.of(10, 3), "개천절"),
    HANGUL_PROCLAMATION_DAY(MonthDay.of(10, 9), "한글날"),
    CHRISTMAS(MonthDay.of(12, 25), "성탄절"),
    ;

    private final MonthDay date;
    private final String name;

    Holiday(MonthDay date, String name) {
        this.date = date;
        this.name = name;
    }

    public MonthDay getMonthDay() {
        return date;
    }
}
