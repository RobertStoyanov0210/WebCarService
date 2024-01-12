package com.example.carservice.web.view.controllers;

import com.example.carservice.dto.role.CreateRoleDTO;
import com.example.carservice.services.RoleService;
import com.example.carservice.web.view.model.role.CreateRoleViewModel;
import com.example.carservice.web.view.model.role.RoleViewModel;
import com.example.carservice.web.view.model.role.UpdateRoleViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/roles")
@AllArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class RoleController {
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @GetMapping("/")
    public String getRoles(Model model){
        model.addAttribute("roles",roleService.getRoles().stream()
                .map(r -> modelMapper.map(r, RoleViewModel.class))
                .collect(Collectors.toList()));
        return "/roles/roles";
    }
    @GetMapping("/create-role")
    public String showRoleCreateForm(Model model){
        model.addAttribute("role",new CreateRoleViewModel());
        return "/roles/create-role";
    }
    @PostMapping("/create")
    public String createRole(@Valid @ModelAttribute("role") CreateRoleViewModel role, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/roles/create-role";
        }
        roleService.create(modelMapper.map(role, CreateRoleDTO.class));
        return "redirect:/roles/";
    }
    @GetMapping("/edit/{id}")
    public String showRoleEditForm(Model model, @PathVariable long id){
        model.addAttribute("role",modelMapper.map(roleService.getRole(id), UpdateRoleViewModel.class));
        return "/roles/edit-role";
    }

//    @PostMapping("/update/{id}")
//    @GetMapping("/delete/{id}")
}
