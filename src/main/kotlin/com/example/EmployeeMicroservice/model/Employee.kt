package com.example.EmployeeMicroservice.model

import io.swagger.annotations.ApiModelProperty
import org.hibernate.annotations.UpdateTimestamp
import java.util.Date
import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "employee")
class Employee(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    var employeeId: Int? = null,

    @ApiModelProperty(value = "Employee Name", example = "1", required = true)
    @NotBlank(message = "EmployeeName cannot be null or blank.")
    @Column(name = "employee_name")
    var employeeName: String,

    @ApiModelProperty(value = "Employee Email", example = "Test@gmail.com", required = true)
    @NotBlank(message = "Email cannot be null or blank.")
    @Column(name = "employee_email")
    var employeeEmail: String,

    @ApiModelProperty(value = "Employee Age", example = "29", required = true)
    @NotBlank(message = "employeeAge cannot be null or blank.")
    @Column(name = "employee_age")
    var employeeAge: String,

    @ApiModelProperty(value = "Employee Salary", example = "20000", required = true)
    @NotBlank(message = "employeeSalary cannot be null or blank.")
    @Column(name = "employee_salary")
    var employeeSalary: String,

    @ApiModelProperty(value = "Employee Address", example = "Newyork", required = true)
    @NotBlank(message = "employeeAddress cannot be null or blank.")
    @Column(name = "employee_address")
    var employeeAddress: String,

    @Basic(optional = false)
    @Column(name = "created_time")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    val createdAt: Date? = null,
    @Basic(optional = false)
    @Column(name = "modified_time")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    val modifiedAt: Date? = null
)
