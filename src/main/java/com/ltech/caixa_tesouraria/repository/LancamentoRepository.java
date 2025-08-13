package com.ltech.caixa_tesouraria.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ltech.caixa_tesouraria.model.CategoriaLancamento;
import com.ltech.caixa_tesouraria.model.FundoFinanceiro;
import com.ltech.caixa_tesouraria.model.Lancamentos;
import com.ltech.caixa_tesouraria.model.TipoMovimentacao;

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

    @Query("""
                SELECT SUM(
                    CASE
                        WHEN l.tipoMovimentacao.id = 1 THEN l.valor
                        WHEN l.tipoMovimentacao.id = 2 THEN -l.valor
                        ELSE 0
                    END
                )
                FROM Lancamentos l
                WHERE MONTH(l.dataLancamento) = :numeroDoMes
                  AND YEAR(l.dataLancamento) = :numeroAno
            """)
    BigDecimal getValorTotalGanhosMesCorrente(@Param("numeroDoMes") int numeroDoMes,
            @Param("numeroAno") int numeroAno);

    @Query("""
                SELECT SUM(valor) FROM Lancamentos l
                WHERE YEAR(l.dataLancamento) = :numeroAno
            """)
    BigDecimal getValorTotalGanhosAno(@Param("numeroAno") int numeroAno);

    @Query("SELECT l FROM Lancamentos l " +
            "WHERE (:tipoMovimentacao IS NULL OR l.tipoMovimentacao = :tipoMovimentacao) " +
            "AND (:categoriaLancamento IS NULL OR l.categoriaLancamento = :categoriaLancamento) " +
            "AND (:fundoFinanceiro IS NULL OR l.fundoFinanceiro = :fundoFinanceiro) " +
            "AND (:dataInicio IS NULL OR l.dataLancamento >= :dataInicio) " +
            "AND (:dataFim IS NULL OR l.dataLancamento <= :dataFim) " +
            "AND (:dataLancamento IS NULL OR l.dataLancamento = :dataLancamento)")
    List<Lancamentos> getPesquisarComFiltro(@Param("tipoMovimentacao") TipoMovimentacao tipoMovimentacao,
            @Param("categoriaLancamento") CategoriaLancamento categoriaLancamento,
            @Param("fundoFinanceiro") FundoFinanceiro fundoFinanceiro,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim,
            @Param("dataLancamento") LocalDate dataLancamento);
}
