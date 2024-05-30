package com.kloster.kotlin.backend.Final.product

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

class ProductServiceTest {

    @InjectMocks
    lateinit var productService: ProductService

    @Mock
    lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test findSort with name`() {
        val name = "test"
        val sortBy = "name"
        val page = 0
        val size = 10
        val pageable: Pageable = PageRequest.of(page, size, Sort.by(sortBy))

        val product = Product(1, name, "Test")
        val productList = listOf(product)

        Mockito.`when`(productRepository.findByNameContaining(name)).thenReturn(productList)

        val result = productService.findSort(name, sortBy, page, size)

        assertEquals(productList, result)
        Mockito.verify(productRepository).findByNameContaining(name)
    }


}