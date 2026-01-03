package oncall.domain.work;

import java.util.Collections;
import java.util.List;

public class WeekdayWorkers {

    private final List<String> workers;
    private final int workerCount;
    private int workerIndex = 0;

    public WeekdayWorkers(List<String> workers) {
        this.workers = workers;
        this.workerCount = workers.size();
    }

    public String getWorker() {
        if (workerIndex > 0) {
            String yesterdayWorker = workers.get((workerIndex - 1) % workerCount);
            String todayWorker = workers.get(workerIndex % workerCount);
            if (yesterdayWorker.equals(todayWorker)) {
                Collections.swap(workers, workerIndex, (workerIndex + 1) % workerCount);
            }
        }

        return workers.get(workerIndex++ % workerCount);
    }
}
