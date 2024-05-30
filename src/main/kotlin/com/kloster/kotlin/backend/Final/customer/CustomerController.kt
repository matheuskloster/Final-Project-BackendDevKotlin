package com.kloster.kotlin.backend.Final.customer

import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.*
import java.util.logging.Logger

@Slf4j
@RestController
@RequestMapping("/customers")
class CustomerController(private val service: CustomerService) {

    private val logger = Logger.getLogger(CustomerController::class.java.name)

    @GetMapping
    fun findAll(): List<Customer> {
    logger.info("GET /customers")
        return service.findAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = service.getById(id)

    @PostMapping
    fun create(@RequestBody customer: Customer) = service.create(customer)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody customer: Customer) = service.update(id, customer)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)
}