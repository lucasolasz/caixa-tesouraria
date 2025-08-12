package com.ltech.caixa_tesouraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ltech.caixa_tesouraria.model.Lancamentos;
import com.ltech.caixa_tesouraria.service.LancamentoService;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController extends CrudController<Lancamentos, Long, LancamentoService> {

    public RelatorioController(String viewPath, LancamentoService service, String nomeTela) {
        super("relatorio", service, "Relatório");
    }

    @PostMapping("/pesquisar")
    public String pesquisarComFiltro(@ModelAttribute("objeto") Lancamentos entity, Model model) {
        return this.getViewPathOperacaoVisualizar();
    }

}
