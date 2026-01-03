package oncall.domain;

public class Workers {

    private WeekdayWorkers weekdayWorkers;
    private HolidayWorkers holidayWorkers;

    public Workers(WeekdayWorkers weekdayWorkers, HolidayWorkers holidayWorkers) {
        this.weekdayWorkers = weekdayWorkers;
        this.holidayWorkers = holidayWorkers;
    }
}
