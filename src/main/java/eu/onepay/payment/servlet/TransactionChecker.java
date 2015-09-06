package eu.onepay.payment.servlet;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

import eu.onepay.payment.OurTransaction;

public class TransactionChecker {

    public static final String threadName = "_transacton_thread";

    private static boolean threadActive = false;

    private TransactionChecker ( ){
    }

    private static ConcurrentLinkedQueue<OurTransaction> queue = new ConcurrentLinkedQueue<OurTransaction>();

    public static void addToTransactions(OurTransaction transaction) {

        queue.add(transaction);

        if (threadActive) {

        } else {
            threadActive = true;
            Thread t = new Thread(new TransactionChecker().new TransactionCheckerRunner());
            t.setName(TransactionChecker.threadName);
            t.start();

        }
    }

    private class TransactionCheckerRunner implements Runnable {

        @Override
        public void run() {
            try {
                while (queue.isEmpty() == false) {

                    OurTransaction peek = queue.peek();
                    Date time = peek.timeSentOut();
                    Date now = new Date();

                    Long deltaDate = now.getTime() - time.getTime();
                    Date delta = new Date(deltaDate);
                    long minutes = TimeUnit.MILLISECONDS.toMinutes(delta.getTime());

                    if (minutes < 6) {
                        try {
                            wait(360_000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // TODO: DATABASE
                        // check if the transaction is still in 'paying' state
                        boolean isPaying = true;
                        if (isPaying) { // Set transaction as LeftUnpaid in DB

                        } else {
                            queue.poll();
                        }
                    }
                }
            } finally {
                threadActive = false;

            }

        }

    }

}
