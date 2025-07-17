package com.ltech.caixa_tesouraria.service;

import org.springframework.stereotype.Service;

import com.ltech.caixa_tesouraria.model.Lancamentos;
import com.ltech.caixa_tesouraria.repository.LancamentoRepository;
import com.ltech.caixa_tesouraria.repository.ServiceCrud;

@Service
public class LancamentoService extends ServiceCrud<Lancamentos, Long, LancamentoRepository> {

    public LancamentoService(LancamentoRepository repository) {
        super(repository);
    }

}
