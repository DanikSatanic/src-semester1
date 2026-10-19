package semester1.practice06.homework;

class QuadraticEquation {
    private double a;
    private double b;
    private double c;

    public void setCoefficients(double a, double b, double c) {
        if (a == 0) {
            System.out.println("Увага: коефіцієнт a не може дорівнювати 0. Використано a = 1.");
            a = 1.0;
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getDiscriminant() {
        return b * b - 4 * a * c;
    }

    public boolean hasRealRoots() {
        return getDiscriminant() >= 0;
    }

    public double getRoot1() {
        if (!hasRealRoots()) return Double.NaN;
        return (-b + Math.sqrt(getDiscriminant())) / (2 * a);
    }

    public double getRoot2() {
        if (!hasRealRoots()) return Double.NaN;
        return (-b - Math.sqrt(getDiscriminant())) / (2 * a);
    }

    public void printSolution() {
        System.out.println("Рівняння: " + a + "*x^2 + (" + b + ")*x + (" + c + ") = 0");
        double d = getDiscriminant();
        System.out.printf("Дискримінант D = %.2f%n", d);
        if (d > 0) {
            System.out.printf("Два корені: x1 = %.2f, x2 = %.2f%n", getRoot1(), getRoot2());
        } else if (d == 0) {
            System.out.printf("Один корінь: x = %.2f%n", getRoot1());
        } else {
            System.out.println("Рівняння не має дійсних коренів (D < 0).");
        }
    }
}

public class Task2QuadraticEquation {
    public static void main(String[] args) {
        System.out.println("=== РОЗВ'ЯЗУВАЧ КВАДРАТНИХ РІВНЯНЬ ===");
        QuadraticEquation equation = new QuadraticEquation();
        equation.setCoefficients(1, -5, 6);
        System.out.println("Тест 1:");
        equation.printSolution();
        equation.setCoefficients(1, -4, 4);
        System.out.println("Тест 2:");
        equation.printSolution();
        equation.setCoefficients(1, 2, 5);
        System.out.println("Тест 3:");
        equation.printSolution();
    }
}
