package com.acb.empmgnt.controller;

import com.acb.empmgnt.entity.EmployeeEntity;
import com.acb.empmgnt.entity.dto.EmployeeDTO;
import com.acb.empmgnt.service.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@Tag(name = "Employee API")
public class EmployeeControllerV1 {

    private final EmployeeService service;

    public EmployeeControllerV1(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeEntity create(@Valid @RequestBody EmployeeDTO dto) {
        return service.create(convertDtoToEntityMap(dto));
    }

    @GetMapping
    public List<EmployeeEntity> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeEntity getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public EmployeeEntity update(@PathVariable Integer id, @RequestBody EmployeeDTO dto) {
        return service.update(id, convertDtoToEntityMap(dto));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "Deleted successfully";
    }

    private EmployeeEntity convertDtoToEntityMap(EmployeeDTO dto) {
        EmployeeEntity e = new EmployeeEntity();

        e.setEmployeeNumber(dto.getEmployeeNumber());
        e.setFirstName(dto.getFirstName());
        e.setLastName(dto.getLastName());
        e.setEmail(dto.getEmail());
        e.setJobTitle(dto.getJobTitle());
        e.setExtension(dto.getExtension());
        e.setOfficeCode(dto.getOfficeCode());
        e.setReportsTo(dto.getReportsTo());

        return e;
    }

    // ✅ Excel Download API
    @GetMapping("/report")
    public ResponseEntity<byte[]> downloadEmployeeReport() {

        byte[] excelData = service.generateEmployeeExcelReport();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData(
                "attachment", "employee-report.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(excelData);
    }
}
