import java.util.Queue;
import java.util.concurrent.Callable;

class PoolWorker<T extends Callable> extends Thread {
    private ResourcePool<T> resourcePool;

    PoolWorker(ResourcePool<T> resourcePool) {
        this.resourcePool = resourcePool;
    }


    @Override
    public void run() {
        while (resourcePool.getSize() - resourcePool.getStopCount() > 0) {
            int needed = (resourcePool.getSize() / resourcePool.getThreadCount()) + 1;
            Queue<T> requestedFromPool = resourcePool.get(needed);
            System.out.println(this.getName() + ": got from pool list of " + requestedFromPool.size() + " resources");
            try {
                resourcePool.increaseStopCount();
                System.out.println(requestedFromPool.peek().call());
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }

            resourcePool.release(requestedFromPool);
            System.out.println(this.getName() + ": released list of " + requestedFromPool.size() + " resources");
        }
    }
}
