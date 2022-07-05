package com.example.heyder.modelos;

public class CNPJ {

    private String numero;

    public CNPJ(String numero) {
        if (numero == null) {
            throw new IllegalArgumentException("O número do CNPJ não pode ser nulo");
        }
        if (numero.length() != 14) {
            throw new IllegalArgumentException("O número do CNPJ deve ter 14 dígitos");
        }
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
}
