import java.sql.*;

public class DataTable {
    public static void showData() {
        // i have to move it over to database connection
        String getDataQuery = "SELECT * FROM daily_weight;";
        CommandLineTable dataTable = new CommandLineTable();
        dataTable.setShowVerticalLines(true);
        dataTable.setHeaders("Date", "Week day", "Weight");
        try {
            String weekDay;
            Statement myStatement = DatabaseConnection.connection.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(getDataQuery);
            while (myResultSet.next()) {
                // System.out.println(myResultSet.getDate("Date").toString(),myResultSet.getByte("day_of_week"),myResultSet.getFloat("weight"));
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
                // String kir = myResultSet.getFloat("weight").toString();
            }
            dataTable.print();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // dataTable.print();
    }
}