package com.employeemicroservice.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

   @Autowired
    EmployeeRepository employeeRepository;




    @GetMapping("/getMsg/{name}")
    public String sayHello(@PathVariable String name){
        String template= "Hi team its ";
        return template+name;
    }

    @PostMapping("/createEmp")
    public Employee createEmployee(@RequestBody Employee employee){

        return employeeRepository.save(employee);
    }

    @PutMapping("/updateEmp")
    public Employee updateEmployee(@RequestBody Employee employee){
      Optional<Employee> oldEmployee=employeeRepository.findById(employee.getEmployeeId());
        if(oldEmployee.isPresent()){
            return employeeRepository.save(employee);
        }
        return null;
    }

    @GetMapping("/searchEmp/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
//        return employeeRepository.findById(employeeId);
        return CacheOperations.cache.get(employeeId);
    }


    @GetMapping("/getAllEmployee")
    public List<Employee> getAllEmployee(){
        return  employeeRepository.findAll();

    }

    @DeleteMapping("/deleteEmp/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        if(employeeRepository.findById(employeeId).isPresent()){
            employeeRepository.deleteById(employeeId);
            return "The employee deleted successfully";
        }
        else{
            return "employee not found";
        }
    }
}
