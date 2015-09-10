package bank;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Anna on 08.09.2015.
 */
public class Bank {

    private final Account[] accounts;

    private final Lock bankLock = new ReentrantLock();
    private final Condition enoughMoney = bankLock.newCondition();

    private class Account {

        private String name;
        private int balance;

        public Account(String name, int balance) {
            this.name = name;
            this.balance = balance;
        }

        public String getName() {
            return name;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(int amount) {
            balance = balance + amount;
        }

        public void withdraw(int amount) {
            balance = balance - amount;
        }
    }

    private void checkNumberOfAccounts(int numberOfAccounts) {
        if (numberOfAccounts <= 0)
            throw new IllegalArgumentException(
                    "Number of accounts is equal or less than zero");
    }

    private void checkAccountNumber(int number) {
        if ((number < 0) || (number >= accounts.length))
            throw new IllegalArgumentException("Account number "+number+" is illegal");
    }

    private void checkAmount(int amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount "+amount+" is illegal");
    }

    public Bank(int numberOfAccounts, int maxInitialBalance) {
        checkNumberOfAccounts(numberOfAccounts);
        accounts = new Account[numberOfAccounts];
        Random random = new Random();
        for (int i = 0; i < accounts.length; i++)
        {
            accounts[i] = new Account("Account "+i,
                    random.nextInt(maxInitialBalance)+1);
        }
    }

    public boolean transfer(int from, int to, int amount) {
        {
            checkAccountNumber(from);
            checkAccountNumber(to);
            checkAmount(amount);
            bankLock.lock();
            try
            {
                long nanos = 1000;
                while (accounts[from].getBalance() < amount)
                {
                    if (nanos > 0)
                        nanos = enoughMoney.awaitNanos(nanos);
                    else
                        return false;
                }
                accounts[from].withdraw(amount);
                accounts[to].deposit(amount);
                enoughMoney.signal();
                return true;
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                return false;
            }
            finally
            {
                bankLock.unlock();
            }
        }
    }

    public long getTotalBalance() {
        bankLock.lock();
        try
        {
            long sum = 0L;
            for (Account a : accounts)
            {
                sum += a.getBalance();
            }
            return sum;
        }
        finally
        {
            bankLock.unlock();
        }
    }

    public int getNumberOfAccounts() {
        return accounts.length;
    }

}
