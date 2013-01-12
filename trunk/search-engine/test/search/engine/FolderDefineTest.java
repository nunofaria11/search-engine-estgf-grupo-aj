/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author nuno
 */
public class FolderDefineTest {

    public FolderDefineTest() {
    }

    public void testFolderDefine(String path) {



        ProcessorQC p = new ProcessorQC();
        try {
            p.folderDefine(path);
            System.out.println(p.getFileNames(path));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FolderDefineTest.class.getName()).log(Level.SEVERE, null, ex);
        }




    }

    @Test
    public void testAllFolderDefine() {
//        testFolderDefine("files/");
        testFolderDefine("1234");
//        testFolderDefine("c:\\ESII\\files\\");
//        testFolderDefine("3.0");

    }
}
