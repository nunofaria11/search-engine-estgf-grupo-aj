/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine;

import java.io.FileNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nuno
 */
public class SearchPhraseTest {

    public SearchPhraseTest() {
    }

    private void testSearchPhrase(String query) {

        boolean success;

        ProcessorQC p = new ProcessorQC();


        try {
            p.folderDefine("files/");
            success = p.searchPhrase(query);

        } catch (FileNotFoundException ex) {
            success = false;
            System.out.println("erro");
        } catch (NullPointerException ex) {
            success = false;
            System.out.println("erro");
        }

        System.out.println("search phrase: " + success + " (\"" + query + "\")");


    }
    /*
     * Eu gosto muito de engenharia de software 2!!!	
	
	
	
     Este é o ano de 2012/1013 da ESTGF!!!!!	
	
     123456!!!!!!	
	
     */

    @Test
    public void testAllSearchPhrases() {
        testSearchPhrase("Eu gosto muito de engenharia de software 2!!!");
        testSearchPhrase("");
        testSearchPhrase("Este é o ano de 2012/1013 da ESTGF!!!!!");
        testSearchPhrase("123456!!!!!!");


    }
}
