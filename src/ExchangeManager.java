import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

// Класс ExchangeManager представляет собой центральное звено программы обмена валют.
// Его основная задача - управление обменом валют и хранение истории обменов.
// Вот краткое описание его функций и взаимодействия с другими классами:
//
//Управление курсами обмена валют:
//
//В классе ExchangeManager есть метод initializeExchangeRates(), который инициализирует курсы обмена валют
// при создании объекта.
//Эти курсы обмена хранятся в HashMap<CurrencyEnum, Double> exchangeRates,
// где ключами являются объекты перечисления CurrencyEnum, представляющие валюты,
// а значениями - их соответствующие курсы обмена.
//Выполнение обмена валют:
//
//Метод performExchange(Scanner scanner) позволяет пользователю выполнить обмен валюты.
//Пользователь вводит сумму для обмена и сокращения валюты, которые он хочет обменять и приобрести.
//Класс ExchangeManager использует метод getCurrencyByAbbreviation() для получения объектов валюты
// на основе введенных сокращений.
//Затем он проверяет наличие указанных валют в списке курсов обмена и вычисляет результат обмена.
//Создается запись об обмене, которая добавляется в список exchangeHistory.
//Результат обмена также сохраняется в файл text.txt с помощью класса BufferedWriter.
//Просмотр истории обменов:
//
//Метод viewExchangeHistory() отображает историю всех предыдущих обменов, хранящуюся в списке exchangeHistory.
//Это позволяет пользователям просматривать прошлые обмены в удобном формате.
//Отображение доступных валют:
//
//Метод displayCurrencyAbbreviations() отображает список доступных валют, который получается из перечисления CurrencyEnum.
//Это помогает пользователям выбирать валюты для обмена, используя их сокращения.
//Взаимодействие с другими классами:
//
//Класс ExchangeManager взаимодействует с классом CurrencyExchangeApp,
// предоставляя методы для выполнения обмена и просмотра истории обменов.
//Он также используется классом ExchangeRecord, чтобы создавать записи об обмене,
// которые затем добавляются в список exchangeHistory.
//Кроме того, ExchangeManager сохраняет результаты обменов в файл text.txt,
// что обеспечивает постоянное хранение истории обменов между сеансами работы программы.

public class ExchangeManager {

    // Хранит текущие курсы обмена валют
    private final HashMap<CurrencyEnum, Double> exchangeRates = new HashMap<>();
    // Хранит историю обменов
    private final ArrayList<ExchangeRecord> exchangeHistory = new ArrayList<>();
    // Формат для вывода результатов обмена
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    // Конструктор класса, инициализирует курсы обмена
    public ExchangeManager() {
        initializeExchangeRates();
    }
    // Инициализация курсов обмена
    private void initializeExchangeRates() {
        exchangeRates.put(CurrencyEnum.USD, 1.0);
        exchangeRates.put(CurrencyEnum.EUR, 0.85);
        exchangeRates.put(CurrencyEnum.GBP, 0.73);
        exchangeRates.put(CurrencyEnum.CHF, 1.08);
        exchangeRates.put(CurrencyEnum.PLN, 3.95);
        exchangeRates.put(CurrencyEnum.CZK, 22.10);
    }
    // Метод для выполнения обмена валют
    public void performExchange(Scanner scanner) {
        try {
            System.out.print("Введите сумму для обмена: ");
            double amount = scanner.nextDouble();

            if (amount < 0) {
                throw new IllegalArgumentException("Сумма для обмена не может быть отрицательной.");
            }

            System.out.print("Введите сокращение валюты, которую вы хотите обменять: ");
            String sourceCurrencyAbbreviation = scanner.next().toUpperCase();
            CurrencyEnum sourceCurrency = getCurrencyByAbbreviation(sourceCurrencyAbbreviation);

            System.out.print("Введите сокращение валюты, которую вы хотите приобрести: ");
            String targetCurrencyAbbreviation = scanner.next().toUpperCase();
            CurrencyEnum targetCurrency = getCurrencyByAbbreviation(targetCurrencyAbbreviation);
            // Проверка наличия указанных валют в списке курсов обмена
            if (exchangeRates.containsKey(sourceCurrency) && exchangeRates.containsKey(targetCurrency)) {
                double sourceRate = exchangeRates.get(sourceCurrency);
                double targetRate = exchangeRates.get(targetCurrency);
                // Вычисление результата обмена
                double resultAmount = amount * (targetRate / sourceRate);
                // Вывод результата обмена
                System.out.println("Результат обмена: " + decimalFormat.format(resultAmount) + " " + targetCurrency.getDescription());
                // Создание записи об обмене
                ExchangeRecord exchangeRecord = new ExchangeRecord(new Date(), amount, sourceCurrency, targetCurrency, resultAmount);
                exchangeHistory.add(exchangeRecord);

                // Добавляем запись в файл
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
    // Метод для просмотра истории обменов
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
    // Метод для отображения доступных валют
    public void displayCurrencyAbbreviations() {
        System.out.println("Доступные валюты:");
        for (CurrencyEnum currency : CurrencyEnum.values()) {
            System.out.println(currency.name() + ": " + currency.getDescription());
        }
    }
    // Метод для получения объекта валюты по сокращению
    private CurrencyEnum getCurrencyByAbbreviation(String abbreviation) {
        for (CurrencyEnum currency : CurrencyEnum.values()) {
            if (currency.name().equals(abbreviation) || currency.getDescription().equalsIgnoreCase(abbreviation)) {
                return currency;
            }
        }
        throw new IllegalArgumentException("Неверное сокращение валюты.");
    }
}