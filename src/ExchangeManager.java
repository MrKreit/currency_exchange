import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class ExchangeManager {
  // Хранит текущие курсы обмена валюты
  // Эта строка является объявлением и инициализацией поля класса ExchangeManager с именем exchangeRates.
  //
  //private final: Это модификаторы доступа и ключевое слово final, которые указывают,
  // что это поле доступно только внутри класса ExchangeManager, и его значение нельзя изменить после его инициализации.
  //HashMap<CurrencyEnum, Double>: Это тип данных поля. Это HashMap, который ассоциирует объекты типа CurrencyEnum
  // с объектами типа Double. В данном контексте, он используется для хранения курсов обмена валюты,
  // где ключами являются объекты CurrencyEnum, а значениями - курсы обмена, представленные типом Double.
  //exchangeRates: Это имя поля, которое мы используем для обращения к этому объекту внутри класса ExchangeManager.
  //new HashMap<>(): Это инициализация объекта HashMap, которая создает новый экземпляр HashMap
  // и присваивает его переменной exchangeRates.
  private final HashMap<CurrencyEnum, Double> exchangeRates = new HashMap<>();
  // Форматирование десятичных чисел для вывода результата обмена
  // Эта строка представляет собой объявление и инициализацию поля класса ExchangeManager с именем decimalFormat.
  //
  //private final: Это модификаторы доступа и ключевое слово final, обозначающие, что это поле доступно только
  // внутри класса ExchangeManager, и его значение нельзя изменить после его инициализации.
  //
  //DecimalFormat: Это тип данных поля. DecimalFormat предоставляет возможность форматирования числовых значений
  // в строковом представлении. В данном случае, он инициализируется объектом класса DecimalFormat.
  //
  //decimalFormat: Это имя поля, которое мы используем для обращения к этому объекту внутри класса ExchangeManager.
  //
  //= new DecimalFormat("#.##"): Это инициализация объекта DecimalFormat,
  // создающая новый экземпляр DecimalFormat с указанным форматом.
  // Здесь # обозначает цифры, а . и ## указывают на количество знаков после запятой.
  // Таким образом, формат #.## будет выводить число с двумя знаками после запятой.
  private final DecimalFormat decimalFormat = new DecimalFormat("#.##");
  // Конструктор класса ExchangeManager
  // При создании объекта ExchangeManager происходит инициализация курсов валют.
  // Эти курсы сохраняются в хэш-карте exchangeRates.
  public ExchangeManager() {
    initializeExchangeRates();
  }
  // Инициализация курсов валют
  // Этот метод initializeExchangeRates() используется для инициализации курсов обмена валюты в объекте ExchangeManager.
  // Внутри метода происходит заполнение хэш-карты exchangeRates значениями курсов обмена для каждой валюты.

  // Каждый вызов put() добавляет новую запись в хэш-карту, где ключом является объект перечисления CurrencyEnum,
  // а значением - курс обмена для соответствующей валюты.
  // Этот метод вызывается при создании объекта ExchangeManager
  // и выполняет предварительную настройку объекта, заполняя его курсы обмена по умолчанию.
  private void initializeExchangeRates() {
    exchangeRates.put(CurrencyEnum.USD, 1.0);
    exchangeRates.put(CurrencyEnum.EUR, 0.85);
    exchangeRates.put(CurrencyEnum.GBP, 0.73);
    exchangeRates.put(CurrencyEnum.CHF, 1.08);
    exchangeRates.put(CurrencyEnum.PLN, 3.95);
    exchangeRates.put(CurrencyEnum.CZK, 22.10);
  }
  // Метод для выполнения обмена валюты
  // Метод performExchange: Этот метод запрашивает у пользователя данные для обмена валюты
  // (сумму и сокращения валют). Затем он проверяет, существуют ли указанные валюты в списке доступных,
  // и если да, то рассчитывает результат обмена и выводит его на экран.

  public void performExchange(Scanner scanner) {
    try {
      //Запрос суммы для обмена: В начале метод запрашивает у пользователя сумму для обмена,
      // используя объект Scanner. Эта сумма сохраняется в переменной amount.
      System.out.print("Введите сумму для обмена: ");
      double amount = scanner.nextDouble();
      // Далее выполняется проверка, является ли введенная сумма отрицательной.
      // Если это так, выбрасывается исключение типа IllegalArgumentException
      // с сообщением "Сумма для обмена не может быть отрицательной."
      if (amount < 0) {
        throw new IllegalArgumentException("Сумма для обмена не может быть отрицательной.");
      }
      // Затем запрашивается у пользователя сокращение валюты,
      // которую он хочет обменять. Введенное значение конвертируется в верхний регистр
      // с помощью метода toUpperCase() и сохраняется в переменной sourceCurrencyAbbreviation.
      System.out.print("Введите сокращение валюты, которую вы хотите обменять: ");
      String sourceCurrencyAbbreviation = scanner.next().toUpperCase();
      // Получение объекта валюты для обмена: Далее вызывается метод getCurrencyByAbbreviation,
      // который принимает сокращение валюты и возвращает соответствующий объект перечисления CurrencyEnum (sourceCurrency).
      // Этот метод проверяет существование валюты с указанным сокращением и выбрасывает исключение,
      // если такой валюты не существует.
      CurrencyEnum sourceCurrency = getCurrencyByAbbreviation(sourceCurrencyAbbreviation);
      // Запрос сокращения целевой валюты: Затем аналогично запрашивается сокращение валюты,
      // которую пользователь хочет приобрести. Введенное значение также конвертируется в верхний регистр
      // и сохраняется в переменной targetCurrencyAbbreviation.
      System.out.print("Введите сокращение валюты, которую вы хотите приобрести: ");
      String targetCurrencyAbbreviation = scanner.next().toUpperCase();
      // Получение объекта целевой валюты для обмена: Снова вызывается метод getCurrencyByAbbreviation,
      // чтобы получить объект целевой валюты (targetCurrency).
      CurrencyEnum targetCurrency = getCurrencyByAbbreviation(targetCurrencyAbbreviation);
      // Расчет результата обмена: Если обе валюты присутствуют в списке обмена, происходит расчет результата обмена.
      // Для этого получаются курсы валют и производится математическое вычисление.
      if (exchangeRates.containsKey(sourceCurrency) && exchangeRates.containsKey(
              targetCurrency)) {
        double sourceRate = exchangeRates.get(sourceCurrency);
        double targetRate = exchangeRates.get(targetCurrency);

        double resultAmount = amount * (targetRate / sourceRate);
        // Вывод результата обмена: Результат обмена выводится на экран, округленный до двух знаков после запятой,
        // с помощью объекта DecimalFormat.
        System.out.println("Результат обмена: " + decimalFormat.format(resultAmount) + " "
                + targetCurrency.getDescription());
        // Создание и добавление записи в историю обменов: Создается объект ExchangeRecord,
        // содержащий информацию о текущем обмене, включая дату и время.
        // Затем этот объект добавляется в список exchangeHistory.
        ExchangeRecord exchangeRecord = new ExchangeRecord(new Date(), amount,
                sourceCurrency, targetCurrency, resultAmount);


        // Запись обмена в файл: Для хранения истории обменов в файл "text.txt" создается объект BufferedWriter.
        // Запись происходит в конец файла, а затем файл закрывается. Если возникает ошибка ввода-вывода,
        // выводится соответствующее сообщение.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("text.txt", true))) {
          writer.write(exchangeRecord.toString());
          writer.newLine();
        } catch (IOException e) {
          System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
      } else {
        System.out.println("Неверные валюты. Пожалуйста, выберите существующие валюты.");
      }
    } catch (Exception e) {
      System.out.println("Ошибка обмена: " + e.getMessage());
    }
  }

  // Метод для чтения истории обменов из файла "text.txt" и вывода на экран
  // Этот метод открывает файл "text.txt" и читает из него историю обменов.
  // Полученные записи выводятся на экран.
  public void viewExchangeHistoryFromFile() {
    // Выводит заголовок "История обменов:" для обозначения начала вывода истории обменов на консоль.
    System.out.println("\nИстория обменов:");
    // Открывает файл "text.txt" для чтения с использованием объекта Scanner.
    // Это делается в блоке try-with-resources, чтобы автоматически закрыть Scanner после чтения файла,
    // даже если возникнет исключение.
    try (Scanner fileScanner = new Scanner(new File("text.txt"))) {
      // Проверяет, есть ли еще строки в файле для чтения.
      while (fileScanner.hasNextLine()) {
        // Считывает следующую строку из файла.
        String line = fileScanner.nextLine();
        // Выводит считанную строку на консоль.
        System.out.println(line);
      }
      //  Обрабатывает исключение, если файл "text.txt" не найден.
      //  В этом случае выводится сообщение "Файл с историей обменов не найден.
    } catch (FileNotFoundException e) {
      System.out.println("Файл с историей обменов не найден.");
    }
  }

  // Метод для получения валюты по ее сокращению
  // Этот закрытый метод используется для получения объекта CurrencyEnum по его сокращению.
  // Он принимает на вход сокращение валюты в виде строки и проходит по всем значениям перечисления CurrencyEnum,
  // сравнивая сокращение с именем или описанием валюты.
  private CurrencyEnum getCurrencyByAbbreviation(String abbreviation) {
    //  Этот цикл проходит по всем значениям перечисления CurrencyEnum.
    for (CurrencyEnum currency : CurrencyEnum.values()) {
      // Внутри цикла проверяется, совпадает ли сокращение валюты (abbreviation)
      // с именем или описанием текущей валюты.
      // Если есть совпадение, возвращается текущий объект currency.
      if (currency.name().equals(abbreviation) || currency.getDescription()
              .equalsIgnoreCase(abbreviation)) {
        return currency;
      }
    }
    //  Если ни одна из валют не соответствует переданному сокращению, генерируется исключение
    //  типа IllegalArgumentException с сообщением "Неверное сокращение валюты".
    throw new IllegalArgumentException("Неверное сокращение валюты.");
  }
}