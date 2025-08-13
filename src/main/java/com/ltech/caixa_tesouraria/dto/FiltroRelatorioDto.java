
package com.ltech.caixa_tesouraria.dto;

import java.time.LocalDate;

import com.ltech.caixa_tesouraria.model.CategoriaLancamento;
import com.ltech.caixa_tesouraria.model.FundoFinanceiro;
import com.ltech.caixa_tesouraria.model.TipoMovimentacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FiltroRelatorioDto {

    private TipoMovimentacao tipoMovimentacao;
    private CategoriaLancamento categoriaLancamento;
    private FundoFinanceiro fundoFinanceiro;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalDate dataLancamento;

}