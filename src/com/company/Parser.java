package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

/**
 * Nikita Pavlenko | 13524873
 */
public class Parser {

    private String inputfilename;
    private String testDatasetFilename = "test.dataset";
    private String trainingDatasetFilename = "train.dataset";
    private Hashtable<String, String> proteins = new Hashtable<>();
    private ArrayList<Entry> entries = new ArrayList<>();
    HashSet<Character> classes = new HashSet<>();

    Parser(String inputFilename) {
        // Create a default out/file
        this.inputfilename = inputFilename;
        readfile();
    }

    Parser() {
        readfile();
    }

    void readfile() {
        String thisLine = null;
        // FIXME: Ordered HashSet instead?

        try {
            // FIXME: output has to go to a different method.
            // open input file 5sequences.txt for reading and create out.txt for output.
            BufferedReader br = new BufferedReader(new FileReader("src/astral.txt"));
            int linenum = 0;
            String details = "";
            String sequence = "";

            while ((thisLine = br.readLine()) != null) {
                //System.out.println(linenum++);
                if (thisLine.contains(">")) {
                    if (!sequence.isEmpty()) {
                        entries.add(new Entry(details, sequence));
                        sequence = "";
                    }
                    details = thisLine;
                } else {
                    sequence += thisLine;
                }
            }
            entries.add(new Entry(details, sequence));
            System.out.println("here" + classes.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int getNumberOfClasses() {
        Set<Integer> classes = new HashSet<>();
        for (Entry entry : entries) {
            if (!classes.contains(entry.getNumClass())) {
                classes.add(entry.getNumClass());
            }
        }
        return classes.size();
    }

    void writeToFile() {
        try {
            // In file:
            // Line 1: # of examples
            // Line 2: # of legal symbols, # of classes
            //FIXME: for now number of legal symbols is fixed to 24
            int entriesForTraining = (entries.size() - entries.size() / 4);
            int entriesForTesting = entries.size() / 4;

            if ((entriesForTesting + entriesForTraining) == entries.size()) {
                System.out.println("Number of entries match");
            }

            System.out.println("Total number of entries:" + entries.size());
            System.out.println("Number of entries for training :" + entriesForTraining);
            System.out.println("Number of entries for testing  :" + entriesForTesting);

            BufferedWriter wrTrain = new BufferedWriter(new FileWriter("src/train.dataset"));
            BufferedWriter wrTest = new BufferedWriter(new FileWriter("src/test.dataset"));

            wrTrain.write((Integer.toString(entriesForTraining))); // Number of examples
            wrTrain.newLine();
            wrTrain.write("24 " + getNumberOfClasses());
            wrTrain.newLine();

            wrTest.write((Integer.toString(entriesForTesting))); // Number of examples
            wrTest.newLine();
            wrTest.write("24 " + getNumberOfClasses());
            wrTest.newLine();

            for (Entry entry : entries) {
                if (entries.lastIndexOf(entry) % 4 == 0) {
                    //System.out.println("Test \n" + entry.entryToString());
                    wrTest.write(entry.entryToString());
                    wrTest.newLine();
                } else {
                    //System.out.println("Train \n" + entry.entryToString());
                    wrTrain.write(entry.entryToString());
                    wrTrain.newLine();
                }

                System.out.printf(entry.statsToString());
            }

            wrTest.close();
            wrTrain.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void writeToFile(boolean createFourFoldCrossValidationSets) {
        // Creates 4 datasets for 4 fold cross-validation placed in respective src/fold folders.

        try {
            // In file:
            // Line 1: # of examples
            // Line 2: # of legal symbols, # of classes
            int entriesForTraining = (entries.size() - entries.size() / 4);
            int entriesForTesting = entries.size() / 4;
            System.out.println("Total number of entries:" + entries.size());
            System.out.println("Number of entries for training :" + entriesForTraining);
            System.out.println("Number of entries for testing  :" + entriesForTesting);



            // Init Fold 1 Writer
            BufferedWriter wrTrain = new BufferedWriter(new FileWriter("src/fold1/train.dataset"));
            BufferedWriter wrTest = new BufferedWriter(new FileWriter("src/fold1/test.dataset"));

            wrTrain.write((Integer.toString(entriesForTraining))); // Number of training examples
            wrTrain.newLine();
            wrTrain.write("22 " + getNumberOfClasses());
            wrTrain.newLine();

            wrTest.write((Integer.toString(entriesForTesting))); // Number of test examples
            wrTest.newLine();
            wrTest.write("22 " + getNumberOfClasses());
            wrTest.newLine();

            // Init Fold 2 Writer
            BufferedWriter wrTrain2 = new BufferedWriter(new FileWriter("src/fold2/train.dataset"));
            BufferedWriter wrTest2 = new BufferedWriter(new FileWriter("src/fold2/test.dataset"));

            wrTrain2.write((Integer.toString(entriesForTraining)));
            wrTrain2.newLine();
            wrTrain2.write("22 " + getNumberOfClasses());
            wrTrain2.newLine();

            wrTest2.write((Integer.toString(entriesForTesting)));
            wrTest2.newLine();
            wrTest2.write("22 " + getNumberOfClasses());
            wrTest2.newLine();

            // Init Fold 3 Writer
            BufferedWriter wrTrain3 = new BufferedWriter(new FileWriter("src/fold3/train.dataset"));
            BufferedWriter wrTest3 = new BufferedWriter(new FileWriter("src/fold3/test.dataset"));

            wrTrain3.write((Integer.toString(entriesForTraining)));
            wrTrain3.newLine();
            wrTrain3.write("22 " + getNumberOfClasses());
            wrTrain3.newLine();

            wrTest3.write((Integer.toString(entriesForTesting)));
            wrTest3.newLine();
            wrTest3.write("22 " + getNumberOfClasses());
            wrTest3.newLine();

            // Init Fold 4 Writer
            BufferedWriter wrTrain4 = new BufferedWriter(new FileWriter("src/fold4/train.dataset"));
            BufferedWriter wrTest4 = new BufferedWriter(new FileWriter("src/fold4/test.dataset"));

            wrTrain4.write((Integer.toString(entriesForTraining)));
            wrTrain4.newLine();
            wrTrain4.write("22 " + getNumberOfClasses());
            wrTrain4.newLine();

            wrTest4.write((Integer.toString(entriesForTesting)));
            wrTest4.newLine();
            wrTest4.write("22 " + getNumberOfClasses());
            wrTest4.newLine();

            int foldIndex = 0;

            int test[] = {0,0,0,0};

            for (Entry entry : entries) {
                switch (foldIndex) {
                    case 0:
                        wrTest.write(entry.entryToString());
                        wrTest.newLine();

                        wrTrain2.write(entry.entryToString());
                        wrTrain2.newLine();

                        wrTrain3.write(entry.entryToString());
                        wrTrain3.newLine();

                        wrTrain4.write(entry.entryToString());
                        wrTrain4.newLine();
                        foldIndex++; test[0]++;
                        break;

                    case 1:
                        wrTest2.write(entry.entryToString());
                        wrTest2.newLine();

                        wrTrain.write(entry.entryToString());
                        wrTrain.newLine();

                        wrTrain3.write(entry.entryToString());
                        wrTrain3.newLine();

                        wrTrain4.write(entry.entryToString());
                        wrTrain4.newLine();
                        foldIndex++; test[1]++;
                        break;

                    case 2:
                        wrTest3.write(entry.entryToString());
                        wrTest3.newLine();

                        wrTrain.write(entry.entryToString());
                        wrTrain.newLine();

                        wrTrain2.write(entry.entryToString());
                        wrTrain2.newLine();

                        wrTrain4.write(entry.entryToString());
                        wrTrain4.newLine();
                        foldIndex++; test[2]++;
                        break;

                    case 3:
                        wrTest4.write(entry.entryToString());
                        wrTest4.newLine();

                        wrTrain.write(entry.entryToString());
                        wrTrain.newLine();

                        wrTrain2.write(entry.entryToString());
                        wrTrain2.newLine();

                        wrTrain3.write(entry.entryToString());
                        wrTrain3.newLine();
                        foldIndex = 0; test[3]++;
                        break;
                }
            }

            wrTest.close(); wrTest2.close(); wrTest3.close(); wrTest4.close();
            wrTrain.close(); wrTrain2.close(); wrTrain3.close(); wrTrain4.close();

            System.out.println("test: " + test[0] + " " + test[1] + " " + test[2] + " " + test[3]);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void writeFrequenciesToFile(){
        // Create CSV with Stats of Amino acid Frequencies

        try {
            BufferedWriter wrStats = new BufferedWriter(new FileWriter("src/stats.csv"));


            //Write stats to file.


            wrStats.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public static void main(String[] args) {
        Parser parserr = new Parser("yay");

        System.out.println("Number of classes:" + parserr.getNumberOfClasses());
        parserr.writeToFile(true);
    }

}
