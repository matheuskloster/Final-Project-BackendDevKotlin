package com.kloster.kotlin.backend.Final.customer

import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service


@Service
class CustomerService(private val repository: CustomerRepository) {


    fun findAll(): List<Customer> {
        return repository.findAll()
    }

    fun getById(id: Long): Customer = repository.findById(id).orElseThrow { Exception("Customer not found") }

    fun create(customer: Customer): Customer = repository.save(customer)

    fun update(id: Long, customer: Customer): Customer {
        getById(id)
        return repository.save(customer.copy(id = id))
    }

    fun delete(id: Long) = repository.deleteById(id)
}