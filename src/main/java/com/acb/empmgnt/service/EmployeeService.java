package com.acb.empmgnt.service;

import java.util.List;

import com.acb.empmgnt.entity.EmployeeEntity;

public interface EmployeeService {

    EmployeeEntity create(EmployeeEntity employee);

    List<EmployeeEntity> getAll();

    EmployeeEntity getById(Integer id);

    EmployeeEntity update(Integer id, EmployeeEntity employee);

    void delete(Integer id);
}
