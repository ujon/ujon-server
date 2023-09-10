package io.ujon.api

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


//@SpringBootTest
//@ExtendWith(RestDocumentationExtension::class, SpringExtension::class)
//@AutoConfigureRestDocs
//@AutoConfigureMockMvc
//private class UjonApiTest {
//    @Autowired
//    lateinit var mockMvc: MockMvc
//
//
//    @BeforeEach
//    fun setUp(webApplicationContext: WebApplicationContext?, restDocumentation: RestDocumentationContextProvider?) {
//        mockMvc = webApplicationContext?.let {
//            MockMvcBuilders.webAppContextSetup(it)
//                .apply<DefaultMockMvcBuilder>(documentationConfiguration(restDocumentation))
//                .build()
//        }!!
//    }
//
//    @Test
//    fun testGetBook() {
//        mockMvc.perform(MockMvcRequestBuilders.get("/test"))
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andDo(document("get-book"))
//    }
//}