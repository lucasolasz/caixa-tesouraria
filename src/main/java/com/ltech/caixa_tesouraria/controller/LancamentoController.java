package com.ltech.caixa_tesouraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ltech.caixa_tesouraria.model.Lancamentos;
import com.ltech.caixa_tesouraria.service.LancamentoService;
import com.ltech.caixa_tesouraria.service.TipoMovimentacaoService;

@Controller
@RequestMapping("/lancamentos")
public class LancamentoController extends CrudController<Lancamentos, Long, LancamentoService> {

    private final TipoMovimentacaoService tipoMovimentacaoService;

    public LancamentoController(LancamentoService lancamentoService, TipoMovimentacaoService tipoMovimentacaoService) {
        super("lancamentos", lancamentoService, "Lançamentos");
        this.tipoMovimentacaoService = tipoMovimentacaoService;
    }

    @Override
    public void cargaAuxiliarObjetos(Model model) {
        model.addAttribute("listaTipoMovimentacao", tipoMovimentacaoService.recuperarTodos());
    }
}
