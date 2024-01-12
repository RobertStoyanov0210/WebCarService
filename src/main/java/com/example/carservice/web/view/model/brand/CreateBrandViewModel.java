package com.example.carservice.web.view.model.brand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateBrandViewModel {
    @NotBlank
    private String name;
}
