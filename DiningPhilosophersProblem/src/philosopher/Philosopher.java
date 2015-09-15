package philosopher;

/**
 * Created by Anna on 15.09.2015.
 */
public class Philosopher implements Runnable {

    private static final int PHILOSOPHIZE_TIME = 500;
    private static final int EAT_TIME = 200;

    private final String name;

    private final ChineseChopstick leftChineseChopstick;
    private final ChineseChopstick rightChineseChopstick;

    private volatile boolean isHungry = true;

    private int quantityOfFoodIntakes = 0;

    public Philosopher(String name, ChineseChopstick leftChineseChopstick, ChineseChopstick rightChineseChopstick) {
        this.name = name;
        this.leftChineseChopstick = leftChineseChopstick;
        this.rightChineseChopstick = rightChineseChopstick;
    }

    public void setIsHungry(boolean isHungry) {
        this.isHungry = isHungry;
    }

    @Override
    public void run() {
        try
        {
            while (isHungry)
            {
                if (leftChineseChopstick.pickUp(this, "left"))
                {
                    if (rightChineseChopstick.pickUp(this, "right"))
                    {
                        eat();
                        rightChineseChopstick.putDown(this, "right");
                    }
                    leftChineseChopstick.putDown(this, "left");
                }
                philosophize();
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private void philosophize() throws InterruptedException {
        System.out.println(name + " is philosophizing");
        Thread.sleep(PHILOSOPHIZE_TIME);
    }

    private void eat() throws InterruptedException {
        System.out.println(name + " is eating");
        quantityOfFoodIntakes += 1;
        Thread.sleep(EAT_TIME);
    }

    public int getQuantityOfFoodIntakes() {
        return quantityOfFoodIntakes;
    }

    @Override
    public String toString() {
        return name;
    }
}
