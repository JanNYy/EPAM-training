package producerconsumer;

/**
 * Created by Anna on 08.09.2015.
 */
public class Consumer implements Runnable {

    private static int globalID;

    private int consumerID;
    private final CircularBuffer buffer;
    private Object[] consumerData;
    private int dataSize;
    private int currentIndex;

    private void checkBuffer(CircularBuffer buf) {
        if (buf == null) throw new IllegalArgumentException("Buffer is null");
    }

    private void checkDataSize(int size) {
        if (size <= 0) throw new IllegalArgumentException("Data size is equal or less than 0");
    }

    public Consumer(CircularBuffer consumerBuffer, int consumerDataSize) {
        checkBuffer(consumerBuffer);
        checkDataSize(consumerDataSize);
        consumerID = globalID++;
        buffer = consumerBuffer;
        dataSize = consumerDataSize;
        consumerData = new Object[dataSize];
    }

    @Override
    public void run() {
        System.out.println("Consumer "+consumerID+" is running");
        while (currentIndex < dataSize)
        {
            try
            {
                consumerData[currentIndex++] = buffer.get();
                System.out.println("Consumer " + consumerID + " got " + consumerData[currentIndex-1]);
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("Consumer "+consumerID+" stopped working");
    }
}
