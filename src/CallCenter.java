import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCenter {
    public static final int MILLIS_CUSTOMER = 3000;
    public static final int CUSTOMERS_PER_SECOND = 50;
    public final ConcurrentLinkedQueue<Cases> casesPool = new ConcurrentLinkedQueue<>();

    public void createCases() {
        for (int i = 0; i < CUSTOMERS_PER_SECOND; i++) {
            casesPool.add(new Cases());
        }
        System.out.println("Cases Pool: " + casesPool + " " + Thread.currentThread().getName());
        System.out.println("Cases Pool Size: " + casesPool.size() + " " + Thread.currentThread().getName());
    }

    public void workWithCases() {
        while (!casesPool.isEmpty()) {
            try {
                Cases firstCase = casesPool.poll();
                System.out.println(" - Work with case: " + firstCase + " " + Thread.currentThread().getName());
                Thread.sleep(MILLIS_CUSTOMER);
                System.out.println(" - Done with case: " + firstCase + " " + Thread.currentThread().getName());
                System.out.println("Cases Pool Size: " + casesPool.size() + " " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
