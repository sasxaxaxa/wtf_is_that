package task1;

import java.util.Map;

public class Calculator {
  private static final Map<String, Integer> romanArabic = Map.of(
      "I", 1,
      "II", 2,
      "III", 3,
      "IV", 4,
      "V", 5,
      "VI", 6,
      "VII", 7,
      "VIII", 8,
      "IX", 9,
      "X", 10
  );

  // Римские числа с учётом сокращений
  private static final int[] arabicValues = {100, 90, 50, 40, 10, 9, 5, 4, 1};
  private static final String[] romanSymbols = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

  public static int addition(int first, int second) {
    return first + second;
  }

  public static int subtraction(int first, int second) {
    return first - second;
  }

  public static int multiplication(int first, int second) {
    return first * second;
  }

  //Результатом операции деления является целое число, остаток отбрасывается.
  public static int division(int first, int second) throws ArithmeticException {
    if (second == 0) {
      throw new ArithmeticException("Деление на 0");
    }

    return first / second;
  }

  public static int getRimToArab(String rimNum) {
    int count = 0;
    Map<String, Integer> romanArabic = Map.of(
        "I", 1,
        "V", 5,
        "X", 10,
        "L", 50,
        "C", 100
    );

    for (int i = 0; i < rimNum.length(); i++) {
      int current = romanArabic.get(String.valueOf(rimNum.charAt(i)));
      if (i != rimNum.length() - 1) {
        if (current < romanArabic.get(String.valueOf(rimNum.charAt(i + 1)))) {
          count -= current;
        } else {
          count += current;
        }
      } else {
        count += current;
      }
    }
    return count;

  }

  public static String getArabToRim(int arabNum) {
    if (arabNum < 1 || arabNum > 100) {
      throw new UncorrectInputException("Некорректный ответ для римских чисел");
    }

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < arabicValues.length; i++) {
      while (arabNum >= arabicValues[i]) {
        arabNum -= arabicValues[i];
        sb.append(romanSymbols[i]);
      }
    }

    return sb.toString();
  }

  public static String calculate(String str) {
    str = str.trim();

    String[] list = str.split(" ");

    if (list.length != 3) {
      throw new UncorrectInputException("Некорректный ввод");
    }

    String first = list[0];
    String second = list[2];
    String operation = list[1];

    boolean flag = romanArabic.containsKey(first) && romanArabic.containsKey(second);

    if (!flag) {
      // Арабские числа
      try {
        int firstNum = Integer.parseInt(first);
        int secondNum = Integer.parseInt(second);

        return String.valueOf(resultOfCalc(firstNum, secondNum, operation));
      } catch (NumberFormatException e) {
        throw new UncorrectInputException("Некорректные числа", e);
      }
    } else {
      // Римские числа
      int firstNum = getRimToArab(first);
      int secondNum = getRimToArab(second);

      return getArabToRim(resultOfCalc(firstNum, secondNum, operation));
    }
  }

  public static int resultOfCalc(int firstNum, int secondNum, String operation) {
    return switch (operation) {
      case "+" -> addition(firstNum, secondNum);
      case "-" -> subtraction(firstNum, secondNum);
      case "*" -> multiplication(firstNum, secondNum);
      case "/" -> division(firstNum, secondNum);
      default -> throw new UncorrectInputException("Некорректная операция");
    };
  }

}

