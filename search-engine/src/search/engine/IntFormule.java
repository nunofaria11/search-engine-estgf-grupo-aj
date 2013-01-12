/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine;

/**
 *
 * @author nuno nunes / Luis Teixeira
 */
public interface IntFormule {
    /**
     * 
     * @param M
     * @param Q
     * @param document
     * @return 
     */
    public double calculateValue(double[][] M, double[][] Q, int document);
    
}
