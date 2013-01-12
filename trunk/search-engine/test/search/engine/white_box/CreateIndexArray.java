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
public class CreateIndexArray {

    public CreateIndexArray() {
    }

    /**
     * Função que cria uma Array de palavras
     * @return list
     */
    private ArrayList<String> buildExpectedIndexArray() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("palavra");
        list.add("outra");
        list.add("coisa");
        return list;
    }

    /**
     * Excução do teste
     * @throws FileNotFoundException 
     */
    @Test
    public void testCreateIndexArray() throws FileNotFoundException {
        ProcessorQC processor = new ProcessorQC();
        processor.folderDefine("files/");   // passo 1
        processor.searchPhrase("palavra");  // passo 2   

        ArrayList<String> resultList = processor.createIndexArray(processor.getDocLineM());

        ProcessorQC.printStringMatrix(processor.getDocLineM());
        System.out.println("LIST: " + resultList);
        assert resultList.equals(buildExpectedIndexArray());
    }
}
