package com.giftery.Service.Role;

import com.giftery.Model.Role.Role;
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
class RoleServiceImplTest
{
    @Autowired
    private RoleServiceImpl roleService;

    @Test
    public void saveRoleTest()
    {
        Role role = new Role("ROLE_TEST");
        roleService.saveRole(role);
        assertNotNull(roleService.findByNameIgnoreCase("ROLE_TEST"));
    }

    @Test
    public void findByNameIgnoreCaseTest()
    {
        assertNotNull(roleService.findByNameIgnoreCase("ROLE_PREMIUM"));
    }

    @Test
    public void findByIdTest()
    {
        Role testRole = new Role("ROLE_TEST");
        roleService.saveRole(testRole);

        Role role = roleService.findById(testRole.getId());
        assertNotNull(role);
    }

    @Test
    public void deleteByIdTest()
    {
        Role testRole = new Role("ROLE_TEST");
        roleService.saveRole(testRole);

        Role role = roleService.findByNameIgnoreCase("ROLE_TEST");
        roleService.deleteById(role.getId());
        assertNull(roleService.findByNameIgnoreCase("ROLE_TEST"));
    }

    @Test
    public void findAllPageableTest()
    {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Role> roles = roleService.findAll(pageable);
        assertNotNull(roles);
    }

    @Test
    public void findAll()
    {
        List<Role> roles = roleService.findAll();
        assertNotNull(roles);
    }
}