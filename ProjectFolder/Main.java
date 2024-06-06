import java.sql.*;


public class Main
{
    public static String url = "jdbc:mysql://localhost:3306/project";
    public static String username = "java";
    public static String password = "password";
    public static void main(String[] args)
    {
        
        System.out.println("My First Java Program.");
        System.out.println("Connecting database ...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
};