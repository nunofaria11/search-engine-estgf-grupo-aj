/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Processador de Queries
 *
 * @author nuno
 */
public final class ProcessorQC {

    private double[][] matrixM;
    private double[][] matrixQ;
    private ArrayList<String> indexArray; // *** para que serve???
    private ArrayList<String> files; // nomes do ficheiro
    private String[][] docLineM;
    private String path;

    public ProcessorQC() {
        this.path = ".";
        this.files = getFileNames(path);
        this.matrixM = new double[getNumberOfDocs()][];
        this.docLineM = createDocLineM(path, files);
    }

    public ProcessorQC(String path) {
        this.path = path;
        this.files = getFileNames(path);
        this.matrixM = new double[getNumberOfDocs()][];
        this.docLineM = createDocLineM(path, files);
        this.indexArray = createIndexArray(docLineM);
        this.matrixM = createMatrixM(docLineM, indexArray);
    }

    public String digitsDelete(String word) {
        return word.replaceAll("[\\d.]", "");
    }

    /*
     * .!?,:*+/
     */
    public String ponctuationDelete(String word) {
        return word.replaceAll("[\\.!\\?,:\\*\\+/]", "");
    }

    public void addFile(String filepath) {
    }

    public Integer getNumberOfDocs() {
        return files.size();
    }

    /**
     *
     * @param path Caminho diretoria
     * @return Lista de ficheiros TXT
     */
    public ArrayList<String> getFileNames(String path) {
        ArrayList<String> fileList = new ArrayList<String>();
        String filename;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
                filename = listOfFiles[i].getName();
                if (filename.endsWith(".txt") || filename.endsWith(".TXT")) {
                    fileList.add(filename);
                }
            }
        }
        return fileList;
    }

    private String[][] createDocLineM(String path, ArrayList<String> files) {

        String[][] matrix = new String[getNumberOfDocs()][];

        int docLine = 0;

        for (String filename : files) {
            //
            try {
                FileInputStream fis = new FileInputStream(path + filename);
                //InputStreamReader in = new InputStreamReader(fis, "UTF-8");
                Scanner scanner = new Scanner(fis);
                String input = "";

                while (scanner.hasNext()) {
                    input += scanner.nextLine();
                }
                String noDigits = digitsDelete(input);

                String noPunctuation = ponctuationDelete(noDigits);

                String[] words = noPunctuation.split(" ");

                matrix[docLine] = words;
                docLine++;

            } catch (FileNotFoundException ex) {
                //Logger.getLogger(ProcessorQC.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("O ficheiro " + filename + " não foi encontrado");
            }

        }
        return matrix;
    }

    private ArrayList<String> createIndexArray(String[][] matrix) {
        ArrayList<String> indexes = new ArrayList<String>();

        int num_docs = matrix.length;

        for (int documento_idx = 0; documento_idx < num_docs; documento_idx++) {

            int num_pals = matrix[documento_idx].length;

            for (int palavra_idx = 0; palavra_idx < num_pals; palavra_idx++) {

                String word = matrix[documento_idx][palavra_idx];

                if (!indexes.contains(word)) {
                    indexes.add(word);
                }
            }
        }


        return indexes;
    }

    private double[][] createMatrixM(String[][] docLineM, ArrayList<String> indexes) {
        int numDocs = docLineM.length;
        int totalWords = indexes.size();

        double[][] M = new double[numDocs][totalWords];

        for (int docIdx = 0; docIdx < numDocs; docIdx++) {

            int numWords = docLineM[docIdx].length;

            for (int wordIdx = 0; wordIdx < numWords; wordIdx++) {

                String word = docLineM[docIdx][wordIdx];

                int pos = indexes.indexOf(word);

                M[docIdx][pos]++;


            }
        }

        return M;

    }

    public String[][] getDocLineM() {
        return docLineM;
    }

    public ArrayList<String> getIndexArray() {
        return indexArray;
    }

    public double[][] getMatrixM() {
        return matrixM;
    }

    public static void main(String[] args) {
        /*
         * teste
         */
        ProcessorQC p = new ProcessorQC("files/");
        
        System.out.println("docLineM: ");
        String[][] m = p.getDocLineM();

        for (int i = 0; i < m.length; i++) {
            System.out.println("");
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j] + ", ");
            }
        }

        
        System.out.println("\nindexArray: "+p.getIndexArray());

        double[][] matrixM = p.getMatrixM();

        for (int i = 0; i < matrixM.length; i++) {
            System.out.println("");
            for (int j = 0; j < matrixM[i].length; j++) {
                System.out.print(matrixM[i][j] + ", ");
            }
        }

    }
}