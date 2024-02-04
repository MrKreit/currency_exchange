import java.text.DecimalFormat;
import java.util.Date;

public class ExchangeRecord {

    private final Date date;
    private final double amount;
    private final CurrencyEnum sourceCurrency;
    private final CurrencyEnum targetCurrency;
    private final double resultAmount;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public ExchangeRecord(Date date, double amount, CurrencyEnum sourceCurrency, CurrencyEnum targetCurrency, double resultAmount) {
      this.date = date;
      this.amount = amount;
      this.sourceCurrency = sourceCurrency;
      this.targetCurrency = targetCurrency;
      this.resultAmount = resultAmount;
    }

    @Override
    public String toString() {
      return "Дата: " + date + ", Сумма: " + amount + " " + sourceCurrency +
          " -> " + decimalFormat.format(resultAmount) + " " + targetCurrency.getDescription();
    }

}
