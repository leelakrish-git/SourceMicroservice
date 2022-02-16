package com.example.EmployeeMicroservice.repository

import com.example.EmployeeMicroservice.model.Employee
import org.springframework.data.repository.CrudRepository

interface EmployeeRepositary : CrudRepository<Employee?, Int?>
