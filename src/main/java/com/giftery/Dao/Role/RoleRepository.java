package com.giftery.Dao.Role;

import com.giftery.Model.Role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
    public Role findByNameIgnoreCase(String name);
    public Page<Role> findAll(Pageable pageable);
    public List<Role> findAll();
}
