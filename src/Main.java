import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        Checker checker = new Checker(monthlyReport, yearlyReport);

        int year = 2021;

         while (true) {
          printMenu();
          int userInput = scanner.nextInt();
          if (userInput == 1) {
              for (int i = 1; i <= 3; i++){
                  monthlyReport.loadFile(i, "resources/m.20210" + i + ".csv");
              } System.out.println("Месячные отчеты считаны");

          } else if (userInput == 2) {
              yearlyReport.loadFile(year, "resources/y." + year + ".csv");
              System.out.println("Годовой отчет считан");

          } else if (userInput == 3) {
              checker.printCheckResult();

          } else if (userInput == 4) {
              monthlyReport.printInfo();

          } else if (userInput == 5) {
              yearlyReport.printInfo(year);

          } else if (userInput == 0) {
              System.out.println("Выход");
              scanner.close();
              break;

          } else {
              System.out.println("Извините, такой команды нет");
          }
      }

    }

        private static void printMenu() {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчеты");
        System.out.println("2 - Считать годовой отчет");
        System.out.println("3 - Сверить отчеты");
        System.out.println("4 - Вывести информацию о всех месячных отчетах");
        System.out.println("5 - Вывести информацию о годовом отчете");
        System.out.println("0 - Выход");
    }


}

