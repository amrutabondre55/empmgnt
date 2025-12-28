package com.acb.empmgnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acb.empmgnt.entity.EmployeeEntity;

public interface EmployeeRepository
        extends JpaRepository<EmployeeEntity, Integer> {
}
