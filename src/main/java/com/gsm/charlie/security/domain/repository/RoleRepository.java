package com.gsm.charlie.security.domain.repository;

import com.gsm.charlie.security.domain.model.ERole;
import com.gsm.charlie.security.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}