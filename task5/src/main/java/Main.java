import javax.naming.SizeLimitExceededException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static final int RESOURCE_NUMBER = 10;

    public static void main(String[] args) throws FileNotFoundException, InterruptedException, SizeLimitExceededException {
        int poolFallingAsleepTime = 5;
        List<QuadraticEquation> tasksList = new ArrayList<>();
        File file = new File(Main.class.getClassLoader().getResource("input").getFile());

        Scanner scanner = new Scanner(file);

        for (int i = 0; i < RESOURCE_NUMBER; ++i) {
            tasksList.add(new QuadraticEquation(scanner.nextLine()));
        }
        ResourcePool<QuadraticEquation> pool = new ResourcePool<>(tasksList, 2000, 8);

        for (int i = 0; i < pool.getThreadCount(); ++i) {
            PoolWorker<QuadraticEquation> worker = new PoolWorker<>(pool);
            worker.start();
        }

        pool.addResource(new QuadraticEquation("0 0 2"));

        while (true) {
            Thread.sleep(poolFallingAsleepTime * 1000);
            if (pool.isTimeToStop()) {
                System.out.println("Пул был выключен ввиду бездействия.");
                return;
            }
        }
    }

}
