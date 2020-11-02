import java.util.Map;
import java.util.HashMap;

public class TrieNodeP {
    Map<Character, TrieNodeP> child;
    boolean endOfWord;
    Student student;

    public TrieNodeP(){
        this.child = new HashMap<>();
        this.endOfWord = false;
    }
}
