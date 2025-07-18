package com.ltech.caixa_tesouraria.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class TextoUtil {
    /**
     * Verifica se uma string é nula, vazia ou contém apenas caracteres em branco
     * 
     * @param texto
     * @return
     */
    public static boolean eVazio(String texto) {
        if (texto == null) {
            return true;
        }
        return texto.trim().isEmpty();
    }

    /**
     * Formata um BigDecimal no padrão monetário brasileiro (ex: R$ 1.234,56)
     * 
     * @param valor BigDecimal a ser formatado
     * @return String formatada como moeda
     */
    public static String formatarComoMoedaBrasileira(BigDecimal valor) {
        if (valor == null) {
            return null;
        }
        NumberFormat formatoBrasileiro = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
        return formatoBrasileiro.format(valor);
    }

}
