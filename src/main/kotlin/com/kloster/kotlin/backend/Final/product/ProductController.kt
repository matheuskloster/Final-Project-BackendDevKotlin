package com.kloster.kotlin.backend.Final.productpackage

import com.kloster.kotlin.backend.Final.product.Product
import com.kloster.kotlin.backend.Final.product.ProductService
import io.swagger.annotations.Api
import org.hibernate.query.SortDirection
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
@Api(value = "Product Controller", description = "API operations for products")
class ProductController(private val service: ProductService) {

    @GetMapping("/search")
    fun findAll(@RequestParam(required = false) name: String,
                @RequestParam(defaultValue = "name") sortBy: String,
                @RequestParam(defaultValue = "0") page: Int,
                @RequestParam(defaultValue = "10") size: Int,
                @RequestParam(defaultValue = "ASC") direction: String
    ) = service.findSort(name, sortBy, page, size, direction)

    @GetMapping
    fun findAll() = service.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = service.getById(id)

    @PostMapping
    fun create(@RequestBody product: Product) = service.create(product)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody product: Product) = service.update(id, product)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)
}
