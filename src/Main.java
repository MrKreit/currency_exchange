import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    CurrencyExchangeApp currencyExchangeApp = new CurrencyExchangeApp();
    Scanner scanner = new Scanner(System.in);
    currencyExchangeApp.displayCurrencyAbbreviations();
    try {
      while (true) {
        currencyExchangeApp.displayMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
          case 1:
            currencyExchangeApp.performExchange(scanner);
            break;
          case 2:
            currencyExchangeApp.viewExchangeHistory();
            break;
          case 3: {
            System.out.println("Выход из программы. До свидания!");
            System.exit(0);
            break;
          }
          default:
            System.out.println("Неверный выбор. Пожалуйста, выберите снова.");
            break;
        }
      }
    } catch (InputMismatchException e) {
      System.out.println("Ошибка ввода. Введите число.");
    }
  }

}
