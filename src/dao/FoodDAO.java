package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Food;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {

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

    //1️⃣ Thêm món ăn vào database
    public boolean insertFood(Food food) {
        String sql = "INSERT INTO foods (food_id, food_name, price, image_path, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, food.getMaMonAn()); // Mã món ăn (String)
            ps.setString(2, food.getTenMonAn());
            ps.setDouble(3, food.getGiaTien());
            ps.setString(4, food.getHinhAnh());
            ps.setString(5, food.getTrangThai());

            return ps.executeUpdate() > 0; // Nếu insert thành công thì trả về true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2️⃣ Cập nhật thông tin món ăn
    public boolean updateFood(Food food) {
        String sql = "UPDATE foods SET food_name = ?, price = ?, image_path = ?, status = ? WHERE food_id = ?";
        try (Connection conn = openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, food.getTenMonAn());
            ps.setDouble(2, food.getGiaTien());
            ps.setString(3, food.getHinhAnh());
            ps.setString(4, food.getTrangThai());
            ps.setString(5, food.getMaMonAn()); // WHERE food_id = ?

            return ps.executeUpdate() > 0; // Nếu update thành công thì trả về true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3️⃣ Xóa món ăn khỏi database
    public boolean deleteFood(String foodId) {
        String sql = "DELETE FROM foods WHERE food_id = ?";
        try (Connection conn = openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, foodId);
            return ps.executeUpdate() > 0; // Nếu delete thành công thì trả về true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4️⃣ Lấy danh sách tất cả món ăn
    public List<Food> getAllFoods() {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT * FROM foods";
        try (Connection conn = openConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Food food = new Food(
                        rs.getString("food_id"),
                        rs.getString("food_name"),
                        rs.getDouble("price"),
                        rs.getString("image_path"),
                        rs.getString("status")
                );
                list.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 5️⃣ Tìm món ăn theo mã
    public Food getFoodById(String foodId) {
        String sql = "SELECT * FROM foods WHERE food_id = ?";
        try (Connection conn = openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, foodId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Food(
                        rs.getString("food_id"),
                        rs.getString("food_name"),
                        rs.getDouble("price"),
                        rs.getString("image_path"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Food> getFoodByName(String foodName) {
        List<Food> foodList = new ArrayList<>();
        String sql = "SELECT * FROM foods WHERE food_name LIKE ?";  // Truy vấn tìm kiếm món ăn theo tên

        try (Connection conn = openConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Sử dụng '%' để tìm kiếm theo chuỗi con trong tên món ăn
            stmt.setString(1, "%" + foodName + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Tạo đối tượng Food từ dữ liệu trả về
                Food food = new Food(
                        rs.getString("food_id"), // Mã món ăn
                        rs.getString("food_name"), // Tên món ăn
                        rs.getDouble("price"), // Giá món ăn
                        rs.getString("image_path"), // Đường dẫn ảnh món ăn
                        rs.getString("status") // Trạng thái món ăn
                );

                // Thêm món ăn vào danh sách
                foodList.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foodList;
    }

    // 6️⃣ Lấy danh sách món ăn theo trạng thái
    public List<Food> getFoodsByStatus(String status) {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT * FROM foods WHERE status = ?";
        try (Connection conn = openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Food food = new Food(
                        rs.getString("food_id"),
                        rs.getString("food_name"),
                        rs.getDouble("price"),
                        rs.getString("image_path"),
                        rs.getString("status")
                );
                list.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 7️⃣ Lấy danh sách món ăn sắp xếp theo giá
    public List<Food> getFoodsSortedByPrice(String order) {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT * FROM foods ORDER BY price " + (order.equalsIgnoreCase("Tăng dần") ? "ASC" : "DESC");

        try (Connection conn = openConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Food food = new Food(
                        rs.getString("food_id"),
                        rs.getString("food_name"),
                        rs.getDouble("price"),
                        rs.getString("image_path"),
                        rs.getString("status")
                );
                list.add(food);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
