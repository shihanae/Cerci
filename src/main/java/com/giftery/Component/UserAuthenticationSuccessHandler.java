package com.giftery.Component;

import com.giftery.Model.User.User;
import com.giftery.Service.User.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{

    @Autowired
    private UserServiceImpl userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        String userName = authentication.getName();
        User user = userService.findByUsernameIgnoreCase(userName);

        // now place in the session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        // forward to home page
        response.sendRedirect(request.getContextPath() + "/");
    }

}
