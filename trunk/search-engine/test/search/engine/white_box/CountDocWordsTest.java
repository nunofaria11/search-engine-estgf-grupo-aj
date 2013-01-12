/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine.white_box;

import java.io.FileNotFoundException;
import org.junit.Test;
import search.engine.ProcessorQC;

/**
 *
 * @author nuno
 */
public class CountDocWordsTest {

    public CountDocWordsTest() {
    }

    @Test
    public void testCountDocWords() throws FileNotFoundException {

        ProcessorQC processor = new ProcessorQC();

        processor.folderDefine("files_test/");   // passo 1
        processor.searchPhrase("palavra");  // passo 2
        double[][] occMatrix = processor.createMatrixOcc(processor.getDocLineM());
        processor.setMatrixM(occMatrix);

        int countPalavra = 3;
        int countOutra = 2;

        assert countPalavra == processor.countDocWords(occMatrix, 0) && countOutra == processor.countDocWords(occMatrix, 1);


    }
}
