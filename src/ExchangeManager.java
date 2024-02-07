
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class ExchangeManager {

  private final HashMap<CurrencyEnum, Double> exchangeRates = new HashMap<>();
  private final ArrayList<ExchangeRecord> exchangeHistory = new ArrayList<>();
  private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

  public ExchangeManager() {
    initializeExchangeRates();
  }

  private void initializeExchangeRates() {
    exchangeRates.put(CurrencyEnum.USD, 1.0);
    exchangeRates.put(CurrencyEnum.EUR, 0.85);
    exchangeRates.put(CurrencyEnum.GBP, 0.73);
    exchangeRates.put(CurrencyEnum.CHF, 1.08);
    exchangeRates.put(CurrencyEnum.PLN, 3.95); // Примерные курсы, вы можете их изменить
    exchangeRates.put(CurrencyEnum.CZK, 22.10);
  }

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

      if (exchangeRates.containsKey(sourceCurrency) && exchangeRates.containsKey(targetCurrency)) {
        double sourceRate = exchangeRates.get(sourceCurrency);
        double targetRate = exchangeRates.get(targetCurrency);

        double resultAmount = amount * (targetRate / sourceRate);

        System.out.println("Результат обмена: " + decimalFormat.format(resultAmount) + " "
            + targetCurrency.getDescription());

        ExchangeRecord exchangeRecord = new ExchangeRecord(new Date(), amount, sourceCurrency,
            targetCurrency, resultAmount);
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

  private CurrencyEnum getCurrencyByAbbreviation(String abbreviation) {
    for (CurrencyEnum currency : CurrencyEnum.values()) {
      if (currency.name().equals(abbreviation) || currency.getDescription()
          .equalsIgnoreCase(abbreviation)) {
        return currency;
      }
    }
    throw new IllegalArgumentException("Неверное сокращение валюты.");
  }
}