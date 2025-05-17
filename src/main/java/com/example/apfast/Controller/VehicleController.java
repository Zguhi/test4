package com.example.apfast.Controller;

import com.example.apfast.Entity.Vehicle;
import com.example.apfast.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/monitor", "/catalogues"})
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public String listVehicles(Model model) {
        model.addAttribute("vehicles", vehicleService.findAll());
        model.addAttribute("vehicle", new Vehicle());
        return "vehicle/list";
    }

    @PostMapping("/add")
    public String addVehicle(@ModelAttribute Vehicle vehicle) {
        vehicleService.create(vehicle);
        return "redirect:/monitor";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("vehicle", vehicleService.getAcademicYearsById(id));
        return "vehicle/list";
    }

    @PostMapping("/edit")
    public String updateVehicle(@ModelAttribute Vehicle vehicle) {
        vehicleService.update(vehicle);
        return "redirect:/monitor";
    }

    @GetMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.delete(id);
        return "redirect:/monitor";
    }
}