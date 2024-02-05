
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Класс ExchangeManager представляет менеджер для обмена валюты и хранения истории обменов.
 * private final HashMap<CurrencyEnum, Double> exchangeRates = new HashMap<>(); - это хэш-карта, которая содержит
 * курсы обмена для различных валют.
 * Ключами в этой карте являются объекты перечисления CurrencyEnum, представляющие виды валют,
 * а значениями - соответствующие курсы обмена (отношение к одной базовой валюте, например, к доллару).
 * Эта карта инициализируется в конструкторе класса.
 * private final ArrayList<ExchangeRecord> exchangeHistory = new ArrayList<>(); - это список,
 * который представляет собой историю обменов. Каждый элемент списка - это объект ExchangeRecord,
 * который содержит информацию о конкретном обмене (сумма, исходная и целевая валюты, результат обмена и дата).
 * История обменов также инициализируется в конструкторе.
 * private final DecimalFormat decimalFormat = new DecimalFormat("#.##"); - это объект DecimalFormat,
 * который используется для форматирования чисел с плавающей запятой (double) с точностью до двух знаков после запятой.
 * Это нужно для красивого вывода результатов обменов.
 **/
public class ExchangeManager {
    private final HashMap<CurrencyEnum, Double> exchangeRates = new HashMap<>();
    private final ArrayList<ExchangeRecord> exchangeHistory = new ArrayList<>();
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    /**
     * Конструктор для создания объекта ExchangeManager и инициализации начальных курсов валют.
     */

    public ExchangeManager()
    {
        initializeExchangeRates();
    }

    /**
     * Инициализирует начальные курсы валют.
     */

    private void initializeExchangeRates() {
        exchangeRates.put(CurrencyEnum.USD, 1.0);
        exchangeRates.put(CurrencyEnum.EUR, 0.85);
        exchangeRates.put(CurrencyEnum.GBP, 0.73);
        exchangeRates.put(CurrencyEnum.CHF, 1.08);
        exchangeRates.put(CurrencyEnum.PLN, 3.95);
        exchangeRates.put(CurrencyEnum.CZK, 22.10);
    }

    /**
     *Метод performExchange в классе ExchangeManager отвечает за выполнение операции обмена валюты.
     *  Давайте разберем, что он делает:
     * Ввод данных от пользователя:
     * Запрашивает у пользователя сумму для обмена.
     * Проверяет, что введенная сумма не отрицательна.
     * Запрашивает сокращение валюты для обмена и целевой валюты.
     * Проверка введенных данных:
     * Проверяет, что введенные сокращения валют существуют в предопределенном
     * списке (используя CurrencyEnum).
     * Если введенные валюты корректны, продолжает выполнение операции обмена.
     * Вычисление результата обмена:
     * Получает курсы обмена для выбранных валют из хранилища курсов (exchangeRates).
     * Вычисляет сумму в целевой валюте, используя введенные данные и курсы обмена.
     * Округляет результат до двух знаков после запятой.
     * Вывод результата и обновление истории:
     * Выводит на консоль результат обмена (сумму в целевой валюте).
     * Создает объект ExchangeRecord для текущего обмена и добавляет его в историю (exchangeHistory).
     * Обработка ошибок ввода:
     * Использует блок try-catch для обработки возможных исключений:
     * InputMismatchException: если пользователь вводит некорректное значение (например, буквы вместо числа).
     * IllegalArgumentException: если введена отрицательная сумма для обмена.
     * Этот метод обеспечивает пользовательский ввод, проверку корректности данных, вычисление результатов обмена,
     * вывод информации и обновление истории обменов. Такая структура позволяет легко поддерживать и расширять функциональность программы.
     */

    public void performExchange(Scanner scanner) {
        /**
         * Использование блока try-catch в данном классе (ExchangeManager) связано с обработкой исключений,
         * которые могут возникнуть в процессе выполнения программы. Давайте рассмотрим, почему это важно:
         * InputMismatchException: Возникает, когда тип данных, введенных пользователем, не соответствует ожидаемому типу.
         * Например, если пользователь вводит строку, когда ожидается ввод числа.
         * Блок try-catch позволяет программе корректно обрабатывать такие ситуации
         * и предоставлять информативные сообщения об ошибке.
         * IllegalArgumentException: Возникает, когда в метод передается аргумент, который не соответствует ожидаемым условиям.
         * Например, отрицательная сумма для обмена валюты. Блок try-catch помогает программе контролировать
         * и обрабатывать эти ошибки, предоставляя пользователю понятные сообщения.
         * Блок try-catch обеспечивает более устойчивое выполнение программы, предотвращая аварийное завершение
         * из-за некорректных вводов пользователя или других непредвиденных ситуаций.
         * Он также позволяет программе дать пользователю обратную связь о том, что произошла ошибка,
         * и запросить корректный ввод.
         **/
        try {
            System.out.print("Введите сумму для обмена: ");
            double amount = scanner.nextDouble();

            if (amount < 0) {
                throw new IllegalArgumentException("Сумма для обмена не может быть отрицательной.");
            }

            System.out.print("Введите сокращение валюты, которую вы хотите обменять: ");
            String sourceCurrencyAbbreviation = scanner.next().toUpperCase();

            /**
             * sourceCurrencyAbbreviation - это строка, которую пользователь вводит в консоли как сокращение валюты,
             * которую он хочет обменять.
             * getCurrencyByAbbreviation(sourceCurrencyAbbreviation) - это метод getCurrencyByAbbreviation
             * класса ExchangeManager,
             * который принимает сокращение валюты в качестве аргумента и возвращает соответствующий объект CurrencyEnum.
             * CurrencyEnum sourceCurrency = ... - создает переменную sourceCurrency и присваивает ей объект валюты,
             * полученный из метода getCurrencyByAbbreviation.
             * Таким образом, после выполнения этой строки sourceCurrency будет содержать
             * объект перечисления CurrencyEnum,
             * представляющий валюту, которую пользователь хочет обменять. Это обеспечивает удобство и читаемость кода,
             * так как переменная sourceCurrency затем используется для работы с выбранной пользователем валютой
             * при выполнении обмена.
             **/
            CurrencyEnum sourceCurrency = getCurrencyByAbbreviation(sourceCurrencyAbbreviation);

            System.out.print("Введите сокращение валюты, которую вы хотите приобрести: ");
            /**
             * scanner.next() - эта команда считывает следующую строку ввода из консоли.
             * .toUpperCase() - метод вызывается для преобразования всех символов строки в верхний регистр
             * (в данном случае, приводит сокращение валюты к верхнему регистру).
             * String targetCurrencyAbbreviation = ... - создает переменную targetCurrencyAbbreviation
             * и присваивает ей сокращение валюты, введенное пользователем, преобразованное в верхний регистр.
             * Таким образом, после выполнения этой строки targetCurrencyAbbreviation
             * будет содержать сокращение валюты, которую пользователь хочет приобрести.
             * Это обеспечивает консистентность данных в программе, так как сокращение валюты сравнивается
             * с перечислением CurrencyEnum, и это сравнение не зависит от регистра введенных пользователем символов.
             **/
            String targetCurrencyAbbreviation = scanner.next().toUpperCase();
            /**
             * sourceCurrencyAbbreviation - это строка, которую пользователь вводит в консоли как сокращение валюты,
             * которую он хочет обменять.
             * getCurrencyByAbbreviation(sourceCurrencyAbbreviation)
             * - это метод getCurrencyByAbbreviation класса ExchangeManager, который принимает сокращение валюты
             * в качестве аргумента и возвращает соответствующий объект CurrencyEnum.
             * CurrencyEnum sourceCurrency = ... - создает переменную sourceCurrency и присваивает ей объект валюты,
             * полученный из метода getCurrencyByAbbreviation.
             * Таким образом, в результате выполнения этой строки sourceCurrency будет содержать
             * объект перечисления CurrencyEnum, представляющий валюту, которую пользователь хочет обменять.
             +*/
            CurrencyEnum targetCurrency = getCurrencyByAbbreviation(targetCurrencyAbbreviation);
            /**
             * if (exchangeRates.containsKey(sourceCurrency) && exchangeRates.containsKey(targetCurrency))
             * - проверяет, содержат ли карты курсов валют (exchangeRates)
             * выбранные пользователем валюты (sourceCurrency и targetCurrency).
             * Если оба значения присутствуют в картах, код продолжит выполнение внутри блока if,
             * иначе перейдет к блоку else.
             * double sourceRate = exchangeRates.get(sourceCurrency); и double targetRate = exchangeRates.get(targetCurrency);
             * - получают курсы обмена для выбранных валют из карты курсов валют.
             * double resultAmount = amount * (targetRate / sourceRate); - вычисляет сумму после обмена.
             * Обратите внимание, что это простой способ вычислить сумму в одной валюте после обмена на другую.
             * System.out.println("Результат обмена: " + decimalFormat.format(resultAmount) + " " + targetCurrency.getDescription());
             * - выводит пользователю результат обмена с отформатированной суммой и описанием целевой валюты.
             * ExchangeRecord exchangeRecord = new ExchangeRecord(new Date(), amount, sourceCurrency, targetCurrency, resultAmount);
             * - создает объект ExchangeRecord, представляющий запись об обмене, и добавляет его в историю обменов (exchangeHistory).
             * else { System.out.println("Неверные валюты. Пожалуйста, выберите существующие валюты."); }
             * - выводит сообщение об ошибке в случае, если хотя бы одна из выбранных валют не существует в картах курсов валют.
             * Этот участок кода обеспечивает корректное выполнение обмена валюты, расчет суммы после обмена
             * и добавление записи об обмене в историю, а также предоставляет информацию об ошибке при неверном выборе валют.
             **/

            if (exchangeRates.containsKey(sourceCurrency) && exchangeRates.containsKey(targetCurrency)) {
                double sourceRate = exchangeRates.get(sourceCurrency);
                double targetRate = exchangeRates.get(targetCurrency);

                double resultAmount = amount * (targetRate / sourceRate);

                System.out.println("Результат обмена: " + decimalFormat.format(resultAmount) + " " + targetCurrency.getDescription());

                ExchangeRecord exchangeRecord = new ExchangeRecord(new Date(), amount, sourceCurrency, targetCurrency, resultAmount);
                exchangeHistory.add(exchangeRecord);
            } else {
                System.out.println("Неверные валюты. Пожалуйста, выберите существующие валюты.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода. Введите корректное значение.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Отображает историю обменов.
     * System.out.println("\nИстория обменов:"); - выводит заголовок, сообщая пользователю,
     * что будет отображена история обменов.
     * <p>
     * if (exchangeHistory.isEmpty()) { System.out.println("История пуста."); } - проверяет,
     * пуста ли история обменов (exchangeHistory). Если история пуста, выводится сообщение о том,
     * что история обменов пуста. Это предотвращает лишний вывод, если история обменов пуста.
     * <p>
     * else { for (ExchangeRecord record : exchangeHistory) { System.out.println(record); } }
     * - если история не пуста, происходит итерация по каждой записи об обмене (ExchangeRecord)
     * в списке exchangeHistory, и каждая запись выводится на экран.
     * В данном случае, используется переопределенный метод toString() в классе ExchangeRecord для удобного вывода
     * информации о каждом обмене.
     * <p>
     * Таким образом, этот метод позволяет пользователю просматривать историю предыдущих обменов,
     * если она существует, или уведомляет, что история обменов пуста, если записей нет.
     */

    public void viewExchangeHistory() {
        System.out.println("\nИстория обменов:");
        if (exchangeHistory.isEmpty()) {
            System.out.println("История пуста.");
        } else {
            for (ExchangeRecord record : exchangeHistory) {
                System.out.println(record);
            }
        }
    }

    /**
     * Отображает сокращения доступных валют.
     * System.out.println("Доступные валюты:"); - выводит заголовок, сообщая пользователю,
     * что будет отображен список доступных валют.
     * <p>
     * for (CurrencyEnum currency : CurrencyEnum.values())
     * { - начинает цикл, проходящий по всем значениям перечисления CurrencyEnum.
     * Таким образом, код будет выполняться для каждой доступной валюты.
     * <p>
     * System.out.println(currency.name() + ": " + currency.getDescription());
     * - для каждой валюты выводится строка, содержащая сокращение валюты (полученное с помощью currency.name())
     * и ее описание (полученное с помощью currency.getDescription()).
     * <p>
     * Таким образом, этот метод предоставляет пользователю информацию о доступных валютах и их сокращениях,
     * что помогает пользователю выбирать корректные обозначения валют при выполнении обмена.
     */

    public void displayCurrencyAbbreviations() {
        System.out.println("Доступные валюты:");
        for (CurrencyEnum currency : CurrencyEnum.values()) {
            System.out.println(currency.name() + ": " + currency.getDescription());
        }
    }

    /**
     * Получает объект CurrencyEnum по сокращению валюты.
     *
     * @param abbreviation Сокращение валюты.
     * @return Объект CurrencyEnum.
     * @throws IllegalArgumentException если сокращение валюты неверное.
     * Метод getCurrencyByAbbreviation(String abbreviation) выполняет поиск объекта CurrencyEnum
     * по заданному сокращению валюты. Давайте разберем этот метод:
     * for (CurrencyEnum currency : CurrencyEnum.values()) { - начинает цикл, проходящий по всем значениям перечисления
     * CurrencyEnum. Таким образом, код будет выполняться для каждой доступной валюты.
     * if (currency.name().equals(abbreviation) || currency.getDescription().equalsIgnoreCase(abbreviation))
     * { - проверяет, совпадает ли сокращение валюты или ее описание (в любом регистре)
     * с заданным сокращением (abbreviation), переданным в метод.
     * return currency; - если сокращение найдено, метод возвращает соответствующий объект CurrencyEnum
     * throw new IllegalArgumentException("Неверное сокращение валюты."); - если ни одно сокращение не совпадает,
     * метод выбрасывает исключение IllegalArgumentException с сообщением об ошибке.
     * Таким образом, этот метод обеспечивает корректный поиск объекта CurrencyEnum по сокращению валюты
     * и обработку ситуации, когда заданное сокращение не соответствует ни одной из доступных валют.
     **/

    private CurrencyEnum getCurrencyByAbbreviation(String abbreviation) {
        for (CurrencyEnum currency : CurrencyEnum.values()) {
            if (currency.name().equals(abbreviation) || currency.getDescription().equalsIgnoreCase(abbreviation)) {
                return currency;
            }
        }
        throw new IllegalArgumentException("Неверное сокращение валюты.");
    }
}
