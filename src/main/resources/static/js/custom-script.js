
$(document).ready(function () {
    // Exibe o Toast Mensagem automaticamente
    $('#toast').toast('show');

    // Ativar automaticamente o data-mask
    $('[data-mask]').each(function () {
        $(this).mask($(this).attr('data-mask'));
    });

     // Mascara automatica para campos de dinheiro
    $('.money').mask("#.##0,00", {reverse: true});

    // Conversão do campo money antes do envio para o Java
    $('form').on('submit', function () {
        $('.money').each(function () {
            let valorOriginal = $(this).val();

            // Remove pontos (milhares) e substitui vírgula decimal por ponto
            let valorConvertido = valorOriginal.replace(/\./g, '').replace(',', '.');

            $(this).val(valorConvertido); // seta o novo valor no input
        });
    });

    $('[data-readonly]').each(function () {
        $(this).css({
            'background-color': '#e9ecef', /* Cor padrão do disabled */
            'cursor': 'default', /* Mantém o cursor padrão */
            'color': '#6c757d', /* Cor do texto similar ao disabled */
            'border': '1px solid #ced4da', /* Borda padrão */
            'pointer-events': 'none' /* Impede interação, mas mantém a aparência */
        });
    });

    // Aplicar máscara e Datepicker a todos os campos com a classe .data-picker
    $(".datePicker").each(function () {
        $(this).datepicker({
            dateFormat: "dd/mm/yy",
            changeMonth: true,
            changeYear: true,
            yearRange: "1900:2100",
            regional: "pt-BR"
        });
    });


    $('.select2').select2({
        placeholder: "Selecione uma opção",
        theme: 'bootstrap4', 
        allowClear: true
    });

    


});
