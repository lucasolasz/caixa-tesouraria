package com.ltech.caixa_tesouraria.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ltech.caixa_tesouraria.dto.FundoFinanceiroDto;
import com.ltech.caixa_tesouraria.model.FundoFinanceiro;
import com.ltech.caixa_tesouraria.model.Lancamentos;
import com.ltech.caixa_tesouraria.repository.FundoFinanceiroRepository;
import com.ltech.caixa_tesouraria.repository.ServiceCrud;
import com.ltech.caixa_tesouraria.util.TextoUtil;

@Service
public class FundoFinanceiroService extends ServiceCrud<FundoFinanceiro, Long, FundoFinanceiroRepository> {

    private final LancamentoService lancamentoService;
    private static final Long TIPO_CREDITO_ID = 1L;
    private static final Long TIPO_DEBITO_ID = 2L;

    public FundoFinanceiroService(FundoFinanceiroRepository repository, LancamentoService lancamentoService) {
        super(repository);
        this.lancamentoService = lancamentoService;
    }

    public List<FundoFinanceiroDto> recuperarCarteirasComTotais() {
        List<FundoFinanceiro> fundosFinanceiros = this.getRepository().findAll();
        List<Lancamentos> listaLancamentos = this.lancamentoService.recuperarTodos();
        List<FundoFinanceiroDto> listaDto = new ArrayList<>();

        for (FundoFinanceiro fundo : fundosFinanceiros) {
            FundoFinanceiroDto dto = new FundoFinanceiroDto();
            dto.setNomeCarteira(fundo.getDescricao());
            dto.setTotalCarteira(this.calcularTotalCarteira(fundo, listaLancamentos));
            listaDto.add(dto);
        }

        return listaDto;

    }

    // private String calcularTotalCarteira(FundoFinanceiro fundo, List<Lancamentos>
    // listaLancamentos) {
    // return TextoUtil.formatarComoMoedaBrasileira(listaLancamentos.stream()
    // .filter(lancamento -> lancamento.getFundoFinanceiro() != null
    // && lancamento.getFundoFinanceiro().getId().equals(fundo.getId()))
    // .map(Lancamentos::getValor)
    // .reduce(BigDecimal.ZERO, BigDecimal::add));
    // }

    private String calcularTotalCarteira(FundoFinanceiro fundo, List<Lancamentos> listaLancamentos) {
        return TextoUtil.formatarComoMoedaBrasileira(
                listaLancamentos.stream()
                        .filter(lancamento -> lancamento.getFundoFinanceiro() != null
                                && lancamento.getFundoFinanceiro().getId().equals(fundo.getId())
                                && lancamento.getTipoMovimentacao() != null
                                && lancamento.getValor() != null)
                        .map(lancamento -> {
                            Long tipoId = lancamento.getTipoMovimentacao().getId();
                            if (tipoId == TIPO_CREDITO_ID) { // Crédito
                                return lancamento.getValor();
                            } else if (tipoId == TIPO_DEBITO_ID) { // Débito
                                return lancamento.getValor().negate();
                            } else {
                                return BigDecimal.ZERO;
                            }
                        })
                        .reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}
