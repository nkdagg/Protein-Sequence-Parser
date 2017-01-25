package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String sequence = "";
        String proteinDetails = "";

        String thisLine = null;
        try {
            // open input stream 5sequences.txt for reading purpose.
            BufferedReader br = new BufferedReader(new FileReader("src/5sequences.txt"));
            while ((thisLine = br.readLine()) != null) {
                if (thisLine.contains(">")) {
                    if(proteinDetails == null){
                        // new protein
                        proteinDetails = thisLine;
                    } else {
                        // store protein in a HashTable, and move onto next one
                        proteinDetails = thisLine;
                    }
                } else {
                    sequence += thisLine;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
