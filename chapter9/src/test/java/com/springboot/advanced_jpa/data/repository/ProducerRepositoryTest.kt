package com.springboot.advanced_jpa.data.repository

import com.springboot.advanced_jpa.data.entity.Producer
import com.springboot.advanced_jpa.data.entity.Product
import org.assertj.core.util.Lists
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

@SpringBootTest
open class ProducerRepositoryTest {
    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var producerRepository: ProducerRepository

    @Test
    @Transactional
    open fun relationshipTest() {
        val product1 = saveProduct("동글펜", 500, 1000)
        val product2 = saveProduct("네모 공책", 100, 2000)
        val product3 = saveProduct("지우개", 152, 1234)

        val producer1 = saveProducer("flature")
        val producer2 = saveProducer("wikibooks")

        producer1.addProduct(product1)
        producer1.addProduct(product2)

        producer2.addProduct(product2)
        producer2.addProduct(product3)

        producerRepository.saveAll(Lists.newArrayList(producer1, producer2))
        println(producerRepository.findById(1L).get().products)
    }

    @Test
    @Transactional
    open fun relationshipTest2() {
        val product1 = saveProduct("동글펜", 500, 1000)
        val product2 = saveProduct("네모 공책", 100, 2000)
        val product3 = saveProduct("지우개", 152, 1234)

        val producer1 = saveProducer("flature")
        val producer2 = saveProducer("wikibooks")

        producer1.addProduct(product1)
        producer1.addProduct(product2)
        producer2.addProduct(product2)
        producer2.addProduct(product3)

        product1.addProducer(producer1)
        product2.addProducer(producer1)
        product2.addProducer(producer2)
        product3.addProducer(producer2)

        producerRepository.saveAll(Lists.newArrayList(producer1, producer2))
        productRepository.saveAll(Lists.newArrayList(product1, product2, product3))

        println("products : ${producerRepository.findById(1L).get().products}")
        println("producers : ${productRepository.findById(1L).get().producers}")
    }

    private fun saveProduct(name: String, price: Int, stock: Int): Product {
//        val product = Product()
//        product.name = name
//        product.price = price
//        product.stock = stock
//        return productRepository.save(product)

        return productRepository.save(
            Product().apply {
                this.name = name
                this.price = price
                this.stock = stock
            }
        )
    }

    private fun saveProducer(name: String): Producer {
        return producerRepository.save(
            Producer().apply {
                this.name = name
            }
        )
    }
}