package com.giftery.Controller.Home;

import com.giftery.Component.CustomAuthenticationProvider;
import com.giftery.Service.User.UserDetailsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(LookUpProductTestController.class)
public class LookUpProductTestControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserDetailsServiceImpl userDetailsService;

    @MockBean
    CustomAuthenticationProvider customAuthenticationProvider;

    @Test
    @PostConstruct
    @DisplayName("Test Should Return A Sunny Health Exercise Bike After Making a Get Request to endpoint - /product-lookup/search/?asin=[asin]")
    void shouldSarchAsinAndGetExerciseBike() throws Exception
    {
           mockMvc.perform(MockMvcRequestBuilders.get("/product-lookup/search/")
                    .param("asin", "B07FCJH59R"))
                    .andExpect(MockMvcResultMatchers.model()
                            .attribute("amazonProduct", hasProperty("productTitle", containsStringIgnoringCase("SF-B1805"))));

    }
}