package com.example.carservice.web.view.model.employee;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Qualification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeViewModel {
    private long id;
    private String firstName;
    private String secondName;
    private String username;
    private CarService carService;
    private Set<Qualification> qualifications;
}
