import java.util.Scanner;

// Класс CurrencyExchangeApp представляет собой консольное приложение
// для взаимодействия с пользователем.
// Этот класс является частью пользовательского интерфейса и связывает
// методы из класса ExchangeManager
// с консольным вводом/выводом, обеспечивая взаимодействие пользователя с функциональностью обмена валют.

// private final ExchangeManager exchangeManager = new ExchangeManager();
// - создает объект ExchangeManager, который будет использоваться для управления обменом валют
// и хранения истории обменов.
// Этот объект инициализируется в конструкторе класса.


// public void displayMenu() - выводит меню приложения на экран. Пользователь видит опции для выбора: обмен валюты,
// просмотр истории обменов или выход из приложения.
public class CurrencyExchangeApp {

  private final ExchangeManager exchangeManager = new ExchangeManager();

  public void displayMenu() {
    System.out.println("\nМеню:");
    System.out.println("1. Обмен валюты");
    System.out.println("2. История обменов");
    System.out.println("3. Выход");
    System.out.print("Выберите опцию: ");
  }
  public void displayCurrencyAbbreviations() {
    System.out.println("Доступные валюты:");
    for (CurrencyEnum currency : CurrencyEnum.values()) {
      System.out.println(currency.name() + ": " + currency.getDescription());
    }
  }

  public void performExchange(Scanner scanner) {

    exchangeManager.performExchange(scanner);
  }

  public void viewExchangeHistory() {
    exchangeManager.viewExchangeHistoryFromFile();
  }
}