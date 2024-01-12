package com.example.carservice.web.view.model.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UpdateRoleViewModel {
    @NotBlank
    private String authority;

}
