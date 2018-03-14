package com.company;

//import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.util.Hashtable;

// ASTRAL protein sequence parser.

public class Main {

    public static void main(String[] args) throws IOException {
//        // FIXME: Main class may be redundant at this stage, turn it into a runner?
        String sequence = "";
        String proteinDetails = "";
        Hashtable<String, String> proteins = new Hashtable<>();

        String thisLine = null;
        try {
            // open input file 5sequences.txt for reading and create out.txt for output.
            BufferedReader br = new BufferedReader(new FileReader("src/5sequences.txt"));
            BufferedWriter wr = new BufferedWriter(new FileWriter("src/out.txt"));

            while ((thisLine = br.readLine()) != null) {
                if (thisLine.contains(">")) {
                    if (proteinDetails.equals("")) {
                        proteinDetails = thisLine;
                    } else {
                        // store protein in a HashTable, and move onto next one
                        proteins.put(proteinDetails, sequence);
                        sequence = "";
                        proteinDetails = thisLine;
                    }
                } else {
                    sequence += thisLine;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Printing keys :");

        for(String key: proteins.keySet()){
            System.out.println(key);
            System.out.println();
        }

        System.out.println("Printing values");
        for(String key: proteins.values()){
            System.out.println(key);
            System.out.println();
        }
    }
}


// Protein class
// contains - Details and Sequence.
// Require protein by name or code, request sequence, request
