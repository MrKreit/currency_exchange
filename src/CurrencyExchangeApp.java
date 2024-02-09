import java.util.Scanner;

/* Класс CurrencyExchangeApp  содержит методы для отображения меню,
   выбора опций пользователем, просмотра Доступные валюты, выполнения обмена валюты
   и просмотра истории обменов.
 */
public class CurrencyExchangeApp {

  // Здесь создается экземпляр класса ExchangeManager и инициализируется в поле exchangeManager
  private final ExchangeManager exchangeManager = new ExchangeManager();

  // Этот метод отображает меню с опциями для пользователя.
  // Выводит на экран текст с номерами опций и приглашением выбрать опцию.
  public void displayMenu() {
    System.out.println("\nМеню:");
    System.out.println("1. Обмен валюты");
    System.out.println("2. История обменов");
    System.out.println("3. Выход");
    System.out.print("Выберите опцию: ");
  }

  // metod shows currencies
  // Итерируется по значениям перечисления CurrencyEnum и выводит их аббревиатуры и описания.
  /*
  Метод .name() возвращает строку с именем этой константы.
  Например, если у вас есть константа CurrencyEnum.USD, то CurrencyEnum.USD.name()
  вернет строку "USD"

  Метод values() возвращает массив, содержащий все значения перечисления в том порядке,
  в котором они объявлены.
  Этот метод часто используется в сочетании с циклом for-each для итерации по значениям перечисления.
  */
  public void displayCurrencyAbbreviations() {
    System.out.println("Доступные валюты:");
    for (CurrencyEnum currency : CurrencyEnum.values()) {
      System.out.println(currency.name() + ": " + currency.getDescription());
    }
  }

  /* Этот метод выполняет обмен валюты.
     Передает управление методу performExchange из объекта exchangeManager.
  */
  public void performExchange(Scanner scanner) {

    exchangeManager.performExchange(scanner);
  }

  /* Этот метод отображает историю обменов.Передает управление методу
     viewExchangeHistoryFromFile из объекта exchangeManager
  */
  public void viewExchangeHistory() {
    exchangeManager.viewExchangeHistoryFromFile();
  }

}