/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author nuno nunes / Luis Teixeira
 */
public class RankingList {
/**
 * Variáveis de instância
 */
    private IntFormule formula;
    private ArrayList<String> rankingList;
    private HashMap<String, Double> documentRanking;

    public RankingList(IntFormule formula) {
        this.formula = formula;
    }

    
    
    
    /**
     * Função que cria o ranking por ordem dos resultados da função base calculateValue
     * @param M matrixM
     * @param Q matrixQ
     * @param filenames  ficheiros
     */
    public void createRankingList(double[][] M, double[][] Q, ArrayList<String> filenames) {

        int numDocs = filenames.size();

        documentRanking = new HashMap<String, Double>();

        for (int line = 0; line < numDocs; line++) {

            double docValue = formula.calculateValue(M, Q, line);

            documentRanking.put(filenames.get(line), docValue);

        }

        rankingList = new ArrayList<String>();

        rankingList.addAll(documentRanking.keySet());

        Comparator<String> rankingComparator = new Comparator<String>() {
            @Override
            public int compare(String doc1, String doc2) {
                Double doc1Val = documentRanking.get(doc1);
                Double doc2Val = documentRanking.get(doc2);
                return doc2Val.compareTo(doc1Val);
            }
        };

        /*
         * ordenar a lista de documentos
         */
        Collections.sort(rankingList, rankingComparator);
    }

    /**
     * Retorna a lista de documentos ordendados por ranking
     *
     * @return ArrayList<String> com os nomes dos documentos ordenados
     */
    public ArrayList<String> getRankingList() {
        return rankingList;
    }

    
    
    
    /**
     * Imprime para o ecrã o ranking dos ficheiros
     */
    public void showRankingList() {
        for (String filename : rankingList) {
            System.out.println(filename + " [ " + documentRanking.get(filename) + " ]");
        }
    }
}
