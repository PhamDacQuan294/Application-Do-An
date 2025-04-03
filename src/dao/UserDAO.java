package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;

public class UserDAO {

    public static Connection openConnection() {
        Connection conn = null;
        try {
            System.out.println("Hello from DBConfig.driver " + DBConfig.driver);
            Class.forName(DBConfig.driver);
            conn = DriverManager.getConnection(DBConfig.url, DBConfig.user, DBConfig.password);
            System.out.println("connected successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static User handleLogin(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection c = openConnection(); PreparedStatement ps = c.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getString("email"),
                            rs.getString("password")
                    );
                }
            }
        } catch (Exception ex) {
            System.err.println("Error during login: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean isExistUsername(String email) {
        String query = "SELECT 1 FROM users WHERE email = ?";
        try (Connection c = openConnection(); PreparedStatement ps = c.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return true;  
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false; 
    }

    public static boolean insertUser(User user) {
        String query = "INSERT INTO USERS(full_name, email, password) VALUES (?, ?, ?)";
        try (Connection c = openConnection(); PreparedStatement ps = c.prepareStatement(query)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            int row = ps.executeUpdate();
            return row > 0; 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
