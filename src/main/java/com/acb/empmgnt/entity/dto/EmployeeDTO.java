package com.acb.empmgnt.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeDTO {

    @NotNull
    private Integer employeeNumber;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    private String email;

    private String extension;
    private String officeCode;
    private Integer reportsTo;
    private String jobTitle;


    // getters & setters
    
}
