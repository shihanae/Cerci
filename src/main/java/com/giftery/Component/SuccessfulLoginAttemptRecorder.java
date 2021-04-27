package com.giftery.Component;

import com.giftery.Model.User.User;
import com.giftery.Service.User.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SuccessfulLoginAttemptRecorder implements ApplicationListener<AuthenticationSuccessEvent>
{
    @Autowired
    private UserServiceImpl userService;

    //On Every successful login attempt, record it in the activityLog table.
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event)
    {
        Object userName = event.getAuthentication().getPrincipal();

        /*
            Code for IP Address
            ---------------------------------------
            WebAuthenticationDetails details = (WebAuthenticationDetails) event.getAuthentication().getDetails();
            String ipAddress = details.getRemoteAddress();
         */

        if(userService.findByUsernameIgnoreCase(userName.toString()) != null)
        {
            User user = userService.findByUsernameIgnoreCase(userName.toString());
            user.setLastSeen(LocalDateTime.now());
            userService.updateUser(user);
        }
    }
}
