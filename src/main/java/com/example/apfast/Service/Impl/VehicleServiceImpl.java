package com.example.apfast.Service.Impl;

import com.example.apfast.Entity.Vehicle;
import com.example.apfast.Exception.NotFoundException;
import com.example.apfast.Repository.VehicleRepository;
import com.example.apfast.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getAcademicYearsById(Long id) {  // Thay đổi Long thành long
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found with id: " + id));
    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle update(Vehicle vehicle) {
        if (vehicle.getVehicleId() == null) {
            throw new IllegalArgumentException("Vehicle ID cannot be null for update");
        }

        Vehicle existingVehicle = getAcademicYearsById(vehicle.getVehicleId());
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void delete(Long id) {
        Vehicle vehicle = getAcademicYearsById(id);
        vehicleRepository.delete(vehicle);
    }
}