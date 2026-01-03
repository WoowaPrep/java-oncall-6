package oncall.domain.work;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeekdayWorkers {

    private final List<String> workers;
    private final int workerCount;
    private int workerIndex = 0;

    public WeekdayWorkers(List<String> workers) {
        this.workers = new ArrayList<>(workers);
        this.workerCount = workers.size();
    }

    public void swapWithNext() {
        int nextIndex = (workerIndex + 1) % workerCount;
        Collections.swap(workers, workerIndex, nextIndex);
    }

    public void incrementIndex() {
        workerIndex++;
    }

    public String getWorker() {
        return workers.get(workerIndex % workerCount);
    }
}
