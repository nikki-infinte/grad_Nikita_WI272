import java.time.*;
import java.util.*;
import java.util.concurrent.*;

class BikeTask implements Runnable {

    private String bikeName;
    private int distance;
    private CountDownLatch startSignal;

    private Instant startTime;
    private Instant endTime;

    public BikeTask(String bikeName, int distance, CountDownLatch startSignal) {
        this.bikeName = bikeName;
        this.distance = distance;
        this.startSignal = startSignal;
    }

    Random random = new Random();

    public void run() {

        System.out.println(bikeName + " is ready at starting line");

        try {
            // wait until countdown finishes
            startSignal.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        startTime = Instant.now();

        int covered = 0;

        while (covered < distance) {

            try {
                Thread.sleep(random.nextInt(400) + 200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            covered += 100;
        }

        endTime = Instant.now();
    }

    public String getBikeName() {
        return bikeName;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public long getTimeTaken() {
        return Duration.between(startTime, endTime).toMillis();
    }
}



class RaceDashboard {

    public static void showResults(List<BikeTask> bikes) {

        System.out.println("\n========== RACE DASHBOARD ==========");

        for (BikeTask bike : bikes) {

            System.out.println(
                bike.getBikeName()
                + " | Start: " + bike.getStartTime()
                + " | End: " + bike.getEndTime()
                + " | Time Taken: " + bike.getTimeTaken() + " ms"
            );
        }
    }
}


public class BikeRaceGameExecutor {

    public static void main(String[] args) throws Exception {

        int numberOfBikes = 5;
        int raceDistance = 1000;

        ExecutorService executor = Executors.newFixedThreadPool(numberOfBikes);

        CountDownLatch startSignal = new CountDownLatch(1);

        List<BikeTask> bikes = new ArrayList<>();

        for (int i = 1; i <= numberOfBikes; i++) {

            BikeTask bike = new BikeTask("Bike-" + i, raceDistance, startSignal);
            bikes.add(bike);

            executor.submit(bike);
        }

        System.out.println("\nAll bikes ready. Waiting for countdown...\n");

        // countdown
        for (int i = 5; i >= 1; i--) {

            System.out.println("Race begins in " + i);
            Thread.sleep(1000);
        }

        System.out.println("GOOOOOOO !!\n");

        // release all bikes simultaneously
        startSignal.countDown();

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);

        RaceDashboard.showResults(bikes);
    }
}
