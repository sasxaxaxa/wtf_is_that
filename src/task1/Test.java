package task1;

public class Test {
    public static void main(String[] args) {
        runTest("Тест 1", "I + I", "II");
        runTest("Тест 2", "X - IV", "VI");
        runTest("Тест 3", "V - III", "II");
        runTest("Тест 4", "II * III", "VI");
        runTest("Тест 5", "X / II", "V");
        runTest("Тест 6", "X * IX", "XC");
        runTest("Тест 7", "IV / II", "II");
        runTest("Тест 8", "IX + V", "XIV");
        runTest("Тест 9", "X - I", "IX");
        runTest("Тест 10", "I + III", "IV");
        runTest("Тест 11", "V - II", "III");
        runTest("Тест 12", "V / II", "II");
    }

    // Метод для обычных тестов
    private static void runTest(String testName, String input, String expectedOutput) {
        try {
            System.out.println(testName);
            String result = Calculator.calculate(input);
            if (result.equals(expectedOutput)) {
                System.out.println("Тест пройден успешно!");
            } else {
                System.out.println("Ошибка: результат не совпадает с ожидаемым!");
                System.out.println("Получено: " + result);
                System.out.println("Ожидаемый результат: " + expectedOutput);
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            System.out.println("Ожидаемый результат: " + expectedOutput);
            System.out.println("Тест не пройден.");
        }
        System.out.println();
    }
}
