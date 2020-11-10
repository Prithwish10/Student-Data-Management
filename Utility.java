import java.util.Scanner;

public class Utility {

    // String fileName_N = "C:\\Users\\prith\\OneDrive\\Documents\\Employee Management System\\FileN.txt";
    // String fileName_P = "C:\\Users\\prith\\OneDrive\\Documents\\Employee Management System\\FileP.txt";
    // String fileName_ID = "C:\\Users\\prith\\OneDrive\\Documents\\Employee Management System\\FileID.txt";

    private TrieNodeN rootN;
    private TrieNodeP rootP;
    private TrieNodeID rootID;
    /*
    ** Constructor to initialize the rootN (Trie Node where Student Records are been inserted by Student Name)
        and rootP (Trie Node where Student Records are been inserted by Student Phone Number) 
    */
    Utility(){
        rootN = new TrieNodeN();
        rootP = new TrieNodeP();
        rootID = new TrieNodeID();
    }
    /*
    ** INSERT BY NAME OF THE STUDENT
    */
    public void insert_By_Name(String name, Student std){

        TrieNodeN current = rootN;

        for(int i = 0; i < name.length(); i ++){
            char ch = name.charAt(i);
            TrieNodeN node = current.getChild().get(ch);

            if(node == null){
                node = new TrieNodeN();
                current.setChild(ch, node);
            }
            current = node;
        }
        current.setEndOfWord(true);
        current.setStudent(std);
        System.out.println("\nINSERTION SUCCESSFUL !!");
    }
    /*
    ** INSERT BY THE PHONE NUMBER OF THE STUDENT
    */
    public void insert_By_Phone(String phone, Student std){

        TrieNodeP current = rootP;

        for(int index = 0; index < phone.length(); index ++){

            char ch = phone.charAt(index);
            TrieNodeP node = current.getChild().get(ch);

            if(node == null){
                node = new TrieNodeP();
                current.getChild().put(ch, node);
            }
            current = node;
        }
        current.setEndOfWord(true);
        current.setStudent(std);
        System.out.println("\nINSERTION SUCCESSFUL !!");
    }
    /*
    ** INSERT BY THE PHONE NUMBER OF THE STUDENT
    */
    public void insert_By_ID(String Id, Student std){

        TrieNodeID current = rootID;

        for(int index = 0; index < Id.length(); index ++){
            char ch = Id.charAt(index);
            TrieNodeID node = current.getChild().get(ch);

            if(node == null){
                node = new TrieNodeID();
                current.setChild(ch, node);
            }
            current = node;
        }
        current.setEndOfWord(true);
        current.setStudent(std);
        System.out.println("\nINSERTION SUCCESSFUL !!");
    }
    /*
    ** SEARCH BY THE ID OF THE STUDENT
    */
    public void search_By_ID(String Id){

        TrieNodeID current = rootID;
        boolean flag = true;

        for(int index = 0; index < Id.length(); index ++){
            char ch = Id.charAt(index);
            TrieNodeID node = current.getChild().get(ch);
            if(node == null){
                flag = false;
                break;
            }
            current = node;
        }
        if(flag){
            if(current.getEndOfWord() == true){
                printDetails(current.getStudent().getName(), current.getStudent().getId(), current.getStudent().getEmail(), current.getStudent().getDepartment(), current.getStudent().getRoll(), current.getStudent().getPhone(), current.getStudent().getRegistration_no());
            }
        }
        else{
            System.out.println("Sorry !! No match Found");
        }
    }

    public boolean isValid_ID(String Id){
        TrieNodeID current = rootID;
        boolean flag = true;

        for(int index = 0; index < Id.length(); index ++){
            char ch = Id.charAt(index);
            TrieNodeID node = current.getChild().get(ch);
            if(node == null){
                flag = false;
                break;
            }
            current = node;
        }
        if(flag){
            if(current.getEndOfWord() == true){
                return true;
            }
            return false;
        }
        return false;
    }
    /*
    ** SEARCH BY THE NAME OF THE STUDENT
    */
    public void search_By_Name(String name){

        TrieNodeN current = rootN;
        boolean flag = true;

        for(int index = 0; index < name.length(); index ++){

            char ch = name.charAt(index);
            TrieNodeN node = current.getChild().get(ch);
            
            if(node == null){
                flag = false;
                break;
            }
            current = node;
        }
        
        if(flag){
            if(current.getEndOfWord() == true){
                printDetails(current.getStudent().getName(), current.getStudent().getId(), current.getStudent().getEmail(), current.getStudent().getDepartment(), current.getStudent().getRoll(), current.getStudent().getPhone(), current.getStudent().getRegistration_no());
            }
        }
        else{
            System.out.println("Sorry !! No match Found");
        }
    }
    /*
    ** SEARCH RECORDS BY STUDENTS PHONE NUMBER
    */
    public void search_By_Phone(String phone){

        TrieNodeP current = rootP;
        boolean flag = true;

        for(int index = 0; index < phone.length(); index ++){

            char ch = phone.charAt(index);
            TrieNodeP node = current.getChild().get(ch);

            if(node == null){
                flag = false;
                break;
            }
            current = node;
        }
    
        if(flag){
            if(current.getEndOfWord()){
                printDetails(current.getStudent().getName(), current.getStudent().getId(), current.getStudent().getEmail(), current.getStudent().getDepartment(), current.getStudent().getRoll(), current.getStudent().getPhone(), current.getStudent().getRegistration_no());
            }
        }
        else{
            System.out.println("Sorry !! No match Found");
        }
    }
    /*
    ** PRINT THE RESPECTIVE STUDENT RECORD
    */
    public void printDetails(String name, String Id, String email, String department, Integer roll,             String phone, Integer registration_no){

        System.out.println("                                  ************************************** STUDENT DETAILS *****************************************\n");

        System.out.println("Name\t\t\tId\t\t\tEmail\t\t\t    Department\t\t\tRoll\t\t\tPhone\t\t\tRegistration Number\n");
        if(name != null)
            System.out.print(name);
        if(Id != null)
            System.out.print("\t\t"+ Id);
        if(email != null)
            System.out.print("\t\t"+email);
        if(department != null)
            System.out.print("\t\t  "+department);
        if(roll != null)
            System.out.print("\t\t\t"+roll);
        if(phone != null)
            System.out.print("\t\t    "+phone);
        if(registration_no != null)
            System.out.print("\t\t          "+registration_no);
        System.out.println("\n============================================================================================================================================================================");
    }
    /*
    ** PERFORM PREFIX BASED SEARCH BASED ON THE NAME OF THE STUDENTS
    */
    public void prefixSearch_By_Name(String name){

        TrieNodeN current = rootN;
        String prefix = "";

        for(int index = 0; index < name.length(); index ++){

            prefix += name.charAt(index);
            char lastCharacter = name.charAt(index);

            TrieNodeN node = current.getChild().get(lastCharacter);

            if(node == null){
                System.out.println("Sorry !! There is no student record with name that starts with "+prefix);
                break;
            }
            if(index == name.length() - 1){
                System.out.println("Suggestions based on Name "+prefix +" are ");
                prefixSearch_By_Name_Display(node, prefix);
            }
            current = node;
        }
    }
    /*
    ** DISPLAY ALL THE RECORDS THAT STARTS WITH THE GIVEN NAME
    */
    public void prefixSearch_By_Name_Display(TrieNodeN current, String prefix){

        if(current.getEndOfWord() == true){
            printDetails(current.getStudent().getName(), current.getStudent().getId(), current.getStudent().getEmail(), current.getStudent().getDepartment(), current.getStudent().getRoll(), current.getStudent().getPhone(), current.getStudent().getRegistration_no());
            return;
        }
        for(char c : current.getChild().keySet()){
            TrieNodeN next = current.getChild().get(c);
            if(next != null){
                prefixSearch_By_Name_Display(next, prefix + c);
            }
        }
    }
    /*
    ** PERFORM PREFIX BASED SEARCH BASED ON THE PHONE NUMBER OF THE STUDENTS
    */
    public void prefixSearch_By_Phone(String phone){

        TrieNodeP current = rootP;
        String prefix = "";

        for(int index = 0; index < phone.length(); index ++){

            prefix += phone.charAt(index);
            char lastCharacter = phone.charAt(index);
            TrieNodeP node = current.getChild().get(lastCharacter);

            if(node == null){
                System.out.println("Sorry !! There are no students whose phone number starts with "+prefix);
                break;
            }
            if(index == phone.length() - 1){
                System.out.println("Suggestions based on Phone Number "+prefix +" are ");
                prefixSearch_By_Phone_Display(node, prefix);
            }
            current = node;
        }
    }
    /*
    ** DISPLAY ALL THE RECORDS THAT STARTS WITH THE GIVEN NAME
    */
    public void prefixSearch_By_Phone_Display(TrieNodeP current, String prefix){

        if(current.getEndOfWord() == true){
            printDetails(current.getStudent().getName(), current.getStudent().getId(), current.getStudent().getEmail(), current.getStudent().getDepartment(), current.getStudent().getRoll(), current.getStudent().getPhone(), current.getStudent().getRegistration_no());
            return;
        }
        for(char c : current.getChild().keySet()){
            TrieNodeP next = current.getChild().get(c);
            if(next != null){
                prefixSearch_By_Phone_Display(next, prefix + c);
            }
        }
    }
    /*
    ** CHECK WHETHER THE INPUT PHONE NUMBER IS VALID OR NOT
    */
    public boolean check_Valid_PhoneNumber(String phone) {
        
        if(phone == null){
            //System.out.println("\t\t\tPhone Field cannot be null");
            return false;
        }
        int count = 0;
        boolean isValid = true;
        for(int index = 0; index < phone.length(); index ++){
            char digit = phone.charAt(index);
            if(digit == ' '){
                isValid = false;
                break;
            }
            if(!(digit >= 48 && digit <= 57)){
                isValid = false;
                break;
            }
            count ++;
        }
        if(count == 10 && isValid == true)
            return isValid;
        return false;
    }
    /*
    ** ENTER STUDENT DETAILS
    */
    public Student enterStudentDetail(){

        Scanner sc = new Scanner(System.in);
        Student std = new Student();
        System.out.println("****************Enter Student details****************");

        System.out.print("\t\tName : \t\t\t");
        std.setName(sc.next());
        System.out.println();

        System.out.print("\t\tId : \t\t\t");
        while(true){
            String Id = sc.next();
            if(isValid_ID(Id) == false){
                std.setId(Id);
                break;
            }
            System.out.println("\t   Student with same Id already exist !!");
            System.out.print("\t\t\t\t\t");
        }
        System.out.println();

        System.out.print("\t\tEmail : \t\t");
        std.setEmail(sc.next());
        System.out.println();

        System.out.print("\t\tDepartment : \t\t");
        std.setDepartment(sc.next());
        System.out.println();

        System.out.print("\t\tPhone Number : \t\t");
        while(true){
            
            String phone = sc.next();
            
            if(check_Valid_PhoneNumber(phone) == true){
                std.setPhone(phone);
                break;
            }
            System.out.println("\t   InValid Number!! Re-enter your phone number");
            System.out.print("\t\t\t\t\t");
        }
        System.out.println();

        System.out.print("\t\tRoll Number : \t\t");
        std.setRoll(sc.nextInt());
        System.out.println();

        System.out.print("\t\tRegistration Number : \t");
        std.setRegistration_no(sc.nextInt());
        System.out.println();
      
        return std;
    }
    /*public void readData() {
		
		File fN = new File(fileName_N);
        File fP = new File(fileName_P);
        File fID = new File(fileName_ID);

        //ArrayList<Student> deserialize = null;
		try {
			FileInputStream fisN = new FileInputStream(fN);
            ObjectInputStream oisN = new ObjectInputStream(fisN);
            
            FileInputStream fisP = new FileInputStream(fP);
            ObjectInputStream oisP = new ObjectInputStream(fisP);

            FileInputStream fisID = new FileInputStream(fID);
			ObjectInputStream oisID = new ObjectInputStream(fisID);
			
            this.rootN = (TrieNodeN) oisN.readObject();
            this.rootP = (TrieNodeP) oisP.readObject();
            this.rootID = (TrieNodeID) oisID.readObject();

            oisN.close();
            oisP.close();
            oisID.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
        }
        //return deserialize;
    }

    public void writeData() {

        File fN = new File(fileName_N);
        File fP = new File(fileName_P);
        File fID = new File(fileName_ID);

		try {
			FileOutputStream fosN = new FileOutputStream(fN);
			ObjectOutputStream oosN = new ObjectOutputStream(fosN);
            
            FileOutputStream fosP = new FileOutputStream(fP);
            ObjectOutputStream oosP = new ObjectOutputStream(fosP);
            
            FileOutputStream fosID = new FileOutputStream(fID);
            ObjectOutputStream oosID = new ObjectOutputStream(fosID);
            
            oosN.writeObject(rootN);
            oosP.writeObject(rootP);
            oosID.writeObject(rootID);

            oosN.close();
            oosP.close();
            oosID.close();

			System.out.println("Writing Done");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}