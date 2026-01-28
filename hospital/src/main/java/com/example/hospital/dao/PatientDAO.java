package com.example.hospital.dao;

import com.example.hospital.dto.PatientDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientDAO {

    private final JdbcTemplate jdbcTemplate;

    public PatientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer create(String name, Integer age, String diagnosis, Integer hospitalId) {
        if (hospitalId == null) {
            return jdbcTemplate.queryForObject(
                    "INSERT INTO patients(name, age, diagnosis, hospital_id) VALUES (?, ?, ?, NULL) RETURNING id",
                    Integer.class,
                    name, age, diagnosis
            );
        }

        return jdbcTemplate.queryForObject(
                "INSERT INTO patients(name, age, diagnosis, hospital_id) VALUES (?, ?, ?, ?) RETURNING id",
                Integer.class,
                name, age, diagnosis, hospitalId
        );
    }

    public List<PatientDTO> findAll() {
        String sql = """
            SELECT p.id, p.name, p.age, p.diagnosis, p.hospital_id,
                   h.name AS hospital_name
            FROM patients p
            LEFT JOIN hospitals h ON p.hospital_id = h.id
            ORDER BY p.id
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new PatientDTO(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("diagnosis"),
                (Integer) rs.getObject("hospital_id"),
                rs.getString("hospital_name")
        ));
    }

    public boolean updateDiagnosis(Integer id, String diagnosis) {
        return jdbcTemplate.update(
                "UPDATE patients SET diagnosis = ? WHERE id = ?",
                diagnosis, id
        ) > 0;
    }

    public boolean deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM patients WHERE id = ?", id) > 0;
    }
}
