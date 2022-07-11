package com.example.heyder;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.example.heyder.modelos.CPF;



public class CPFTest {

    @Test
    void naoDeveiriaCriarCpfInvalido(){
        assertThrows(IllegalArgumentException.class, () -> new CPF(""));
        assertThrows(IllegalArgumentException.class, () -> new CPF("1234567890"));
        
    }
    
}
