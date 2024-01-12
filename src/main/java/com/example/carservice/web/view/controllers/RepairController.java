package com.example.carservice.web.view.controllers;

import com.example.carservice.data.entity.CarServicePricelist;
import com.example.carservice.data.entity.Qualification;
import com.example.carservice.data.entity.Repair;
import com.example.carservice.dto.carService.CarServiceDTO;
import com.example.carservice.dto.repair.CreateRepairDTO;
import com.example.carservice.dto.repair.RepairDTO;
import com.example.carservice.dto.reservation.UpdateReservationDTO;
import com.example.carservice.dto.vehicle.VehicleDto;
import com.example.carservice.services.CarServiceService;
import com.example.carservice.services.RepairService;
import com.example.carservice.services.ReservationService;
import com.example.carservice.services.VehicleService;
import com.example.carservice.web.view.model.qualification.QualificationViewModel;
import com.example.carservice.web.view.model.repair.CreateRepairFromReservationViewModel;
import com.example.carservice.web.view.model.repair.CreateRepairViewModel;
import com.example.carservice.web.view.model.reservation.ReservationViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class RepairController {
    private final RepairService repairService;
    private final VehicleService vehicleService;
    private final CarServiceService carServiceService;
    private final ReservationService reservationService;
    private final ModelMapper modelMapper;
    //Gets all current repairs

    @PostMapping("/repairs/create")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE','ADMIN')")
    public String createRepair(@Valid @ModelAttribute("repair") CreateRepairFromReservationViewModel repairViewModel, BindingResult bindingResult, Model model){
//        System.out.println(repairViewModel);
        if (bindingResult.hasErrors()){
//            System.out.println("POST TEST");
//            System.out.println(bindingResult.getAllErrors());
////            System.out.println(repairViewModel);
//            System.out.println("RESERVATION cs=" + repairViewModel.getCarServiceId() + " v=" + repairViewModel.getVehicleId() +  " date=" + repairViewModel.getDate());
            long carServiceId = repairViewModel.getCarServiceId();
            long vehicleId = repairViewModel.getVehicleId();
            LocalDate date = repairViewModel.getDate();
            VehicleDto vehicleDto = vehicleService.getVehicleById(vehicleId);
            long customerId = vehicleDto.getOwner().getId();
            Set<CarServicePricelist> pricelists = carServiceService.getCarService(carServiceId).getPricelist();

//            CreateRepairFromReservationViewModel repairFromReservationViewModel = new CreateRepairFromReservationViewModel();
//            repairFromReservationViewModel.setReservationViewModel(repairViewModel.getReservationViewModel());
//            model.addAttribute("repair",repairFromReservationViewModel);
            model.addAttribute("repair",repairViewModel);
            model.addAttribute("reservation",modelMapper.map(reservationService.getReservationByCarServiceAndVehicleAndDate(carServiceId, vehicleId,date), ReservationViewModel.class));
            model.addAttribute("customerId",customerId);
            Set<QualificationViewModel> qualifications = pricelists.stream()
                    .map(p -> modelMapper.map(p.getQualification(),QualificationViewModel.class))
                    .collect(Collectors.toSet());
            model.addAttribute("pricelists", qualifications);
            return "/carservices/" + carServiceId + "/reservations/"+vehicleId+ "/" + date + "/take";
        }
        //Calculating sum
        double calculatedSum = calculateSum(repairViewModel.getCreateRepairViewModel());
        repairViewModel.getCreateRepairViewModel().setSum(calculatedSum);
        //Checking if capacity is ok
        CarServiceDTO carServiceDTO = carServiceService.getCarService(repairViewModel.getCarServiceId());
        if (carServiceDTO.getVehiclesInRepair().size() < carServiceDTO.getMaxRepairingCars()){
            repairService.createRepair(modelMapper.map(repairViewModel.getCreateRepairViewModel(), CreateRepairDTO.class));
            //Closing reservation
            UpdateReservationDTO updateReservationDTO = modelMapper.map(reservationService.getReservationByCarServiceAndVehicleAndDate(repairViewModel.getCarServiceId(), repairViewModel.getVehicleId(), repairViewModel.getDate()),UpdateReservationDTO.class);
            System.out.println(updateReservationDTO);
            reservationService.closeReservation(updateReservationDTO);
        } else {
            Set<ReservationViewModel> reservations = reservationService.getCarServicesActiveReservation(repairViewModel.getCarServiceId()).stream()
                    .map(r -> modelMapper.map(r,ReservationViewModel.class))
                    .collect(Collectors.toSet());
            model.addAttribute("reservations",reservations);
            model.addAttribute("limitReached",true);
            return "/carServices/carservice-reservations";
        }


        return "redirect:/";
    }

    @GetMapping("/carservices/{carServiceId}/repairs/{repairId}/finish")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE','ADMIN')")
    public String finishRepair(@PathVariable("carServiceId") long carServiceId,@PathVariable("repairId") long repairId){
        repairService.finishRepair(repairId);

        return "redirect:" + "/carservices/" + carServiceId + "/repairs";
    }

    private double calculateSum(CreateRepairViewModel createRepairViewModel){
        double sum = 0.;
        Set<CarServicePricelist> pricelists = carServiceService.getCarService(createRepairViewModel.getCarService().getId()).getPricelist();
        Set<Qualification> selected = createRepairViewModel.getPlannedFixes();
        for (Qualification s:selected) {
            for (CarServicePricelist cp:pricelists) {
                if (cp.getQualification().equals(s)){
                    sum += cp.getPrice();
                }
            }
        }
        return sum;
    }

}
