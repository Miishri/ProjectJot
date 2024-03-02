package org.personal.projectjot.bootstrap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.personal.projectjot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RoleBootstrapperTest {

    private Bootstrapper roleBootstrapper;
    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        roleBootstrapper = new Bootstrapper(roleRepository);
    }

    @Test
    void testRoleBootstrapPersistence() {
        roleBootstrapper.run();

        assertThat(roleRepository.findAll().size()).isEqualTo(2);
    }
}
