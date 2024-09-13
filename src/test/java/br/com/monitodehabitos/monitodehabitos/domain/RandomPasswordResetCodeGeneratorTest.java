package br.com.monitodehabitos.monitodehabitos.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomPasswordResetCodeGeneratorTest {

    @Test
    @DisplayName("Deve gerar um código de redefinição de senha de 8 caracteres")
    void testGenerateRandomCodeLength() {
        RandomPasswordResetCodeGenerator generator = new RandomPasswordResetCodeGenerator();
        String code = generator.generateRandomCode();

        // Verifica se o código gerado tem o tamanho correto
        Assertions.assertEquals(8, code.length(), "O código deve ter 8 caracteres");
    }

    @Test
    @DisplayName("Deve gerar códigos diferentes em chamadas consecutivas")
    void testGenerateDifferentCodes() {
        RandomPasswordResetCodeGenerator generator = new RandomPasswordResetCodeGenerator();
        String code1 = generator.generateRandomCode();
        String code2 = generator.generateRandomCode();

        // Verifica se os códigos gerados são diferentes
        Assertions.assertNotEquals(code1, code2, "Os códigos gerados devem ser diferentes");
    }
}
