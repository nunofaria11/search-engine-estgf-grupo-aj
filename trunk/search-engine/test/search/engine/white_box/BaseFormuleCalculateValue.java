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
 * @author nuno
 */
public class BaseFormuleCalculateValue {

    public BaseFormuleCalculateValue() {
    }

    private double[][] buildExpectedQ() {
        double[][] q = new double[1][];
        q[0] = new double[2];

        q[0][0] = 0;
        q[0][1] = 0.176091259055681;

        return q;
    }

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

    private double calculateA(double[][] M, double[][] Q, int l) {
        double a = 0;
        for (int i = 0; i < M[l].length; i++) {
            a += M[l][i] * Q[0][i];
        }
        return a;
    }

    private double calculateB(double[][] M, double[][] Q, int l) {
        double b = 0;
        for (int u = 0; u < M[l].length; u++) {
            b += M[l][u] * M[l][u];
        }
        b = Math.sqrt(b);
        return b;
    }

    private double calculateC(double[][] M, double[][] Q, int l) {
        double c = 0;

        for (int x = 0; x < Q[0].length; x++) {
            c += Q[0][x] * Q[0][x];
        }
        c = Math.sqrt(c);
        return c;
    }

    private double calculate(double[][] M, double[][] Q, int l) {

        double a = calculateA(M, Q, l);
        double b = calculateB(M, Q, l);
        double c = calculateC(M, Q, l);

        System.out.println("A: "+a);
        System.out.println("B: "+b);
        System.out.println("C: "+c);
        return a / (b * c);
    }

    @Test
    public void testCalculateValue() throws FileNotFoundException {

        double[][] expectedM = buildExpectedM();
        double[][] expectedQ = buildExpectedQ();


        String path = "files/";
        String query = "engenharia de software";

        ProcessorQC processor = new ProcessorQC();
        processor.folderDefine(path);   // passo 1
        processor.searchPhrase(query);  // passo 2
        processor.process();            // passo 3

        BaseFormule bf = new BaseFormule();

        double[][] M = processor.getMatrixM();
        double[][] Q = processor.getMatrixQ();

        int l = 2;
        double value = bf.calculateValue(M, Q, l);
        double expectedValue = calculate(expectedM, expectedQ, l);

        System.out.println("value = " + value);
        System.out.println("expectedvalue = " + expectedValue);
        System.out.println(processor.createIndexArray(processor.getDocLineM()));
        ProcessorQC.printMatrix(processor.getFullMatrixM());

        double PRECISAO = 0.000001;

        assert Math.abs(value - expectedValue) < PRECISAO;


    }
}
