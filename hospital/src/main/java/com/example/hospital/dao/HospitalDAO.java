package com.example.hospital.dao;

import com.example.hospital.dto.HospitalDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HospitalDAO {

    private final JdbcTemplate jdbc;

    public HospitalDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<HospitalDTO> findAll() {
        return jdbc.query(
                "SELECT id, name, address FROM hospitals ORDER BY id",
                (rs, rowNum) -> new HospitalDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address")
                )
        );
    }

    public int create(String name, String address) {
        return jdbc.queryForObject(
                "INSERT INTO hospitals(name, address) VALUES (?, ?) RETURNING id",
                Integer.class,
                name, address
        );
    }

    public boolean deleteById(int id) {
        return jdbc.update("DELETE FROM hospitals WHERE id = ?", id) > 0;
    }
}
