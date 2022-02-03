package numbers.vsevolod_mikulik;

import numbers.do_not_change.NumberCreator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class MyNumberCreator extends NumberCreator {

    private int amountOfNumbers;
    private int subclassesOfNumber = 8;

    /**
     * This method allows to choose the size of list
     * with numbers
     *
     * @return the quantity of numbers in the list
     */
    @Override
    public int validateAndSetNumberQuantity() {
        Scanner in = new Scanner(System.in);
        int userInput = 0;
        while (!quantityIsInRange(userInput)) {
            System.out.println("Enter the desired amount of numbers in the list (" + MIN_NUMBER_QUANTITY + " - " +
                    MAX_NUMBER_QUANTITY + "): ");

            try {
                userInput = in.nextInt();
            } catch (InputMismatchException exception) {
                System.out.println("It seems that you have typed something mysterious ( not an integer :) )\n" +
                        "Press enter to try again...");
                waitForEnter(in);
                continue;
            }

            if (!quantityIsInRange(userInput)) {
                System.out.println("The number you have typed is out of range. Press enter to try again...");
                waitForEnter(in);
            } else {
                amountOfNumbers = userInput;
                System.out.println("The amount of numbers in the list was set to "
                        + amountOfNumbers + "\nPress enter to continue...");
                waitForEnter(in);
            }
        }
        return userInput;
    }

    /**
     * This method allows generating the list of numbers
     * of different types
     *
     * @param numberQuantity the quantity of numbers in the list
     * @return the list of generated numbers
     */
    @Override
    public List<Number> generateNumbers(int numberQuantity) {

        List<Number> randomNumbers = new ArrayList<>();

        if (quantityIsInRange(numberQuantity)) {

            Random random = new Random();

            for (int i = 0; i < numberQuantity; i++) {
                int typeToGenerate = random.nextInt(subclassesOfNumber);
                Number newNumber;
                switch (typeToGenerate) {
                    case 1:
                        newNumber = (short) (random.nextInt(NUMBER_UPPER_BOUNDARY - NUMBER_LOWER_BOUNDARY)
                                + NUMBER_LOWER_BOUNDARY);
                        break;
                    case 2:
                        newNumber = random.nextInt(NUMBER_UPPER_BOUNDARY - NUMBER_LOWER_BOUNDARY)
                                + NUMBER_LOWER_BOUNDARY;
                        break;
                    case 3:
                        newNumber = (long) random.nextInt(NUMBER_UPPER_BOUNDARY - NUMBER_LOWER_BOUNDARY)
                                + NUMBER_LOWER_BOUNDARY;
                        break;
                    case 4:
                        newNumber = random.nextFloat() * (NUMBER_UPPER_BOUNDARY - NUMBER_LOWER_BOUNDARY)
                                + NUMBER_LOWER_BOUNDARY;
                        break;
                    case 5:
                        newNumber = random.nextDouble() * (NUMBER_UPPER_BOUNDARY - NUMBER_LOWER_BOUNDARY)
                            + NUMBER_LOWER_BOUNDARY;
                        break;
                    case 6:
                        newNumber = new BigInteger(Integer.toString(random.nextInt(NUMBER_UPPER_BOUNDARY
                                - NUMBER_LOWER_BOUNDARY)
                                + NUMBER_LOWER_BOUNDARY));
                        break;
                    case 7:
                        newNumber = new BigDecimal(random.nextDouble()
                                * (NUMBER_UPPER_BOUNDARY - NUMBER_LOWER_BOUNDARY)
                                + NUMBER_LOWER_BOUNDARY);
                        break;
                    default:
                        newNumber = (byte) (random.nextInt(NUMBER_UPPER_BOUNDARY - NUMBER_LOWER_BOUNDARY)
                                + NUMBER_LOWER_BOUNDARY);
                        break;
                }
                randomNumbers.add(newNumber);
            }
            System.out.println("The list of numbers with the size of " + numberQuantity + " has been generated");
            System.out.println(randomNumbers.toString());
        } else {
            System.out.println("The quantity is out of range. Try again.");
        }
        return randomNumbers;
    }

    /**
     * This method allows to insert numbers into the list
     * of randomly chosen types. The user has to print the
     * values via console. The incorrectly printed data has
     * to be handled
     *
     * @param numberQuantity the amount of numbers in the list
     * @return the list of numbers inserted by user
     */
    @Override
    public List<Number> insertNumbers(int numberQuantity) {
        Scanner in = new Scanner(System.in);
        List<Number> numberList = new ArrayList<>();
        Random random = new Random();

        if (quantityIsInRange(numberQuantity)) {
            for (int i = 0; i < numberQuantity; i++) {
                int newNumberType = random.nextInt(subclassesOfNumber);
                numberList.add(getUserNumber(in, NumberTypes.values()[newNumberType]));
            }
        }
        return numberList;
    }

    /**
     * This method makes the program wait for the user to press enter
     * @param in Scanner which is currently used to read user input
     */
    private void waitForEnter(Scanner in) {
        in.nextLine();
        in.nextLine();
    }

    /**
     * This method checks that the received quantity is in the allowed range
     * @param quantity the amount of numbers in the list
     * @return true if the quantity is in range
     */
    public boolean quantityIsInRange(int quantity) {
        if (quantity >= MIN_NUMBER_QUANTITY && quantity <= MAX_NUMBER_QUANTITY) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method checks that the received number is in the allowed range
     * @param number number that has to be added to the list
     * @return true if the number is in range
     */
    public boolean numberIsInRange(double number) {
        if (number >= NUMBER_LOWER_BOUNDARY && number <= NUMBER_UPPER_BOUNDARY) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Handles user input
     * @param type Class of the number
     * @return user input as instance of Number
     */
    private Number getUserNumber(Scanner in, NumberTypes type) {
        Number result = -1;
        // String userInput = "";

        boolean isSuccessful = false;

        while (!isSuccessful) {

            System.out.println("Type in a new " + type + ": ");
            try {
                Number curr = NUMBER_LOWER_BOUNDARY - 1;
                switch (type) {
                    case BYTE:
                        curr = in.nextByte();
                        break;
                    case SHORT:
                        curr = in.nextShort();
                        break;
                    case INTEGER:
                        curr = in.nextInt();
                        break;
                    case LONG:
                        curr = in.nextLong();
                        break;
                    case FLOAT:
                        curr = in.nextFloat();
                        break;
                    case DOUBLE:
                        curr = in.nextDouble();
                        break;
                    case BIG_INTEGER:
                        String userInput = in.next();
                        curr = new BigInteger(userInput);
                        break;
                    case BIG_DECIMAL:
                        userInput = in.next();
                        curr = new BigDecimal(userInput);
                        break;
                }
                if (numberIsInRange(curr.doubleValue())) {
                    result = curr;
                    isSuccessful = true;
                } else {
                    System.out.println("The number is out of range! Try again.");
                }
            } catch (InputMismatchException | NumberFormatException exception) {
                System.out.println("You've typed something mysterious (String instead of a number or a number " +
                        "which is out of range). Try again.\n");
                in.next();
            }
        }
        return result;
    }
}

/**
 * This Enum is used to make working with different types of numbers easier
 */
enum NumberTypes {
    BYTE,
    SHORT,
    INTEGER,
    LONG,
    FLOAT,
    DOUBLE,
    BIG_INTEGER,
    BIG_DECIMAL
}
