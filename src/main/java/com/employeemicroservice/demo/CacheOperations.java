package com.employeemicroservice.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class CacheOperations {

//    to fetch the data from DB to cache
    @Autowired
    EmployeeRepository employeeRepository;

    //   cache implement
   public static HashMap<Integer,Employee> cache=new HashMap<>();
   public  List<Employee> employeeList;

   @Scheduled(cron = "0 */2 * ? * *")

    public void loadCache() {
       System.out.println("cache loading started");
        employeeList=employeeRepository.findAll();
        if(employeeList.isEmpty()){
            employeeList.stream().forEach(employee->cache.put(employee.getEmployeeId(),employee));
//        list to hashmap conversion ho gaya
        }

    }



}
