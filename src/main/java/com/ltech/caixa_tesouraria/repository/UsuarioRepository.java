package com.ltech.caixa_tesouraria.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ltech.caixa_tesouraria.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("""
                SELECT l FROM Usuario l
                WHERE LOWER(l.username) LIKE LOWER(CONCAT('%', :search, '%'))
                   OR LOWER(l.firstName) LIKE LOWER(CONCAT('%', :search, '%'))
                   OR LOWER(l.lastName) LIKE LOWER(CONCAT('%', :search, '%'))
            """)
    Page<Usuario> searchAllColumns(@Param("search") String search, Pageable pageable);
}
