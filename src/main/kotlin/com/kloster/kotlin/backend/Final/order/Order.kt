package com.kloster.kotlin.backend.Final.order

import com.kloster.kotlin.backend.Final.customer.Customer
import com.kloster.kotlin.backend.Final.product.Product
import jakarta.persistence.*

@Entity(name = "\"order\"")
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    val customer: Customer = Customer(),

    @ManyToMany
    @JoinTable(
        name = "\"order_products\"",
        joinColumns = [JoinColumn(name = "order_id")],
        inverseJoinColumns = [JoinColumn(name = "product_id")]
    )
    val products: List<Product> = emptyList()
)