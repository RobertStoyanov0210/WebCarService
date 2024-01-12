package com.example.carservice.web.view.controllers;

import com.example.carservice.dto.brand.CreateBrandDTO;
import com.example.carservice.dto.brand.UpdateBrandDTO;
import com.example.carservice.services.BrandService;
import com.example.carservice.web.view.model.brand.BrandViewModel;
import com.example.carservice.web.view.model.brand.CreateBrandViewModel;
import com.example.carservice.web.view.model.brand.UpdateBrandViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/brands")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class BrandController {
    private final BrandService brandService;
    private final ModelMapper mapper;

    @GetMapping("/")
    public String getBrands(Model model){
        model.addAttribute("brands",brandService.getBrands().stream()
                .map(b -> mapper.map(b, BrandViewModel.class))
                .collect(Collectors.toList()));
        return "/brands/brands";
    }
    @GetMapping("/create-brand")
    public String showBrandCreateForm(Model model){
        model.addAttribute("brand",new CreateBrandViewModel());
        return "/brands/create-brand";
    }
    @PostMapping("/create")
    public String createBrand(@Valid @ModelAttribute("brand")CreateBrandViewModel brand, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/brands/create-brand";
        }
        brandService.create(mapper.map(brand,CreateBrandDTO.class));
        return "redirect:/brands/";
    }
    @GetMapping("/edit/{id}")
    public String showBrandEditForm(Model model, @PathVariable long id){
        UpdateBrandViewModel brand = mapper.map(brandService.getBrand(id),UpdateBrandViewModel.class);
        model.addAttribute("brand",brand);

        return "/brands/edit-brand";
    }
    @PostMapping("/update/{id}")
    public String updateBrand(@PathVariable long id,@Valid @ModelAttribute("brand") UpdateBrandViewModel brand,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/brands/edit-brand";
        }
        brandService.updateBrand(id,mapper.map(brand, UpdateBrandDTO.class));
        return "redirect:/brands/";
    }
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteBrand(@PathVariable long id){
        brandService.deleteBrand(id);
        return "redirect:/brands/";
    }
}
