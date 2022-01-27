package br.com.contabil.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidade que representa uma conta
 * @author emanuel developer
 *
 */
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "CONTA", schema = "CONTABILIDADE")
public class Conta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "serial")
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "VALOR_ORIGINAL", nullable = false)
    private BigDecimal valorOriginal;

    @Column(name = "VALOR_CORRIGIDO")
    private BigDecimal valorCorrigido;

    @Column(name = "QTD_DIAS_ATRASO")
    private Long qtdDiasAtraso;

    @Column(name = "DATA_VENCIMENTO", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "DATA_PAGAMENTO", nullable = false)
    private LocalDate dataPagamento;

    @Column(name = "DATA_CADASTRO")
    private LocalDate dataCadastro;

}
