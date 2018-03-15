package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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

    void writeToFile(boolean crossValidation) {

    }


    public static void main(String[] args) {
        Parser parserr = new Parser("yay");

        System.out.println("Number of classes:" + parserr.getNumberOfClasses());
        parserr.writeToFile();
    }

}
