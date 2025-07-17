package com.ltech.caixa_tesouraria.service;

import org.springframework.stereotype.Service;

import com.ltech.caixa_tesouraria.model.FundoFinanceiro;
import com.ltech.caixa_tesouraria.repository.FundoFinanceiroRepository;
import com.ltech.caixa_tesouraria.repository.ServiceCrud;

@Service
public class FundoFinanceiroService extends ServiceCrud<FundoFinanceiro, Long, FundoFinanceiroRepository> {

    public FundoFinanceiroService(FundoFinanceiroRepository repository) {
        super(repository);
    }

    @Override
    public void ajusteAntesGravacao(FundoFinanceiro entity) {
        // Implement any specific logic before saving a FundoFinanceiro entity
        super.ajusteAntesGravacao(entity);
    }

}
