package semester1.practice03.homework;

import java.util.Scanner;

public class Task2DeliveryService {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== ТАРИФІКАТОР ДОСТАВКИ ===");
        System.out.print("Вага (кг): ");
        double weight = scanner.nextDouble();
        System.out.print("Відстань (км): ");
        int distance = scanner.nextInt();
        System.out.print("Доставка (1 - Відділення, 2 - Поштомат, 3 - Кур'єр): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // Прибираємо залишок рядка після числа.
        System.out.print("Є карта Premium (так/ні): ");
        String answer = scanner.nextLine();
        if (weight <= 0 || weight > 50) {
            System.out.println("Помилка: вага повинна бути від 0 до 50 кг (не включаючи 0).");
        } else if (distance <= 0) {
            System.out.println("Помилка: відстань має бути додатною.");
        } else {
            double basePrice = switch (type) {
                case 1 -> 50.0;
                case 2 -> {
                    if (weight > 15) {
                        System.out.println("Поштомат не приймає посилки важчі за 15 кг!");
                        yield -1.0;
                    }
                    yield 60.0;
                }
                case 3 -> 100.0;
                default -> -1.0;
            };
            if (basePrice < 0) {
                System.out.println("Доставка неможлива: некоректний тип або завелика вага.");
            } else {
                double distanceFee;
                if (distance <= 50) distanceFee = 0;
                else if (distance <= 200) distanceFee = 35;
                else distanceFee = 80;
                boolean premium = answer.equalsIgnoreCase("так");
                double total = basePrice + distanceFee;
                double finalPrice = premium ? total * 0.8 : total;
                String deliveryName = switch (type) {
                    case 1 -> "Відділення";
                    case 2 -> "Поштомат";
                    default -> "Кур'єр";
                };
                System.out.println("--- НАКЛАДНА ---");
                System.out.printf("Спосіб: %s; базовий тариф: %.2f грн%n", deliveryName, basePrice);
                System.out.printf("Доплата за %d км: %.2f грн%n", distance, distanceFee);
                System.out.printf("До знижки: %.2f грн%n", total);
                System.out.println("Premium: " + (premium ? "так (-20%)" : "ні"));
                System.out.printf("РАЗОМ: %.2f грн%n", finalPrice);
            }
        }
        scanner.close();
    }
}
