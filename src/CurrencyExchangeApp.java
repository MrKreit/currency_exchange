import java.util.Scanner;
// Класс CurrencyExchangeApp представляет собой консольное приложение
// для взаимодействия с пользователем.
// Этот класс является частью пользовательского интерфейса и связывает методы из класса ExchangeManager
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
    System.out.println("3. Выход");
    System.out.print("Выберите опцию: ");
  }

  // public void performExchange(Scanner scanner) - предоставляет пользователю возможность
  // выполнить обмен валюты. Сначала отображает доступные валюты с их сокращениями,
  // а затем вызывает метод performExchange объекта ExchangeManager,
  // передавая ему сканнер для ввода данных пользователем.

  public void performExchange(Scanner scanner) {
    exchangeManager.displayCurrencyAbbreviations();
    exchangeManager.performExchange(scanner);
  }

  // public void viewExchangeHistory() - предоставляет пользователю возможность просмотреть историю обменов.
  // Вызывает метод viewExchangeHistory объекта ExchangeManager.

  public void viewExchangeHistory() {
    exchangeManager.viewExchangeHistory();
  }
}
