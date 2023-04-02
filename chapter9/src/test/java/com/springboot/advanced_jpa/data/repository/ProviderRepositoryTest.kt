package com.springboot.advanced_jpa.data.repository

import com.springboot.advanced_jpa.data.entity.Product
import com.springboot.advanced_jpa.data.entity.Provider
import org.assertj.core.util.Lists
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

@SpringBootTest
open class ProviderRepositoryTest {
    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var providerRepository: ProviderRepository

    @Test
    fun relationshipTest1() {
        val provider = Provider()
        provider.name = "ㅇㅇ물산"

        providerRepository.save(provider)

        val product = Product()
        product.name = "가위"
        product.price = 5000
        product.stock = 500
        product.provider = provider

        productRepository.save(product)

        println("product : ${productRepository.findById(1L).orElseThrow()}")
        println("provider : ${productRepository.findById(1L).orElseThrow().provider}")
    }

    @Test
    fun relationshipTest() {
        val provider = Provider()
        provider.name = "ㅇㅇ상사"

        providerRepository.save(provider)

        val product1 = Product()
        product1.run {
            name = "펜"
            price = 2000
            stock = 100
            this.provider = provider
        }

        val product2 = Product()
        product2.run {
            name = "가방"
            price = 20000
            stock = 200
            this.provider = provider
        }

        val product3 = Product()
        product3.run {
            name = "노트"
            price = 3000
            stock = 1000
            this.provider = provider
        }

        productRepository.run {
            save(product1)
            save(product2)
            save(product3)
        }

        val products = providerRepository.findById(provider.id).get().productList

        products.forEach { println(it) }
    }

    @Test
    @Transactional
    open fun orphanRemovalTest() {
        val provider = savedProvider("새로운 공급업체")

        val product1 = savedProduct("상품1", 1000, 1000)
        val product2 = savedProduct("상품2", 500, 1500)
        val product3 = savedProduct("상품3", 750, 500)

        product1.provider = provider
        product2.provider = provider
        product3.provider = provider

        provider.productList.addAll(Lists.newArrayList(product1, product2, product3))
        providerRepository.saveAndFlush(provider)

        providerRepository.findAll().forEach { println(it) }
        productRepository.findAll().forEach { println(it) }

        val foundProvider = providerRepository.findById(1L).get()
        foundProvider.productList.removeAt(0)

        providerRepository.findAll().forEach(::println)
        productRepository.findAll().forEach(::println)
    }

    private fun savedProvider(name: String): Provider {
        return Provider().apply {
            this.name = name
        }
    }

    private fun savedProduct(name: String, price: Int, stock: Int): Product {
        return Product().apply {
            this.name = name
            this.price = price
            this.stock = stock
        }
    }
}