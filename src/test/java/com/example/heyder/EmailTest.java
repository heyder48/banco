package com.example.heyder;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.example.heyder.modelos.Email;

public class EmailTest {

    @Test
    void naoDeveriaCriarEmailsComEnderecosInvalidos() {
        assertThrows(IllegalArgumentException.class,() -> new Email(null));
        assertThrows(IllegalArgumentException.class,() -> new Email(""));
        assertThrows(IllegalArgumentException.class,() -> new Email("email"));
        assertThrows(IllegalArgumentException.class,() -> new Email("email@"));
        assertThrows(IllegalArgumentException.class,() -> new Email("email@."));
        assertThrows(IllegalArgumentException.class,() -> new Email("email@.com"));
        
    
    }
    
}
