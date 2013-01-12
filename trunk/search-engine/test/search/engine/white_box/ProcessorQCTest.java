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
 * @author nuno nunes / Luis Teixeira
 */
public class ProcessorQCTest {

    public ProcessorQCTest() {
    }

    /**
     * Execução do teste
     */
    @Test
    public void testRemoverPontuacaoDigitos() {
        ProcessorQC proc = new ProcessorQC();
        try {
            proc.folderDefine("files/");
            proc.searchPhrase("O mundo da coca-");
            proc.process();
            String input = "Olá eu sou o Nuno de 1984.";
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
