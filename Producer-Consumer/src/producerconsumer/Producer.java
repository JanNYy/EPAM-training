package producerconsumer;

/**
 * Created by Anna on 08.09.2015.
 */
public class Producer implements Runnable {

    private static int globalID;

    private int producerID;
    private final CircularBuffer buffer;
    private Object[] producerData;
    private int currentIndex;

    private void checkBuffer(CircularBuffer buf) {
        if (buf == null) throw new IllegalArgumentException("Buffer is null");
    }

    private void checkData(Object[] data) {
        if (data == null) throw new IllegalArgumentException("Data is null");
    }

    public Producer(CircularBuffer producerBuffer, Object[] data) {
        checkBuffer(producerBuffer);
        checkData(data);
        producerID = globalID++;
        buffer = producerBuffer;
        producerData = data;
    }

    @Override
    public void run() {
        System.out.println("Producer " + producerID + " is running");
        while (currentIndex < producerData.length)
        {
            try
            {
                buffer.put(producerData[currentIndex++]);
                System.out.println("Producer " + producerID + " put " + producerData[currentIndex - 1]);
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("Producer "+producerID+" stopped working");
    }
}
