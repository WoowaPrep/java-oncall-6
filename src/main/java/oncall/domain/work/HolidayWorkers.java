package oncall.domain.work;

import java.util.Collections;
import java.util.List;

public class HolidayWorkers {

    private final List<String> workers;
    private final int workerCount;
    private int workerIndex = 0;

    public HolidayWorkers(List<String> workers) {
        this.workers = workers;
        this.workerCount = workers.size();
    }

    public void swapWithNext() {
        Collections.swap(workers, workerIndex, workerIndex + 1);
    }

    public String assignWorker() {
        String worker = getWorker();
        workerIndex++;
        return worker;
    }

    public String getWorker() {
        return workers.get(workerIndex % workerCount);
    }
}
