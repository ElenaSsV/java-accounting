import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;


     public class MonthlyReport {
         public ArrayList<MonthlyEntry>  monthlyEntries = new ArrayList<>();

         public void loadFile(int month, String path) {
             String content = readFileContentsOrNull(path);
             String[] lines = content.split("\r?\n");
             for (int i = 1; i < lines.length; i++) {
                 String line = lines[i];
                 String[] parts = line.split(",");
                 String item_name = parts[0];
                 boolean isExpense = Boolean.parseBoolean(parts[1]);
                 int quantity = Integer.parseInt(parts[2]);
                 int sum_of_one = Integer.parseInt(parts[3]);
                 MonthlyEntry entry = new MonthlyEntry(item_name, isExpense, quantity, sum_of_one, month);
                 monthlyEntries.add(entry);
             }
         }

         public void findMaxExpense(int month) {
             HashMap<String, Integer> items = new HashMap<>();
             for (MonthlyEntry entry : monthlyEntries) {
                 if (!entry.isExpense) {
                     continue;
                 }
                 if (entry.month == month) {
                     items.put(entry.item_name, entry.quantity * entry.sum_of_one);
                 }
             }
             String maxExpenseName = "";
             int maxExpense = 0;
                 for (String item_name : items.keySet()) {
                     int expense = items.get(item_name);
                     if (expense > maxExpense) {
                         maxExpense = expense;
                         maxExpenseName = item_name;
                     }
                 } System.out.println(maxExpenseName + " " + maxExpense);
             }

         public void findMostProfitableItem (int month) {
             HashMap<String, Integer> items = new HashMap<>();
             for (MonthlyEntry entry : monthlyEntries) {
                 if (entry.isExpense) {
                     continue;
                 }
                 if (entry.month == month) {
                     items.put(entry.item_name, entry.quantity * entry.sum_of_one);
                 }
             }
             String mostProfitableItemName = "";
             int maxProfit = 0;
             for (String item_name : items.keySet()) {
                 int expense = items.get(item_name);
                 if (expense > maxProfit) {
                     maxProfit = expense;
                     mostProfitableItemName = item_name;
                 }
             } System.out.println(mostProfitableItemName + " " + maxProfit);
         }



        public String readFileContentsOrNull(String path) {
             try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчетом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }

}
