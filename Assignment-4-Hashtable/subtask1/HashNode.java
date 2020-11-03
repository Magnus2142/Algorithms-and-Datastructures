package subtask1;

public class HashNode{

    String key;
    HashNode next;

    public HashNode(String key){
        this.key = key;
        this.next = null;
    }

    @Override
    public String toString() {
        return key + " | ";
    }
}
