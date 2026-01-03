package oncall.domain.work;

import oncall.domain.date.OncallDate;

public class Workers {

    private WeekdayWorkers weekdayWorkers;
    private HolidayWorkers holidayWorkers;

    public Workers(WeekdayWorkers weekdayWorkers, HolidayWorkers holidayWorkers) {
        this.weekdayWorkers = weekdayWorkers;
        this.holidayWorkers = holidayWorkers;
    }

    public String work(OncallDate date) {
        if (date.isWeekday() && !date.isHoliday()) {
            OncallDate yesterday = date.minusOneDay();
            if (yesterday.isWeekend() || yesterday.isHoliday()) {
                return assignWeekdayWorker();
            }

            return weekdayWorkers.assignWorker();
        }

        OncallDate yesterday = date.minusOneDay();
        if (yesterday.isWeekend() && !yesterday.isHoliday()) {
            return assignHolidayWorker();
        }

        return holidayWorkers.assignWorker();
    }

    private String assignWeekdayWorker() {
        if (isSameWorker()) {
            weekdayWorkers.swapWithNext();
        }

        return weekdayWorkers.assignWorker();
    }

    private String assignHolidayWorker() {
        if (isSameWorker()) {
            holidayWorkers.swapWithNext();
        }

        return holidayWorkers.assignWorker();
    }

    private boolean isSameWorker() {
        String firstWorker = weekdayWorkers.getWorker();
        String secondWorker = holidayWorkers.getWorker();
        return firstWorker.equals(secondWorker);
    }
}
