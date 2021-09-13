public class Menu {
    private static String menu = "\n *** MENU ***\n\n" + "1. Add daily weight\n" + "2. Show data\n" + "3. Quit\n"
            + "\nEnter your choice: ";

    public static void printMenu() {
        System.out.print(menu);
        char choice = main.keyboard.nextLine().charAt(0);
        System.out.println("--------------");
        switch (choice) {
            case '1':
                // insert to daily_weight
                // DatabaseConnection.insertToDailyWeight();
                DatabaseConnection.insertToStandard5km();
                break;
            case '2':
                DataTable.showData();
                break;
            case '3':
                System.out.println("Have a nice day!");
                System.exit(0);
                break;
            default:
                System.out.println("Choice not implemented!");
                break;
        }
    }
}