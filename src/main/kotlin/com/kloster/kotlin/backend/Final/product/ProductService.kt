package com.kloster.kotlin.backend.Final.product

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class ProductService(private val repository: ProductRepository) {

    fun findSort(name: String?, sortBy: String, page: Int, size: Int): List<Product> {
        val sort = Sort.by(Sort.Direction.ASC, sortBy)
        val pageable = PageRequest.of(page, size, sort)

        var products: List<Product>  = emptyList()

        if (name != null) {
            products = repository.findByNameContaining(name)
        } else {
            products =repository.findAll(pageable).content
        }

        return products.sortedWith(compareBy {
            when (sortBy) {
                "price" -> it.price
                "name" -> it.name
                else -> it.id
            }
        })


    }

    fun findByName(name: String): List<Product> = repository.findByNameContaining(name)

    fun findAll(): List<Product> = repository.findAll()

    fun getById(id: Long): Product = repository.findById(id).orElseThrow { Exception("Product not found") }

    fun create(product: Product): Product = repository.save(product)

    fun update(id: Long, product: Product): Product {
        getById(id)
        return repository.save(product.copy(id = id))
    }

    fun delete(id: Long) = repository.deleteById(id)
}