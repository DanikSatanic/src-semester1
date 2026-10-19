package semester1.practice04.homework;

import java.util.Scanner;

public class Task1RangeStatistics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        do {
            System.out.print("Введіть додатне N: ");
            n = scanner.nextInt();
            if (n <= 0) System.out.println("Помилка: N має бути більше 0!");
        } while (n <= 0);
        long sum = 0, evenSum = 0, oddSum = 0;
        int evenCount = 0, oddCount = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
            if (i % 2 == 0) { evenSum += i; evenCount++; }
            else { oddSum += i; oddCount++; }
        }
        double average = (double) sum / n;
        System.out.println("=== СТАТИСТИКА [1.." + n + "] ===");
        System.out.println("Загальна сума: " + sum);
        System.out.println("Парні: сума " + evenSum + ", кількість " + evenCount);
        System.out.println("Непарні: сума " + oddSum + ", кількість " + oddCount);
        System.out.printf("Середнє арифметичне: %.2f%n", average);
        scanner.close();
    }
}
