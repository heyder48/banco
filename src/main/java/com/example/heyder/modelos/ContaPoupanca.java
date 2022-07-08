package com.example.heyder.modelos;

import java.math.BigDecimal;
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
    
    super.tipo = TipoDeConta.POUPANCA;
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
        if(valor.compareTo(super.saldo) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        super.saldo.subtract(valor);
        
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
            super.saldo.add(super.saldo.multiply(taxaRendimento.divide(new BigDecimal(100)).add(new BigDecimal(1))).multiply(new BigDecimal(ChronoUnit.MONTHS.between(aniversarioConta, dataAtual))));
            this.atualizarAniversario(dataAtual);
        }
    }

}
