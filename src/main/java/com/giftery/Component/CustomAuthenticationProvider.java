package com.giftery.Component;

import com.giftery.Exception.AccountDeletedException;
import com.giftery.Model.Role.Role;
import com.giftery.Model.User.User;
import com.giftery.Service.User.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider
{
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*
        This is the authentication for user login.
        If failedLogins need to be logged, this is where the logging code will be placed.
     */

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        WebAuthenticationDetails details = null;
        String ipAddress = null;

        if(authentication.getDetails() != null) {
            details = (WebAuthenticationDetails) authentication.getDetails();
            ipAddress = details.getRemoteAddress();
        }

        User user = userService.findByUsernameIgnoreCase(username);


        if(user == null || !bCryptPasswordEncoder.matches(password, user.getPassword()))
        {
            throw new BadCredentialsException("1000");
        }

        if(user.isDeleted())
        {
            throw new AccountDeletedException("1001");
        }

        List<Role> userRoles = (List<Role>) user.getRoles();

        return new UsernamePasswordAuthenticationToken(username, null, userRoles.stream().map(x->new SimpleGrantedAuthority(x.getName())).collect(Collectors.toList()));

    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
