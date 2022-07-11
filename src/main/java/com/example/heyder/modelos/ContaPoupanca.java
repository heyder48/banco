package com.example.heyder.modelos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Classe que representa uma conta poupanca
 * Herda de Conta
 * @author Heyder
 *
 */
public class ContaPoupanca extends Conta {

    /**
     * Atributo taxaRendimento representa a taxa de rendimento da conta
     *
     */
    private final BigDecimal taxaRendimento = new BigDecimal("0.5");

    /**
     * Atributo aniversarioConta representa o aniversario da conta
     *
     */
    private LocalDate aniversarioConta;

    private BigDecimal rendimento;

    private BigDecimal saldo;

    

    /**
     * Construtor da classe ContaPoupanca
     * @param numero
     * @param agencia
     * @param cliente
     * 
     */

    public ContaPoupanca(String numero, String agencia, Cliente cliente) {
        super(numero, agencia, cliente);
        if(cliente.tipoDePessoa == TipoDePessoa.JURIDICA){
            throw new IllegalArgumentException("Conta poupança não pode ser para pessoa jurídica");
        }

    this.aniversarioConta = LocalDate.now();
    rendimento = BigDecimal.ZERO;
    super.tipo = TipoDeConta.POUPANCA;
    this.saldo = BigDecimal.ZERO;
    
    System.out.println("Conta poupança criada com sucesso!");
    
    
    }

    /**
     * Método para realizar o saque na conta poupança.
     * Sobrescreve o método sacar da classe Conta porque a conta poupança não adimite pessoa jurídica.
     * @param valor
     * @throws IllegalArgumentException se o valor for maior que o saldo da conta
     */
    @Override
    public void sacar(BigDecimal valor) {
        if(valor.compareTo(this.saldo) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        this.saldo.subtract(valor);
        
    }

    @Override
    public BigDecimal getSaldo() {
        return this.saldo.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public void depositar(BigDecimal valor) {
        if(valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Valor inválido");
        }
        
        this.saldo = this.saldo.add(valor);
    }

    
    /**
     * Atualiza o aniversario da conta poupança
     * @param aniversarioConta
     *
     */
    
    private void atualizarAniversario(LocalDate aniversarioConta) {
        this.aniversarioConta = aniversarioConta;
    }

    
    /** 
     * @return BigDecimal
     */
    public BigDecimal getRendimento() {
        return rendimento;
    }

    /**
     * Método que realiza o rendimento da conta poupança
     * Atualiza o saldo da conta poupança com a taxa de rendimento mensal.
     * @param dataAtual
     * @throws IllegalArgumentException se a data for maior que a data de aniversario da conta
     *
     */

    public void atualizaRendimento(LocalDate dataAtual){
        if(ChronoUnit.DAYS.between(aniversarioConta, dataAtual) < 0) {
            throw new IllegalArgumentException("Data inválida");
        }
        if(ChronoUnit.MONTHS.between(aniversarioConta, dataAtual) >= 1){
            this.rendimento = this.saldo.multiply(taxaRendimento.divide(new BigDecimal(100))).multiply(new BigDecimal(ChronoUnit.MONTHS.between(aniversarioConta, dataAtual)));
            this.saldo = this.saldo.add(this.rendimento);
            this.atualizarAniversario(dataAtual);
        }
    }

}
