import java.util.InputMismatchException;
import java.util.Scanner;

// Определяем основной класс приложения
// Определяем основной класс программы с именем Main.
public class Main {

  // Главный метод программы, который запускается при её старте.
  public static void main(String[] args) {
    // Создаю объект приложения для обмена валют, чтобы использовать его функционал.
    CurrencyExchangeApp currencyExchangeApp = new CurrencyExchangeApp();
    // Создаю объект Scanner для считывания пользовательского ввода из консоли.
    Scanner scanner = new Scanner(System.in);
    // Вызываю метод для отображения списка аббревиатур валют, доступных для обмена.
    currencyExchangeApp.displayCurrencyAbbreviations();
    // Использую переменную для отслеживания корректности ввода пользователя.
    boolean validInput = false;

    // Цикл продолжается, пока пользователь не сделает корректный выбор действия.
    while (!validInput) {
      try {
        // Отображаю меню с возможными действиями в приложении обмена валют.
        currencyExchangeApp.displayMenu();
        // Считываю выбор пользователя из консоли.
        int choice = scanner.nextInt();
        // Очищаю буфер сканера после считывания числа, чтобы избежать ошибок при следующем считывании.
        scanner.nextLine();

        // Обрабатываю выбор пользователя с помощью конструкции switch.
        switch (choice) {
          case 1:
            // Если пользователь выбрал 1, вызываю метод для выполнения обмена валют.
            currencyExchangeApp.performExchange(scanner);
            break;
          case 2:
            // Если пользователь выбрал 2, вызываю метод для просмотра истории обменов.
            currencyExchangeApp.viewExchangeHistory();
            break;
          case 3:
            // Если пользователь выбрал 3, выводится сообщение о выходе и цикл завершается.
            System.out.println("Выход из программы. До свидания!");
            validInput = true; // Устанавливаю флаг корректного ввода в true для выхода из цикла.
            break;
          default:
            // Если ввод пользователя не соответствует ожидаемым вариантам, сообщаю об этом.
            System.out.println("Неверный выбор. Пожалуйста, выберите снова.");
            break;
        }
        // Ловлю исключение, которое возникает при вводе данных неправильного формата (не числа).
      } catch (InputMismatchException e) {
        // Сообщаю пользователю об ошибке ввода и прошу ввести число.
        System.out.println("Ошибка ввода. Введите число.");
        // Очищаю буфер сканера для предотвращения бесконечного цикла ошибок.
        scanner.nextLine();
      }
    }
  }
}