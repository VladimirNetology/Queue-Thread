import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static final int num_tech_supports = 10;

    public static void main(String[] args)  {
        final CallCenter callCenter = new CallCenter();

        new Thread(callCenter::createCases).start();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < num_tech_supports; i++) {
            executorService.submit(callCenter::workWithCases);
        }
    }
}

