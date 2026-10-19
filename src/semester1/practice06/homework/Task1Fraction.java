package semester1.practice06.homework;

class Fraction {
    private int numerator;
    private int denominator = 1;

    public void setValues(int num, int den) {
        if (den == 0) {
            System.out.println("Помилка: знаменник не може дорівнювати 0! Встановлено 1.");
            den = 1;
        }
        if (den < 0) { num = -num; den = -den; }
        numerator = num;
        denominator = den;
    }

    private int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public void simplify() {
        int divisor = gcd(numerator, denominator);
        if (divisor > 0) {
            numerator /= divisor;
            denominator /= divisor;
        }
    }

    public double toDecimal() {
        return (double) numerator / denominator;
    }

    public void add(int otherNum, int otherDen) {
        if (otherDen == 0) {
            System.out.println("Помилка: не можна додати дріб зі знаменником 0!");
            return;
        }
        if (otherDen < 0) { otherNum = -otherNum; otherDen = -otherDen; }
        numerator = numerator * otherDen + otherNum * denominator;
        denominator *= otherDen;
        simplify();
    }

    public void printFraction() {
        if (denominator == 1) System.out.print(numerator);
        else System.out.print(numerator + "/" + denominator);
    }
}

public class Task1Fraction {
    public static void main(String[] args) {
        System.out.println("=== ЗВИЧАЙНІ ДРОБИ ===");
        Fraction first = new Fraction();
        first.setValues(8, 12);
        System.out.print("Перший дріб: ");
        first.printFraction();
        first.simplify();
        System.out.print("\nСкорочений: ");
        first.printFraction();
        System.out.printf(" = %.4f%n", first.toDecimal());
        System.out.println("Додаємо 3/4:");
        first.add(3, 4);
        System.out.print("Результат: ");
        first.printFraction();
        System.out.printf(" = %.4f%n", first.toDecimal());
        Fraction second = new Fraction();
        second.setValues(-10, -5);
        second.simplify();
        System.out.print("Другий дріб: ");
        second.printFraction();
        System.out.println();
    }
}
