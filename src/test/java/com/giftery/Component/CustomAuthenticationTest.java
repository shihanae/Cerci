package com.giftery.Component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.TestPropertySource;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@TestPropertySource(locations="classpath:test.properties")
public class CustomAuthenticationTest
{
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

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
