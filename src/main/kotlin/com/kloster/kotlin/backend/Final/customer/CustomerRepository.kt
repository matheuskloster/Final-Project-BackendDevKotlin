package com.kloster.kotlin.backend.Final.customer

import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long>{
}