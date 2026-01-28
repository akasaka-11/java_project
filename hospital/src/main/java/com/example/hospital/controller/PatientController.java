package com.example.hospital.controller;

import com.example.hospital.dao.PatientDAO;
import com.example.hospital.dto.PatientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientDAO patientDAO;

    public PatientController(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @GetMapping
    public List<PatientDTO> getAllPatients() {
        return patientDAO.findAll();
    }

    @PostMapping
    public ResponseEntity<Map<String, Integer>> createPatient(@RequestBody PatientDTO request) {
        Integer id = patientDAO.create(
                request.name(),
                request.age(),
                request.diagnosis(),
                request.hospitalId()
        );

        return ResponseEntity
                .created(URI.create("/api/patients/" + id))
                .body(Map.of("id", id));
    }

    @PatchMapping("/{id}/diagnosis")
    public ResponseEntity<Void> updateDiagnosis(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        String diagnosis = body.get("diagnosis");
        if (diagnosis == null) return ResponseEntity.badRequest().build();

        return patientDAO.updateDiagnosis(id, diagnosis)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer id) {
        return patientDAO.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
