import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Utility {

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
    // *************************************** INSERT RECORD **********************************************
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
    }
    /*
    ** INSERT BY THE ID OF THE STUDENT
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
    }

    // ************************************* SEARCH RECORD **********************************************
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

    // ****************************** AUTO-COMPLETE FEATURE **********************************************
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

    // ************************************ DELETE RECORDS ***************************************************
    /*
    ** DELETE THE STUDENT RECORD WITH THE GIVEN NAME
    */
    public void delete_By_Name(String name){
        delete_By_Name(rootN, name, 0);
    }

    public boolean delete_By_Name(TrieNodeN current, String name, int index){
       
        if(index == name.length()){
            if(current.getEndOfWord() == false)
                return false;
            current.setEndOfWord(false);
            return current.getChild().size() == 0;
        }
        char ch = name.charAt(index);
        TrieNodeN node = current.getChild().get(ch);
        if(node == null)
            return false;

        boolean canDeleteNode = delete_By_Name(node, name, index + 1);
        
        if(canDeleteNode){
            current.getChild().remove(ch);
            return current.getChild().size() == 0;
        }
        return false;
    }

    /*
    ** DELETE THE STUDENT RECORD BY THE GIVEN PHONE NUMBER
    */
    public void delete_by_Phone(String phone){
        delete_by_Phone(rootP, phone, 0);
    }

    public boolean delete_by_Phone(TrieNodeP current, String phone, int index){

        if(index == phone.length()){
            if(current.getEndOfWord() == false)
                return false;
            current.setEndOfWord(false);
            return current.getChild().size() == 0;
        }
        char ch = phone.charAt(index);
        TrieNodeP node = current.getChild().get(ch);
        if(node == null)
            return false;

        boolean canDeleteNode = delete_by_Phone(node, phone, index + 1);

        if(canDeleteNode){
            current.getChild().remove(ch);
            return current.getChild().size() == 0;
        }
        return false;
    }

    /*
    ** DELETE THE STUDENT RECORD BY THE GIVEN ID
    */
    public void delete_by_Id(String Id){
        delete_by_Id(rootID, Id, 0);
    }
    public boolean delete_by_Id(TrieNodeID current, String Id, int index){

        if(index == Id.length()){
            if(current.getEndOfWord() == false)
                return false;
            current.setEndOfWord(false);
            return current.getChild().size() == 0;
        }
        char ch = Id.charAt(index);
        TrieNodeID node = current.getChild().get(ch);
        if(node == null)
            return false;
        
        boolean canDeleteNode = delete_by_Id(node, Id, index + 1);

        if(canDeleteNode){
            current.getChild().remove(ch);
            return current.getChild().size() == 0;
        }
        return false;
    }
    // ************************************ VALIDATION CHECKS *************************************************

    /*
        CHECK WHETHER THE GIVEN STUDENT NAME EXIST OR NOT
    */
    public Student is_Name_Exist(String name){

        TrieNodeN current = rootN;

        for(int index = 0; index < name.length(); index++){

            char ch = name.charAt(index);
            TrieNodeN node = current.getChild().get(ch);
          
            if(node == null)
                return null;
            
            current = node;
        }
        
        if(current.getEndOfWord() == true)
            return current.getStudent();
        return null;
    }
    /*
        CHECK WHETHER THE GIVEN STUDENT PHONE NUMBER EXIST OR NOT
    */
    public Student is_Phone_Exist(String phone){

        TrieNodeP current = rootP;

        for(int index = 0; index < phone.length(); index++){

            char ch = phone.charAt(index);
            TrieNodeP node = current.getChild().get(ch);
            if(node == null)
                return null;
            
            current = node;
        }
        
        if(current.getEndOfWord() == true)
            return current.getStudent();
        return null;
    }
    /*
        CHECK WHETHER THE GIVEN STUDENT ID EXIST OR NOT
    */
    public Student is_ID_Exist(String ID){

        TrieNodeID current = rootID;

        for(int index = 0; index < ID.length(); index++){

            char ch = ID.charAt(index);
            TrieNodeID node = current.getChild().get(ch);
            if(node == null)
                return null;
            
            current = node;
        }
        
        if(current.getEndOfWord() == true)
            return current.getStudent();
        return null;
    }
    
    /*
    ** SINCE ID IS A PRIMARY KEY SO DUPLICATION IS NOT ALLOWED
    */
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
    //*********************************** INPUT DETAILS ****************************************************
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
    // ***************************************** FILE HANDLING *********************************************
    /*
    ** READ DATA FROM THE FILE
    */
    public ArrayList<Student> readData(String fileName) {
		
        File f = new File(fileName);
        
        if(f.length() == 0)
            return null;

        ArrayList<Student> deserialize = null;
        
		try {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			deserialize = (ArrayList<Student>) ois.readObject();
            ois.close();
            fis.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
        }
        return deserialize;
    }
    /*
    ** DELETE A RECORD FROM THE FILE BY THE PHONE NUMBER
    */
    public void delete_Record_From_File_By_Phone(String fileName, String phone){

        ArrayList<Student> studentList = readData(fileName);
        ArrayList<Student> studentListNew = new ArrayList<>();

        for(Student student : studentList){
            if(!student.getPhone().equals(phone))
                studentListNew.add(student);
        }
        // Re-Write the new file with the deleted record over the existing file
        String tmpFile = "STUDENT_DETAILS.ser";
        File newFile = new File(tmpFile);

        try{
            FileOutputStream fos = new FileOutputStream(newFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(studentListNew);
            oos.close();
            fos.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /*public void delete_Record_From_File_By_Name(String fileName, String name){

        ArrayList<Student> studentList = readData(fileName);
        ArrayList<Student> studentListNew = new ArrayList<>();

        for(Student student : studentList){
            if(!student.getName().equals(name))
                studentListNew.add(student);
        }
        // Re-Write the new file with the deleted record over the existing file
        String tmpFile = "STUDENT_DETAILS.ser";
        File newFile = new File(tmpFile);

        try{
            FileOutputStream fos = new FileOutputStream(newFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(studentListNew);
            oos.close();
            fos.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }*/
    /*
    ** WRITE DATA INTO THE FILE
    */
    public void writeData(String fileName, ArrayList<Student> l) {

		File f = new File(fileName);
		try {
            ArrayList<Student> list = readData_Modify(fileName);
		
			if(list != null)
				for(Student s : list)
					l.add(s);
			
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(l);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
    }
    /*
    ** THIS FUNCTION PICKS UP THE RECORDS FROM THE EXISTING LIST
    SO THAT WHEN THE PROGRAM IS RAN THEN NO PREVIOUSLY EXISTING DATA(IF ANY) IS LOST.
    
    ** THIS FUNCTION IS CALLED FROM THE WRITE FUNCTION SO AS TO APPEND THE PREVIOUSLY EXISTING DATA WITH THE NEW DATA/RECORD
    */
    public ArrayList<Student> readData_Modify(String fileName) {
	
        File f = new File(fileName);
        
		if(f.length() == 0)
            return null;
            
		ArrayList<Student> list = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);

			ArrayList<Student> l = (ArrayList<Student>) ois.readObject();
			
			for(Student stu : l) {
				Student std = new Student();
				std.setName(stu.getName()); 
				std.setId(stu.getId());
                std.setEmail(stu.getEmail()); 
                std.setDepartment(stu.getDepartment());
                std.setPhone(stu.getPhone());
                std.setRoll(stu.getRoll());
                std.setRegistration_no(stu.getRegistration_no());
				list.add(std);
			}
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
}