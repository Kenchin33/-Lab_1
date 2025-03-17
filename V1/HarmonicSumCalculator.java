package Lab1.V1;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HarmonicSumCalculator {

    // Функція обрахунку суми
    public static BigDecimal calculateHarmonicSum(int n, int precision) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 1; i <= n; i++) {
            sum = sum.add(BigDecimal.ONE.divide(BigDecimal.valueOf(i), precision + 5, RoundingMode.HALF_UP));
        }
        return sum.setScale(precision, RoundingMode.HALF_UP);
    }
}

