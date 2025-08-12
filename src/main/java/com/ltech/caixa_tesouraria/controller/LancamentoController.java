package com.ltech.caixa_tesouraria.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ltech.caixa_tesouraria.model.Lancamentos;
import com.ltech.caixa_tesouraria.service.CategoriaLancamentoService;
import com.ltech.caixa_tesouraria.service.FundoFinanceiroService;
import com.ltech.caixa_tesouraria.service.LancamentoService;
import com.ltech.caixa_tesouraria.service.TipoMovimentacaoService;

@Controller
@RequestMapping("/lancamentos")
public class LancamentoController extends CrudController<Lancamentos, Long, LancamentoService> {

    private final TipoMovimentacaoService tipoMovimentacaoService;
    private final FundoFinanceiroService fundoFinanceiroService;
    private final CategoriaLancamentoService categoriaLancamentoService;

    public LancamentoController(LancamentoService lancamentoService, TipoMovimentacaoService tipoMovimentacaoService,
            FundoFinanceiroService fundoFinanceiroService, CategoriaLancamentoService categoriaLancamentoService) {
        super("lancamentos", lancamentoService, "Lançamentos");
        this.tipoMovimentacaoService = tipoMovimentacaoService;
        this.fundoFinanceiroService = fundoFinanceiroService;
        this.categoriaLancamentoService = categoriaLancamentoService;
    }

    @Override
    public void cargaAuxiliarObjetos(Model model) {
        model.addAttribute("listaTipoMovimentacao", tipoMovimentacaoService.recuperarTodos());
        model.addAttribute("listaFundoFinanceiro", fundoFinanceiroService.recuperarTodos());
        model.addAttribute("listaCategoriaLancamento", categoriaLancamentoService.recuperarTodos());
    }

    @Override
    public void preCarregarAtributosObjeto(Lancamentos entity, Model model) {
        entity.setDataLancamento(LocalDate.now());
    }

}
