package com.crud.demo

import com.crud.demo.entity.Product
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.*
import org.junit.jupiter.api.condition.EnabledOnJre
import org.junit.jupiter.api.condition.JRE
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.FixedWidth
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = [DemoApplication::class]
)
@AutoConfigureMockMvc
@TestPropertySource(locations = ["classpath:application-test.properties"])
@ExtendWith(SpringExtension::class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@DisplayName("API TESTS")
class DemoApplicationTests {

    @Autowired
    lateinit var mockMvc:MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper


    @Test
    @Order(1)
    @DisplayName("/create API")
    @EnabledOnJre(JRE.JAVA_8)
    fun createProduct(){
        //GIVEN
        val newProduct = Product("product1", "500")
        val json = objectMapper.writeValueAsString(newProduct)

        //WHEN
        val result= mockMvc.perform(

                MockMvcRequestBuilders.post("/create").content(json).contentType(MediaType.APPLICATION_JSON)
        )
                //EXPECT
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").exists())
                .andReturn()
    }

    @Test
    @Order(2)
    @DisplayName("/all API")
    @EnabledOnJre(JRE.JAVA_8)
    fun getAllProducts(){
        //GIVEN NOTHING
        //WHEN
        mockMvc.perform(

                MockMvcRequestBuilders.get("/all").contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()


    }
    @Test
    @Order(3)
    @DisplayName("/product/id API")
    @EnabledOnJre(JRE.JAVA_8)
    fun getProductById(){
        mockMvc.perform(

                MockMvcRequestBuilders.get("/product/1").contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk)

    }

    @Test
    @Order(4)
    @DisplayName("/delete/id API")
    @EnabledOnJre(JRE.JAVA_8)
    fun deleteById(){
        mockMvc.perform(

                MockMvcRequestBuilders.delete("/delete/1").contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string("Product Deleted"))
    }

}


