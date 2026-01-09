import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    public int createPatient(String name, int age, String diagnosis, Integer hospitalId) throws SQLException {
        String sql = "INSERT INTO patients(name, age, diagnosis, hospital_id) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, diagnosis);

            if (hospitalId == null) ps.setNull(4, Types.INTEGER);
            else ps.setInt(4, hospitalId);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("id");
        }
    }
    public List<String> getAllPatients() throws SQLException {
        String sql = """
            SELECT p.id, p.name, p.age, p.diagnosis, h.name AS hospital_name
            FROM patients p
            LEFT JOIN hospitals h ON p.hospital_id = h.id
            ORDER BY p.id
        """;
        List<String> list = new ArrayList<>();

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getInt("age") + " | " +
                                rs.getString("diagnosis") + " | Hospital: " +
                                rs.getString("hospital_name")
                );
            }
        }
        return list;
    }
    public boolean updateDiagnosis(int patientId, String newDiagnosis) throws SQLException {
        String sql = "UPDATE patients SET diagnosis = ? WHERE id = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newDiagnosis);
            ps.setInt(2, patientId);
            return ps.executeUpdate() > 0;
        }
    }
    public boolean deletePatient(int patientId) throws SQLException {
        String sql = "DELETE FROM patients WHERE id = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patientId);
            return ps.executeUpdate() > 0;
        }
    }
}
