package com.example.EmployeeMicroservice.util

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("TaxCalculaterMicroservice2")
interface FeignClient {
    @GetMapping("/employeeTax/{employeeSalary}")
    fun getTaxDetails(@PathVariable employeeSalary: String?): String?
}
