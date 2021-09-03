import java.sql.*;

public class DataTable {
    public static void showData() {
        CommandLineTable dataTable = new CommandLineTable();
        String showDataMenu = "\n *** SHOW DATA ***\n\n" + "1. Daily weight\n" + "2. Weekly weight\n"
                + "\nEnter your choice: ";
        System.out.print(showDataMenu);
        char choice = main.keyboard.nextLine().charAt(0);
        System.out.println("--------------");
        switch (choice) {
            case '1':
                dataTable = getDailyWeightTable();
                System.out.println("Daily Weight Table:");
                break;
            case '2':
                dataTable = getWeeklyWeightTable();
                System.out.println("Weekly Weight Table:");
                break;
            default:
                dataTable = null;
                System.out.println("Choice not implemented!");
                break;
        }
        dataTable.print();
    }

    public static CommandLineTable getDailyWeightTable() {
        String getDataQuery = "SELECT * FROM daily_weight;";
        CommandLineTable dataTable = new CommandLineTable();
        dataTable.setShowVerticalLines(true);
        dataTable.setHeaders("Date", "Week day", "Weight");
        try {
            String weekDay;
            Statement myStatement = DatabaseConnection.connection.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(getDataQuery);
            while (myResultSet.next()) {
                switch (myResultSet.getByte("day_of_week")) {
                    case 0:
                        weekDay = "Saturday";
                        break;
                    case 1:
                        weekDay = "Sunday";
                        break;
                    case 2:
                        weekDay = "Monday";
                        break;
                    case 3:
                        weekDay = "Tuesday";
                        break;
                    case 4:
                        weekDay = "Wednesday";
                        break;
                    case 5:
                        weekDay = "Thursday";
                        break;
                    case 6:
                        weekDay = "Friday";
                        break;
                    default:
                        weekDay = "Unknown";
                        break;
                }
                dataTable.addRow(myResultSet.getDate("Date").toString(), weekDay,
                        Float.toString(myResultSet.getFloat("weight")));
            }
            // dataTable.print();
            return dataTable;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static CommandLineTable getWeeklyWeightTable() {
        String getWeeklyWeightQuery = "SELECT * FROM weekly_weight;";
        CommandLineTable weeklyWeightTable = new CommandLineTable();
        weeklyWeightTable.setShowVerticalLines(true);
        weeklyWeightTable.setHeaders("Start", "End", "Average Weight");
        try {
            Statement getWeeklyWeightStatement = DatabaseConnection.connection.createStatement();
            ResultSet weeklyWeightResultSet = getWeeklyWeightStatement.executeQuery(getWeeklyWeightQuery);
            while (weeklyWeightResultSet.next()) {
                weeklyWeightTable.addRow(weeklyWeightResultSet.getDate("week_start").toString(),
                        weeklyWeightResultSet.getDate("week_end").toString(),
                        Float.toString(weeklyWeightResultSet.getFloat("avg_weight")));
            }
            return weeklyWeightTable;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}