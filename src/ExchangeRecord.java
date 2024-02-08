import java.text.DecimalFormat;
import java.util.Date;

// Определяем класс ExchangeRecord, который будет использоваться для хранения информации о конкретной операции обмена валют
public class ExchangeRecord {

  // Объявляем приватное поле date типа Date для хранения даты операции обмена
  private final Date date;
  // Объявляем приватное поле amount типа double для хранения суммы, которая обменивается
  private final double amount;
  // Объявляем приватное поле sourceCurrency типа CurrencyEnum для хранения исходной валюты операции
  private final CurrencyEnum sourceCurrency;
  // Объявляем приватное поле targetCurrency типа CurrencyEnum для хранения целевой валюты операции
  private final CurrencyEnum targetCurrency;
  // Объявляем приватное поле resultAmount типа double для хранения результата обмена в целевой валюте
  private final double resultAmount;
  // Объявляем и инициализируем поле decimalFormat для форматирования вывода суммы результата с двумя знаками после запятой
  private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

  // Конструктор класса ExchangeRecord, который принимает параметры для инициализации всех полей класса
  public ExchangeRecord(Date date, double amount, CurrencyEnum sourceCurrency,
      CurrencyEnum targetCurrency, double resultAmount) {
    // Инициализация поля date
    this.date = date;
    // Инициализация поля amount
    this.amount = amount;
    // Инициализация поля sourceCurrency
    this.sourceCurrency = sourceCurrency;
    // Инициализация поля targetCurrency
    this.targetCurrency = targetCurrency;
    // Инициализация поля resultAmount
    this.resultAmount = resultAmount;
  }

  // Переопределение метода toString() класса Object для предоставления строкового представления объекта ExchangeRecord
  @Override
  public String toString() {
    // Формируем и возвращаем строку, содержащую информацию об операции обмена,
    // включая дату, сумму в исходной валюте, сумму в целевой валюте и названия валют.
    // Используем decimalFormat для форматирования суммы результата.
    return "Дата: " + date + ", Сумма: " + amount + " " + sourceCurrency +
        " -> " + decimalFormat.format(resultAmount) + " " + targetCurrency.getDescription();
  }
}

// Класс ExchangeRecord предназначен для создания объектов, которые могут хранить информацию
// о конкретной операции обмена валют, включая дату операции, сумму для обмена, исходную валюту,
// целевую валюту и результат обмена в целевой валюте. В методе toString() формируется
// строковое представление объекта, которое может быть использовано для вывода
// информации об операции в удобочитаемом формате.