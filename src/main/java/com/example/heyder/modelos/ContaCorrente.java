package com.example.heyder.modelos;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe que representa uma conta corrente
 * @author heyder
 *
 */
public class ContaCorrente extends Conta {

    private BigDecimal saldo;

    
    /**
     * Construtor da classe ContaCorrente
     * @param numero
     * @param agencia
     * @param cliente
     */
     
    public ContaCorrente(String numero, String agencia, Cliente cliente) {
        super(numero, agencia, cliente);
        super.tipo = TipoDeConta.CORRENTE;
        System.out.println("Conta corrente criada com sucesso!");
        this.saldo = BigDecimal.ZERO;
    }

    @Override
    public void depositar(BigDecimal valor) {
        if(valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Valor inválido");
        }
        
        this.saldo = this.saldo.add(valor);
    }

    @Override
    public BigDecimal getSaldo() {
        return this.saldo.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public void sacar(BigDecimal valor){
        if(valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Valor do saque não pode ser menor ou igual a zero");
        }
        if(valor.compareTo(saldo) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        if(cliente.tipoDePessoa == TipoDePessoa.JURIDICA){
            if(valor.compareTo(saldo.multiply(new BigDecimal(0.995))) > 0) {
                throw new IllegalArgumentException("Saldo insuficiente");
            }
            this.saldo.subtract(valor.multiply(new BigDecimal(1.005)));
        }
        else {
            this.saldo.subtract(valor);
        }

    }

    public void transferir(BigDecimal valor, Conta conta){
        if (valor.compareTo(saldo) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        if(valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Valor da transferencia não pode ser menor ou igual a zero");
        }
        if(conta == null){
            throw new IllegalArgumentException("Conta de destino não pode ser nula");
        }
        this.sacar(valor);
        conta.depositar(valor);
    }

    
    
    
     
     
    
    
}
