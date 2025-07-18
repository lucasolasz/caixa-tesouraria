package com.ltech.caixa_tesouraria.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ltech.caixa_tesouraria.model.Lancamentos;
import com.ltech.caixa_tesouraria.model.Usuario;
import com.ltech.caixa_tesouraria.repository.LancamentoRepository;
import com.ltech.caixa_tesouraria.repository.UsuarioRepository;

@RestController
@RequestMapping("/dataTable")
public class DataTablesController {

    private final LancamentoRepository lancamentoRepository;
    private final UsuarioRepository usuarioRepository;

    public DataTablesController(LancamentoRepository lancamentoRepository, UsuarioRepository usuarioRepository) {
        this.lancamentoRepository = lancamentoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/lancamentos")
    public Map<String, Object> getLancamentos(@RequestParam int draw, @RequestParam int start, @RequestParam int length,
            @RequestParam(value = "search[value]", required = false) String searchValue) {

        int page = start / length;
        Pageable pageable = PageRequest.of(page, length);

        Page<Lancamentos> objectPage;
        if (searchValue != null && !searchValue.isEmpty()) {
            objectPage = lancamentoRepository.searchAllColumns(searchValue,
                    pageable);
        } else {
            objectPage = lancamentoRepository.findAll(pageable);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("draw", draw);
        response.put("recordsTotal", lancamentoRepository.count());
        response.put("recordsFiltered", objectPage.getTotalElements());
        response.put("data", objectPage.getContent());

        return response;
    }

    @GetMapping("/usuarios")
    public Map<String, Object> getUsuarios(@RequestParam int draw, @RequestParam int start, @RequestParam int length,
            @RequestParam(value = "search[value]", required = false) String searchValue) {

        int page = start / length;
        Pageable pageable = PageRequest.of(page, length);

        Page<Usuario> objectPage;
        if (searchValue != null && !searchValue.isEmpty()) {
            objectPage = usuarioRepository.searchAllColumns(searchValue,
                    pageable);
        } else {
            objectPage = usuarioRepository.findAll(pageable);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("draw", draw);
        response.put("recordsTotal", lancamentoRepository.count());
        response.put("recordsFiltered", objectPage.getTotalElements());
        response.put("data", objectPage.getContent());

        return response;
    }

}
