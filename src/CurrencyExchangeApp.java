import java.util.Scanner;

// Класс CurrencyExchangeApp представляет собой консольное приложение
// для взаимодействия с пользователем.
// Этот класс является частью пользовательского интерфейса и связывает
// методы из класса ExchangeManager
// с консольным вводом/выводом, обеспечивая взаимодействие пользователя с функциональностью обмена валют.
public class CurrencyExchangeApp {

  // private final ExchangeManager exchangeManager = new ExchangeManager();
  // - создает объект ExchangeManager, который будет использоваться для управления обменом валют
  // и хранения истории обменов.
  // Этот объект инициализируется в конструкторе класса.
  private final ExchangeManager exchangeManager = new ExchangeManager();

  // public void displayMenu() - выводит меню приложения на экран. Пользователь видит опции для выбора: обмен валюты,
  // просмотр истории обменов или выход из приложения.

  public void displayMenu() {
    System.out.println("\nМеню:");
    System.out.println("1. Обмен валюты");
    System.out.println("2. Просмотр истории обменов");
    System.out.println("3. Сохранить историю в файл");
    System.out.println("4. Выход");
    System.out.print("Выберите опцию: ");
  }
  // add
  public void saveHistoryToFile(Scanner scanner) {
    System.out.print("Введите имя файла для сохранения истории: ");
    String fileName = scanner.next();
    exchangeManager.saveHistoryToFile(fileName);
    System.out.println("История успешно сохранена в файле " + fileName);
  }

  // public void performExchange(Scanner scanner) - предоставляет пользователю возможность
  // выполнить обмен валюты. Сначала отображает доступные валюты с их сокращениями,
  // а затем вызывает метод performExchange объекта ExchangeManager,
  // передавая ему сканнер для ввода данных пользователем.

  public void performExchange(Scanner scanner) {
    exchangeManager.displayCurrencyAbbreviations();
    exchangeManager.performExchange(scanner);
    int choice = scanner.nextInt();
    scanner.nextLine();
    switch (choice) {
      case 1:
        exchangeManager.performExchange(scanner);
        break;
      case 2:
        exchangeManager.viewExchangeHistory();
        break;
      case 3:
        saveHistoryToFile(scanner);
        break;
      case 4:
        System.out.println("Выход из программы. До свидания!");
        System.exit(0);
        break;
      default:
        System.out.println("Неверный выбор. Пожалуйста, выберите снова.");
    }
  }

  // public void viewExchangeHistory() - предоставляет пользователю возможность просмотреть историю обменов.
  // Вызывает метод viewExchangeHistory объекта ExchangeManager.

  public void viewExchangeHistory() {
      exchangeManager.viewExchangeHistory();
  }
}
