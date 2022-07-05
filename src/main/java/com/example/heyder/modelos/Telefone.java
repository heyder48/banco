package com.example.heyder.modelos;

public class Telefone {

    private String ddd;
    private String numero;

    public Telefone(String ddd, String numero) {
        if (ddd == null || numero == null || !ddd.matches("^[0-9]{2}$") || !numero.matches("^([0-9]{8}|[0-9]{9})$")) {
            throw new IllegalArgumentException("Telefone invalido");
        }
        this.ddd = ddd;
        this.numero = numero;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
