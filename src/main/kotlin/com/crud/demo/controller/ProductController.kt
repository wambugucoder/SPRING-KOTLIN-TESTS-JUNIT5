package com.crud.demo.controller

import com.crud.demo.entity.Product
import com.crud.demo.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class ProductController {

    @Autowired
    lateinit var productService: ProductService

    @PostMapping("/create",consumes = [MediaType.APPLICATION_JSON_VALUE],produces = [MediaType.APPLICATION_JSON_VALUE])
     fun create(@RequestBody body:Product): Product {
         return productService.createProduct(body)
     }
    @GetMapping("/all",consumes=[MediaType.APPLICATION_JSON_VALUE],produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAll(): MutableList<Product> {
        return productService.getProduct()
    }
    @GetMapping("/product/{id}",consumes = [MediaType.APPLICATION_JSON_VALUE],produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getById(@PathVariable id:Long): Optional<Product> {
        return productService.getProductById(id)
    }
    @DeleteMapping("/delete/{id}",consumes = [MediaType.APPLICATION_JSON_VALUE],produces=[MediaType.APPLICATION_JSON_VALUE])
    fun delete(@PathVariable id:Long): String {
        return productService.deleteProduct(id)
    }
}