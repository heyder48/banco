package com.example.heyder.modelos;

/**
 * Classe que representa uma conta corrente
 * @author heyder
 *
 */
public class ContaCorrente extends Conta {

    
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
    }

    
    
    
     
     
    
    
}
