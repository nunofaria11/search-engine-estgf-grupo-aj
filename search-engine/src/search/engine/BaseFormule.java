/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine;

/**
 *
 * @author nuno
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
        for (int i = 0; i < M[l].length; i++) {
            b += M[l][i] * M[l][i];
        }
        // c: somatorio
        double c = 0;

        for (int i = 0; i < Q[0].length; i++) {
            c += Q[0][i] * Q[0][i];
        }

        result = a / (b * c);
        return result;
    }
}
