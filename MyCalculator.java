package numbers.vsevolod_mikulik;

import numbers.do_not_change.Calculator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyCalculator extends Calculator {

    /**
     * This is the constructor of a Calculator
     *
     * @param numbers the list of numbers
     */
    public MyCalculator(List<Number> numbers) {
        super(numbers);
    }

    /**
     * This method allows summarizing all elements of
     * the input list of numbers
     *
     * @return the double sum of all elements
     */
    @Override
    public double summarize() {
        double sum = 0;
        for (Number currentNumber : getNumbers()) {
            sum += currentNumber.doubleValue();
        }
        return sum;
    }

    /**
     * This method allows multiplying all elements of
     * the input list of numbers
     *
     * @return the double result of all element
     * multiplication
     */
    @Override
    public double multiply() {
        double product = 1;
        for (Number currentNumber : getNumbers()) {
            product *= currentNumber.doubleValue();
        }
        return product;
    }

    /**
     * This method allows deleting all negative numbers
     * from the list
     */
    @Override
    public void deleteNegativeNumbers() {
        Iterator<Number> iterator = getNumbers().iterator();
        while (iterator.hasNext()) {
            Number currentNumber = iterator.next();
            if (currentNumber.doubleValue() < 0) {
                iterator.remove();
            }
        }
        System.out.println(getNumbers().toString());
    }

    /**
     * This method allows dividing each number of the
     * list by the divisor and rewrite the list values
     *
     * @param divisor the divisor
     */
    @Override
    public void divideBy(double divisor) {
        if (divisor != 0) {
            for (int i = 0; i < getNumbers().size(); i++) {
                Number currentNumber = getNumbers().get(i);
                if (currentNumber instanceof Byte) {
                    getNumbers().set(i, currentNumber.byteValue() / ((byte) divisor));
                } else if (currentNumber instanceof Short) {
                    getNumbers().set(i, currentNumber.shortValue() / ((short) divisor));
                } else if (currentNumber instanceof Integer) {
                    getNumbers().set(i, currentNumber.intValue() / ((int) divisor));
                } else if (currentNumber instanceof Long) {
                    getNumbers().set(i, currentNumber.longValue() / ((long) divisor));
                } else if (currentNumber instanceof Float) {
                    getNumbers().set(i, currentNumber.floatValue() / ((float) divisor));
                } else if (currentNumber instanceof Double) {
                    getNumbers().set(i, currentNumber.doubleValue() / divisor);
                } else if (currentNumber instanceof BigInteger) {
                    getNumbers().set(i, ((BigInteger) currentNumber).divide(new BigInteger(String.valueOf((int) divisor))));
                } else if (currentNumber instanceof BigDecimal) {
                    BigDecimal bigDivisor = new BigDecimal(divisor);
                    getNumbers().set(i, ((BigDecimal) currentNumber).divide(bigDivisor,
                            ((BigDecimal) currentNumber).scale() - bigDivisor.scale(), RoundingMode.HALF_UP));
                }
            }
        } else {
            System.out.println("It seems that you are trying to divide by zero.");
        }
        System.out.println(getNumbers().toString());
    }

    /**
     * This method allows calculating square root
     * for each element of the array and updating the
     * list values
     */
    @Override
    public void getSquareRoot() {
        List<Integer> list = new LinkedList<>();
        boolean negativePresent = false;
        for (int i = 0; i < getNumbers().size(); i++) {
            Number currentNumber = getNumbers().get(i);
            if (currentNumber.doubleValue() >= 0) {
                if (currentNumber instanceof Byte) {
                    getNumbers().set(i, (byte) Math.sqrt(currentNumber.byteValue()));
                } else if (currentNumber instanceof Short) {
                    getNumbers().set(i, (short) Math.sqrt(currentNumber.shortValue()));
                } else if (currentNumber instanceof Integer) {
                    getNumbers().set(i, (int) Math.sqrt(currentNumber.intValue()));
                } else if (currentNumber instanceof Long) {
                    getNumbers().set(i, (long) Math.sqrt(currentNumber.longValue()));
                } else if (currentNumber instanceof Float) {
                    getNumbers().set(i, (float) Math.sqrt(currentNumber.floatValue()));
                } else if (currentNumber instanceof Double) {
                    getNumbers().set(i, Math.sqrt(currentNumber.doubleValue()));
                } else if (currentNumber instanceof BigInteger) {
                    int currSqrt = (int) Math.sqrt(currentNumber.intValue());
                    getNumbers().set(i, new BigInteger(Integer.toString(currSqrt)));
                } else if (currentNumber instanceof BigDecimal) {
                    double sqrt = Math.sqrt(currentNumber.doubleValue());
                    getNumbers().set(i, new BigDecimal(sqrt));
                }
            } else {
                negativePresent = true;
            }
        }
        if (negativePresent) {
            System.out.println("Negative numbers were not modified");
        }
        System.out.println(getNumbers().toString());
    }
}
