package com.example.carservice.services.implementations;

import com.example.carservice.data.entity.Role;
import com.example.carservice.data.repository.RoleRepository;
import com.example.carservice.dto.role.CreateRoleDTO;
import com.example.carservice.dto.role.RoleDTO;
import com.example.carservice.services.RoleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public RoleDTO getRole(@Min(1) long id) {
        return modelMapper.map(roleRepository.findById(id).orElseThrow(
                () -> new NullPointerException("Role id is invalid!")),RoleDTO.class);
    }

    @Override
    public List<RoleDTO> getRoles() {
        return roleRepository.findAll().stream()
                .map(r -> modelMapper.map(r,RoleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void create(CreateRoleDTO role) {
        roleRepository.save(modelMapper.map(role, Role.class));
    }

}
