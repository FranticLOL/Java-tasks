import javax.naming.SizeLimitExceededException;
import java.util.*;

public class ResourcePool<T> {
    private int size;
    private int maxPoolSize;
    private int stopCount;
    private int threadCount;
    private Queue<T> resources = new LinkedList<>();

    public ResourcePool(List<T> resources, int maxPoolSize, int threadCount) {
        this.resources.addAll(resources);
        size = this.resources.size();
        this.maxPoolSize = maxPoolSize;
        this.threadCount = threadCount;
    }

    public synchronized Queue<T> get(int neededCount) {
        Queue<T> requested = new LinkedList<>();
        while (resources.size() < neededCount) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < neededCount; ++i) {
            requested.add(resources.poll());
        }
        return requested;
    }

    public void increaseStopCount() {
        ++stopCount;
    }

    public int getStopCount() {
        return stopCount;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public Boolean isTimeToStop() {
        return resources.size() <= stopCount;
    }

    public synchronized void addResource(T resource) throws SizeLimitExceededException {
        if (size + 1 < maxPoolSize) {
            resources.offer(resource);
            ++size;
        } else {
            throw new SizeLimitExceededException("Pool is overflowed");
        }
    }

    public synchronized void release(Queue<T> released) {
        resources.addAll(released);
        notifyAll();
    }

    public synchronized int getSize() {
        return size;
    }
}
