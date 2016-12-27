package eu.onepay.payment.servlet;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import eu.onepay.payment.PaymentTransaction;

public class TransactionChecker {

    public static final String threadName = "_transacton_thread";

    private static boolean threadActive = false;

    private TransactionChecker ( ){
    }

    private static LinkedBlockingQueue<PaymentTransaction> queue = new LinkedBlockingQueue <PaymentTransaction>();

    public static void addToTransactions(PaymentTransaction transaction) {

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
        public synchronized void run() {
            try {
                while (queue.isEmpty() == false) {

                    PaymentTransaction peek = queue.peek();
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
                            
                            // if all good poll form queue
                            queue.poll();
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
