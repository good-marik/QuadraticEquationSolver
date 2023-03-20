package tests;

import java.awt.Point;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestingRounding {
    public static void main(String[] alg) {
        BigDecimal bd = getStep4(Double.parseDouble(alg[0]));
        System.out.println(alg[0] + " --> " + bd);
        System.out.println();
        Double[] testNumbers = new Double[] { 0.0093, 1.23, 123.2, 694.0234 };
        for (Double x : testNumbers) {
            bd = getStep4(x);
            System.out.println(x + " --> " + bd);
            System.out.println();
        }
        System.out.println(19 % 2);
        Point x = null;
        System.out.println(x);
    }

    public static BigDecimal getStep4(double x) {
        double multiplicationFactor = (x > 0) ? 1 : -1;
        double positiveNumber = Math.abs(x);
        int scale = 0;
        while (positiveNumber >= 10) {
            positiveNumber = positiveNumber / 10;
            multiplicationFactor = multiplicationFactor * 10;
        }
        while (positiveNumber < 1) {
            positiveNumber = positiveNumber * 10;
            multiplicationFactor = multiplicationFactor / 10;
            scale++;
//            if (positiveNumber >= 9.5) {
//                scale--;
//            }
        }
//        System.out.println("input x: " + x);
//        System.out.println("scale : " + scale);
        BigDecimal multiplicationFactorBD = BigDecimal.valueOf(multiplicationFactor);
        BigDecimal numberBD = BigDecimal.valueOf(positiveNumber);
        BigDecimal numberRoundedBD = numberBD.setScale(0, RoundingMode.HALF_UP);
        int numberRoundedInt = numberRoundedBD.intValueExact();
        switch (numberRoundedInt) {
        case 3:
            numberRoundedInt = 2;
            break;
        case 4:
            numberRoundedInt = 5;
            break;
        case 6:
            numberRoundedInt = 5;
            break;
        case 7:
            numberRoundedInt = 5;
            break;
        case 8:
            numberRoundedInt = 10;
        case 9:
            numberRoundedInt = 10;
        case 10:
            scale--;
        }
        numberRoundedBD = BigDecimal.valueOf(numberRoundedInt);
        BigDecimal resultBD = numberRoundedBD.multiply(multiplicationFactorBD);
        return resultBD.setScale(scale, RoundingMode.HALF_UP);
    }

    public static BigDecimal getStep3(double d) {
        double factor = (d > 0) ? 1 : -1;
        d = Math.abs(d);
        while (d >= 1) {
            d = d / 10;
            factor = factor * 10;
        }
        while (d < 0.1) {
            d = d * 10;
            factor = factor / 10;
        }
//        if (d < 0.25) {
//            d = 0.1;
//        } else if (d >= 0.75) {
//            d = 1;
//        } else {
//            d = 0.50;
//        }
        BigDecimal bdFactor = BigDecimal.valueOf(factor);
        BigDecimal bdD = BigDecimal.valueOf(d);
        BigDecimal bdX = bdD.setScale(1, RoundingMode.HALF_UP);
        System.out.println();
        System.out.println("bdFactor: " + bdFactor + "; scale: " + bdFactor.scale());
        System.out.println("bdD: " + bdD + "; scale: " + bdD.scale());
        System.out.println("bdX: " + bdX + "; scale: " + bdX.scale());
        return bdX.multiply(bdFactor);
    }

    public static double getStep2(double d) {
        double factor = 1;
        while (d >= 1) {
            d = d / 10;
            factor = factor * 10;
        }
        BigDecimal bd = new BigDecimal(d);
        BigDecimal x;
        int scale = 1;
        while (true) {
            x = bd.setScale(scale, RoundingMode.DOWN);
            if (x.doubleValue() == 0.0) {
                scale++;
                System.out.println(scale);
            } else {
                x = bd.setScale(scale, RoundingMode.HALF_UP);
                return x.doubleValue() * factor;
            }
        }
    }

    public static double getStep(double d) {
        BigDecimal bd = new BigDecimal(d);
        BigDecimal x;
        int scale = 1;
        while (true) {
            x = bd.setScale(scale, RoundingMode.DOWN);
            if (x.doubleValue() == 0.0) {
                scale++;
                System.out.println(scale);
            } else {
                x = bd.setScale(scale, RoundingMode.HALF_UP);
                return x.doubleValue();
            }
        }
    }
}
