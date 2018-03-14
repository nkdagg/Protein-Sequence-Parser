package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// Nikita Pavlenko | 13524873
public class Entry {

    private String code;
    private String alignmentCode;
    private String alignmentEntry;
    private String proteinClass;
    private String group;
    private String taxID;
    private int numClass;
    private String sequence;
    private String encodedString = new String();
    private char charClass;
    private HashMap<Character, Integer> stat = new HashMap<>();
    private int[] statistic = new int[24]; // Per entry statistic of character occurences

    //Init Statistic array


    // Parse all of these details. pack them into a list.

    Entry() {

    }

    public Entry(String top, String bottom) {
        // Construct an entry from 2 strings
        String[] topSplit = top.split(" ");
        this.alignmentCode = topSplit[0].replaceAll("[>]", "");;
        this.code = topSplit[0].replaceAll("[>_]", "");
//        System.out.println(this.code);
        this.group = topSplit[1].trim();
        this.sequence = bottom;
//        System.out.println(this.group);
        insertAlignment();
        //encodeSequence(bottom);
        this.proteinClass = topSplit[2].replaceAll("[^A-Za-z]+", "");
        this.proteinClass = this.proteinClass.replaceAll("[()]:", "");
        this.charClass = group.charAt(0);
        //System.out.println(this.proteinClass);
        switch (charClass) {
//            case 49 :
//                this.numClass = 1;
//                break;
//            case 50 :
//                this.numClass = 2;
//                break;
//            case 51 :
//                this.numClass = 3;
//                break;
//            case 52 :
//                this.numClass = 4;
//                break;
//            case 53 :
//                this.numClass = 5;
//                break;
//            case 54 :
//                this.numClass = 6;
//                break;
//            case 55 :
//                this.numClass = 7;
//                break;
//            case 58 :
//                this.numClass = 8;
//                break;
//            case 59 :
//                this.numClass = 9;
//                break;
            case 97 :
                this.numClass = 0;
                break;
            case 98:
                this.numClass = 1;
                break;
            case 99:
                this.numClass = 2;
                break;
            case 100:
                this.numClass = 3;
                break;
            case 101:
                this.numClass = 4;
                break;
            case 102:
                this.numClass = 5;
                break;
            case 103:
                this.numClass = 6;
                break;
            case 104:
                this.numClass = 7;
                break;
            case 105:
                this.numClass = 8;
                break;
            case 106:
                this.numClass = 9;
                break;
            default:
                System.out.println("Error in constructor, undefined sequence class");
        }
//        System.out.println(this.proteinClass);

//FIXME: Do entry class in words and name need to be interpreted?

        this.taxID = topSplit[topSplit.length - 1].replaceAll("[^0-9]+", "");
//        System.out.println(this.taxID);


        // Init statistic array to 0s

        for(int i: this.statistic){
            i = 0;
        }
    }

    public int getNumClass() {
        return this.numClass;
    }

    private void insertAlignment(){

        String filename = this.alignmentCode + ".ann";
        StringBuilder sb = new StringBuilder();
        String thisLine = null;

        //System.out.printf(filename + '\n');


        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Parsed/"+filename));
            while((thisLine = br.readLine()) != null){
                char[] test = thisLine.toCharArray();
                if(!Character.isLetter(test[0])){ //Hack to remove ID from alignment substitution
                    sb.append(thisLine + '\n');
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.alignmentEntry = sb.toString();
    }

    private void encodeSequence(String sequence) {
        char[] sequenceAsArrayOfChars = sequence.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char sequenceElt : sequenceAsArrayOfChars) {
            switch (sequenceElt) {
                case 'a':
                    sb.append("0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 ");
                    statistic[0]++;
                    break;
                case 'b':
                    sb.append("0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 ");
                    statistic[1]++;
                    break;
                case 'c':
                    sb.append("0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 ");
                    statistic[2]++;
                    break;
                case 'd':
                    sb.append("0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 ");
                    statistic[3]++;
                    break;
                case 'e':
                    sb.append("0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 ");
                    statistic[4]++;
                    break;
                case 'f':
                    sb.append("0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[5]++;
                    break;
                case 'g':
                    sb.append("0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[6]++;
                    break;
                case 'h':
                    sb.append("0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[7]++;
                    break;
                case 'i':
                    sb.append("0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[8]++;
                    break;
                case 'k':
                    sb.append("0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[9]++;
                    break;
                case 'l':
                    sb.append("0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[10]++;
                    break;
                case 'm':
                    sb.append("0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[11]++;
                    break;
                case 'n':
                    sb.append("0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[12]++;
                    break;
                case 'p':
                    sb.append("0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[13]++;
                    break;
                case 'q':
                    sb.append("0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[14]++;
                    break;
                case 'r':
                    sb.append("0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[15]++;
                    break;
                case 's':
                    sb.append("0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[16]++;
                    break;
                case 't':
                    sb.append("0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[17]++;
                    break;
                case 'u':
                    sb.append("0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[18]++;
                    break;
                case 'v':
                    sb.append("0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[19]++;
                    break;
                case 'w':
                    sb.append("0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[20]++;
                    break;
                case 'x':
                    sb.append("0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[21]++;
                    break;
                case 'X':
                    sb.append("0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[21]++;
                    break;
                case 'y':
                    sb.append("0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[22]++;
                    break;
                case 'z':
                    sb.append("1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ");
                    statistic[23]++;
                    break;
                default:
                    break;
//                case 'a':
//                    sb.append("1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 "); break;
//                case 'b':
//                    sb.append("0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 "); break;
//                case 'c':
//                    sb.append("0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 "); break;
//                case 'd':
//                    sb.append("0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 "); break;
//                case 'e':
//                    sb.append("0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 "); break;
//                case 'f':
//                    sb.append("0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 "); break;
//                case 'g':
//                    sb.append("0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 "); break;
//                case 'h':
//                    sb.append("0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 "); break;
//                case 'i':
//                    sb.append("0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 "); break;
//                case 'k':
//                    sb.append("0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 "); break;
//                case 'l':
//                    sb.append("0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 "); break;
//                case 'm':
//                    sb.append("0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 "); break;
//                case 'n':
//                    sb.append("0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 "); break;
//                case 'p':
//                    sb.append("0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 "); break;
//                case 'q':
//                    sb.append("0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 "); break;
//                case 'r':
//                    sb.append("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 "); break;
//                case 's':
//                    sb.append("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 "); break;
//                case 't':
//                    sb.append("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 "); break;
//                case 'u':
//                    sb.append("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 "); break;
//                case 'v':
//                    sb.append("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 "); break;
//                case 'w':
//                    sb.append("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 "); break;
//                case 'x':
//                    sb.append("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 "); break;
//                case 'y':
//                    sb.append("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 "); break;
//                case 'z':
//                    sb.append("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 "); break;
//                default:
//                    break;
//                case 'a':
//                    encodedString += "1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ");
//                case 'b':
//                    encodedString += "0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ";
//                case 'c':
//                    encodedString += "0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ";
//                case 'd':
//                    encodedString += "0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ";
//                case 'e':
//                    encodedString += "0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ";
//                case 'f':
//                    encodedString += "0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ";
//                case 'g':
//                    encodedString += "0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ";
//                case 'h':
//                    encodedString += "0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ";
//                case 'i':
//                    encodedString += "0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ";
//                case 'k':
//                    encodedString += "0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ";
//                case 'l':
//                    encodedString += "0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 ";
//                case 'm':
//                    encodedString += "0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 ";
//                case 'n':
//                    encodedString += "0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 ";
//                case 'p':
//                    encodedString += "0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 ";
//                case 'q':
//                    encodedString += "0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 ";
//                case 'r':
//                    encodedString += "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 ";
//                case 's':
//                    encodedString += "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 ";
//                case 't':
//                    encodedString += "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 ";
//                case 'u':
//                    encodedString += "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 ";
//                case 'v':
//                    encodedString += "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 ";
//                case 'w':
//                    encodedString += "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 ";
//                case 'x':
//                    encodedString += "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 ";
//                case 'y':
//                    encodedString += "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 ";
//                case 'z':
//                    encodedString += "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 ";
            }
            encodedString = sb.toString();
        }
    }

    void getCode() {

    }

    String entryToString() {
        // For each entry:
        // Line 1: name of example ( the code after > and before _)
        // Line 2: lenght of sequence
        // Line 3: input vector
        // Line 4: target class
        // Line 5: blank

//        if(sequence.length() * 96 != encodedString.length()){
//            System.out.println(code);
//            System.out.println("Fail");
//            System.exit(0);
//        }

        String theString = code.toUpperCase() + "\n";
        theString += alignmentEntry;
        theString += numClass + "\n";


//        String theString = code.toUpperCase() + "\n";
//        theString += sequence.length() + "\n";
//        theString += encodedString + "\n";
//        theString += numClass + "\n";
        System.out.println(theString);
        return theString;
    }

    String statsToString(){
        String out = "";

        for(int i = 0; i < statistic.length; i++){
            out += i + " : " + statistic[i] + " ";
        }
        return out;
    }

}
