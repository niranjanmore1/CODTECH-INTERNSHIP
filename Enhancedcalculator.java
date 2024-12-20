import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

public class Enhancedcalculator {

    private static final List<String> history = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printHeader();

        int calculationCount = 0;

        while (true) {
            System.out.println("\nNew Calculation:");
            System.out.println("----------------");

            // Input: First number
            double num1 = getNumber(scanner, "Enter the first number: ");

            // Display operation menu
            System.out.println("\nSelect an operation:");
            displayOperations();

            // Input: Operation choice
            int choice = getOperationChoice(scanner);

            // Perform calculation
            double result = 0;
            boolean valid = true;
            double num2 = 0; // To store the second input number if needed

            switch (choice) {
                case 1: // Addition
                    num2 = getNumber(scanner, "Enter the second number: ");
                    result = num1 + num2;
                    addToHistory(num1, num2, result, "+");
                    break;
                case 2: // Subtraction
                    num2 = getNumber(scanner, "Enter the second number: ");
                    result = num1 - num2;
                    addToHistory(num1, num2, result, "-");
                    break;
                case 3: // Multiplication
                    num2 = getNumber(scanner, "Enter the second number: ");
                    result = num1 * num2;
                    addToHistory(num1, num2, result, "*");
                    break;
                case 4: // Division
                    num2 = getNumber(scanner, "Enter the second number: ");
                    if (num2 == 0) {
                        System.out.println("Error: Division by zero is not allowed.");
                        valid = false;
                    } else {
                        result = num1 / num2;
                        addToHistory(num1, num2, result, "/");
                    }
                    break;
                case 5: // Modulus
                    num2 = getNumber(scanner, "Enter the second number: ");
                    if (num2 == 0) {
                        System.out.println("Error: Division by zero is not allowed.");
                        valid = false;
                    } else {
                        result = num1 % num2;
                        addToHistory(num1, num2, result, "%");
                    }
                    break;
                case 6: // Power
                    num2 = getNumber(scanner, "Enter the exponent: ");
                    result = Math.pow(num1, num2);
                    addToHistory(num1, num2, result, "^");
                    break;
                case 7: // Square Root
                    if (num1 < 0) {
                        System.out.println("Error: Cannot calculate square root of a negative number.");
                        valid = false;
                    } else {
                        result = Math.sqrt(num1);
                        addToHistory(num1, result, "√");
                    }
                    break;
                default:
                    System.out.println("Error: Invalid operation choice.");
                    valid = false;
            }

            if (valid) {
                if (choice == 7) { // Special case for square root
                    System.out.printf("\nResult: √%.2f = %.2f\n", num1, result);
                } else {
                    System.out.printf("\nResult: %.2f %s %.2f = %.2f\n", num1, getOperator(choice), num2, result);
                }
                calculationCount++;
            }

            // Option to continue
            System.out.print("\nWould you like to perform another calculation? (yes/no): ");
            String continueChoice = scanner.nextLine().trim().toLowerCase();
            if (!continueChoice.equals("yes")) {
                break;
            }
        }

        // Display history
        printHistory(calculationCount);

        printFooter();
        scanner.close();
    }

    private static void printHeader() {
        System.out.println("========================================");
        System.out.println("       Welcome to Enhanced Calculator");
        System.out.println("========================================");
    }

    private static void printFooter() {
        System.out.println("\n========================================");
        System.out.println("      Thank you for using our tool!");
        System.out.println("========================================");
    }

    private static void displayOperations() {
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.println("5. Modulus (%)");
        System.out.println("6. Power (^)");
        System.out.println("7. Square Root (√)");
        System.out.print("Enter your choice (1-7): ");
    }

    private static double getNumber(Scanner scanner, String prompt) {
        double number;
        while (true) {
            try {
                System.out.print(prompt);
                number = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return number;
    }

    private static int getOperationChoice(Scanner scanner) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 7) {
                    break;
                } else {
                    System.out.print("Invalid choice. Please select a number between 1 and 7: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number between 1 and 7: ");
            }
        }
        return choice;
    }

    private static String getOperator(int choice) {
        switch (choice) {
            case 1: return "+";
            case 2: return "-";
            case 3: return "*";
            case 4: return "/";
            case 5: return "%";
            case 6: return "^";
            case 7: return "√";
            default: return "";
        }
    }

    private static void addToHistory(double num1, double num2, double result, String operator) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DayOfWeek dayOfWeek = now.getDayOfWeek();

        String date = now.format(dateFormatter);
        String time = now.format(timeFormatter);

        history.add(String.format(
            "Operation: %.2f %s %.2f = %.2f\n    Time: %s\n    Day: %s\n    Date: %s",
            num1, operator, num2, result, time, dayOfWeek, date
        ));
    }

    private static void addToHistory(double num1, double result, String operator) { // Overloaded for square root
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DayOfWeek dayOfWeek = now.getDayOfWeek();

        String date = now.format(dateFormatter);
        String time = now.format(timeFormatter);

        history.add(String.format(
            "Operation: %s%.2f = %.2f\n    Time: %s\n    Day: %s\n    Date: %s",
            operator, num1, result, time, dayOfWeek, date
        ));
    }

    private static void printHistory(int calculationCount) {
        System.out.println("\n========================================");
        System.out.println("           Calculation History");
        System.out.println("========================================");
        if (history.isEmpty()) {
            System.out.println("No calculations performed yet.");
        } else {
            System.out.printf("Total calculations performed: %d\n", calculationCount);
            for (int i = 0; i < history.size(); i++) {
                System.out.printf("[%d] %s\n", i + 1, history.get(i));
            }
        }
    }
}
