package org.personal.projectjot.repository;

import org.personal.projectjot.entities.user.JotUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JotUserRepository extends JpaRepository<JotUser, Long> {

}
