import java.util.HashMap;


public class Checker {
    public MonthlyReport monthlyReport;
    public YearlyReport yearlyReport;
    boolean check = true;
    public Checker(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }

    public boolean isMonthlyReportNull() {
        if (monthlyReport.monthlyEntries.size() == 0) {
            System.out.println("Месячные отчеты не считаны!");
          return true;
        } else {
            return false;
        }
    }

    public boolean isYearlyReportNull() {
        if (yearlyReport.yearlyEntries.size() == 0) {
            System.out.println("Годовой отчет не считан!");
            return true;
        } else {
            return false;
        }
    }
    public boolean checkIncome() {
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
                    System.out.println("В месяце " + month + " сумма доходов не сходится");
                    check = false;
                }
            } return check;
    }

    public boolean checkExpenses() {
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
                    System.out.println("В месяце " + month + " сумма расходов не сходится");
                    check = false;
                }
            }
            return check;
    }

     public void printCheckResult() {
        boolean isMonthlyReportNull = isMonthlyReportNull();
        boolean isYearlyReportNull = isYearlyReportNull();

        if (isMonthlyReportNull || isYearlyReportNull) {
            return;
        }
         boolean isExpenseCorrect = checkExpenses();
         boolean isIncomeCorrect = checkIncome();

        if (isExpenseCorrect && isIncomeCorrect) {
            System.out.println("В отчетах по суммам доходов и расходов расхождений нет");
        } else {
            System.out.println("Ошибка!");
        }
     }
}
