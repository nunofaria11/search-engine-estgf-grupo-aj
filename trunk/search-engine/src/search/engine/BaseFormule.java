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

    private double[][] matrixM;
    private double[][] matrixQ;

    public BaseFormule(double[][] M, double[][] Q) {
        this.matrixM = M;
        this.matrixQ = Q;
    }

    @Override
    public double calculateValue(double[][] M, double[][] Q) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
