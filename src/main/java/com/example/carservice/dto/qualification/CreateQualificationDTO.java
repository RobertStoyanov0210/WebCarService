package com.example.carservice.dto.qualification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateQualificationDTO {
    @NotBlank
    private String name;
}
