package com.kloster.kotlin.backend.Final.product

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class ProductService(private val repository: ProductRepository) {

    fun findSort(name: String?, sortBy: String, page: Int, size: Int, direction: String): List<Product> {
        val sort = Sort.by(Sort.Direction.fromString(direction), sortBy)
        val pageable = PageRequest.of(page, size, sort)

        var products: List<Product> = emptyList()

        if (name != null) {
            products = repository.findByNameContaining(name)
        } else {
            products = repository.findAll(pageable).content
        }

        return products.sortedWith(compareBy {
            when (sortBy) {
                "price" -> if (direction == "ASC") it.price else -it.price
                "name" -> if (direction == "ASC") it.name else it.name.reversed()
                else -> if (direction == "ASC") it.id else -it.id
            }
        })


    }

    fun findAll(): List<Product> = repository.findAll()

    fun getById(id: Long): Product = repository.findById(id).orElseThrow { Exception("Product not found") }

    fun create(product: Product): Product = repository.save(product)

    fun update(id: Long, product: Product): Product {
        getById(id)
        return repository.save(product.copy(id = id))
    }

    fun delete(id: Long) {
        try {
            repository.deleteById(id)
        } catch (e: DataIntegrityViolationException) {
            throw Exception("Erro ao deletar o produto com id: $id", e)
        }
    }
}