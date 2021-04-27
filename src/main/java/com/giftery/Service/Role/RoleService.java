package com.giftery.Service.Role;

import com.giftery.Model.Role.Role;
import com.giftery.Model.User.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService
{
    public void saveRole(Role role);
    public void deleteById(long id);
    public Role findById(long id);

    public Role findByNameIgnoreCase(String name);
    public Page<Role> findAll(Pageable pageable);
    public List<Role> findAll();
}
