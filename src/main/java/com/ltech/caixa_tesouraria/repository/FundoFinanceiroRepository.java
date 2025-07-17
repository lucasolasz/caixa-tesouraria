package com.ltech.caixa_tesouraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ltech.caixa_tesouraria.model.FundoFinanceiro;

public interface FundoFinanceiroRepository extends JpaRepository<FundoFinanceiro, Long> {

}
