package com.example.carservice.web.view.controllers;

import com.example.carservice.data.entity.CarServicePricelist;
import com.example.carservice.data.entity.Qualification;
import com.example.carservice.dto.qualification.QualificationDTO;
import com.example.carservice.dto.reservation.CreateReservationDTO;
import com.example.carservice.dto.reservation.ReservationDTO;
import com.example.carservice.dto.vehicle.VehicleDto;
import com.example.carservice.services.CarServiceService;
import com.example.carservice.services.QualificationService;
import com.example.carservice.services.ReservationService;
import com.example.carservice.services.VehicleService;
import com.example.carservice.web.view.model.carService.CarServiceViewModel;
import com.example.carservice.web.view.model.qualification.QualificationViewModel;
import com.example.carservice.web.view.model.repair.CreateRepairFromReservationViewModel;
import com.example.carservice.web.view.model.repair.CreateRepairViewModel;
import com.example.carservice.web.view.model.reservation.CreateReservationViewModel;
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
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ReservationController {
    private final ReservationService reservationService;
    private final CarServiceService carServiceService;
    private final VehicleService vehicleService;
    private final QualificationService qualificationService;
    private final ModelMapper modelMapper;

    @GetMapping("/carservices/reserve/{id}")
    public String showAvailableForReservation(Model model, @PathVariable("id") long vehicleId, Authentication authentication) {
        VehicleDto vehicle = vehicleService.getCustomerVehicle(authentication, vehicleId);

//        List<CarServiceDTO> carServiceDTOList = carServiceService.getCarServicesByBrand(vehicle.getBrand());
//        System.out.println(ca);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("carServices", carServiceService.getCarServicesByBrand(vehicle.getBrand()).stream()
                .map(cs -> modelMapper.map(cs, CarServiceViewModel.class))
                .collect(Collectors.toList()));
        model.addAttribute("qualifications", qualificationService.getQualifications().stream()
                .map(q -> modelMapper.map(q, QualificationViewModel.class))
                .collect(Collectors.toSet()));
        return "/customers/reservation-search-list";
    }

    @PostMapping("/carservices/filtered")
    public String showAvailableForReservationFiltered(Model model, @RequestParam("vehicleId") long vehicleId, @RequestParam("qualification") long qualificationId, Authentication authentication) {
//        System.out.println("VEHICLE ID");
//        System.out.println(vehicleId);
//        System.out.println("Quali ID");
//        System.out.println(qualificationId);
//        return "";
        VehicleDto vehicle = vehicleService.getCustomerVehicle(authentication, vehicleId);

//        List<CarServiceDTO> carServiceDTOList = carServiceService.getCarServicesByBrand(vehicle.getBrand());
//        System.out.println(ca);

        QualificationViewModel qualification = modelMapper.map(qualificationService.getQualification(qualificationId), QualificationViewModel.class);
        Set<CarServiceViewModel> carServices = carServiceService.getCarServicesByBrand(vehicle.getBrand()).stream()
                .map(cs -> modelMapper.map(cs, CarServiceViewModel.class))
                .filter(cs -> cs.getServiceQualifications().contains(qualification))
                .collect(Collectors.toSet());
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("carServices", carServices);
        model.addAttribute("qualifications", qualificationService.getQualifications().stream()
                .map(q -> modelMapper.map(q, QualificationViewModel.class))
                .collect(Collectors.toSet()));
        return "/customers/reservation-search-list";
    }

    @GetMapping("/carservices/{carServiceId}/reserve/{vehicleId}")
    public String showReservationCreateForm(Model model, @PathVariable("carServiceId") long carServiceId, @PathVariable("vehicleId") long vehicleId, Authentication authentication) {
        //TODO check if every ID is correct (make checks for them)
        VehicleDto vehicleDto = vehicleService.getCustomerVehicle(authentication, vehicleId);
        model.addAttribute("vehicle", modelMapper.map(vehicleDto, VehicleViewModel.class));
        model.addAttribute("carService", carServiceService.getCarServicesByBrand(vehicleDto.getBrand()).stream()
                .filter(cs -> cs.getId() == carServiceId)
                .map(cs -> modelMapper.map(cs, CarServiceViewModel.class))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                })
                .get());
        model.addAttribute("reservation", new CreateReservationViewModel());
        return "/customers/create-reservation";
    }

    @PostMapping("/carservices/reserve")
    public String createReservation(@Valid @ModelAttribute("reservation") CreateReservationViewModel createReservationViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(createReservationViewModel.getDate());
            System.out.println(bindingResult.getAllErrors());
            return "/customers/create-reservation";
        }
//        System.out.println(createReservationViewModel);
        reservationService.createReservation(modelMapper.map(createReservationViewModel, CreateReservationDTO.class));
        return "redirect:/vehicles/my";
    }

    @GetMapping("/reservations/{id}")
    @PreAuthorize("isAuthenticated()")
    public String viewVehicleReservations(@PathVariable("id") long vehicleId, Authentication authentication, Model model) {
        VehicleDto vehicleDto = vehicleService.getCustomerVehicle(authentication, vehicleId);
        VehicleViewModel vehicleViewModel = modelMapper.map(vehicleDto, VehicleViewModel.class);
        System.out.println(vehicleViewModel.getReservations());
        model.addAttribute("reservations", modelMapper.map(vehicleDto, VehicleViewModel.class).getReservations());

        return "/vehicles/vehicle-reservations";
    }

    //TODO add preAUTHORIZE
    @GetMapping("/carservices/{id}/reservations")
    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
    public String viewCarServiceReservations(@PathVariable("id") long carServiceId, Model model) {
        Set<ReservationViewModel> reservations = reservationService.getCarServicesActiveReservation(carServiceId).stream()
                .map(r -> modelMapper.map(r, ReservationViewModel.class))
                .collect(Collectors.toSet());
        model.addAttribute("reservations", reservations);
        model.addAttribute("limitReached", false);
        return "/carServices/carservice-reservations";
    }

    //NEED car service id, vehicle id and date, because there is no basic id
    @PreAuthorize("hasAnyAuthority('EMPLOYEE','ADMIN')")
    @GetMapping("/carservices/{carServiceId}/reservations/{vehicleId}/{date}/take")
    public String convertReservationToRepair(@PathVariable long carServiceId, @PathVariable long vehicleId, @PathVariable LocalDate date, Model model) {
//        System.out.println("RESERVATION TEST TAKE");
//        System.out.println(modelMapper.map(reservationService.getReservationByCarServiceAndVehicle(carServiceId, vehicleId,date),ReservationViewModel.class));
        ReservationDTO reservationDTO = reservationService.getReservationByCarServiceAndVehicleAndDate(carServiceId, vehicleId, date);
        VehicleDto vehicleDto = vehicleService.getVehicleById(vehicleId);
        long customerId = vehicleDto.getOwner().getId();
        Set<CarServicePricelist> pricelists = carServiceService.getCarService(carServiceId).getPricelist();

        CreateRepairFromReservationViewModel repairFromReservationViewModel = new CreateRepairFromReservationViewModel();
        repairFromReservationViewModel.setCarServiceId(reservationDTO.getCarService().getId());
        repairFromReservationViewModel.setVehicleId(reservationDTO.getVehicle().getId());
        repairFromReservationViewModel.setDate(reservationDTO.getDate());
        repairFromReservationViewModel.setCreateRepairViewModel(new CreateRepairViewModel());
//        System.out.println("GET TEST");
//        System.out.println(repairFromReservationViewModel.getCarServiceId() );
        model.addAttribute("repair", repairFromReservationViewModel);
        model.addAttribute("reservation", modelMapper.map(reservationService.getReservationByCarServiceAndVehicleAndDate(carServiceId, vehicleId, date), ReservationViewModel.class));
        model.addAttribute("customerId", customerId);
        Set<QualificationViewModel> qualifications = pricelists.stream()
                .map(p -> modelMapper.map(p.getQualification(), QualificationViewModel.class))
                .collect(Collectors.toSet());
        System.out.println("TESTING TESTING");
        System.out.println(qualifications);
        model.addAttribute("pricelists", qualifications);

        return "/carServices/create-repair";
    }
}
