package org.personal.projectjot.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.personal.projectjot.entities.JotUser;
import org.personal.projectjot.entities.JotRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
@Rollback
public class JotUserRepositoryTest {

    @Autowired
    private JotUserRepository jotUserRepository;
    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void beforeEach() {
        createTempRole();
    }

    @Test
    void testUserPersistenceToDatabase() {
        JotUser testJotUser = JotUser.builder()
                .username("JotUserTest")
                .password("JotTest")
                .roles(
                        List.of(roleRepository.findRoleByRoleName("USER"))
                )
                .build();

        JotUser expectedTestJotUser = jotUserRepository.save(testJotUser);


        assertThat(testJotUser.getUsername()).isEqualTo(expectedTestJotUser.getUsername());
    }

    @Test
    void roleExists() {
        assertThat(roleRepository.findRoleByRoleName("USER")).isNotNull();
    }

    private void createTempRole() {
        roleRepository.save(JotRole.builder().roleName("USER").build());
    }

}
