import java.io.Serializable;

public class Student implements Serializable{

    private static final long serialVersionUID = 1L;
    private String name;
    private String Id;
    /*
    ** CREATING DATA MODEL CLASS TO HOLD STUDENT PERSONAL INFORMATION
    */
    private String email;
    private String department;
    private int roll;
    private String phone;
    private int registration_no;

    //Constructor to initialize all the fields of Student Class
    public Student(String name, String Id,String email, String department, int roll,String phone, int registration_no){
        this.name = name;
        this.Id = Id;
        this.email = email;
        this.department = department;
        this.roll = roll;
        this.phone = phone;
        this.registration_no = registration_no;
    }
    public Student(){}
    
    /* 
    ** GETTER AND SETTER METHODS
    */
    public String getName(){
        return this.name;
    }
    public String getId(){
        return this.Id;
    }
    public String getEmail(){
        return this.email;
    }
    public String getDepartment(){
        return this.department;
    }
    public int getRoll(){
        return this.roll;
    }
    public String getPhone(){
        return this.phone;
    }
    public int getRegistration_no(){
        return this.registration_no;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setId(String Id){
        this.Id = Id;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setDepartment(String department){
        this.department = department;
    }
    public void setRoll(int roll){
        this.roll = roll;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setRegistration_no(int registration_no){
        this.registration_no = registration_no;
    }
}