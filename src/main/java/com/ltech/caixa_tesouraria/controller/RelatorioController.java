package com.ltech.caixa_tesouraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ltech.caixa_tesouraria.dto.FiltroRelatorioDto;
import com.ltech.caixa_tesouraria.model.Lancamentos;
import com.ltech.caixa_tesouraria.service.CategoriaLancamentoService;
import com.ltech.caixa_tesouraria.service.FundoFinanceiroService;
import com.ltech.caixa_tesouraria.service.LancamentoService;
import com.ltech.caixa_tesouraria.service.TipoMovimentacaoService;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController extends CrudController<Lancamentos, Long, LancamentoService> {

    private final TipoMovimentacaoService tipoMovimentacaoService;
    private final FundoFinanceiroService fundoFinanceiroService;
    private final CategoriaLancamentoService categoriaLancamentoService;

    public RelatorioController(LancamentoService lancamentoService, TipoMovimentacaoService tipoMovimentacaoService,
            FundoFinanceiroService fundoFinanceiroService, CategoriaLancamentoService categoriaLancamentoService) {
        super("relatorio", lancamentoService, "Relatório");
        this.tipoMovimentacaoService = tipoMovimentacaoService;
        this.fundoFinanceiroService = fundoFinanceiroService;
        this.categoriaLancamentoService = categoriaLancamentoService;
    }

    @Override
    public void cargaAuxiliarObjetos(Model model) {
        this.carregarAtributosTela(model);
        model.addAttribute("filtro", new FiltroRelatorioDto());

    }

    @PostMapping("/pesquisar")
    public String pesquisarComFiltro(@ModelAttribute FiltroRelatorioDto filtro, Model model) {
        model.addAttribute("filtro", filtro);
        this.carregarAtributosTela(model);
        return this.getViewPathOperacaoVisualizar();
    }

    private void carregarAtributosTela(Model model) {
        model.addAttribute("listaTipoMovimentacao", tipoMovimentacaoService.recuperarTodos());
        model.addAttribute("listaFundoFinanceiro", fundoFinanceiroService.recuperarTodos());
        model.addAttribute("listaCategoriaLancamento", categoriaLancamentoService.recuperarTodos());
        model.addAttribute("listaLancamentoResultadoPesquisa", this.getService().recuperarTodos());
    }

}
