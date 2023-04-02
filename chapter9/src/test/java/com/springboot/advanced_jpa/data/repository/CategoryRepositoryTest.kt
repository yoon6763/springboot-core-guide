package com.springboot.advanced_jpa.data.repository

import com.springboot.advanced_jpa.data.entity.Category
import com.springboot.advanced_jpa.data.entity.Product
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CategoryRepositoryTest {
    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Test
    fun relationshipTest() {
        val product = Product().apply {
            name = "펜"
            price = 2000
            stock = 100
        }

        productRepository.save(product)

        val category = Category().apply {
            code = "S1"
            name = "도서"
            products.add(product)
        }

        categoryRepository.save(category)


        // 테스트 코드
        val products:List<Product> = categoryRepository.findById(1L).get().products
        products.forEach {
            println(it)
        }
    }
}