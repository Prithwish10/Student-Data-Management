import java.util.HashMap;
import java.util.Map;

public class TrieNodeN {
    Map<Character, TrieNodeN> child;
    boolean endOfWord;
    Student student;

    public TrieNodeN(){
        this.child = new HashMap<>();
        this.endOfWord = false;
    }
}
