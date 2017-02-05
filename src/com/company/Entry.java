package com.company;
// Nikita Pavlenko | 13524873
public class Entry {

    private String code;
    private String name;
    private Character proteinClass;
    private String group;
    private String taxID;
    private String sequence;

    // Parse all of these details. pack them into a list.

    public Entry(String top, String bottom){
        // Construct an entry from 2 strings
    }

    void getCode(){

    }

    void entryToString(){
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
