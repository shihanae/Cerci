package com.giftery.Service.User;

import com.giftery.Model.User.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService
{
    public void saveUser(User user);
    public void updateUser(User user);
    public void deleteById(long id);
    public User findById(long id);

    public User findByUsernameIgnoreCase(String username);
    public User findByEmailIgnoreCase(String email);
    public Page<User> findAll(Pageable pageable);
    public List<User> findAll();
}
