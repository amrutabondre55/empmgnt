package com.acb.empmgnt.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.acb.empmgnt.entity.EmployeeEntity;
import com.acb.empmgnt.exception.ResourceNotFoundException;
import com.acb.empmgnt.repository.EmployeeRepository;

import ch.qos.logback.classic.Logger;

import java.io.ByteArrayOutputStream;



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

    @Override
    public byte[] generateEmployeeExcelReport() {

        List<EmployeeEntity> employees = repository.findAll();

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Employees");

            // 🔹 Header row
            Row headerRow = sheet.createRow(0);

            String[] headers = {
                    "Employee Number", "First Name", "Last Name",
                    "Email", "Job Title", "Office Code"
            };

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // 🔹 Data rows
            int rowIndex = 1;
            for (EmployeeEntity emp : employees) {
                Row row = sheet.createRow(rowIndex++);

                row.createCell(0).setCellValue(emp.getEmployeeNumber());
                row.createCell(1).setCellValue(emp.getFirstName());
                row.createCell(2).setCellValue(emp.getLastName());
                row.createCell(3).setCellValue(emp.getEmail());
                row.createCell(4).setCellValue(emp.getJobTitle());
                row.createCell(5).setCellValue(emp.getOfficeCode());
            }

//            // 🔹 Auto-size columns
//            for (int i = 0; i < headers.length; i++) {
//                sheet.autoSizeColumn(i);
//            }

            workbook.write(out);
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate Excel report", e);
        }
    }
}
