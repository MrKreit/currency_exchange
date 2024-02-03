public class Main {
  public static double exchange(Currency from, Currency to, double amount) {
    // получить курс валюты, из которой обмениваем
    double fromRate = from.getRate();
    // получить курс валюты, в которую обмениваем
    double toRate = to.getRate();
    // рассчитать сумму в другой валюте
    double result = amount * fromRate / toRate;
    // вернуть результат
    return result;
  }
  public static void main(String[] args) {

  }
}
