/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 *
 * @author nuno
 */
public class Engine {

    public static void main(String[] args) throws UnsupportedEncodingException, IOException {

        Reader reader = new InputStreamReader(System.in, Charset.forName("UTF-8"));
        BufferedReader stdin = new BufferedReader(reader);

        System.out.print("Caminho (default ENTER \".\"): ");

        // aqui tem que escrever "files/" 
        String path = stdin.readLine();
        if (path.equals("")) {
            path = ".";
        }

        System.out.print("Query: ");
        String query = stdin.readLine();

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

        rankingList.showRankingList();


    }
}
