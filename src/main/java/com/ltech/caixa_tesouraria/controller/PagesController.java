package com.ltech.caixa_tesouraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ltech.caixa_tesouraria.service.LancamentoService;

@Controller
public class PagesController {

    private final LancamentoService lancamentoService;

    public PagesController(LancamentoService lancamentoService) {
        this.lancamentoService = lancamentoService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("totalMes", lancamentoService.getValorTotalGanhosMes());
        return "pages/home";
    }

}
