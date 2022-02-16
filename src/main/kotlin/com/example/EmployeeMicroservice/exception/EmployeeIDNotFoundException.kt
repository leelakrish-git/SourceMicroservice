package com.example.EmployeeMicroservice.exception

class EmployeeIDNotFoundException(employeeID: Int?) : RuntimeException(
    "Employee with this ID $employeeID is not found in the DB"
)
