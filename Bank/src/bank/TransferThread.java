package bank;

import java.util.Random;

/**
 * Created by Anna on 08.09.2015.
 */
public class TransferThread extends Thread {

    private final Bank bank;
    private int maxAmount;

    private boolean isStopped;
    private final Random random = new Random();

    private void checkBank(Bank bank) {
        if (bank == null)
            throw  new IllegalArgumentException("Bank is null");
    }

    private void checkAmount(int amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount "+amount+" is illegal");
    }

    public TransferThread(Bank bank, int maxAmount) {
        checkBank(bank);
        checkAmount(maxAmount);
        this.bank = bank;
        this.maxAmount = maxAmount;
    }

    @Override
    public void run() {
        while (!isStopped)
        {
            int from = random.nextInt(bank.getNumberOfAccounts());
            int to;
            do
            {
                to = random.nextInt(bank.getNumberOfAccounts());
            }
            while (from == to);
            int amount = random.nextInt(maxAmount)+1;

            if (bank.transfer(from,to,amount))
                System.out.println("Thread " + this.getName()
                    + " transferred " + amount
                    + " USD from " + from + " to " + to);
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void stopThread() {
        isStopped = true;
    }
}
