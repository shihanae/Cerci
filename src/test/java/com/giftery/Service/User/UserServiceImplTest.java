package com.giftery.Service.User;

import com.giftery.Model.Role.Role;
import com.giftery.Model.User.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@TestPropertySource(locations="classpath:test.properties")
class UserServiceImplTest
{
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void findByUsernameIgnoreCase()
    {
        assertNotNull(userService.findByUsernameIgnoreCase("admin"));
    }


    @Test
    public void saveUserTest()
    {
        User user = new User("test-user", "test@test.com", "password", "firstName", "lastname");
         userService.saveUser(user);
         assertNotNull(userService.findByUsernameIgnoreCase("test-user"));
    }

    @Test
    public void updateUserTest()
    {
        User user = new User("test-user", "test@test.com", "password", "firstName", "lastname");
        userService.saveUser(user);
        String originalFirstName = user.getFirstName();
        user.setFirstName("changedFirstName");
        userService.updateUser(user);
        assertNotEquals(originalFirstName, user.getFirstName());
    }

    @Test
    public void deleteByIdTest()
    {
        User testUser = new User("test-user", "test@test.com", "password", "firstName", "lastname");
        userService.saveUser(testUser);

        User user = userService.findByUsernameIgnoreCase("test-user");
        userService.deleteById(user.getId());

        assertNull(userService.findByUsernameIgnoreCase("test-user"));
    }

    @Test
    public void findByIdTest()
    {
        User testUser = new User("test-user", "test@test.com", "password", "firstName", "lastname");
        userService.saveUser(testUser);

        User user = userService.findById(testUser.getId());
        assertNotNull(user);
    }



    @Test
    public void findByEmailIgnoreCase()
    {
        User testUser = new User("test-user", "user@test.com", "password", "firstName", "lastname");
        userService.saveUser(testUser);

        User user = userService.findByEmailIgnoreCase("user@test.com");
        assertNotNull(user);
    }

    @Test
    public void findAllPageableTest()
    {
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> users = userService.findAll(pageable);

        assertNotNull(users);
        assertNotEquals(users.getSize(), 0);
    }

    @Test
    public void findAll()
    {
        List<User> users = userService.findAll();
        assertNotNull(users);
        assertNotEquals(users.size(), 0);
    }
}