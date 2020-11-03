package subtask1;

import java.util.Arrays;

public class HashTable {

    HashNode[] hashTable;

    public HashTable(int tableSize){
        hashTable = new HashNode[tableSize];
    }

    public int addHashNodesFromArray(String[] array){
        int noCollisions = 0;
        for (String s : array) {
            if (addHashNodeRestDivision(s)) {
                noCollisions++;
            }
        }
        return noCollisions;
    }

    public boolean addHashNodeRestDivision(String key){
        boolean didCollide = false;

        //Hashing the key
        int keyValue = hashKeyRestDivision(key, hashTable.length);
        HashNode newNode = new HashNode(key);
        if(hashTable[keyValue] == null){
            hashTable[keyValue] = newNode;
        }else{
            System.out.println("Obs! Collision between " + newNode + " and " + hashTable[keyValue]);
            didCollide = true;
            HashNode current = hashTable[keyValue];
            hashTable[keyValue] = newNode;
            newNode.next = current;
        }

        return didCollide;
    }

    private int hashKeyRestDivision(String key, int tableSize){
        return convertStringToHashNumber(key) % tableSize;
    }

    private int convertStringToHashNumber(String hashString){
        int hashNumber = 0;
        for(int i = 0; i < hashString.length(); i ++){
            hashNumber += ((int)hashString.charAt(i) * (i+1));
        }
        return hashNumber;
    }

    public HashNode findPerson(String name){
        int index = hashKeyRestDivision(name, hashTable.length);

        if(hashTable[index] == null){
            return null;
        }
        else if(hashTable[index].key.equalsIgnoreCase(name)){
            return hashTable[index];
        }else {
            HashNode n = hashTable[index];
            while (n != null && !n.key.equalsIgnoreCase(name)) {
                n = n.next;
            }
            return n;
        }
    }

    public double getLoadFactor(){
        int noTakenSpots = 0;
        for(int i = 0; i < hashTable.length; i ++){
            if(hashTable[i] != null){
                noTakenSpots ++;
            }
        }
        return (double)noTakenSpots/hashTable.length;
    }

    public void printHashTable(){
        Arrays.stream(hashTable).forEach(hashNode -> {
            if(hashNode == null || hashNode.next == null){
                System.out.println(hashNode);
            }else{
                HashNode n = hashNode;
                while(n != null){
                    System.out.print(n + " ");
                    n = n.next;
                }
                System.out.println();
            }
        });
    }
}
