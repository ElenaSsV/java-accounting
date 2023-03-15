import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;


public class YearlyReport {
    public ArrayList<YearlyEntry> yearlyEntries = new ArrayList<>();

    public void loadFile (int year, String path) {
        String content = readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            YearlyEntry incomeAndExpense = new YearlyEntry(year, month, amount, isExpense);
            yearlyEntries.add(incomeAndExpense);
        }
    }

    public void findProfitPerMonth() {
        HashMap<Integer, Integer>  profitPerMonth = new HashMap<>();
        int profit = 0;
        int expense = 0;
        int income = 0;
        for (YearlyEntry entry : yearlyEntries) {
            if (entry.isExpense) {
                expense = entry.amount;
            } else {
                income = entry.amount;
            } profit = income - expense;
            profitPerMonth.put(entry.month, profit);
        }
        for (Integer month : profitPerMonth.keySet()) {
            System.out.println("Месяц " + month);
            int value = profitPerMonth.get(month);
            System.out.println(value);
        }
    }

    public int findAverageExpense() {
       HashMap<Integer, Integer> expensesPerMonth = new HashMap<>(); // месяц, сумма расхода
        for (YearlyEntry entry : yearlyEntries) {
            if (!entry.isExpense) {
                continue;
            }
            expensesPerMonth.put(entry.month, entry.amount);
        }
         int expensesSum = 0;
         int averageExpense = 0;
        for (Integer expense : expensesPerMonth.values()) {
            expensesSum += expense;
            averageExpense = expensesSum / 12;
        }
        return averageExpense;
   }

    public int findAverageIncome() {
        HashMap<Integer, Integer> incomePerMonth = new HashMap<>(); // месяц, сумма расхода
        for (YearlyEntry entry : yearlyEntries) {
            if (entry.isExpense) {
                continue;
            }
            incomePerMonth.put(entry.month, entry.amount);
        }
        int expensesSum = 0;
        int averageIncome = 0;
        for (Integer expense : incomePerMonth.values()) {
            expensesSum += expense;
            averageIncome = expensesSum / 12;
        }
        return averageIncome;
    }

    public void printInfo(int year) {
        if (yearlyEntries.size() == 0) {
            System.out.println("Ошибка! Годовой отчет не считан!");
            return;
        }
        System.out.println("Год: " + year);
        System.out.println("Прибыль по месяцам: ");
        findProfitPerMonth();
        System.out.println("Средний расход за год: " + findAverageExpense());
        System.out.println("Средний доход за год: " + findAverageIncome());
    }

    public String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчетом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }
}