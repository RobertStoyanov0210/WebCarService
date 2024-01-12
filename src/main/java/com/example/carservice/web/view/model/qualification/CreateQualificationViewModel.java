package com.example.carservice.web.view.model.qualification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CreateQualificationViewModel {
    @NotBlank
    private String name;
}
