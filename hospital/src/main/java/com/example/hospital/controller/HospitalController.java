package com.example.hospital.controller;

import com.example.hospital.dao.HospitalDAO;
import com.example.hospital.dto.HospitalDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    private final HospitalDAO hospitalDAO;

    public HospitalController(HospitalDAO hospitalDAO) {
        this.hospitalDAO = hospitalDAO;
    }

    @GetMapping
    public List<HospitalDTO> getAllHospitals() {
        return hospitalDAO.findAll();
    }

    @PostMapping
    public ResponseEntity<HospitalDTO> createHospital(
            @RequestBody HospitalDTO request
    ) {
        Integer id = hospitalDAO.create(
                request.name(),
                request.address()
        );

        HospitalDTO created = new HospitalDTO(
                id,
                request.name(),
                request.address()
        );

        return ResponseEntity
                .created(URI.create("/api/hospitals/" + id))
                .body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospital(
            @PathVariable Integer id
    ) {
        return hospitalDAO.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
