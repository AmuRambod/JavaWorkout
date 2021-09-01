import java.sql.*;

public class DatabaseConnection {
    // #region Attributes
    private static String database = "workout";
    private static String userName;
    private static String password;
    private static String url;
    public static Connection connection;
    private static boolean connectionStatus = false; // true = connected
    public static String lastSaturdayDate;
    // #endregion

    // #region Getters and Setters
    public static void setUserName(String userName) {
        DatabaseConnection.userName = userName;
    }

    public static void setPassword(String password) {
        DatabaseConnection.password = password;
    }

    public static void setConnectionStatus(boolean connectionStatus) {
        DatabaseConnection.connectionStatus = connectionStatus;
    }

    public static boolean getConnectionStatus() {
        return connectionStatus;
    }

    public static void setUrl() {
        DatabaseConnection.url = "jdbc:mysql://localhost:3306/" + database;
    }
    // #endregion

    // #region Functions
    public static void getConnectionPassword() {
        setUrl();
        System.out.print("Password: ");
        setPassword(main.keyboard.nextLine());
    }

    public static void connect() {
        // ? should I check if we got the connection info ?
        try {
            connection = DriverManager.getConnection(url, userName, password);
            setConnectionStatus(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            setConnectionStatus(false);
        }
    }

    public static void insertToDailyWeight() {
        System.out.println("\n\n *** DAILY-WEIGHT ***");
        System.out.print("\nDate: ");
        String date = main.keyboard.nextLine();
        System.out.print("Day of the week: ");
        String dayWeek = main.keyboard.nextLine();
        System.out.print("Weight: ");
        String weight = main.keyboard.nextLine();
        String query = "INSERT INTO daily_weight VALUES (\'" + date + "\'," + dayWeek + "," + weight + ")";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("\nVictory!");
            statement.close();
            if (dayWeek.equals("6")) {
                System.out.println("\nend of the week!");
                insertToWeeklyWeight(date);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("---------------------");
    }

    public static void insertToWeeklyWeight(String fridayDate) {
        // getting the date of the last saturday
        String query = "SELECT MAX(date) FROM daily_weight WHERE day_of_week = 0;";
        float averageWeightOfWeek = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet lastSaturdayResultSet = statement.executeQuery(query);
            while (lastSaturdayResultSet.next()) {
                lastSaturdayDate = lastSaturdayResultSet.getDate("MAX(date)").toString();
                // System.out.println(lastSaturdayDate);
            }
            // getting the AVG(weight) for the week
            query = "SELECT AVG(weight) FROM daily_weight WHERE date >= '" + lastSaturdayDate + "' AND date <= '"
                    + fridayDate + "';";
            ResultSet averageWeightOfWeekResultSet = statement.executeQuery(query);
            while (averageWeightOfWeekResultSet.next()) {
                averageWeightOfWeek = averageWeightOfWeekResultSet.getFloat("AVG(weight)");
            }
            // System.out.println(averageWeightOfWeek);
            // inserting data to weekly_weight
            query = "INSERT INTO weekly_weight VALUES ('" + lastSaturdayDate + "','" + fridayDate + "',"
                    + averageWeightOfWeek + ");";
            statement.executeUpdate(query);
            System.out.println("Average weight of the week: " + averageWeightOfWeek);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    // #endregion
}