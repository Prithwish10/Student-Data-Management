import java.util.Map;
import java.util.HashMap;

public class TrieNodeP {
    private Map<Character, TrieNodeP> child;
    private boolean endOfWord;
    private Student student;

    public TrieNodeP(){
        this.child = new HashMap<>();
        this.endOfWord = false;
    }
    public Map<Character, TrieNodeP> getChild(){
        return this.child;
    }
    public boolean getEndOfWord(){
        return this.endOfWord;
    }
    public Student getStudent(){
        return this.student;
    }
    public void setChild(char character, TrieNodeP child){
        this.child.put(character, child) ;
    }
    public void setEndOfWord(boolean endOfWord){
        this.endOfWord = endOfWord;
    }
    public void setStudent(Student student){
        this.student = student;
    }
}
