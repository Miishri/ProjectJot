package org.personal.projectjot.bootstrap;


import org.personal.projectjot.entities.user.JotRole;
import org.personal.projectjot.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Bootstrapper implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public Bootstrapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args){
        bootstrapRoles();
    }

    private void bootstrapRoles() {

        JotRole userRole = JotRole.builder()
                .roleName("USER")
                .build();

        JotRole adminRole = JotRole.builder()
                .roleName("ADMIN")
                .build();

        roleRepository.saveAll(List.of(userRole, adminRole));
    }
}
