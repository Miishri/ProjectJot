package org.personal.projectjot.repository;


import org.personal.projectjot.entities.JotRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<JotRole, Long> {
    JotRole findRoleByRoleName(String roleName);
}
