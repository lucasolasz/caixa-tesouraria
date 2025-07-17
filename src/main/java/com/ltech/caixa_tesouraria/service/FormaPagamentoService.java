package com.ltech.caixa_tesouraria.service;

import org.springframework.stereotype.Service;

import com.ltech.caixa_tesouraria.model.FormaPagamento;
import com.ltech.caixa_tesouraria.repository.FormaPagamentoRepository;
import com.ltech.caixa_tesouraria.repository.ServiceCrud;

@Service
public class FormaPagamentoService extends ServiceCrud<FormaPagamento, Long, FormaPagamentoRepository> {

    public FormaPagamentoService(FormaPagamentoRepository repository) {
        super(repository);
    }

}
