/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine.white_box;

import java.io.FileNotFoundException;
import org.junit.Test;
import search.engine.ProcessorQC;

/**
 * Entra uma matrixM e sai uma matrixM
 *
 * @author nuno nunes / Luis Teixeira
 */
public class CreateUpdateMatrixTest {

    public CreateUpdateMatrixTest() {
    }

    /**
     * Constrói a matriz esperada para os ficheiros na pasta "files_test"
     *
     * @return Matriz de ocorrências esperada
     */
    private double[][] buildExpectedOccMatrix() {
        double[][] m = new double[3][];
        m[0] = new double[2];
        m[1] = new double[2];
        m[2] = new double[2];

        m[0][0] = 3;
        m[0][1] = 0;

        m[1][0] = 4;
        m[1][1] = 1;

        m[2][0] = 4;
        m[2][1] = 2;
        return m;
    }

    /**
     * Função que atualiza a matriz de ocorrências
     * @return 
     */
    private double[][] buildExpectedUpdatedMatrix() {
        double[][] m = new double[3][];
        m[0] = new double[2];
        m[1] = new double[2];
        m[2] = new double[2];

        m[0][0] = 0;
        m[0][1] = 0;

        m[1][0] = 0;
        m[1][1] = 0.176091259;

        m[2][0] = 0;
        m[2][1] = 0.352182518;
        return m;
    }

    /**
     * Verifica se duas matrizes são exatamente iguais
     *
     * @param matriz A
     * @param matriz B
     * @return true se forem iguais, false se forem diferentes
     */
    private boolean matrixEquals(double[][] mA, double[][] mB) {

        double PRECISAO = 0.0000001;

        int linhasA = mA.length;
        int linhasB = mB.length;

        if (linhasA != linhasB) {
            return false;
        }

        boolean result = true;
        for (int linha = 0; linha < linhasA; linha++) {

            int colunasA = mA[linha].length;
            int colunasB = mB[linha].length;

            if (colunasA != colunasB) {
                return false;
            }
            for (int coluna = 0; coluna < colunasA; coluna++) {
                if (mA[linha][coluna] != mB[linha][coluna]) {
                    return false;
                }
                result &= Math.abs(mA[linha][coluna] - mB[linha][coluna]) > PRECISAO;

            }
        }

        return result;
    }

    /**
     * Execução do tste da criação da matriz de ocorrências
     * @throws FileNotFoundException 
     */
    @Test
    public void testCreateMatrixOcc() throws FileNotFoundException {

        double[][] expectedOccMatrix = buildExpectedOccMatrix();

        ProcessorQC processor = new ProcessorQC();

        processor.folderDefine("files_test/");   // passo 1
        processor.searchPhrase("palavra");  // passo 2
        double[][] occMatrix = processor.createMatrixOcc(processor.getDocLineM()); 

        processor.setMatrixM(occMatrix); 

        System.out.println("EXPECTED OCC MATRIX: ");
        ProcessorQC.printMatrix(expectedOccMatrix);

        System.out.println("\nOCC MATRIX: ");
        ProcessorQC.printMatrix(processor.getMatrixM());

        // verifica se as matrizes são iguais
        assert matrixEquals(expectedOccMatrix, processor.getMatrixM());

    }

    /**
     * Execução do teste de atualização da matriz
     * @throws FileNotFoundException 
     */
    @Test
    public void testUpdateMatrix() throws FileNotFoundException {

        double[][] expectedUpdatedMatrix = buildExpectedUpdatedMatrix();

        ProcessorQC processor = new ProcessorQC();

        processor.folderDefine("files_test/");   // passo 1
        processor.searchPhrase("palavra");  // passo 2

        double[][] occMatrix = processor.createMatrixOcc(processor.getDocLineM());

        processor.setMatrixM(occMatrix); 

        processor.updateMatrix(occMatrix); 

        System.out.println("EXPECTED UPDATED MATRIX: ");
        ProcessorQC.printMatrix(expectedUpdatedMatrix);

        System.out.println("\nUPDATED MATRIX: ");
        ProcessorQC.printMatrix(processor.getMatrixM());

       // verifica se as matrizes são iguais
        assert matrixEquals(expectedUpdatedMatrix, processor.getMatrixM());


    }
}
