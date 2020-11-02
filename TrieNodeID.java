import java.util.Map;
import java.util.HashMap;

public class TrieNodeID {

    private Map<Character, TrieNodeID> child;
    private boolean endOfWorrd;
    private Student std;

    TrieNodeID(){
        this.child = new HashMap<>();
        this.endOfWorrd = false;
    }
    public Map<Character, TrieNodeID> getChild(){
        return this.child;
    }
    public boolean getEndOfWord(){
        return this.endOfWorrd;
    }
    public Student getStudent(){
        return this.std;
    }
    public void setChild(char character, TrieNodeID child){
        this.child.put(character, child) ;
    }
    public void setEndOfWord(boolean endOfWord){
        this.endOfWorrd = endOfWord;
    }
    public void setStudent(Student std){
        this.std = std;
    }
}