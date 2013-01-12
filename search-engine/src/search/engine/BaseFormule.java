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
    public double calculateValue(double[][] M, double[][] Q, int l) {
        double result;
        // a: somatorio
        double a = 0;
        for (int i = 0; i < M[l].length; i++) {
            a += M[l][i] * Q[0][i];
        }
        // b: somatorio
        double b = 0;
        for (int u = 0; u < M[l].length; u++) {
            b += M[l][u] * M[l][u];
        }
        b = Math.sqrt(b);
        // c: somatorio
        double c = 0;

        for (int x = 0; x < Q[0].length; x++) {
            c += Q[0][x] * Q[0][x];
        }
        c = Math.sqrt(c);

        result = a / (b * c);
        return result;
    }
}
