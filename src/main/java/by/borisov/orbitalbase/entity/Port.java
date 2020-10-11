package by.borisov.orbitalbase.entity;

import by.borisov.orbitalbase.exception.CustomException;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {

    private static Port instance;
    private static final AtomicBoolean isPortCreated = new AtomicBoolean(false);
    private static final Lock lock = new ReentrantLock();

    public static final int PIERS_AMOUNT = 3;
    private final Semaphore semaphore = new Semaphore(PIERS_AMOUNT, true);
    private final Queue<Pier> piers;


    private Port() {
        piers = new LinkedList<>();
        for (int i = 1; i <= PIERS_AMOUNT; i++) {
            piers.add(new Pier(i));
        }
    }

    public static Port getInstance() {
        if (!isPortCreated.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new Port();
                    isPortCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Pier getPier() throws CustomException {
        try {
            semaphore.acquire();
            return piers.poll(); // выдать пирс

        } catch (InterruptedException e) {
            throw new CustomException(e);
        }
    }

    public void returnPier(Pier pier) {
        piers.add(pier);
        semaphore.release();
    }
}
