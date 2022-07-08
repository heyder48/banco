package com.example.heyder.modelos;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Conta {

    protected String numero;
    protected String agencia;
    protected BigDecimal saldo;
    protected Cliente cliente;
    protected TipoDeConta tipo;

    public Conta(String numero, String agencia, Cliente cliente) {
        this.numero = numero;
        this.agencia = agencia;
        this.saldo = new BigDecimal(0).setScale(2,RoundingMode.HALF_EVEN);
        this.cliente = cliente;
    }

    public abstract void sacar(BigDecimal valor);
    public abstract void depositar(BigDecimal valor);
    public abstract void transferir(BigDecimal valor, Conta conta);
    public BigDecimal getSaldo() {
        return saldo;
    }
}
