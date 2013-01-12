/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine;

/**
 *
 * @author nuno nunes / Luis Teixeira
 */
public class BaseFormule implements IntFormule {

    @Override
    public double calculateValue(double[][] M, double[][] Q, int L) {
        double result;
        // a: somatorio
        double a = 0;
        for (int i = 0; i < M[L].length; i++) {
            a += M[L][i] * Q[0][i];
        }
        // b: somatorio
        double b = 0;
        for (int u = 0; u < M[L].length; u++) {
            b += M[L][u] * M[L][u];
        }
        b = Math.sqrt(b);
        // c: somatorio
        double c = 0;

        for (int x = 0; x < Q[0].length; x++) {
            c += Q[0][x] * Q[0][x];
        }
        c = Math.sqrt(c);

        if (b * c == 0) {
            return 0;
        }
        result = a / (b * c);
        return result;
    }
}
