import subtask1.HashTable;
import subtask2.HashTable2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ExampleProgram1 {

    public static void main(String[] args) {

        System.out.println("PART ONE OF MAIN\n\n");

        /**
         * Making an array of the correct size and inserting the names
         * from the file in to the array by reading from the
         * names.txt file.
         */
        String[] names = new String[86];
        try {
            File nameFile = new File("src/names.txt");
            Scanner myReader = new Scanner(nameFile);

            int counter = 0;
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                if(data != null && !data.equals("")){
                    names[counter] = data;
                    counter ++;
                }
            }
            myReader.close();
        }catch (FileNotFoundException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }

        /**
         * Creating the hashtable, takes the array as a parameter and add
         * all the names into the hash table.
         * prints out the table and load factor.
         */
        HashTable hashTable = new HashTable(107);
        int collisions = hashTable.addHashNodesFromArray(names);

        System.out.println();

        hashTable.printHashTable();
        System.out.println("\n" + hashTable.findPerson("Magnus,Bredeli"));
        System.out.println("Total collisions: " + collisions + "\nAverage collision per person: " + (double)collisions/86 + "\nThe loadfactor: " + hashTable.getLoadFactor());


        /**
         * SUBTASK 2 PART OF THE MAIN
         */
        System.out.println("\n\nPART TWO OF MAIN\n\n");


        /**
         * Fills an array with random numbers in an interval that are substantially
         * larger than the array size.
         */
        Random random = new Random();
        int[] randomNumbers = new int[10000000];
        for(int i = 0; i < 10000000; i ++){
            int randomNumber = random.nextInt(1000000000-100000000) + 100000000;
            randomNumbers[i] = randomNumber;
        }

        /**
         * Creates the two hashtables to be tested
         */
        HashTable2 hashTable2 = new HashTable2();
        HashSet<Integer> javaHashSet = new HashSet<>();

        /**
         * Tested first hashtable method
         */
        long startTime = System.nanoTime();
        int collisions2 = hashTable2.addFromArray(randomNumbers);
        long endTime = System.nanoTime();

        /**
         * Testing the java hashset method
         */
        long startTime2 = System.nanoTime();
        for(int integer : randomNumbers){
            javaHashSet.add(integer);
        }
        long endTime2 = System.nanoTime();


        /**
         * The results
         */
        System.out.println("Java's hashtable: " + (endTime2-startTime2)/1000000 + "ms");
        System.out.println("My hashtable: " + (endTime-startTime)/1000000 + "ms");
        System.out.println("Total amount of collisions: " + collisions2 + "\nLoad factor: " + hashTable2.findLoadFactor());
    }
}
