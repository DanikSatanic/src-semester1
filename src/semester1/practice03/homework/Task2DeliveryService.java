package semester1.practice03.homework;

import java.util.Scanner;

/**
 * Calculates the delivery price based on parcel and delivery details.
 */
public class Task2DeliveryService {
    private static final double PREMIUM_DISCOUNT_RATE = 0.20;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== ТАРИФІКАТОР ДОСТАВКИ ===");

            double weight = readWeight(scanner);
            int distance = readPositiveInt(scanner, "Відстань (км): ");
            int deliveryType = readChoice(
                    scanner,
                    "Доставка (1 - Відділення, 2 - Поштомат, 3 - Кур'єр): ",
                    1,
                    3
            );

            if (deliveryType == 2 && weight > 15.0) {
                System.out.println(
                        "Помилка: поштомат не приймає посилки важчі за 15 кг."
                );
                return;
            }

            boolean premium = readYesNo(scanner, "Є карта Premium (так/ні): ");

            String deliveryName;
            double basePrice;
            switch (deliveryType) {
                case 1:
                    deliveryName = "Відділення";
                    basePrice = 50.0;
                    break;
                case 2:
                    deliveryName = "Поштомат";
                    basePrice = 60.0;
                    break;
                default:
                    deliveryName = "Кур'єр";
                    basePrice = 100.0;
                    break;
            }

            double distanceFee;
            if (distance <= 50) {
                distanceFee = 0.0;
            } else if (distance <= 200) {
                distanceFee = 35.0;
            } else {
                distanceFee = 80.0;
            }

            double subtotal = basePrice + distanceFee;
            double discount = premium ? subtotal * PREMIUM_DISCOUNT_RATE : 0.0;
            double finalPrice = subtotal - discount;

            System.out.println("--- НАКЛАДНА ---");
            System.out.printf("Вага посилки: %.2f кг%n", weight);
            System.out.printf("Спосіб: %s; базовий тариф: %.2f грн%n",
                    deliveryName, basePrice);
            System.out.printf("Доплата за %d км: %.2f грн%n",
                    distance, distanceFee);
            System.out.printf("До знижки: %.2f грн%n", subtotal);
            System.out.printf("Знижка Premium: %.2f грн%n", discount);
            System.out.printf("РАЗОМ: %.2f грн%n", finalPrice);
        }
    }

    private static double readWeight(Scanner scanner) {
        while (true) {
            System.out.print("Вага (кг): ");
            if (scanner.hasNextDouble()) {
                double weight = scanner.nextDouble();
                if (weight > 0.0 && weight <= 50.0
                        && !Double.isInfinite(weight)) {
                    return weight;
                }
            } else {
                scanner.next();
            }
            System.out.println(
                    "Помилка: вага має бути більшою за 0 і не перевищувати 50 кг."
            );
        }
    }

    private static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value > 0) {
                    return value;
                }
            } else {
                scanner.next();
            }
            System.out.println("Помилка: введіть додатне ціле число.");
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

    private static boolean readYesNo(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String answer = scanner.next();
            if (answer.equalsIgnoreCase("так")) {
                return true;
            }
            if (answer.equalsIgnoreCase("ні")) {
                return false;
            }
            System.out.println("Помилка: введіть «так» або «ні».");
        }
    }
}
