package com.example.carservice.services;

import com.example.carservice.dto.role.CreateRoleDTO;
import com.example.carservice.dto.role.RoleDTO;

import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.List;

public interface RoleService {
    RoleDTO getRole(@Min(1) long id);
    List<RoleDTO> getRoles();
    void create(CreateRoleDTO map);

}
