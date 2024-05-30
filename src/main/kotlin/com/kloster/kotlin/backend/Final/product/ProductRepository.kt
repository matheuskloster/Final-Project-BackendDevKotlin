package com.kloster.kotlin.backend.Final.product

import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
    fun findByNameContaining(name: String): List<Product>

    fun findByName(name: String): List<Product>

}
