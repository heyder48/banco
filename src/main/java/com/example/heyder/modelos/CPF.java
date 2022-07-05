package com.example.heyder.modelos;

public class CPF {
    private String numero;

    public CPF(String numero) {
        if(numero == null) {
            throw new IllegalArgumentException("O número do CPF não pode ser nulo");
        }
        if(numero.length() != 11) {
            throw new IllegalArgumentException("O número do CPF deve ter 11 dígitos");
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
