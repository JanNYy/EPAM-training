package producerconsumer;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Anna on 08.09.2015.
 */
public class CircularBuffer {

    private final Object[] bufferData;

    private int currentSize;
    private int indexPut;
    private int indexGet;

    final Lock lock = new ReentrantLock();
    final Condition isFull  = lock.newCondition();
    final Condition isEmpty = lock.newCondition();

    private void checkBufferSize(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("Buffer size is equal or less than 0");
    }

    private void checkObjNotNull(Object obj) {
        if (obj == null)
            throw new IllegalArgumentException("Data element is null");
    }

    public CircularBuffer(int bufferSize) {
        checkBufferSize(bufferSize);
        bufferData = new Object[bufferSize];
    }

    private int getCurrentIndex(int index)
    {
        return ++index == bufferData.length ? 0 : index;
    }

    public void put(Object obj) throws InterruptedException {
        checkObjNotNull(obj);
        lock.lockInterruptibly();
        try
        {
            while (currentSize == bufferData.length)
            {
                isFull.await();
            }
            bufferData[indexPut] = obj;
            indexPut = getCurrentIndex(indexPut);
            currentSize += 1;
            isEmpty.signal();
        }
        finally
        {
            lock.unlock();
        }
    }

    public Object get() throws InterruptedException {
        lock.lockInterruptibly();
        try
        {
            while (currentSize == 0)
            {
                isEmpty.await();
            }
            Object obj = bufferData[indexGet];
            bufferData[indexGet] = null;
            indexGet = getCurrentIndex(indexGet);
            currentSize -= 1;
            isFull.signal();
            return obj;
        }
        finally
        {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "Circular buffer: size = " + currentSize + ", data = " + Arrays.toString(bufferData);
    }
}
