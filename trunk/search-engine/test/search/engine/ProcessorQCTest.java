/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
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
            //        boolean success = true;
            //        for (int i = 1; i <= 100; i++) {
            //            String input = "asd" + i + "asd";
            //            String correct = "asdasd";
            //            String correct = "asdasd";
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProcessorQCTest.class.getName()).log(Level.SEVERE, null, ex);
        }

//            success = success && result.equals(correct);
//        }
//        assert success == true;


    }
    //  List<String> inputs = Arrays.asList("files/", "1234", "c:\\ESII\\files\\", "3.0");
}
