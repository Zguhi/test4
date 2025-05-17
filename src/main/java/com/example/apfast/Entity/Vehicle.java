package com.example.apfast.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long vehicleId;

    @NotEmpty(message = "Vehicle name is required")
    @Column(name = "vehicle_name")
    private String vehicleName;

    @NotEmpty(message = "Vehicle model is required")
    @Column(name = "vehicle_model")
    private String vehicleModel;

    @NotNull(message = "Year of manufacture is required")
    @Column(name = "year_of_manufacture")
    private Integer yearOfManufacture;

    @Column(name = "vehicle_color")
    private String vehicleColor;
}