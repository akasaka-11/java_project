package com.example.hospital.dto;

public record PatientDTO(
        Integer id,
        String name,
        Integer age,
        String diagnosis,
        Integer hospitalId,
        String hospitalName
) {}
