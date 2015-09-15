import philosopher.ChineseChopstick;
import philosopher.Philosopher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Anna on 15.09.2015.
 */
public class DiningPhilosophersProblemRunner {

    public static void main(String args[]) throws InterruptedException {

        int numberOfPhilosophers = 5;

        Philosopher[] philosophers =  new Philosopher[numberOfPhilosophers];
        String[] philosophersNames = {"Confucius","Laozi","Zou Yan","Shen Dao","Yue Yi"};

        ChineseChopstick[] chopsticks = new ChineseChopstick[numberOfPhilosophers];

        for (int i = 0; i < numberOfPhilosophers; i++)
            chopsticks[i] = new ChineseChopstick(i+1);

        for (int i = 0; i < numberOfPhilosophers; i++)
            philosophers[i] = new Philosopher(philosophersNames[i],chopsticks[i],chopsticks[(i+1)%numberOfPhilosophers]);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfPhilosophers);

        for (int i = 0; i < numberOfPhilosophers; i++)
            executorService.execute(philosophers[i]);
        Thread.sleep(5000);
        for (Philosopher philosopher : philosophers)
            philosopher.setIsHungry(false);

        executorService.shutdown();

        while (!executorService.isTerminated())
            Thread.sleep(100);

        for (Philosopher philosopher : philosophers)
            System.out.println(philosopher+" ate "+philosopher.getQuantityOfFoodIntakes()+" times");
    }
}
