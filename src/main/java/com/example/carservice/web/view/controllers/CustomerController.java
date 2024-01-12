package com.example.carservice.web.view.controllers;

import com.example.carservice.data.entity.Reservation;
import com.example.carservice.data.entity.ReservationKey;
import com.example.carservice.data.entity.User;
import com.example.carservice.data.entity.Vehicle;
import com.example.carservice.data.repository.UserRepository;
import com.example.carservice.dto.customer.CreateCustomerDTO;
import com.example.carservice.dto.reservation.ReservationDTO;
import com.example.carservice.services.RepairService;
import com.example.carservice.services.ReservationService;
import com.example.carservice.services.UserService;
import com.example.carservice.web.view.model.customer.CreateCustomerViewModel;
import com.example.carservice.web.view.model.customer.CustomerViewModel;
import com.example.carservice.web.view.model.reservation.ReservationViewModel;
import com.example.carservice.web.view.model.vehicle.VehicleViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class CustomerController {
    private final UserService userService;
    private final ReservationService reservationService;
    private final RepairService repairService;
    private final ModelMapper modelMapper;

    @GetMapping("/register")
    public String showCustomerCreateForm(Model model) {
        model.addAttribute("customer", new CreateCustomerViewModel());
        return "/customers/create-customer";
    }

    @PostMapping("/register")
    public String createCustomer(@Valid @ModelAttribute("customer") CreateCustomerViewModel customerViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/customers/create-customer";
        }
        userService.createCustomer(modelMapper.map(customerViewModel, CreateCustomerDTO.class));
        return "redirect:/";
    }

    @GetMapping("/reservations/my")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public String getCustomerVehicles(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Set<Reservation> reservations = new HashSet<>();
//        for (Vehicle v : user.getMyVehicles()) {
//            reservations.addAll(v.getReservations().stream()
//                    .map(r -> modelMapper.map(r, ReservationDTO.class)).collect(Collectors.toSet()));
//        }
//
//        model.addAttribute("reservations", reservations.stream()
//                .map(v -> modelMapper.map(v, ReservationViewModel.class))
//                .collect(Collectors.toSet()));


        for (Vehicle v : user.getMyVehicles()) {
            Set<Reservation> reservationSet = v.getReservations().stream()
                    .filter(r -> r.isComplete() == false)
                    .collect(Collectors.toSet());
            reservations.addAll(reservationSet);
        }

        model.addAttribute("reservations", reservations);

//        System.out.println("CONTROLERR");
//        System.out.println(reservations.stream()
//                .map(v -> modelMapper.map(v, ReservationViewModel.class))
//                .collect(Collectors.toSet()));

        return "/customers/customer-reservations";
    }

    @PostMapping("/reservations/cancel")
    @PreAuthorize("hasAnyAuthority('CUSTOMER','ADMIN')")
    public String cancelReservation(@RequestParam("carServiceId") long carServiceId, @RequestParam("vehicleId") long vehicleId,
                                    @RequestParam("date") LocalDate date) {
        System.out.println("CANCEL CONTROLLER");
//        System.out.println(carServiceId);
//        System.out.println(vehicleId);
//        System.out.println(date);
        ReservationDTO reservationDTO = reservationService.getReservationByCarServiceAndVehicleAndDate(carServiceId, vehicleId, date);
        reservationService.cancelReservation(reservationDTO);
        return "redirect:/vehicles/my";
    }

    @GetMapping("/customer/repairs/history")
    public String showCustomerHistory(Model model,Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("repairs",repairService.getCustomerHistoryRepairs(user.getId()));

        return "/customers/repair-history";
    }
}
