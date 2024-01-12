package com.example.carservice.data.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

//@Getter
//@Setter
//@NoArgsConstructor
//@ToString
//@Entity
//@Table(name = "employee")
//public class Employee extends BaseEntity {
//    @NotBlank
//    private String firstName;
//    @NotBlank
//    private String lastName;
//
////    @ManyToMany
////    @JoinTable(
////            name = "employee_qualifications",
////            joinColumns = @JoinColumn(name = "employee_id"),
////            inverseJoinColumns = @JoinColumn(name = "qualification_id")
////    )
////    private List<Qualification> qualifications;
//}
