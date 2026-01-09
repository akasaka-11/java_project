import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalDAO {
    public int createHospital(String name, String address) throws SQLException {
        String sql = "INSERT INTO hospitals(name, address) VALUES (?, ?) RETURNING id";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, address);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("id");
        }
    }
    public List<String> getAllHospitals() throws SQLException {
        String sql = "SELECT id, name, address FROM hospitals ORDER BY id";
        List<String> list = new ArrayList<>();

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getString("address"));
            }
        }
        return list;
    }
    public boolean updateHospitalAddress(int id, String newAddress) throws SQLException {
        String sql = "UPDATE hospitals SET address = ? WHERE id = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newAddress);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }
    }
    public boolean deleteHospital(int id) throws SQLException {
        String sql = "DELETE FROM hospitals WHERE id = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
