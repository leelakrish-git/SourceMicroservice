package com.example.EmployeeMicroservice.service

import com.example.EmployeeMicroservice.exception.EmployeeIDNotFoundException
import com.example.EmployeeMicroservice.model.Employee
import com.example.EmployeeMicroservice.repository.EmployeeRepositary
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class EmployeeService(val employeeRepositary: EmployeeRepositary) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun addEmployee(employee: Employee) {
        log.info("Entered into EmployeeService to store employee Data")
        employeeRepositary.save(employee)
    }

    fun getEmployee(): Iterable<Employee> {
        log.info("Entered into EmployeeService to retrive employee list")
        return employeeRepositary.findAll() as Iterable<Employee>
    }

    fun getEmployeeDetailsWithID(employeeId: Int): String {
        log.info("Entered into EmployeeService to retrive employeeSalary with ID")
        val findById = employeeRepositary.findById(employeeId).orElseThrow<EmployeeIDNotFoundException> {
            log.info("User not found for given employeeId: $employeeId")
            throw EmployeeIDNotFoundException(employeeId)
        }
        return findById!!.employeeSalary
    }

    fun updateEmployee(employee: Employee): String {
        log.info("Entered into EmployeeService to update employee")
        val byId = employee.employeeId?.let {
            employeeRepositary.findById(it).orElseThrow<EmployeeIDNotFoundException> {
                log.info("User not found for given employeeId: " + employee.employeeId)
                throw EmployeeIDNotFoundException(employee.employeeId)
            }
        }
        employeeRepositary.save(employee)
        return "Update Employee data is sucess"
    }

    fun deleteEmployee(employeeId: Int): String {
        log.info("Entered into EmployeeService to delete employee with ID")
        employeeRepositary.findById(employeeId).orElseThrow<EmployeeIDNotFoundException> {
            log.info("User not found for given employeeId: $employeeId")
            throw EmployeeIDNotFoundException(employeeId)
        }
        employeeRepositary.deleteById(employeeId)
        return "Employee deleted successfully"
    }
}
