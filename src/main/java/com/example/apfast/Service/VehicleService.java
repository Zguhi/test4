package com.example.apfast.Service;

import com.example.apfast.Entity.Vehicle;
import java.util.List;

public interface VehicleService {
    List<Vehicle> findAll();
    Vehicle getAcademicYearsById(Long id);
    Vehicle create(Vehicle vehicle);
    Vehicle update(Vehicle vehicle);
    void delete(Long id);
}