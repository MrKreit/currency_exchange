public enum Currency {
  USD(1.0),
  EUR(0.85),
  GBP(0.72),
  PLN(3.78),
  CZK(21.44);

  private double rate;

  Currency(double rate) {
    this.rate = rate;
  }

  public double getRate() {
    return rate;
  }
}
