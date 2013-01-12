/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine.white_box;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Test;
import search.engine.ProcessorQC;

/**
 *
 *
 * Componente search-engine.java versão 1.0
 *
 * - process()
 *
 * - digitsDelete(String word) DONE
 *
 * - ponctuationDelete(String word) DONE
 *
 * - getFileNames(String path) INUTIL
 *
 * - createDocLineM(String path, ArrayList<String> files) DONE
 *
 * - createIndexArray(String[][] matrix) INUTIL??? esta nao faz muito sentido porque é apenas auxiliar
 *
 * - createMatrixOcc(String[][] docLineM) DONE
 *
 * - updateMatrix(double[][] matrix) DONE
 *
 * - getMatrixM() INUTIL 
 *
 * - getMatrixQ() INUTIL 
 *
 * - createRankingList(double[][] M, double[][] Q, ArrayList<String> filenames)
 *
 * - calculateValue(double[][] M, double[][] Q, int l)
 *
 * - countDocWords(double[][] matrix, int wordColumn) DONE
 *
 *
 * @author nuno
 */
public class ProcessorQCTest {

    public ProcessorQCTest() {
    }

    @Test
    public void testRemoverPontuacaoDigitos() {
        ProcessorQC proc = new ProcessorQC();
        try {
            proc.folderDefine("files/");
            proc.searchPhrase("O mundo da coca-");
            proc.process();
            String input = "Olá eu sou o Nuno de 1988.";
            String result = proc.ponctuationDelete(input);
            result = proc.digitsDelete(result);
            String correct = "Olá eu sou o Nuno de ";
            System.out.println("INPUT = " + input);
            System.out.println("RESULT = " + result);
            System.out.println("CORRECT = " + correct);
            assertEquals(correct, result);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProcessorQCTest.class.getName()).log(Level.SEVERE, null, ex);
        }



    }
}
