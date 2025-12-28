package com.acb.empmgnt.service;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.acb.empmgnt.entity.EmployeeEntity;
import com.acb.empmgnt.exception.ResourceNotFoundException;
import com.acb.empmgnt.repository.EmployeeRepository;

import ch.qos.logback.classic.Logger;



@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger =  (Logger) LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    public EmployeeEntity create(EmployeeEntity employee) {
        logger.info("Creating employee {}", employee.getEmployeeNumber());
        return repository.save(employee);
    }

    public List<EmployeeEntity> getAll() {
        return repository.findAll();
    }

    public EmployeeEntity getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id " + id));
    }

    public EmployeeEntity update(Integer id, EmployeeEntity employee) {
        EmployeeEntity existing = getById(id);
        existing.setFirstName(employee.getFirstName());
        existing.setLastName(employee.getLastName());
        existing.setEmail(employee.getEmail());
        existing.setJobTitle(employee.getJobTitle());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        getById(id);
        repository.deleteById(id);
    }
}
