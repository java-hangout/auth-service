package com.jh.tds.as.test;

/**
 * @author Veeresh N
 * @version 1.0
 */
public class TCSTest {

    static String ABC = "ABC";
    static String ADO = "ADO";
    static String XYZ = "XYZ";
    static String abcOutPut = "ABC : ";
    static String adoOutPut = "ADO : ";
    static String xyzOutPut = "XYZ : ";

    public static void main(String[] args) {
//       input
        String str[] = {"ABC", "BCA", "CBA", "ADO", "DAO", "OAD", "XYZ", "YZX"};

        //output - ABC:ABC,BCA,CBA
//        ADO:ADO,DAO,OAD
//        XYZ: YXY,YZX


        boolean flag = true;
        for (String word : str) {
//           System.out.println( printWord(abcOutPut,word));
            if (word.contains("A") && word.contains("B") && word.contains("C")) {
                if (flag) {
                    abcOutPut = abcOutPut.concat(word);
                    flag = false;
                } else {
                    abcOutPut = abcOutPut.concat("," + word);
                }
                System.out.println();
                if (word.contains("A") && word.contains("D") && word.contains("O")) {
                    if (flag) {
                        abcOutPut = abcOutPut.concat(word);
                        flag = false;
                    } else {
                        abcOutPut = abcOutPut.concat("," + word);
                    }
                }
                System.out.println();
                if (word.contains("X") && word.contains("Y") && word.contains("Z")) {
                    if (flag) {
                        abcOutPut = abcOutPut.concat(word);
                        flag = false;
                    } else {
                        abcOutPut = abcOutPut.concat("," + word);
                    }
                }

            }

//            System.out.println("Output : " + abcOutPut);
        }

    }
        private static String printWord (String original, String word){

            boolean flag = true;
            if (word.contains(original)) {
                if (flag) {
                    abcOutPut = abcOutPut.concat(word);
                    flag = false;
                } else {
                    abcOutPut = abcOutPut.concat("," + word);
                }
                System.out.println();
            }
            return abcOutPut;
        }
}
