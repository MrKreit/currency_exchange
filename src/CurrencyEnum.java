/**
 * Перечисление CurrencyEnum представляет различные валюты,
 * которые поддерживаются в приложении.
 */
public enum CurrencyEnum {
  USD("Доллар США"),
  EUR("Евро"),
  GBP("Фунт стерлингов"),
  CHF("Швейцарский франк"),
  PLN("Польская злота"),
  CZK("Чешская крона");

  // Описание валюты.



  private final String description;

  /**
   * Конструктор для создания элемента перечисления с указанным описанием.
   *
   * @param description Описание валюты.
   */

  CurrencyEnum(String description) {
    this.description = description;
  }

  /**
   * Метод для получения описания валюты.
   *
   * @return Описание валюты.
   */

  public String getDescription() {
    return description;
  }
}
