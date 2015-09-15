package philosopher;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Anna on 15.09.2015.
 */
public class ChineseChopstick {

    private final int chopstickId;

    Lock puttedUp = new ReentrantLock();

    public ChineseChopstick(int chopstickId) {
        this.chopstickId = chopstickId;
    }

    public boolean pickUp(Philosopher philosopher, String side) throws InterruptedException {
        if (puttedUp.tryLock(10, TimeUnit.MILLISECONDS))
        {
            System.out.println(philosopher + " picked up " + side + " №" + chopstickId);
            return true;
        }
        return false;
    }

    public void putDown(Philosopher philosopher, String side) {
        puttedUp.unlock();
        System.out.println(philosopher + " put down " + side + " №" + chopstickId);
    }

    @Override
    public String toString() {
        return "Chopstick №" + chopstickId;
    }
}