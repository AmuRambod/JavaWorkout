import java.util.Scanner;
//import ;

public class main {
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Username not provided!");
            System.exit(0);
        } else {
            DatabaseConnection.setUserName(args[0]);
            while (!DatabaseConnection.getConnectionStatus()) {
                DatabaseConnection.getConnectionPassword();
                DatabaseConnection.connect();
            }
            System.out.println("Connection Successful!");
            // DatabaseConnection.insertToWeeklyWeight("1400-05-27");
            // System.out.println("Test e KIRI.....");

            while (true) {
                Menu.printMenu();
            }

            /*
             * DatabaseConnection.setUserName(args[0]);
             * DatabaseConnection.getConnectionPassword(); DatabaseConnection.connect();
             * System.out.println(DatabaseConnection.getConnectionStatus());
             * DatabaseConnection.insertToDailyWeight();
             */
        }
    }
}