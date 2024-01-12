package com.example.carservice.web.view.controllers;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.User;
import com.example.carservice.dto.carService.CarServiceDTO;
import com.example.carservice.dto.carService.CreateCarServiceDTO;
import com.example.carservice.dto.carService.UpdateCarServiceDTO;
import com.example.carservice.dto.carServicePricelist.CreateCarServicePricelistDTO;
import com.example.carservice.dto.repair.RepairDTO;
import com.example.carservice.services.*;
import com.example.carservice.web.view.model.brand.BrandViewModel;
import com.example.carservice.web.view.model.carService.CarServiceViewModel;
import com.example.carservice.web.view.model.carService.CreateCarServiceViewModel;
import com.example.carservice.web.view.model.carService.UpdateCarServiceViewModel;
import com.example.carservice.web.view.model.carservicePricelist.CreateCarServicePricelistViewModel;
import com.example.carservice.web.view.model.qualification.QualificationViewModel;
import com.example.carservice.web.view.model.repair.RepairViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
@RequestMapping("/carservices")
public class CarServiceController {
    private final CarServiceService carServiceService;
    private final BrandService brandService;
    private final RepairService repairService;
    private final QualificationService qualificationService;
    private final CarServicePricelistService carServicePricelistService;
    private final ModelMapper modelMapper;


    @GetMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getCarServices(Model model) {
        model.addAttribute("carServices", carServiceService.getCarServices().stream()
                .map(cs -> modelMapper.map(cs, CarServiceViewModel.class))
                .collect(Collectors.toList()));
        return "/carServices/carServices";
    }

    @GetMapping("/create-carservice")
    public String showCarServiceCreateForm(Model model) {
        model.addAttribute("carService", new CreateCarServiceViewModel());
        model.addAttribute("brands", brandService.getBrands().stream()
                .map(b -> modelMapper.map(b, BrandViewModel.class))
                .collect(Collectors.toList()));

        return "/carServices/create-carService";
    }

    @PostMapping("/create")
    public String createCarService(@Valid @ModelAttribute("carService") CreateCarServiceViewModel carService, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/carServices/create-carService";
        }
        carServiceService.createCarService(modelMapper.map(carService, CreateCarServiceDTO.class));

        return "redirect:/carservices/";
    }

    @GetMapping("/edit/{id}")
    public String showCarServiceEditForm(Model model, @PathVariable long id) {
        UpdateCarServiceViewModel carServiceViewModel = modelMapper.map(carServiceService.getCarService(id), UpdateCarServiceViewModel.class);
        model.addAttribute("carService", carServiceViewModel);
        model.addAttribute("brands", brandService.getBrands().stream()
                .map(b -> modelMapper.map(b, BrandViewModel.class))
                .collect(Collectors.toList()));

        return "/carservices/edit-carService";
    }

    @PostMapping("/update/{id}")
    public String updateCarService(@Valid @ModelAttribute("carService") UpdateCarServiceViewModel carServiceViewModel, @PathVariable long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/carservices/edit-carService";
        }
        carServiceService.updateCarService(id, modelMapper.map(carServiceViewModel, UpdateCarServiceDTO.class));

        return "redirect:/carservices/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCarService(@PathVariable long id) {
        carServiceService.deleteCarService(id);
        return "redirect:/carservices/";
    }

    @GetMapping("/{id}/manage")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showManageCarService(@PathVariable long id, Model model) {
        CarServiceViewModel carService = modelMapper.map(carServiceService.getCarService(id), CarServiceViewModel.class);
//        model.addAttribute("id",id);
        model.addAttribute("carService", carService);
//        System.out.println("SIZE");
//        System.out.println(carService.getVehiclesInRepair().size());
//        System.out.println(carService.getVehiclesInRepair());
        model.addAttribute("repairs",repairService.getCurrentRepairs(id));
//        model.addAttribute("repairs", carService.getVehiclesInRepair());
//        model.addAttribute("pricelists",carService.getPricelist());

        return "/carServices/manage";
    }

    @GetMapping("/{id}/prices/create-pricelist")
    public String showPricelistCreateForm(Model model, @PathVariable long id) {
        model.addAttribute("qualifications", qualificationService.getQualifications().stream()
                .map(q -> modelMapper.map(q, QualificationViewModel.class))
                .collect(Collectors.toList()));
        model.addAttribute("pricelist", new CreateCarServicePricelistViewModel());
        CarServiceViewModel carService = modelMapper.map(carServiceService.getCarService(id), CarServiceViewModel.class);
        model.addAttribute("carService",carService);
        return "/pricelists/create-pricelist";
    }
    @PostMapping("/{id}/prices/create")
    public String createPricelist(@PathVariable long id, @Valid @ModelAttribute("pricelist") CreateCarServicePricelistViewModel pricelist,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
//            System.out.println("ERRORRRRRRRRRRRRRRRRRRRRS");
            System.out.println(bindingResult.getAllErrors());
            String r = "redirect:/" + id + "/prices/create-pricelist";
            System.out.println(r);
            return r;
        }
//        System.out.println("SUCESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
        carServicePricelistService.createPricelist(modelMapper.map(pricelist,CreateCarServicePricelistDTO.class));

        return "redirect:/carservices/" + id + "/manage";
    }
    //Gets all current repairs

    @GetMapping("/{id}/repairs")
    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
    public String getCurrentRepairs(@PathVariable("id") long carServiceId, Model model){
        Set<RepairViewModel> repairs = repairService.getCurrentRepairs(carServiceId).stream()
                .map(r -> modelMapper.map(r, RepairViewModel.class))
                .collect(Collectors.toSet());

//        System.out.println(repairs);
        model.addAttribute("repairs",repairs);
        return "/carServices/repairs";
    }
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @GetMapping("/history")
    public String getCarServiceHistory(Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("repairs",repairService.getCarServiceHistoryRepairs(user.getCarService().getId()));

        return "/carServices/carservice-history";
    }
}
