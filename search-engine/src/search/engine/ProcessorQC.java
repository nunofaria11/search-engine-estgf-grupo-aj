/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Processador de Queries
 *
 * @author nuno
 */
public final class ProcessorQC {

    private double[][] matrixM;
    private ArrayList<String> files; // nomes do ficheiro
    private String[][] docLineM;

    public ProcessorQC(String path, String query) {
        this.files = getFileNames(path);



        // 1 Matriz M
        this.docLineM = createDocLineM(path, files);
        /* 
         * o createDocLine vai criar uma matriz com #Docs + 1 para depois 
         * adicionar a query a essa matrizz, e tratar tudo na mesma estrutura
         */
        String queryNoDigits = digitsDelete(query);

        String queryNoPunc = ponctuationDelete(queryNoDigits);

        String[] queryWords = queryNoPunc.split(" ");

        // a ultima linha da matriz recebe o conteudo da query
        this.docLineM[docLineM.length - 1] = queryWords;

        this.matrixM = createMatrixOcc(docLineM);
        updateMatrix(matrixM);

    }

    /**
     * Remove dígitos de uma string
     *
     * @param word
     * @return
     */
    private String digitsDelete(String word) {
        return word.replaceAll("[\\d.]", "");
    }

    /*
     * .!?,:*+/
     */
    private String ponctuationDelete(String word) {
        return word.replaceAll("[\\.!\\?,:\\*\\+/]", "");
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

        String[][] matrix = new String[getNumberOfDocs() + 1][];

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
                String[] words1 = noPunctuation.split(" ");

                /*
                 * remover espaços obsoletos
                 */
                ArrayList<String> wordList = new ArrayList<String>();
                for (int w = 0; w < words1.length; w++) {
                    if (!words1[w].isEmpty()) {

                        wordList.add(words1[w]);
                    }
                }



                matrix[docLine] = (String[]) wordList.toArray(new String[wordList.size()]);//
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

    private double[][] createMatrixOcc(String[][] docLineM) {

        ArrayList<String> indexes = createIndexArray(docLineM);

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

    private void updateMatrix(double[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                double currentValue = matrix[i][j];

                int np = countDocWords(matrix, j);

                if (np != 0) {
                    matrix[i][j] = currentValue * Math.log10(getNumberOfDocs() / np);
                } else {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    private int countDocWords(double[][] matrix, int wordColumn) {
        int pos = wordColumn;
        int totalDocuments = 0;
        for (int i = 0; i < matrix.length - 1; i++) {
            if (matrix[i][pos] != 0) {
                totalDocuments++;
            }
        }
        return totalDocuments;
    }

    public String[][] getDocLineM() {
        return docLineM;
    }

    /**
     * Vai buscar a parte superior da matriz de ocorrencias (apenas as
     * ocorrencias dos documentos)
     *
     * @return
     */
    public double[][] getMatrixM() {
        double[][] M = new double[getNumberOfDocs()][];
        for (int i = 0; i < getNumberOfDocs(); i++) {
            M[i] = new double[matrixM[i].length];
            System.arraycopy(matrixM[i], 0, M[i], 0, matrixM[i].length);
        }
        return M;
    }

    /**
     * Vai buscar uma matriz com a ultima linha da matriz principal, que é
     * correspondente ao array de ocorrencias da query
     *
     * @return
     */
    public double[][] getMatrixQ() {
        double[][] Q = new double[1][];
        Q[0] = matrixM[matrixM.length - 1];
        return Q;
    }

    public static void main(String[] args) {
        /*
         * teste
         */
        ProcessorQC p = new ProcessorQC("files/", "O mundo da coca-cola");

        System.out.println("\ndocLineM: ");
        printStringMatrix(p.getDocLineM());

        System.out.println("\nMatrixM: ");
        //printMatrix(p.createMatrixOcc(p.getDocLineM()));
        printMatrix(p.getMatrixM());
    }

    public static void printStringMatrix(String[][] m) {
        for (int i = 0; i < m.length; i++) {
            System.out.println("");
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j] + ", ");
            }
        }
    }

    public static void printMatrix(double[][] matrixM) {
        for (int i = 0; i < matrixM.length; i++) {

            for (int j = 0; j < matrixM[i].length; j++) {
                System.out.format("%6.3f;", matrixM[i][j]);
            }
            System.out.println();
        }

    }
}