package com.ra.repisitory;

import com.ra.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepositoty extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
