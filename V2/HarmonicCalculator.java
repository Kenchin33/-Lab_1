package Lab1.V2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class HarmonicCalculator {
    public BigDecimal computeHarmonicSum(int n, int precision) {
        int numThreads = 1;// Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<BigDecimal>> futures = new ArrayList<>();

        int chunkSize = (int) Math.ceil((double) n / numThreads); // Округлення в більшу сторону
        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize + 1;
            int end = Math.min((i + 1) * chunkSize, n); // Останній потік обробляє до n

            futures.add(executor.submit(new Callable<BigDecimal>() {
                private int localStart = start;
                private int localEnd = end;

                @Override
                public BigDecimal call() {
                    BigDecimal localSum = BigDecimal.ZERO;
                    for (int j = localStart; j <= localEnd; j++) {
                        localSum = localSum.add(BigDecimal.ONE.divide(BigDecimal.valueOf(j), precision + 5, RoundingMode.HALF_UP));
                    }
                    return localSum;
                }
            }));
        }

        BigDecimal sum = BigDecimal.ZERO;
        try {
            for (Future<BigDecimal> future : futures) {
                sum = sum.add(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error in parallel computation: " + e.getMessage());
        }
        executor.shutdown();
        return sum.setScale(precision, RoundingMode.HALF_UP);
    }
}
