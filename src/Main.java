import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    CurrencyExchangeApp currencyExchangeApp = new CurrencyExchangeApp();

    try {
      Scanner scanner = new Scanner(System.in);

      while (true) {
        currencyExchangeApp.displayMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
          case 1 -> currencyExchangeApp.performExchange(scanner);
          case 2 -> currencyExchangeApp.viewExchangeHistory();
          case 3 -> {
            System.out.println("Выход из программы. До свидания!");
            System.exit(0);
          }
          default -> System.out.println("Неверный выбор. Пожалуйста, выберите снова.");
        }
      }
    } catch (InputMismatchException e) {
      System.out.println("Ошибка ввода. Введите число.");
    }
  }
}