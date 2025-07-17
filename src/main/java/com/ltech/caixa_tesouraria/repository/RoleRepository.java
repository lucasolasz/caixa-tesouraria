package com.ltech.caixa_tesouraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ltech.caixa_tesouraria.model.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {

    List<Roles> findByName(String name);
}
