package com.example.heyder;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.example.heyder.modelos.CNPJ;

public class CNPJTeste {
    
    @Test
    void naoDeveiriaCriarCnpjInvalido(){
        assertThrows(IllegalArgumentException.class, () -> new CNPJ(""));
        assertThrows(IllegalArgumentException.class, () -> new CNPJ("1234567890123"));
        assertThrows(IllegalArgumentException.class, () -> new CNPJ("12345678901098887899"));
        
    }
    
}
