package semester1.practice11.homework;

import java.util.ArrayList;
import java.util.List;

public class Task1WrapperAndAutoboxingMemory {

    public static void inspectIntegerCache(int[] testValues) {
        System.out.println("[1. ЕКСПЕРИМЕНТ: INTEGER CACHE [-128..127] (== проти .equals())]:");
        for (int i = 0; i < testValues.length; i++) {
            int val = testValues[i];
            Integer first = Integer.valueOf(val);
            Integer second = Integer.valueOf(val);

            boolean sameReference = first == second;
            boolean sameValue = first.equals(second);

            String source;
            if (val >= -128 && val <= 127) {
                source = "IntegerCache";
            } else {
                source = "Новий об'єкт у Heap!";
            }

            System.out.printf(
                    "  Значення: %5d | (a == b): %-5b | a.equals(b): %-5b | Джерело: %s%n",
                    val,
                    sameReference,
                    sameValue,
                    source
            );
        }
    }

    public static void demonstrateAutoboxingUnderTheHood() {
        System.out.println("[2. AUTOBOXING / UNBOXING ПІД КАПОТОМ ТА ЗАХИСТ ВІД NULL]:");

        Integer autoInt = 42;
        Integer manualInt = Integer.valueOf(42);
        int unboxedInt = manualInt.intValue();
        System.out.println("  Явне пакування: Integer.valueOf(42) -> " + manualInt
                + " | Розпакування: .intValue() -> " + unboxedInt
                + " | Автопакування дає те саме: " + autoInt.equals(manualInt));

        Double autoDouble = 3.75;
        Double manualDouble = Double.valueOf(3.75);
        double unboxedDouble = manualDouble.doubleValue();
        System.out.println("  Явне пакування: Double.valueOf(3.75) -> " + manualDouble
                + " | Розпакування: .doubleValue() -> " + unboxedDouble
                + " | Автопакування дає те саме: " + autoDouble.equals(manualDouble));

        Boolean autoBoolean = true;
        Boolean manualBoolean = Boolean.valueOf(true);
        boolean unboxedBoolean = manualBoolean.booleanValue();
        System.out.println("  Явне пакування: Boolean.valueOf(true) -> " + manualBoolean
                + " | Розпакування: .booleanValue() -> " + unboxedBoolean
                + " | Автопакування дає те саме: " + autoBoolean.equals(manualBoolean));

        List<Integer> sensorValues = new ArrayList<>();
        sensorValues.add(45);
        sensorValues.add(null);
        sensorValues.add(-12);
        sensorValues.add(88);
        sensorValues.add(null);
        sensorValues.add(102);
        System.out.println("  Вхідний список із датчиків (з null): " + sensorValues);

        int sum = 0;
        String skippedIndexes = "";
        for (int i = 0; i < sensorValues.size(); i++) {
            Integer value = sensorValues.get(i);
            if (value == null) {
                if (skippedIndexes.isEmpty()) {
                    skippedIndexes = String.valueOf(i);
                } else {
                    skippedIndexes += " та " + i;
                }
                continue;
            }
            sum += value.intValue();
        }

        System.out.println("  ⚠️ Пропущено null-елемент на індексі " + skippedIndexes
                + " (запобігання NullPointerException при intValue()!)");
        System.out.println("  Сума валідних розпакованих елементів: " + sum);
    }

    public static List<Integer> parseTelemetryStream(String[] rawPackets) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < rawPackets.length; i++) {
            String packet = rawPackets[i];
            if (packet == null || packet.trim().isEmpty()) {
                System.out.println("  ❌ Порожній або null-пакет відхилено");
                continue;
            }
            String cleaned = packet.trim();
            try {
                int value = Integer.parseInt(cleaned);
                result.add(value);
            } catch (NumberFormatException e) {
                System.out.println("  ❌ Пакет '" + cleaned + "' відхилено (NumberFormatException)");
            }
        }
        return result;
    }

    public static void printExtremesAndStats(List<Integer> validData) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        long sum = 0;

        for (int i = 0; i < validData.size(); i++) {
            int value = validData.get(i);
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
            sum += value;
        }

        System.out.println("  Успішно розпізнаний список Integer: " + validData);
        System.out.println("  Знайдений мінімум: " + min + " | Знайдений максимум: " + max);
        System.out.println("  Сума елементів (long, щоб не було переповнення int): " + sum);

        List<Integer> byIndex = new ArrayList<>(validData);
        byIndex.remove(0);
        System.out.println("  Після remove(0) (за індексом): " + byIndex);

        List<Integer> byValue = new ArrayList<>(validData);
        byValue.remove(Integer.valueOf(-30));
        System.out.println("  Після remove(Integer.valueOf(-30)) (за значенням): " + byValue);
    }

    public static void main(String[] args) {
        System.out.println("================ ДОСЛІДЖЕННЯ WRAPPER CLASSES ТА INTEGER CACHE ================");

        int[] testValues = {-128, -50, 0, 127, 128, 500, 2026};
        inspectIntegerCache(testValues);

        System.out.println();
        demonstrateAutoboxingUnderTheHood();

        System.out.println();
        System.out.println("[3. ПАРСИНГ РЯДКІВ (Integer.parseInt) ТА ЕКСТРЕМУМИ (MAX_VALUE / MIN_VALUE)]:");
        System.out.println("  Початкові межі пошуку: min = " + Integer.MAX_VALUE
                + " (MAX_VALUE), max = " + Integer.MIN_VALUE + " (MIN_VALUE)");
        String[] rawPackets = {"  145 ", "-30", "ERR_404", "980", "", null, "2147483647", "-85"};
        List<Integer> validData = parseTelemetryStream(rawPackets);
        printExtremesAndStats(validData);
        System.out.println("==============================================================================");
    }
}
