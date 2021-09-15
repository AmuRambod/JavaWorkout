public class Menu {
    private static String menu = "\n *** MENU ***\n\n" + "1. Add data\n" + "2. Show data\n" + "3. Quit\n"
            + "\nEnter your choice: ";

    public static void printMenu() {
        System.out.print(menu);
        char choice = main.keyboard.nextLine().charAt(0);
        System.out.println("--------------");
        switch (choice) {
            case '1':
                addData();
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

    public static void addData() {
        String menuString = "\n *** ADD DATA ***\n\n" + "1. Add daily weight\n" + "2. Add standard 5km time\n"
                + "\nEnter your choice: ";
        System.out.print(menuString);
        char choice = main.keyboard.nextLine().charAt(0);
        System.out.println("--------------");
        switch (choice) {
            case '1':
                DatabaseConnection.insertToDailyWeight();
                break;
            case '2':
                DatabaseConnection.insertToStandard5km();
                break;
            default:
                System.out.println("Choice not implemented!");
                break;
        }
    }
}