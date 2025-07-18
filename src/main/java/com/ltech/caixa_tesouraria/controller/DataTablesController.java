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
import com.ltech.caixa_tesouraria.repository.LancamentoRepository;

@RestController
@RequestMapping("/dataTable")
public class DataTablesController {

    private final LancamentoRepository lancamentoRepository;

    // private final CondominoRepository condominoRepository;

    public DataTablesController(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
        // this.condominoRepository = condominoRepository;
    }

    @GetMapping("/lancamentos")
    public Map<String, Object> getStudents(@RequestParam int draw, @RequestParam int start, @RequestParam int length,
            @RequestParam(value = "search[value]", required = false) String searchValue) {

        int page = start / length;
        Pageable pageable = PageRequest.of(page, length);

        Page<Lancamentos> studentPage;
        if (searchValue != null && !searchValue.isEmpty()) {
            studentPage = lancamentoRepository.searchAllColumns(searchValue,
                    pageable);
        } else {
            studentPage = lancamentoRepository.findAll(pageable);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("draw", draw);
        response.put("recordsTotal", lancamentoRepository.count());
        response.put("recordsFiltered", studentPage.getTotalElements());
        response.put("data", studentPage.getContent());

        return response;
    }

}
