package oncall.domain.work;

import oncall.domain.date.OncallDate;

public class Workers {

    private final WeekdayWorkers weekdayWorkers;
    private final HolidayWorkers holidayWorkers;
    private String lastWorker = null;

    public Workers(WeekdayWorkers weekdayWorkers, HolidayWorkers holidayWorkers) {
        this.weekdayWorkers = weekdayWorkers;
        this.holidayWorkers = holidayWorkers;
    }

    public String work(OncallDate date) {
        if (date.isWeekday() && !date.isHoliday()) {
            return assignWeekdayWorker();
        }
        return assignHolidayWorker();
    }

    private String assignWeekdayWorker() {
        String worker = weekdayWorkers.getWorker();

        if (needsSwap(worker)) {
            weekdayWorkers.swapWithNext();
            worker = weekdayWorkers.getWorker();
        }

        weekdayWorkers.incrementIndex();
        lastWorker = worker;
        return worker;
    }

    private String assignHolidayWorker() {
        String worker = holidayWorkers.getWorker();

        if (needsSwap(worker)) {
            holidayWorkers.swapWithNext();
            worker = holidayWorkers.getWorker();
        }

        holidayWorkers.incrementIndex();
        lastWorker = worker;
        return worker;
    }

    private boolean needsSwap(String worker) {
        if (lastWorker == null) {
            return false;
        }
        return lastWorker.equals(worker);
    }
}
