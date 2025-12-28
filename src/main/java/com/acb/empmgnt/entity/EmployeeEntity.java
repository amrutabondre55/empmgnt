package com.acb.empmgnt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class EmployeeEntity {
	
	/**
	employeeNumber	int
	lastName	varchar(50)
	firstName	varchar(50)
	extension	varchar(255)
	email	varchar(255)
	officeCode	varchar(10)
	reportsTo	int
	jobTitle	varchar(50)
	
	*/

   
    
    @Id
    @Column(name = "employeeNumber")
    private Integer employeeNumber;

    @Column(name = "firstName", length = 50)
    private String firstName;

    @Column(name = "lastName", length = 50)
    private String lastName;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "extension", length = 255)
    private String extension;

    @Column(name = "officeCode", length = 10)
    private String officeCode;

    @Column(name = "reportsTo")
    private Integer reportsTo;

    @Column(name = "jobTitle", length = 50)
    private String jobTitle;


    // getters & setters
    
}
