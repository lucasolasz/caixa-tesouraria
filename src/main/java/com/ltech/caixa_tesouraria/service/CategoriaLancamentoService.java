package com.ltech.caixa_tesouraria.service;

import org.springframework.stereotype.Service;

import com.ltech.caixa_tesouraria.model.CategoriaLancamento;
import com.ltech.caixa_tesouraria.repository.CategoriaLancamentoRepository;
import com.ltech.caixa_tesouraria.repository.ServiceCrud;

@Service
public class CategoriaLancamentoService extends ServiceCrud<CategoriaLancamento, Long, CategoriaLancamentoRepository> {

    public CategoriaLancamentoService(CategoriaLancamentoRepository repository) {
        super(repository);
    }
}
