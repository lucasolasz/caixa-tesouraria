package com.ltech.caixa_tesouraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ltech.caixa_tesouraria.service.FundoFinanceiroService;
import com.ltech.caixa_tesouraria.service.LancamentoService;

@Controller
public class PagesController {

    private final LancamentoService lancamentoService;
    private final FundoFinanceiroService fundoFinanceiroService;

    public PagesController(LancamentoService lancamentoService, FundoFinanceiroService fundoFinanceiroService) {
        this.lancamentoService = lancamentoService;
        this.fundoFinanceiroService = fundoFinanceiroService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("totalMes", lancamentoService.getValorTotalGanhosMesCorrente());
        model.addAttribute("totalAno", lancamentoService.getValorTotalGanhosAno());
        model.addAttribute("listaFundos", fundoFinanceiroService.recuperarCarteirasComTotais());

        return "pages/home";
    }

}
