package com.example.heyder.modelos;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe que representa uma conta abstrata
 * @author heyder
 */
public abstract class Conta {

    protected String numero;
    
    protected String agencia;
    

    protected BigDecimal saldo;
    protected Cliente cliente;
    protected TipoDeConta tipo;

    public TipoDeConta getTipo() {
        return tipo;
    }

    public Conta(String numero, String agencia, Cliente cliente) {
        if(numero == null || numero.isEmpty()){
            throw new IllegalArgumentException("Numero da conta não pode ser nulo ou vazio");
        }
        if(agencia == null || agencia.isEmpty()){
            throw new IllegalArgumentException("Agencia da conta não pode ser nula ou vazia");
        }
        if(cliente == null){
            throw new IllegalArgumentException("Cliente da conta não pode ser nulo");
        }
        this.numero = numero;
        this.agencia = agencia;
        this.saldo = BigDecimal.ZERO;
        this.cliente = cliente;
    }

    /**
     * Metodo que realiza o saque na conta corrente
     * Para a pessoa juridica o saque é realizado com taxa de 0,5%
     * Verifica, para a pessoa juridica, se o saldo é suficiente para o saque e a taxa de saque.
     * @param valor
     * @throws IllegalArgumentException se o valor for maior que o saldo da conta
     *
     */
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
            saldo.subtract(valor.multiply(new BigDecimal(1.005)));
        }
        else {
            saldo.subtract(valor);
        }

    };

    public  void depositar(BigDecimal valor){
        if(valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Valor do deposito não pode ser menor ou igual a zero");
        }
        
        saldo.add(valor);
    };

    /**
     * Metodo que realiza a transferencia de valor entre contas
     * Utiliza o metodo sacar para realizar o saque e o depositar para realizar o deposito na conta de destino.
     * @param valor
     * @param conta
     * @throws IllegalArgumentException se o valor for maior que o saldo da conta
     * @see Conta#sacar(BigDecimal)
     * @see Conta#depositar(BigDecimal)
     */
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
    public BigDecimal getSaldo() {
        return saldo.setScale(2, RoundingMode.HALF_UP);
    }

    public String getNumero() {
        return numero;
    }

    public String getAgencia() {
        return agencia;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numero='" + numero + '\'' +
                ", agencia='" + agencia + '\'' +
                ", Tipo de Conta= " + tipo.name().substring(0,1).toUpperCase()+ tipo.name().substring(1).toLowerCase() +                    
                '}';
    }
}
