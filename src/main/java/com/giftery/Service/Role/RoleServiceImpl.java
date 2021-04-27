package com.giftery.Service.Role;

import com.giftery.Dao.Role.RoleRepository;
import com.giftery.Dao.User.UserRepository;
import com.giftery.Model.Role.Role;
import com.giftery.Model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService
{
    @Autowired
    private RoleRepository roleRepository;


    public void saveRole(Role role)
    {
        roleRepository.save(role);
    }

    public void deleteById(long id)
    {
         roleRepository.deleteById(id);
    }

    public Role findById(long id) {
        Optional<Role> result = roleRepository.findById(id);

        Role role = null;
        if(result.isPresent())
        {
            role = result.get();
        }
        /*else
        {
            throw new RuntimeException("Did not find user with the id of: " + id);
        }*/
        return role;
    }

    public Role findByNameIgnoreCase(String name)
    {
        return roleRepository.findByNameIgnoreCase(name);
    }

    public Page<Role> findAll(Pageable pageable)
    {
        return roleRepository.findAll(pageable);
    }

    public List<Role> findAll()
    {
        return roleRepository.findAll();
    }
}
