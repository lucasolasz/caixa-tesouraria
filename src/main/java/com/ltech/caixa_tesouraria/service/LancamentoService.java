package com.ltech.caixa_tesouraria.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.ltech.caixa_tesouraria.model.Lancamentos;
import com.ltech.caixa_tesouraria.repository.LancamentoRepository;
import com.ltech.caixa_tesouraria.repository.ServiceCrud;
import com.ltech.caixa_tesouraria.security.UsuarioAutenticadoProvider;

@Service
public class LancamentoService extends ServiceCrud<Lancamentos, Long, LancamentoRepository> {

    private UsuarioAutenticadoProvider usuarioAutenticadoProvider;

    public LancamentoService(LancamentoRepository repository, UsuarioAutenticadoProvider usuarioAutenticadoProvider) {
        super(repository);
        this.usuarioAutenticadoProvider = usuarioAutenticadoProvider;
    }

    @Override
    public void ajusteAntesGravacao(Lancamentos entity) {
        entity.setFuncionarioLancamento(usuarioAutenticadoProvider.getUsuarioLogado());
        super.ajusteAntesGravacao(entity);
    }

    public BigDecimal getValorTotalGanhosMesCorrente() {
        var numeroDoMes = LocalDate.now().getMonthValue();
        var numeroAno = LocalDate.now().getYear();
        return repository.getValorTotalGanhosMesCorrente(numeroDoMes, numeroAno);
    }

    public BigDecimal getValorTotalGanhosAno() {
        var numeroAno = LocalDate.now().getYear();
        return repository.getValorTotalGanhosAno(numeroAno);
    }

}
