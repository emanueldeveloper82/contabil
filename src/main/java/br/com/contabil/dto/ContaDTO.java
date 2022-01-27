package br.com.contabil.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContaDTO {

    private Long id;

    @NotEmpty(message = "O campo Nome não pode ser vazio.")
    @Length(min = 3, max = 200, message = "O campo Nome deve conter entre 3 e 200 caracteres.")
    private String nome;

    @NotNull(message = "O campo Valor Original não pode ser vazio.")
    private BigDecimal valorOriginal;

    private int qtdDiasAtraso;

    @NotNull(message = "O campo Data de Vencimento não pode ser vazio.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;

    @NotNull(message = "O campo Data de Pagamento não pode ser vazio.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataPagamento;

}
