package com.example.EmployeeMicroservice.controller

import com.example.EmployeeMicroservice.configuration.SwaggerConfiguration
import com.example.EmployeeMicroservice.exception.ConnectionFailureException
import com.example.EmployeeMicroservice.model.Employee
import com.example.EmployeeMicroservice.service.EmployeeService
import com.example.EmployeeMicroservice.util.FeignClient
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Api(tags = [SwaggerConfiguration.EMPLOYEE_TAG])
@RestController
@RequestMapping("/employee")
class EmployeeController(val employeeService: EmployeeService, val feignClient: FeignClient) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @ApiOperation(value = "Tax Details of employee")
    @ApiResponses(
        ApiResponse(code = 200, message = "Tax data received successfully"),
        ApiResponse(code = 400, message = "Missing Mandatory Fields."),
        ApiResponse(code = 404, message = "Not Found.")
    )
    @GetMapping("/feign/{employeeId}")
    fun getEmployeeTaxDetails(@PathVariable employeeId: Int): String? {
        log.info("Entered into the employee controller to get taxdetails from TaxCalculaterMicroservice2::")
        return feignClient.getTaxDetails(employeeService.getEmployeeDetailsWithID(employeeId))
    }

    @ApiOperation(value = "Create a new Employee")
    @ApiResponses(
        ApiResponse(code = 200, message = "Employee Created Successful."),
        ApiResponse(code = 400, message = "Missing Mandatory Fields."),
        ApiResponse(code = 404, message = "Not Found.")
    )
    @PostMapping
    fun addEmployee(@Valid @RequestBody employee: Employee) {
        log.info("Entered into the employee controller::")
        return employeeService.addEmployee(employee)
    }

    @ApiOperation(value = "Get All EmployeeList")
    @ApiResponses(
        ApiResponse(code = 200, message = "EmployeeList Iteration Successful.")
    )
    @GetMapping
    fun getEmployee(): Iterable<Employee> {
        log.info("Entered into the employee controller to getEmployee data::")
        return employeeService.getEmployee()
    }

    @ApiOperation(value = "Update Employee")
    @ApiResponses(
        ApiResponse(code = 200, message = "Update Creation Successful."),
        ApiResponse(code = 400, message = "Missing Mandatory Fields.")
    )
    @PutMapping
    fun updateEmployee(@RequestBody employee: Employee): String {
        log.info("Entered into the employee controller to update employee data::")
        return employeeService.updateEmployee(employee)
    }

    @ApiOperation(value = "Delete Employee data")
    @ApiResponses(
        ApiResponse(code = 200, message = "Employee data deleted Successfully."),
        ApiResponse(code = 400, message = "Missing Mandatory Fields.")
    )
    @DeleteMapping("/{employeeId}")
    fun deleteEmployee(@PathVariable employeeId: Int): String {
        log.info("Entered into the employee controller to delete employee with ID::$employeeId")
        return employeeService.deleteEmployee(employeeId)
    }
}
