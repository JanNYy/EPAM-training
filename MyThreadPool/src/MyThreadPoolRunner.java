import threadpool.MyThreadPool;

/**
 * Created by Anna on 02.09.2015.
 */
public class MyThreadPoolRunner {

    public static void main(String[] args) {

        MyThreadPool threadPool = new MyThreadPool(10);

        int numberOfTasks = 100;

        MyThreadPool.Task[] tasks = new MyThreadPool.Task[numberOfTasks];
        for (int i = 0; i < numberOfTasks; i++)
        {
            final int finalI = i;
            tasks[i] = new MyThreadPool.Task() {

                public void run() {
                    System.out.println("> "+"Task #" + finalI + " is completed");
                }

                public String toString() {
                    return "Task #"+finalI;
                }
            };
        }

        for (MyThreadPool.Task t : tasks)
        {
            threadPool.addTask(t);
        }

        try
        {
            threadPool.stopThreadPool();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
