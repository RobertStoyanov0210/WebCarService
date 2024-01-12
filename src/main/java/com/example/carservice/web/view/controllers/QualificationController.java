package com.example.carservice.web.view.controllers;

import com.example.carservice.data.entity.Qualification;
import com.example.carservice.dto.qualification.CreateQualificationDTO;
import com.example.carservice.dto.qualification.QualificationDTO;
import com.example.carservice.dto.qualification.UpdateQualificationDTO;
import com.example.carservice.services.QualificationService;
import com.example.carservice.web.view.model.qualification.CreateQualificationViewModel;
import com.example.carservice.web.view.model.qualification.QualificationViewModel;
import com.example.carservice.web.view.model.qualification.UpdateQualificationViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor
@RequestMapping("/qualifications")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class QualificationController {
    private final QualificationService qualificationService;
    private final ModelMapper modelMapper;

    @GetMapping("/")
    public String getQualifications(Model model){
        model.addAttribute("qualifications",qualificationService.getQualifications().stream()
                .map(q -> modelMapper.map(q, QualificationViewModel.class))
                .collect(Collectors.toList()));
        model.addAttribute("sortDirection", "asc");
        return "/qualifications/qualifications";
    }
    @GetMapping("/sort-by-name/{sortDirection}")
    public String getQualifications(Model model, @PathVariable String sortDirection){
        model.addAttribute("qualifications",qualificationService.getQualificationsSortedByName(sortDirection).stream()
                .map(q -> modelMapper.map(q, QualificationViewModel.class))
                .collect(Collectors.toList()));
        model.addAttribute("sortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        return "/qualifications/qualifications";
    }
    @GetMapping("/pagination/page/{page}/size/{size}")
    public String getSchoolsPagination(Model model, @PathVariable int page, @PathVariable int size) {
        Type pageType = new TypeToken<Page<QualificationViewModel>>() {
        }.getType();
        final Page<QualificationViewModel> pageOfQualifications =
                modelMapper.map(qualificationService.getSchoolsPagination(PageRequest.of(page - 1, size)), pageType);

        model.addAttribute("pageOfQualifications", pageOfQualifications);
        int totalPages = pageOfQualifications.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "/qualifications/qualifications-pagination";
    }

    @GetMapping("/create-qualification")
    public String showQualificationCreateForm(Model model){
        model.addAttribute("qualification",new CreateQualificationViewModel());
        return "/qualifications/create-qualification";
    }
    @PostMapping("/create")
    public String createQualification(@Valid @ModelAttribute("qualification") CreateQualificationViewModel qualification, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/qualifications/create-qualification";
        }
        qualificationService.create(modelMapper.map(qualification, CreateQualificationDTO.class));
        return "redirect:/qualifications/";
    }
    @GetMapping("/edit/{id}")
    public String showQualificationEditForm(Model model,@PathVariable Long id){
        UpdateQualificationViewModel qualification = modelMapper.map(qualificationService.getQualification(id),UpdateQualificationViewModel.class);
        model.addAttribute("qualification",qualification);
        return "/qualifications/edit-qualification";
    }
    @PostMapping("/update/{id}")
    public String updateQualification(@PathVariable long id, @Valid @ModelAttribute("qualification") UpdateQualificationViewModel qualification, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/qualifications/edit-qualification";
        }
        qualificationService.updateQualification(id, modelMapper.map(qualification,UpdateQualificationDTO.class));
        return "redirect:/qualifications/";
    }
    @GetMapping("/delete/{id}")
    public String deleteQualification(@PathVariable long id){
        qualificationService.deleteQualification(id);
        return "redirect:/qualifications/";
    }
}
