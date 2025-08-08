package com.ltech.caixa_tesouraria.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ltech.caixa_tesouraria.util.DataUtil;
import com.ltech.caixa_tesouraria.util.TextoUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lancamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataLancamento;

    @NotEmpty(message = "{validation.notempty}")
    @Size(max = 255, message = "O nome precisa ter até 255 caracteres")
    @Column(length = 255)
    private String descricao;

    @ManyToOne
    private Usuario funcionarioLancamento;

    @NotNull(message = "{validation.notempty}")
    @ManyToOne
    private TipoMovimentacao tipoMovimentacao;

    @NotNull(message = "{validation.notempty}")
    @ManyToOne
    private FundoFinanceiro fundoFinanceiro;

    @NotNull(message = "{validation.notempty}")
    @Column(precision = 19, scale = 2)
    private BigDecimal valor;

    @JsonProperty("dataFormatada")
    public String getDataFormatada() {
        return DataUtil.formatarLocalDateParaString(this.dataLancamento);
    }

    @JsonProperty("valorMonetarioFormatado")
    public String getValorMonetarioFormatado() {
        return TextoUtil.formatarComoMoedaBrasileira(this.valor);
    }

}
