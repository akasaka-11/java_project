import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalProfessionalDAO {

    public int createMedicalProfessional(
            String name,
            int age,
            String specialization,
            Integer hospitalId
    ) throws SQLException {

        String sql = """
            INSERT INTO medical_professional(name, age, specialization, hospital_id)
            VALUES (?, ?, ?, ?) RETURNING id
        """;

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, specialization);

            if (hospitalId == null)
                ps.setNull(4, Types.INTEGER);
            else
                ps.setInt(4, hospitalId);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("id");
        }
    }

    public List<String> getAllMedicalProfessionals() throws SQLException {
        String sql = """
            SELECT mp.id, mp.name, mp.age, mp.specialization, h.name AS hospital_name
            FROM medical_professional mp
            LEFT JOIN hospitals h ON mp.hospital_id = h.id
            ORDER BY mp.id
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
                                rs.getString("specialization") + " | Hospital: " +
                                rs.getString("hospital_name")
                );
            }
        }
        return list;
    }

    public boolean updateSpecialization(int id, String newSpecialization) throws SQLException {
        String sql = "UPDATE medical_professional SET specialization = ? WHERE id = ?";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newSpecialization);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteMedicalProfessional(int id) throws SQLException {
        String sql = "DELETE FROM medical_professional WHERE id = ?";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
