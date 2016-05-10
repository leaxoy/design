package com.example.design.controller

import com.example.design.service.impl.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import spock.lang.Shared
import spock.lang.Specification

import static org.hamcrest.Matchers.containsString
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * AuthController 的测试用例
 * Created by lxh on 16/4/21.
 */
class AuthControllerTest extends Specification {
    @Autowired
    private WebApplicationContext context
    @Autowired
    private UserService userService
    @Shared
    private MockMvc mockMvc

    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }

    void cleanup() {

    }

    @Transactional
    def "Home"() {
        when:
        def result = mockMvc.perform(get("/"))
        then:
        result.andExpect(status().isOk())
        result.andExpect(content().string(containsString('Hello,world')))
    }

    def "Auth"() {

    }

    def "Update"() {

    }

    def "Logout"() {

    }
}
