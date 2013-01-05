/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine;

import java.io.FileNotFoundException;
import org.junit.Test;

/**
 *
 * @author nuno
 */
public class FolderDefineTest {

    public FolderDefineTest() {
    }

    public void testFolderDefine(String input, String query) {

        boolean success = true;

        ProcessorQC p = new ProcessorQC();


        try {
            p.folderDefine(input);

        } catch (FileNotFoundException ex) {
            success = false;
        } catch (NullPointerException ex) {
            success = false;
        }

        System.out.println("input path: " + success + " (\"" + input +"\")");


    }

    @Test
    public void testAllFolderDefine() {
        testFolderDefine("files/", "");
        testFolderDefine("1234", "");
        testFolderDefine("c:\\ESII\\files\\", "");
        testFolderDefine("3.0", "");

    }
}
