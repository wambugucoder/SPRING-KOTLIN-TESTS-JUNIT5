package com.crud.demo.service

import com.crud.demo.entity.Product
import com.crud.demo.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class ProductService {

    @Autowired
    lateinit var productRepository: ProductRepository


    fun createProduct(body:Product): Product {
        val newProduct = Product(name = body.name,price = body.price)
        return productRepository.save(newProduct)
    }
    fun getProduct(): MutableList<Product> {
        return productRepository.findAll()
    }
    fun getProductById(id:Long): Optional<Product> {
        return productRepository.findById(id)

    }
    fun deleteProduct(id: Long): String {
        productRepository.deleteById(id)
        return "Product Deleted"
    }
}