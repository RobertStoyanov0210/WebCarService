package com.example.carservice.dto.brand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateBrandDTO {
    @NotBlank
    private String name;
}
