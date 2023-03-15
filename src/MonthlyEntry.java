public class MonthlyEntry {

    public String item_name;
    public boolean isExpense;
    public int quantity;
    public int sum_of_one;
    public int month;


    public MonthlyEntry(String item_name, boolean isExpense, int quantity, int sum_of_one, int month) {
        this.item_name = item_name;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sum_of_one = sum_of_one;
        this.month = month;
    }
}
