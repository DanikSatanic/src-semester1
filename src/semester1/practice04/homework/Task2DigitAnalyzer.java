package semester1.practice04.homework;

import java.util.Scanner;

public class Task2DigitAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть додатне ціле число: ");
        int number = scanner.nextInt();
        if (number <= 0) {
            System.out.println("Помилка: число має бути додатним.");
            scanner.close();
            return;
        }
        int originalNumber = number;
        int temp = number;
        int count = 0, digitSum = 0, largestDigit = 0;
        long reversedNumber = 0;
        while (temp > 0) {
            int digit = temp % 10;
            count++;
            digitSum += digit;
            if (digit > largestDigit) largestDigit = digit;
            reversedNumber = reversedNumber * 10 + digit;
            temp /= 10;
        }
        System.out.println("--- СТАТИСТИКА ---");
        System.out.println("Початкове: " + originalNumber);
        System.out.println("Кількість цифр: " + count);
        System.out.println("Сума цифр: " + digitSum);
        System.out.println("Найбільша цифра: " + largestDigit);
        System.out.println("Реверс: " + reversedNumber);
        System.out.println(originalNumber == reversedNumber ? "Це паліндром!" : "Це не паліндром.");
        scanner.close();
    }
}
