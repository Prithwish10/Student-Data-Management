import java.util.HashMap;
import java.util.Map;

public class TrieNodeN {
    private Map<Character, TrieNodeN> child;
    private boolean endOfWord;
    private Student student;

    public TrieNodeN(){
        this.child = new HashMap<>();
        this.endOfWord = false;
    }
    public Map<Character, TrieNodeN> getChild(){
        return this.child;
    }
    public boolean getEndOfWord(){
        return this.endOfWord;
    }
    public Student getStudent(){
        return this.student;
    }
    public void setChild(char character, TrieNodeN child){
        this.child.put(character, child) ;
    }
    public void setEndOfWord(boolean endOfWord){
        this.endOfWord = endOfWord;
    }
    public void setStudent(Student student){
        this.student = student;
    }
}
