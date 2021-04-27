package com.giftery.Dao.User;

import com.giftery.Model.User.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    public User findByUsernameIgnoreCase(String username);
    public User findByEmailIgnoreCase(String email);

    public Page<User> findAll(Pageable pageable);
    public List<User> findAll();

}
