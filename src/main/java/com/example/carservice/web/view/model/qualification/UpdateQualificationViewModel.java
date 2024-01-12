package com.example.carservice.web.view.model.qualification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateQualificationViewModel {
    @NotBlank
    private String name;
}
