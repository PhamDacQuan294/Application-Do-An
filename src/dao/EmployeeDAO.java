package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Employee;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public static Connection openConnection() {
        Connection conn = null;
        try {
            Class.forName(DBConfig.driver);
            conn = DriverManager.getConnection(DBConfig.url, DBConfig.user, DBConfig.password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    
    // 1️⃣ Thêm nhân viên
    public boolean insertEmployee(Employee emp) {
        String sql = "INSERT INTO employees (employee_id, password, role, full_name, birth_date, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getEmployeeId());
            ps.setString(2, emp.getPassword());
            ps.setString(3, emp.getRole());
            ps.setString(4, emp.getFullName());
            ps.setDate(5, java.sql.Date.valueOf(emp.getBirthDate()));
            ps.setString(6, emp.getPhoneNumber());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // 2️⃣ Cập nhật nhân viên
    public boolean updateEmployee(Employee emp) {
        String sql = "UPDATE employees SET password = ?, role = ?, full_name = ?, birth_date = ?, phone_number = ? WHERE employee_id = ?";
        try (Connection conn = openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getPassword());
            ps.setString(2, emp.getRole());
            ps.setString(3, emp.getFullName());
            ps.setDate(4, java.sql.Date.valueOf(emp.getBirthDate()));
            ps.setString(5, emp.getPhoneNumber());
            ps.setString(6, emp.getEmployeeId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // 3️⃣ Xóa nhân viên theo ID
    public boolean deleteEmployee(String employeeId) {
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        try (Connection conn = openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, employeeId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // 4️⃣ Lấy danh sách tất cả nhân viên
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection conn = openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee(
                    rs.getString("employee_id"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getString("full_name"),
                    rs.getDate("birth_date").toLocalDate(),
                    rs.getString("phone_number")
                );
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // 5️⃣ Tìm nhân viên theo ID
    public Employee getEmployeeById(String employeeId) {
        String sql = "SELECT * FROM employees WHERE employee_id = ?";
        try (Connection conn = openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, employeeId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Employee(
                    rs.getString("employee_id"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getString("full_name"),
                    rs.getDate("birth_date").toLocalDate(),
                    rs.getString("phone_number")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
