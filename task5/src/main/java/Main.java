import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static final int RESOURCE_NUMBER = 16;
    public static final int TASKS_NUMBER = 1000;
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        List<QuadraticEquation> tasksList = new ArrayList<>();
        File file = new File(Main.class.getClassLoader().getResource("input").getFile());

        Scanner scanner = new Scanner(file);

        for (int i = 0; i < TASKS_NUMBER; ++i) {
            tasksList.add(new QuadraticEquation(scanner.nextLine()));
        }
        ResourcePool<QuadraticEquation> pool = new ResourcePool<>(tasksList,RESOURCE_NUMBER);

        pool.addTask(new QuadraticEquation("0 0 2"));
        
        while (true) {
            pool.run();
            if (pool.tasksIsEmpty()) {
                Thread.sleep(3000);
                if(pool.tasksIsEmpty()) {
                    System.out.println("Poll has stopped due to no new tasks.");
                    return;
                }
            }
        }
    }

}
