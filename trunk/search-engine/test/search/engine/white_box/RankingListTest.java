/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine.white_box;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.Test;
import search.engine.BaseFormule;
import search.engine.ProcessorQC;
import search.engine.RankingList;

/**
 *
 * @author nuno nunes / Luis Teixeira
 */
public class RankingListTest {

    public RankingListTest() {
    }

    
    /**
     * Função que cria o RankingList
     * @return 
     */
    private ArrayList<String> buildExpectedRankingList() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("F3.txt");
        list.add("F2.txt");
        list.add("F1.txt");
        return list;
    }
    
    /**
     * Execução do teste
     * @throws FileNotFoundException 
     */
    @Test
    public void testCreateRanking() throws FileNotFoundException {
        
        String path = "files_test/";
        String query = "outra";
        
        ProcessorQC processor = new ProcessorQC();
        processor.folderDefine(path);   // passo 1
        processor.searchPhrase(query);  // passo 2
        processor.process();            // passo 3
        
        BaseFormule bf = new BaseFormule();
        ArrayList<String> filenames = processor.getFileNames(path);
        double[][] M = processor.getMatrixM();
        double[][] Q = processor.getMatrixQ();

        RankingList rankingList = new RankingList(bf);
        rankingList.createRankingList(M, Q, filenames);
        
        assert rankingList.getRankingList().equals(buildExpectedRankingList());
        
    }
}
