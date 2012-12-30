/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine;

import java.io.BufferedInputStream;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author nuno
 */
public class Engine {

    public static void main(String[] args) {


        Scanner stdin = new Scanner(new BufferedInputStream(System.in));
        System.out.print("Caminho (default ENTER \".\"): ");
        // aqui tem que escrever "files/" 
        String path = stdin.nextLine();
        if (path.equals("")) {
            path = ".";
        }


        System.out.print("Query: ");
        String query = stdin.nextLine();

        ProcessorQC processor = new ProcessorQC(path, query);
        

        BaseFormule bf = new BaseFormule();
        List<String> filenames = processor.getFileNames(path);
        int docCount = 0;
        for (String filename : filenames) {
            double docValue = bf.calculateValue(processor.getMatrixM(), processor.getMatrixQ(), docCount);
            System.out.println("Documento \"" + filename + "\" = " + docValue);
            docCount++;
        }



    }
}
