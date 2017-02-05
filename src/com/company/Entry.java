package com.company;

import java.util.Vector;

// Nikita Pavlenko | 13524873
public class Entry {

    private String code;
    private String name;
    private String proteinClass;
    private String group;
    private String taxID;
    private String sequence;
    private Vector<Integer> encodedSequence = new Vector<Integer>();

    // Parse all of these details. pack them into a list.

    public Entry(String top, String bottom) {
        // Construct an entry from 2 strings
        String[] topSplit = top.split(" ");
        this.code = topSplit[0].replaceAll("[^A-Za-z]+", "");
//        System.out.println(this.code);
        this.group = topSplit[1].trim();
//        System.out.println(this.group);
        this.proteinClass = topSplit[2].replaceAll("[^A-Za-z]+", "");
//        System.out.println(this.proteinClass);

//FIXME: Do entry class in words and name need to be interpreted?

        this.taxID = topSplit[topSplit.length-1].replaceAll("[^0-9]+", "");
//        System.out.println(this.taxID);
    }

    private int[] encodeSequence(String sequence){
        char[] sequenceAsArrayOfChars = sequence.toCharArray();
        Vector<Vector<Integer>> encodedToMatrix = new Vector<>();
        for(char sequenceElt: sequenceAsArrayOfChars){
            // Switch statement?
        }

    }

    void getCode() {

    }

    void entryToString() {
        // In file:
        // Line 1: # of examples
        // Line 2: # of letters involved, # of classes

        // For each entry:
        // Line 1: name of example ( the code after > and before _)
        // Line 2: lenght of sequence
        // Line 3: input vector
        // Line 4: target class
        // Line 5: blank
    }

}
