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

    String inputfilename;
    String testDatasetFilename = "test.dataset";
    String trainingDatasetFilename = "train.dataset";
    Hashtable<String, String> proteins = new Hashtable<>();

    Parser(String inputFilename){
        // Create a default out/file
        this.inputfilename = inputFilename;
        readfile();
    }

    Parser(){
        readfile();
    }

    ArrayList<String> readfile(){
        String thisLine = null;
        ArrayList<String> fileLines = new ArrayList<>();

        try {
            // open input file 5sequences.txt for reading and create out.txt for output.
            BufferedReader br = new BufferedReader(new FileReader("src/5sequences.txt"));
            BufferedWriter wr = new BufferedWriter(new FileWriter("src/out.txt"));

            String details ="";
            String sequence = "";

            while ((thisLine = br.readLine()) != null) {

                if(thisLine.contains(">")){
                    if(!sequence.isEmpty()) {
                        proteins.put(details,sequence);
                        sequence = "";
                    }
                    details = thisLine;
                } else {
                    sequence += thisLine;
                }
                fileLines.add(thisLine);
            }
            proteins.put(details,sequence);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileLines;
    }

    void parse(ArrayList<String> list){
        for(String elt: list){
            if(elt.contains(">")){

            } else {

            }
        }
    }


    public static void main(String[] args) {
        Parser parserr = new Parser("yay");
        parserr.readfile();


        for(String key: parserr.proteins.keySet()){
            System.out.println(key);
        }

        for(String key: parserr.proteins.values()){
            System.out.println(key);
        }
    }
    
}
