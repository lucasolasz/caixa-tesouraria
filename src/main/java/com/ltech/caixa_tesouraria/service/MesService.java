package com.ltech.caixa_tesouraria.service;

import org.springframework.stereotype.Service;

import com.ltech.caixa_tesouraria.model.Mes;
import com.ltech.caixa_tesouraria.repository.MesRepository;
import com.ltech.caixa_tesouraria.repository.ServiceCrud;

@Service
public class MesService extends ServiceCrud<Mes, Long, MesRepository> {

    public MesService(MesRepository repository) {
        super(repository);
    }

}
