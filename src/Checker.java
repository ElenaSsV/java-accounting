import java.util.HashMap;


public class Checker {
    public MonthlyReport monthlyReport;
    public YearlyReport yearlyReport;
    boolean check = true;
    public Checker(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }

    public void printInfoOnMonthlyReports(){
        for (int i = 1; i <= 3; i++) {
            System.out.println("Месяц " + (i));
            System.out.println("Самый прибыльный товар:");
            monthlyReport.findMostProfitableItem(i);
            System.out.println("Самая большая трата за месяц: ");
            monthlyReport.findMaxExpense(i);

        }
    }

    public void printInfoOnYearlyReport(int year) {
        System.out.println("Год: " + year);
        System.out.println("Прибыль по месяцам: ");
        yearlyReport.findProfitPerMonth();
        System.out.println("Средний расход за год: " + yearlyReport.findAverageExpense());
        System.out.println("Средний доход за год: " + yearlyReport.findAverageIncome());
    }


    public boolean checkIncome() {
        if (monthlyReport.monthlyEntries.size() == 0  || yearlyReport.yearlyEntries.size() == 0) {
            System.out.println("Ошибка! Очеты не считаны! Необходимо сначала считать отчеты!");
            return false;
        } else {
            HashMap<Integer, Integer> incomeInMonthlyReport = new HashMap<>(); // month, amount
            for (MonthlyEntry entry : monthlyReport.monthlyEntries) {
                if (entry.isExpense) {
                    continue;
                }
                incomeInMonthlyReport.put(entry.month, incomeInMonthlyReport.getOrDefault(entry.month, 0) +
                        entry.quantity * entry.sum_of_one);
            }
            HashMap<Integer, Integer> incomeInYearlyReport = new HashMap<>();
            for (YearlyEntry entry : yearlyReport.yearlyEntries) {
                if (entry.isExpense) {
                    continue;
                }
                incomeInYearlyReport.put(entry.month, entry.amount);
            }
            for (Integer month : incomeInYearlyReport.keySet()) {
                int incomeByYearlyReport = incomeInYearlyReport.get(month);
                int incomeByMonthlyReport = incomeInMonthlyReport.getOrDefault(month, 0);
                if (incomeByYearlyReport != incomeByMonthlyReport) {
                    System.out.println("В месяце " + month + " сумма не сходится");
                    check = false;
                }
            } return check;
        }
    }

    public boolean checkExpenses() {
        if (monthlyReport.monthlyEntries.size() == 0  || yearlyReport.yearlyEntries.size() == 0) {
            System.out.println("Ошибка! Очеты не считаны! Необходимо сначала считать отчеты!");
           return false;
        } else {
            HashMap<Integer, Integer> expensesByMonthlyReport = new HashMap<>(); // month, amount
            for (MonthlyEntry entry : monthlyReport.monthlyEntries) {
                if (!entry.isExpense) {
                    continue;
                }
                expensesByMonthlyReport.put(entry.month, expensesByMonthlyReport.getOrDefault(entry.month, 0) +
                        entry.quantity * entry.sum_of_one);
            }
            HashMap<Integer, Integer> expensesByYearlyReport = new HashMap<>();
            for (YearlyEntry entry : yearlyReport.yearlyEntries) {
                if (!entry.isExpense) {
                    continue;
                }
                expensesByYearlyReport.put(entry.month, entry.amount);
            }
            for (Integer month : expensesByYearlyReport.keySet()) {
                int expenseByYearlyReport = expensesByYearlyReport.get(month);
                int expenseByMonthlyReport = expensesByMonthlyReport.getOrDefault(month, 0);
                if (expenseByYearlyReport != expenseByMonthlyReport) {
                    System.out.println("В месяце " + month + " сумма не сходится");
                    check = false;
                }
            }
            return check;
        }
    }
}
