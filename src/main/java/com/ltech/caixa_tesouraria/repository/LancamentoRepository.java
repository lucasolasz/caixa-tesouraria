package com.ltech.caixa_tesouraria.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ltech.caixa_tesouraria.model.Lancamentos;

public interface LancamentoRepository extends JpaRepository<Lancamentos, Long> {

    @Query("""
                SELECT l FROM Lancamentos l
                WHERE LOWER(l.descricao) LIKE LOWER(CONCAT('%', :search, '%'))
                   OR CAST(l.dataLancamento AS string) LIKE CONCAT('%', :search, '%')
                   OR CAST(l.valor AS string) LIKE CONCAT('%', :search, '%')
                   OR LOWER(l.tipoMovimentacao.descricao) LIKE LOWER(CONCAT('%', :search, '%'))
                   OR LOWER(l.fundoFinanceiro.descricao) LIKE LOWER(CONCAT('%', :search, '%'))
                   OR LOWER(l.funcionarioLancamento.username) LIKE LOWER(CONCAT('%', :search, '%'))
            """)
    Page<Lancamentos> searchAllColumns(@Param("search") String search, Pageable pageable);
}
