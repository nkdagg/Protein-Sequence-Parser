package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Nikita Pavlenko | 13524873
 */
public class Parser {

    private String inputfilename;
    private String testDatasetFilename = "test.dataset";
    private String trainingDatasetFilename = "train.dataset";
    private Hashtable<String, String> proteins = new Hashtable<>();
    private ArrayList<Entry> entries = new ArrayList<>();

    Parser(String inputFilename) {
        // Create a default out/file
        this.inputfilename = inputFilename;
        readfile();
    }

    // TODO: Try to parse on creation of parser object?
    Parser() {
        readfile();
    }

    ArrayList<String> readfile() {
        String thisLine = null;
        // FIXME: Ordered HashSet instead?
        ArrayList<String> fileLines = new ArrayList<>();

        try {
            // FIXME: output has to go to a different method.
            // open input file 5sequences.txt for reading and create out.txt for output.
            BufferedReader br = new BufferedReader(new FileReader("src/5sequences.txt"));
            //BufferedWriter wr = new BufferedWriter(new FileWriter("src/out.txt"));

            String details = "";
            String sequence = "";

            while ((thisLine = br.readLine()) != null) {
                if (thisLine.contains(">")) {
                    if (!sequence.isEmpty()) {
                        entries.add(new Entry(details,sequence));
                        proteins.put(details, sequence);
                        sequence = "";
                    }
                    details = thisLine;
                } else {
                    sequence += thisLine;
                }
                fileLines.add(thisLine);
            }
            entries.add(new Entry(details, sequence));
            proteins.put(details, sequence);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileLines;
    }

    //TODO: method to output every N-th entry to test.dataset


    public static void main(String[] args) {
        Parser parserr = new Parser("yay");
        parserr.readfile();

    }

}
