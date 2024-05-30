package com.kloster.kotlin.backend.Final.product

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import org.springframework.data.annotation.Id


@Entity
data class Product(
    @jakarta.persistence.Id @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0
) {

}