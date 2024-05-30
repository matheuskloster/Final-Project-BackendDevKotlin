package com.kloster.kotlin.backend.Final.order

import org.springframework.stereotype.Service

@Service
class OrderService(private val repository: OrderRepository) {

    fun findAll(): List<Order> = repository.findAll()

    fun getById(id: Long): Order = repository.findById(id).orElseThrow { Exception("Order not found") }

    fun create(order: Order): Order = repository.save(order)

    fun update(id: Long, order: Order): Order {
        getById(id)
        return repository.save(order.copy(id = id))
    }

    fun delete(id: Long) = repository.deleteById(id)
}