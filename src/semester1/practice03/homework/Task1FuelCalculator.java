package semester1.practice03.homework;

import java.util.Scanner;

/**
 * Calculates the fuel needed for a trip and its estimated price.
 */
public class Task1FuelCalculator {
    private static final double DISCOUNT_THRESHOLD = 2000.0;
    private static final double DISCOUNT_RATE = 0.05;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== КАЛЬКУЛЯТОР ПОЇЗДКИ ТА ПАЛЬНОГО ===");

            double distance = readPositiveDouble(scanner, "Відстань (км): ");
            double fuelConsumption =
                    readPositiveDouble(scanner, "Витрата на 100 км (л): ");
            int fuelType = readChoice(
                    scanner,
                    "Пальне (1 - А-95, 2 - Дизель, 3 - Газ): ",
                    1,
                    3
            );

            String fuelName;
            double pricePerLiter;
            switch (fuelType) {
                case 1:
                    fuelName = "Бензин А-95";
                    pricePerLiter = 56.50;
                    break;
                case 2:
                    fuelName = "Дизель";
                    pricePerLiter = 52.80;
                    break;
                default:
                    fuelName = "Газ";
                    pricePerLiter = 29.40;
                    break;
            }

            double neededFuel = distance * fuelConsumption / 100.0;
            double baseCost = neededFuel * pricePerLiter;
            double discount = baseCost >= DISCOUNT_THRESHOLD
                    ? baseCost * DISCOUNT_RATE
                    : 0.0;
            double finalCost = baseCost - discount;

            System.out.println("--- ЗВІТ ПОЇЗДКИ ---");
            System.out.printf("Пальне: %s (%.2f грн/л)%n", fuelName, pricePerLiter);
            System.out.printf("Необхідно пального: %.2f л%n", neededFuel);
            System.out.printf("Базова вартість: %.2f грн%n", baseCost);
            System.out.printf("Знижка: %.2f грн%n", discount);
            System.out.printf("РАЗОМ: %.2f грн%n", finalCost);
        }
    }

    private static double readPositiveDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                if (value > 0.0 && !Double.isInfinite(value)) {
                    return value;
                }
            } else {
                scanner.next();
            }
            System.out.println("Помилка: введіть додатне число.");
        }
    }

    private static int readChoice(
            Scanner scanner, String prompt, int minimum, int maximum) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice >= minimum && choice <= maximum) {
                    return choice;
                }
            } else {
                scanner.next();
            }
            System.out.printf(
                    "Помилка: виберіть число від %d до %d.%n",
                    minimum,
                    maximum
            );
        }
    }
}
