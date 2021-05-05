package com.giftery.Component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest()
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureMockMvc
public class LoginTest
{
    @Autowired
    private WebApplicationContext context;


    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void loginWithValidUserThenAuthenticated() throws Exception {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("user_one", "!Texas1DERful");
        Authentication user = customAuthenticationProvider.authenticate(token);
        assertTrue(user.isAuthenticated());

    }

    @Test
    public void loginWithInvalidUserThenUnauthenticated() throws Exception
    {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("invalid", "invalid");
        Exception exception = assertThrows(BadCredentialsException.class, () -> customAuthenticationProvider.authenticate(token));
        assertEquals("1000", exception.getMessage());
    }


}
