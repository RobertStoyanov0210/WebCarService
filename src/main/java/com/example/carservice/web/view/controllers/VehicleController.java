package com.example.carservice.web.view.controllers;

import com.example.carservice.data.entity.Reservation;
import com.example.carservice.data.entity.User;
import com.example.carservice.dto.vehicle.CreateVehicleDTO;
import com.example.carservice.dto.vehicle.VehicleDto;
import com.example.carservice.services.BrandService;
import com.example.carservice.services.VehicleService;
import com.example.carservice.web.view.model.brand.BrandViewModel;
import com.example.carservice.web.view.model.vehicle.CreateVehicleViewModel;
import com.example.carservice.web.view.model.vehicle.VehicleViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    @GetMapping("/my")
    public String getCustomerVehicles(Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("vehicles",user.getMyVehicles().stream()
                .map(v -> modelMapper.map(v, VehicleViewModel.class))
                        .map(vehicleViewModel -> {
                            Set<Reservation> active = vehicleViewModel.getReservations().stream().filter(r -> r.isComplete() == false).collect(Collectors.toSet());
                            vehicleViewModel.setReservations(active);
                                    return vehicleViewModel;
                                }
                        )
                .collect(Collectors.toSet()));

        return "/vehicles/my-vehicles";
    }

    //vehicles(customer)
    @GetMapping("/create-vehicle")
    public String showVehicleCreateForm(Model model){
        model.addAttribute("vehicle",new CreateVehicleViewModel());
        model.addAttribute("brands",brandService.getBrands().stream()
                .map(b -> modelMapper.map(b, BrandViewModel.class))
                .collect(Collectors.toList()));

        return "/vehicles/create-vehicle";
    }
    @PostMapping("/create")
    public String createVehicle(@Valid @ModelAttribute("vehicle") CreateVehicleViewModel vehicle, BindingResult bindingResult,Authentication authentication){
        System.out.println(bindingResult.getAllErrors());
        if(bindingResult.hasErrors()){
            return "/vehicles/create-vehicle";
        }
        User user = (User) authentication.getPrincipal();
        vehicle.setOwner(user);

        vehicleService.createVehicle(modelMapper.map(vehicle, CreateVehicleDTO.class));

        return "redirect:/vehicles/my";
    }
}
