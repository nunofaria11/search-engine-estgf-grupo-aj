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
public class BaseFormuleCalculateValue {

    public BaseFormuleCalculateValue() {
    }

    
    
    /**
     * Construção da matrix Q
     * @return q
     */
    private double[][] buildExpectedQ() {
        double[][] q = new double[1][];
        q[0] = new double[2];

        q[0][0] = 0;
        q[0][1] = 0.176091259055681;

        return q;
    }

    
    /**
     * Construção da matrix M
     * @return m
     */
    private double[][] buildExpectedM() {
        double[][] m = new double[3][];
        m[0] = new double[2];
        m[1] = new double[2];
        m[2] = new double[2];

        m[0][0] = 0;
        m[0][1] = 0;

        m[1][0] = 0;
        m[1][1] = 0.176091259;

        m[2][0] = 0;
        m[2][1] = 0.352182518;
        return m;
    }

    
    /**
     * A fórmula está dividida em 3 partes a,b,c
     */
    
    /**
     * Cálculo da parte "a" da formula
     * @param M
     * @param Q
     * @param l
     * @return 
     */
    private double calculateA(double[][] M, double[][] Q, int l) {
        double a = 0;
        for (int i = 0; i < M[l].length; i++) {
            a += M[l][i] * Q[0][i];
        }
        return a;
    }

    
    
    /**
     * Cálculo da parte "b" da formula
     * @param M
     * @param Q
     * @param l
     * @return 
     */
    private double calculateB(double[][] M, double[][] Q, int l) {
        double b = 0;
        for (int u = 0; u < M[l].length; u++) {
            b += M[l][u] * M[l][u];
        }
        b = Math.sqrt(b);
        return b;
    }

    
    /**
     * Cálculo da parte "c" da formula
     * @param M
     * @param Q
     * @param l
     * @return 
     */
    private double calculateC(double[][] M, double[][] Q, int l) {
        double c = 0;

        for (int x = 0; x < Q[0].length; x++) {
            c += Q[0][x] * Q[0][x];
        }
        c = Math.sqrt(c);
        return c;
    }

    
    
    
    /**
     * cáculo total da formula envolvendo todas as partes a,b,c
     * @param M
     * @param Q
     * @param l
     * @return 
     */
    private double calculate(double[][] M, double[][] Q, int l) {

        double a = calculateA(M, Q, l);
        double b = calculateB(M, Q, l);
        double c = calculateC(M, Q, l);


        return (double) ((double) a / (double) (b * c));
    }
  
    
    /**
     * Execução do teste
     * @throws FileNotFoundException 
     */
    @Test
    public void testCalculateValue() throws FileNotFoundException {

        double[][] expectedM = buildExpectedM();
        double[][] expectedQ = buildExpectedQ();
        System.out.println("M: ");
        ProcessorQC.printMatrix(expectedM);
        System.out.println("Q: ");
        ProcessorQC.printMatrix(expectedQ);


        String path = "files_test/";
        String query = "outra palavra";

        ProcessorQC processor = new ProcessorQC();
        processor.folderDefine(path);   // passo 1
        processor.searchPhrase(query);  // passo 2
        processor.process();            // passo 3

        BaseFormule bf = new BaseFormule();

        double[][] M = processor.getMatrixM();
        double[][] Q = processor.getMatrixQ();

        int l = 1;
        double value = bf.calculateValue(M, Q, l);
        double expectedValue = calculate(expectedM, expectedQ, l);

        System.out.println("value = " + value);


        double PRECISAO = 0.000001;

        assert Math.abs(value - expectedValue) < PRECISAO;


    }
}
