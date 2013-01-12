/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine.white_box;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.Test;
import search.engine.ProcessorQC;

/**
 *
 * @author nuno nunes / Luis Teixeira
 */
public class DocLineMTest {

    public DocLineMTest() {
    }

    /**
     * Função que cria a DocLineM
     * @return 
     */
    public String[][] buildExpectedDocLineM() {
        return new String[][]{
                    {"palavra", "palavra", "palavra"},
                    {"palavra", "palavra", "palavra", "outra", "palavra"},
                    {"palavra", "palavra", "palavra", "outra", "outra", "palavra"},
                    {"palavra"}
                };
    }

    /**
     * Verifica se duas matrizes são exatamente iguais
     *
     * @param matriz A
     * @param matriz B
     * @return true se forem iguais, false se forem diferentes
     */
    private boolean matrixEquals(String[][] mA, String[][] mB) {

        int linhasA = mA.length;
        int linhasB = mB.length;

        if (linhasA != linhasB) {
            System.out.println("\nERRO: linhas nao sao iguais");
            return false;
        }

        boolean result = true;
        for (int linha = 0; linha < linhasA; linha++) {

            int colunasA = mA[linha].length;
            int colunasB = mB[linha].length;

            if (colunasA != colunasB) {
                System.out.println("\nERRO: colunas nao sao iguais");
                return false;
            }
            for (int coluna = 0; coluna < colunasA; coluna++) {
                if (!mA[linha][coluna].equals(mB[linha][coluna])) {
                    System.out.println("\nERRO: valores nao sao iguais");
                    return false;
                }

            }
        }

        return result;
    }

    /**
     * Execução do teste
     * @throws FileNotFoundException 
     */
    @Test
    public void testCreateDocLineM() throws FileNotFoundException {

        String path = ",";

        ProcessorQC processor = new ProcessorQC();
        ArrayList<String> filenames = processor.getFileNames(path);
        processor.setFiles(filenames);
        String[][] docLineM = processor.createDocLineM(path, filenames);
        processor.setDocLineM(docLineM);
        processor.searchPhrase("palavra");


        System.out.println("*** expected docLineM: ");
        ProcessorQC.printStringMatrix(buildExpectedDocLineM());

        System.out.println("\n***docLineM: ");
        ProcessorQC.printStringMatrix(docLineM);

        assert matrixEquals(docLineM, buildExpectedDocLineM());

    }
}
