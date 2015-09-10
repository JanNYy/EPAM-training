import producerconsumer.CircularBuffer;
import producerconsumer.Consumer;
import producerconsumer.Producer;

import java.util.Random;

/**
 * Created by Anna on 08.09.2015.
 */
public class ProdConsRunner {

    public static void main(String[] args) {

        Random random = new Random();

        int bufferSize = 5;
        CircularBuffer testBuffer = new CircularBuffer(bufferSize);

        int numOfProducers = 20;
        int numOfConsumers = 10;

        Integer[][] producersData = new Integer[numOfProducers][];

        int numOfProducersData;
        int sumData = 0;
        for (int i = 0; i < numOfProducers; i++)
        {
            producersData[i] = new Integer[numOfProducersData = random.nextInt(11)+2];
            sumData += numOfProducersData;
            for (int j = 0; j < numOfProducersData; j++)
            {
                producersData[i][j] = random.nextInt(100)+1;
            }
        }
        for (int i = 0; i < numOfProducers; i++)
        {
            new Thread(new Producer(testBuffer, producersData[i])).start();
        }
        for (int i = 0; i < numOfConsumers-1; i++)
        {
            new Thread(new Consumer(testBuffer, sumData/numOfConsumers)).start();
        }
        if (sumData%numOfConsumers == 0)
            new Thread(new Consumer(testBuffer,
                    sumData/numOfConsumers)).start();
        else
            new Thread(new Consumer(testBuffer,
                    sumData/numOfConsumers+sumData%numOfConsumers)).start();
    }
}
