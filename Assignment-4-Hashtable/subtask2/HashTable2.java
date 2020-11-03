package subtask2;

public class HashTable2 {

    public int[] hashTable;

    public HashTable2(){
        this.hashTable = new int[12500003];
    }

    public int addFromArray(int[] array){
        int collision = 0;
        for (int j : array) {
            if(add(j)){
                collision ++;
            }
        }
        return collision;
    }

    public boolean add(int key){
        int index = hashWithMod(key);
        boolean collision = false;

        if(hashTable[index] != 0){
            collision = true;
            int index2 = secondHashWithMod(key);

            int i = 1;
            while(true){
                int newIndex = probe(index, index2, i);

                if(hashTable[newIndex] == 0){
                    hashTable[newIndex] = key;
                    break;
                }
                i ++;
            }
        }else{
            hashTable[index] = key;
        }
        return collision;
    }

    private int probe(int hash1, int hash2, int i){
        return (hash1 + (i*hash2)) % hashTable.length;
    }

    private int hashWithMod(int key){
        return key % hashTable.length;
    }

    private int secondHashWithMod(int key){
        return (key % (hashTable.length - 1)) + 1;
    }

    public double findLoadFactor(){
        int noTakenSpots = 0;
        for(int i = 0; i < hashTable.length; i ++){
            if(hashTable[i] != 0){
                noTakenSpots++;
            }
        }
        return (double)noTakenSpots/hashTable.length;
    }
}
