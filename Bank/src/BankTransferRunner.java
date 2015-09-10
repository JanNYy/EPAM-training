import bank.Bank;
import bank.TransferThread;

/**
 * Created by Anna on 08.09.2015.
 */
public class BankTransferRunner {

    public static void main(String[] args) {

        int numberOfAccounts = 500;
        int maxInitialBalance = 1000;
        Bank bank = new Bank(numberOfAccounts, maxInitialBalance);
        long startTotalBalance = bank.getTotalBalance();
//        System.out.println("Start total balance: "+startTotalBalance);

        int numberOfTransferThreads = 2000;
        int maxAmount = 500;
        TransferThread[] threads = new TransferThread[numberOfTransferThreads];
        for (int i = 0; i < numberOfTransferThreads; i++)
        {
            threads[i] = new TransferThread(bank,maxAmount);
            threads[i].start();
        }

        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i < numberOfTransferThreads; i++)
        {
            threads[i].stopThread();
        }
        for (int i = 0; i < numberOfTransferThreads; i++)
        {
            try
            {
                threads[i].join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        long finishTotalBalance = bank.getTotalBalance();
        System.out.println("Start total balance: " + startTotalBalance);
        System.out.println("Finish total balance: " + finishTotalBalance);
    }
}
