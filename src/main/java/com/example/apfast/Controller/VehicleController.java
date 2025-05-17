// src/main/java/com/example/apfast/Controller/VehicleController.java
package com.example.apfast.Controller;

import com.example.apfast.Entity.Vehicle;
import com.example.apfast.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/monitor")
    public String monitorPage(Model model) {
        model.addAttribute("vehicles", vehicleService.findAll());
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("isMonitorPage", true);
        return "vehicle/list";
    }

    @GetMapping("/catalogues")
    public String cataloguesPage(Model model) {
        model.addAttribute("vehicles", vehicleService.findAll());
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("isMonitorPage", false);
        return "vehicle/list";
    }

    @PostMapping("/monitor/add")
    public String addVehicle(@ModelAttribute Vehicle vehicle, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("vehicles", vehicleService.findAll());
            model.addAttribute("isMonitorPage", true);
            return "vehicle/list";
        }
        vehicleService.create(vehicle);
        return "redirect:/monitor";
    }

    @GetMapping("/monitor/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("vehicle", vehicleService.getAcademicYearsById(id));
        model.addAttribute("vehicles", vehicleService.findAll());
        model.addAttribute("isMonitorPage", true);
        return "vehicle/list";
    }

    @PostMapping("/monitor/edit")
    public String updateVehicle(@ModelAttribute Vehicle vehicle, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("vehicles", vehicleService.findAll());
            model.addAttribute("isMonitorPage", true);
            return "vehicle/list";
        }
        vehicleService.update(vehicle);
        return "redirect:/monitor";
    }

    @GetMapping("/monitor/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.delete(id);
        return "redirect:/monitor";
    }
}