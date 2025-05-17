package com.example.apfast.Service;

import com.example.apfast.Entity.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> findAll();
    Vehicle getAcademicYearsById(long id);
    Vehicle create(Vehicle employee);
    Vehicle update(Vehicle employee);
    void delete(Long id);
}
