package threadpool;

import java.util.LinkedList;

/**
 * Created by Anna on 02.09.2015.
 */
public class MyThreadPool {

    private final int numberOfThreads;
    private final ThreadPoolWorker[] workers;

    private final LinkedList<Task> tasksQueue = new LinkedList<Task>();

    private boolean isStopped;
    private boolean isWorkersRun;

    private class ThreadPoolWorker extends Thread {

        private boolean isStopped;

        public void run() {
            Task task;
            while (!isStopped) {
                task = null;
//                synchronized (tasksQueue)
//                {
//                    if (!tasksQueue.isEmpty())
//                    {
//                        task = tasksQueue.removeFirst();
//                        tasksQueue.notify();
//                    }
//                }
                synchronized (tasksQueue)
                {
                    while ((tasksQueue.isEmpty())&&(!isStopped))
                        try
                        {
                            tasksQueue.wait(10);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    if (!tasksQueue.isEmpty())
                    {
                        task = tasksQueue.removeFirst();
                        tasksQueue.notify();
                    }
                }
                if (task != null)
                {
                    System.out.println(this.getName() + " got " + task);
                    task.run();
                }
            }
        }

        public synchronized void stopThreadPoolWorker() {
            isStopped = true;
//            this.interrupt();
        }
    }

    public interface Task {
        void run();
    }

    private void checkNumberOfThreads(int numberOfThreads) {
        if (numberOfThreads <= 0)
            throw new IllegalArgumentException("Number of threads is equal or less than zero");
    }

    private void checkIsStopped() {
        if (isStopped)
            throw new IllegalStateException("ThreadPool is stopped");
    }

    private void checkTaskIsNull(Task task) {
        if (task == null)
            throw new IllegalArgumentException("Task is null");
    }

    public MyThreadPool(int numberOfThreads) {
        checkNumberOfThreads(numberOfThreads);
        this.numberOfThreads = numberOfThreads;
        workers = new ThreadPoolWorker[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++)
        {
            workers[i] = new ThreadPoolWorker();
//            workers[i].start();
        }
    }

    public int getNumberOfThreads() {
        checkIsStopped();
        return numberOfThreads;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void addTask(Task task) {
        checkIsStopped();
        checkTaskIsNull(task);
        if (!isWorkersRun)
        {
            for (int i = 0; i < numberOfThreads; i++)
                workers[i].start();
            isWorkersRun = true;
        }
        synchronized (tasksQueue)
        {
            tasksQueue.addLast(task);
            tasksQueue.notify();
        }
    }

    public void stopThreadPool() throws InterruptedException {
        checkIsStopped();
        isStopped = true;
        synchronized (tasksQueue)
        {
            while (!tasksQueue.isEmpty())
                tasksQueue.wait();

            for (ThreadPoolWorker w : workers)
                w.stopThreadPoolWorker();
        }
    }
}
