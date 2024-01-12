package com.example.carservice.dto.role;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RoleDTO {
    private long id;
    private String authority;
}
