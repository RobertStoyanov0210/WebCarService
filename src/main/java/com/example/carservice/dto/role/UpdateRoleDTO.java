package com.example.carservice.dto.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateRoleDTO {
    @NotBlank
    private String authority;
}
