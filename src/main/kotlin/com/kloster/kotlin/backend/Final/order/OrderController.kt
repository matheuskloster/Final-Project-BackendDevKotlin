package com.kloster.kotlin.backend.Final.order

import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
@Api(value = "Order Controller", description = "API operations for orders")
class OrderController(private val service: OrderService) {

    @GetMapping
    fun findAll() = service.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = service.getById(id)

    @PostMapping
    fun create(@RequestBody order: Order) = service.create(order)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody order: Order) = service.update(id, order)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)
}