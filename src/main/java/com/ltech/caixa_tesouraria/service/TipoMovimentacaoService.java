package com.ltech.caixa_tesouraria.service;

import org.springframework.stereotype.Service;

import com.ltech.caixa_tesouraria.model.TipoMovimentacao;
import com.ltech.caixa_tesouraria.repository.ServiceCrud;
import com.ltech.caixa_tesouraria.repository.TipoMovimentacaoRepository;

@Service
public class TipoMovimentacaoService extends ServiceCrud<TipoMovimentacao, Long, TipoMovimentacaoRepository> {

    public TipoMovimentacaoService(TipoMovimentacaoRepository repository) {
        super(repository);
    }

}
