package unitTest;

// Bibliotecas
import br.com.iterasys.Calculadora;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Classe
public class TesteCalculadora { // inicio da classe
    // Atributos

    // Funções e Métodos

    @Test
    public void testeSomarDoisNumeros(){ // inicio do teste do somar
        // Configura
        // Valores de entrada
        double num1 = 7;
        double num2 = 5;
        // Valores de saída
        double resultadoEsperado = 12;

        // Executa
        double resultadoAtual = Calculadora.somarDoisNumeros(num1, num2);

        // Valida
        assertEquals(resultadoEsperado, resultadoAtual);
    } // final do teste do somar

    // Este é um teste data driven = orientado por dados
    @ParameterizedTest
    @CsvSource(value = {
            "7, 5, 12.0",
            "56, 44, 100.0",
            "10, 0, 10.0",
            "15, -5, 10.0",
            "-8, -6, -14.0"
    }, delimiter = ',')
    public void testeSomarDoisNumerosLendoLista(String  txtNum1, String txtNum2, String resultadoEsperado){ // inicio do teste do somar
        // Configura
        // Os dados de entrada e o resultado esperado vem da lista

        // Executa
        double resultadoAtual = Calculadora.somarDoisNumeros(Integer.valueOf(txtNum1), Integer.valueOf(txtNum2));

        // Valida
        assertEquals(Double.valueOf(resultadoEsperado), resultadoAtual);
    } // final do teste do somar


    @ParameterizedTest
    @CsvFileSource(resources = "csv/massaSomar.csv", numLinesToSkip = 1, delimiter = ',')
    public void testeSomarDoisNumerosLendoArquivo(String  txtNum1, String txtNum2, String resultadoEsperado){ // inicio do teste do somar
        // Configura
        // Os dados de entrada e o resultado esperado vem da lista

        // Executa
        double resultadoAtual = Calculadora.somarDoisNumeros(Integer.valueOf(txtNum1), Integer.valueOf(txtNum2));

        // Valida
        assertEquals(Double.valueOf(resultadoEsperado), resultadoAtual);
    } // final do teste do somar
    @Test
    public void testeDividirDoisNumeros(){ //inicio do teste do dividir
        // Configura
        double num1 = 10;
        double num2 = 4;
        double resultadoEsperado = 2.5;

        // Executa
        double resultadoAtual = Calculadora.dividirDoisNumeros(num1,num2);
        // Valida
        assertEquals(resultadoEsperado, resultadoAtual);

    } // final do teste do dividir

    @Test
    public void testeDividirDoisNumerosInteiros(){ // inicío do teste dividir inteiro
        // Configura
        int numA = 42;
        int numB = 6;
        String resultadoEsperado = "Não é possível dividir por zero";

        // Executa
        String resultadoAtual = Calculadora.dividirDoisNumerosInteiros(numA, numB);

        // Valida
        assertEquals(resultadoEsperado, resultadoAtual);
        System.out.println(numA + " / " + numB + " = " + resultadoAtual);
        System.out.println("O resultado esperado: " + resultadoEsperado);
    } // fim do teste dividir inteiro

    // Exercício função Subtrair: teste de unidade simples e lendo uma lista de valores de teste
    @Test
    public void testeSubtrairDoisNumeros(){
        double num1 = 15;
        double num2 = 5;
        double resultadoEsperado = 10;

        double resultadoAtual = Calculadora.subtrairDoisNumeros(num1, num2);

        assertEquals(resultadoEsperado, resultadoAtual);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "15, 5, 10.0",
            "20, 3, 17.0",
            "30, 4, 26.0",
            "40, 7, 33.0",
            "-7, -3, -4.0"
    }, delimiter = ',')
    public void testeSubtrairDoisNumerosLendoLista(String txtNum1, String txtNum2, String resultadoEsperado){
        // Os dados de entrada e o resultado esperado vem da lista

        double resultadoAtual = Calculadora.subtrairDoisNumeros(Integer.valueOf(txtNum1), Integer.valueOf(txtNum2));

        assertEquals(Double.valueOf(resultadoEsperado), resultadoAtual);
    }

    // Exercício função Multiplicar: teste de unidade simples e lendo uma lista de valores de teste
    @Test
    public void testeMultiplicarDoisNumeros(){
        Integer num1 = 3;
        Integer num2 = 9;
        Double resultadoEsperado = 27.0;

        Double resultadoAtual = Calculadora.multiplicarDoisNumeros(num1, num2);

        assertEquals(resultadoEsperado, resultadoAtual);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "3, 9, 27.0",
            "4, 8, 32.0",
            "5, 9, 45.0",
            "7, 7, 49.0",
            "8, 9, 72.0"
    }, delimiter = ',')
    public void testeMultiplicarDoisNumerosLendoLista(String txtNum1, String txtNum2, String resultadoEsperado){

        // Os dados de entrada e o resultado esperado vem da lista

        double resultadoAtual = Calculadora.multiplicarDoisNumeros(Integer.valueOf(txtNum1), Integer.valueOf(txtNum2));

        assertEquals(Double.valueOf(resultadoEsperado), resultadoAtual);
    }
}
