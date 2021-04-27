package com.giftery.Component;

import com.giftery.Model.Address.Address;
import com.giftery.Model.Role.Role;
import com.giftery.Model.User.User;
import com.giftery.Service.Role.RoleServiceImpl;
import com.giftery.Service.User.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner
{
    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleServiceImpl roleService;

    private String[] roles = {"USER", "STANDARD", "PREMIUM" , "CREATOR", "CHARITY", "MODERATOR", "ADMINISTRATOR", "SUPER_ADMIN"};

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        for (String role : roles) {
            if (roleService.findByNameIgnoreCase(role) == null) {
                roleService.saveRole(new Role(role));
            }
        }

        if(userService.findByUsernameIgnoreCase("admin") == null)
        {
            User admin = new User("admin", "shipikachu@outlook.com", "23uYm=NAfg=UKuxO");
            admin.addRole(roleService.findByNameIgnoreCase("SUPER_ADMIN"));
            userService.saveUser(admin);
        }

        if(userService.findByUsernameIgnoreCase("userOne") == null)
        {
            User userOne = new User("user_one", "shipikachu@outlook.com", "!Texas1DERful");
            userOne.addRole(roleService.findByNameIgnoreCase("USER"));
            userOne.addAddress(new Address("123 Street St", "APT 123", "12345", "Dallas", "TX", "US", userOne));
            userService.saveUser(userOne);

        }

        if(userService.findByUsernameIgnoreCase("userTwo") == null)
        {
            User userTwo = new User("user_two", "shipikachu@outlook.com", "!Texas1DERful");
            userTwo.addRole(roleService.findByNameIgnoreCase("USER"));
            userTwo.addAddress(new Address("123 Street St", "APT 123", "12345", "Dallas", "TX", "US", userTwo));
            userService.saveUser(userTwo);
        }
    }
}
