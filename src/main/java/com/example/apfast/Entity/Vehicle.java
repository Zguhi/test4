package com.example.apfast.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "vehicle_name")
    private String vehicleName;

    @Column(name = "vehicle_model")
    private String vehicleModel;

    @Column(name = "year_of_manufacture")
    private Integer yearOfManufacture;

    @Column(name = "vehicle_color")
    private String vehicleColor;
}