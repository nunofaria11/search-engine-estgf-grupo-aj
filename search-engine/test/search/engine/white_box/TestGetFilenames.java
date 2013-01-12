/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine.white_box;

import java.util.ArrayList;
import org.junit.Test;
import search.engine.ProcessorQC;

/**
 *
 * @author nuno
 */
public class TestGetFilenames {

    public TestGetFilenames() {
    }

    private ArrayList<String> buildExpectedFilenames() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("F1.txt");
        list.add("F2.txt");
        list.add("F3.txt");
        return list;
    }

    @Test
    public void testGetFileNames() {
        ProcessorQC processor = new ProcessorQC();
        
        String path = "files_test/";
        
        ArrayList<String> list = processor.getFileNames(path);

        System.out.println("input: " + path);
        System.out.println("output: " + list);
        

        assert list.equals(buildExpectedFilenames());
    }
}
