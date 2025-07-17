package com.ltech.caixa_tesouraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ltech.caixa_tesouraria.model.Lancamentos;

public interface LancamentoRepository extends JpaRepository<Lancamentos, Long> {

}
