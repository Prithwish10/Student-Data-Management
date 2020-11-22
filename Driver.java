import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

    private static Utility obj;
    private static Scanner sc;

    private static String fileName;
    private static String chooseOption;
    private static String name;
    private static String phone;
    private static String Id;

    private static int op;

    private static boolean exceptionExist;

    private static ArrayList<Student> studentList;

    public static void main(String args[]) {

        obj = new Utility();
        sc = new Scanner(System.in);
        fileName = "C:\\Users\\prith\\OneDrive\\Documents\\Employee Management System\\STUDENT_DETAILS.ser";

        studentList = obj.readData(fileName);
        if(studentList != null){
            for(Student std : studentList){
                obj.insert_By_Name(std.getName().toLowerCase(), std);
                obj.insert_By_Phone(std.getPhone(), std);
                obj.insert_By_ID(std.getId(), std);
            }
        }
       
        studentList = new ArrayList<>();
        while(true){
            System.out.println("1 : Insert Student Details");
            System.out.println("2 : Search Student Details by Student Name ");
            System.out.println("3 : Search Student Details by Student Phone ");
            System.out.println("4 : Search Student Details by Student ID ");
            System.out.println("5 : Perform Prefix Search to search by name ");
            System.out.println("6 : Perform Prefix Search to search by phone number ");
            System.out.println("7 : Delete by name ");
            System.out.println("8 : Delete by phone ");
            System.out.println("9 : Delete by Id ");
            System.out.println("10. Exit ");

            System.out.println("Enter which option do u want to choose :");
            exceptionExist = false;
            op = 0;

            /*
            ** TRY CATCH BLOCK TO HANDLE THE CASE, IF USER ENTERS SOME STRING OR CHARACTER INSTEAD OF CURRENT OPTION
            */
            do {
                exceptionExist = false;
                try {
                    Scanner scan = new Scanner(System.in);
                    op = scan.nextInt();
                }catch(InputMismatchException e) {
                    exceptionExist = true;
                    System.out.println("Enter correct option !!");
                }
            }while(exceptionExist);

            chooseOption = ""; name = ""; phone = ""; Id = "";
            Student std;

            switch(op){
                case 1:
                    //ENTER STUDENT DETAILS
                    std = obj.enterStudentDetail();
                    obj.insert_By_Name(std.getName().toLowerCase(), std);
                    obj.insert_By_Phone(std.getPhone(), std);
                    obj.insert_By_ID(std.getId(), std);
                    // Appending the new Student record in studentList so that it can be save in the file
                    studentList.add(std);
                    System.out.println("\nINSERTION SUCCESSFUL !!\n");
                    obj.writeData(fileName, studentList);
                    break;

                case 2:
                    System.out.println("Enter the name by which you want to search :");
                    name = sc.next();
                    obj.search_By_Name(name.toLowerCase());
                    break;

                case 3:
                    System.out.println("Enter the Phone number by which you want to search :");
                    phone = sc.next();
                    obj.search_By_Phone(phone);
                    break;

                case 4:
                    System.out.println("Enter the Id :");
                    Id = sc.next();
                    obj.search_By_ID(Id);
                    break;

                case 5:
                    System.out.println("Enter prefixes :");
                    String prefixName = "";

                    while(true){
                        System.out.println("Press Q to Exit your search");
                        String prefix = sc.next();
                        prefixName += prefix;
                        obj.prefixSearch_By_Name(prefixName.toLowerCase());
                        if(prefixName.equals("Q"))
                            break;
                        prefixName = "";
                    }
                    break;

                case 6:
                    System.out.println("Enter prefixes :");
                    String prefixPhone = "";

                    while(true){
                        System.out.println("Press Q to Exit your search");
                        String prefix = sc.next();
                        prefixPhone += prefix;
                        obj.prefixSearch_By_Phone(prefixPhone);
                        if(prefixPhone.equals("Q"))
                            break;
                        prefixPhone = "";
                    }
                    break;
                
                case 7:
                    System.out.println("Enter the name of the student :");
                    name = sc.next().toLowerCase();
                    System.out.print("There may be multiple records whose name is "+name+". ");
                    System.out.println("Please choose between Phone or ID. [Phone/ID] :");
                    
                    boolean phn = false;
                    boolean ID = false;
                    String opt = "";

                    while(true){
                        opt = sc.next();
                        if(opt.equalsIgnoreCase("Phone")){
                            while(true){
                                System.out.println("Enter "+name+"'s Phone Number :");
                                phone = sc.next();
                                if(obj.check_Valid_PhoneNumber(phone)){
                                    phn = true;
                                    break;
                                }
                                else{
                                    System.out.println("Invalid Number [Phone Number must consists of 10 digits with no spaces in between or before or after].");
                                    
                                    System.out.println("Do you want to continue? [Yes/No]");
                                    String choice = sc.next();
                                    if(choice.equalsIgnoreCase("No"))
                                        break;
                                }
                            }
                            break;
                        }
                        else if(opt.equalsIgnoreCase("ID")){
                            System.out.println("Enter "+name+"'s ID :");
                            Id = sc.next();
                            ID = true;
                            break;
                        }
                        else{
                            System.out.println("Wrong Input !! Please Enter between 'Phone' or 'ID' ");
                        }
                    }
                    if(phn == false && ID == false)
                        break;
                    if(phn == true)
                        System.out.println("Are You Sure You Want To Permanently Delete The Records With Name "+name+" having a Phone Number "+phone+". [Yes/No] :");
                    else
                        System.out.println("Are You Sure You Want To Permanently Delete The Records With Name "+name+" having an ID "+Id+". [Yes/No] :");

                    chooseOption = sc.next();
                    Student student = null;

                    if(chooseOption.equalsIgnoreCase("No")){
                        break;
                    }
                    else if(chooseOption.equalsIgnoreCase("Yes")){

                        if(phn == true){
                            Student studentWithPhone = obj.is_Phone_Exist(phone);
                            
                            if(studentWithPhone == null || !studentWithPhone.getName().equalsIgnoreCase(name)){
                                System.out.println("Sorry there exist no record with name "+name+" having a phone number "+phone);
                                break;
                            }
                            student = studentWithPhone;
                        }
                        else if(ID == true){
                            Student studentWithID = obj.is_ID_Exist(Id);

                            if(studentWithID == null || !studentWithID.getName().equalsIgnoreCase(name)){
                                System.out.println("Sorry there is no record with name "+name+" having an ID "+Id);
                                break;
                            }
                            student = studentWithID;
                        }
                        
                        if(student != null && ID == true){
                            obj.delete_by_Id(Id);
                            obj.delete_by_Phone(student.getPhone());
                            obj.delete_By_Name(student.getName().toLowerCase());
                            obj.delete_Record_From_File_By_ID(fileName, Id);
                            System.out.println("Record Deleted Successfully !!");
                        }
                        else if(student != null && phn == true){
                            obj.delete_by_Phone(phone);
                            obj.delete_by_Id(student.getId());
                            obj.delete_By_Name(student.getName().toLowerCase());
                            obj.delete_Record_From_File_By_Phone(fileName, phone);
                            System.out.println("Record Deleted Successfully !!");
                        }  
                        else
                            System.out.println("Record doesnot exist !!");
                    }
                    break;

                case 8:
                    System.out.println("Enter the phone number of the student :");
                    phone = sc.next();
                    System.out.print("Are You Sure You Want To Permanently Delete The Records With Phone Number "+phone+". "+ "Yes/No (Case Sensitive) :");
                    chooseOption = sc.next();

                    if(chooseOption.equals("No")){
                        break;
                    }
                    else if(chooseOption.equals("Yes")){

                        student = obj.is_Phone_Exist(phone);
                        if(student != null){
                            obj.delete_by_Phone(phone);
                            obj.delete_by_Id(student.getId());
                            obj.delete_By_Name(student.getName().toLowerCase());
                            obj.delete_Record_From_File_By_Phone(fileName, phone);
                            System.out.println("Record Deleted Successfully !!");
                        }    
                        else
                            System.out.println("Record doesnot exist !!");
                    }
                    break;

                case 9:
                    System.out.println("Enter the ID of the student :");
                    Id = sc.next();
                    System.out.print("Are You Sure You Want To Permanently Delete The Records With ID "+Id+". "+ "Yes/No (Case Sensitive) :");
                    chooseOption = sc.next();

                    if(chooseOption.equals("No")){
                        break;
                    }
                    else if(chooseOption.equals("Yes")){

                        student = obj.is_ID_Exist(Id);
                        if(student != null){
                            //All students name are stored in lower case
                            obj.delete_by_Id(Id);
                            obj.delete_by_Phone(student.getPhone());
                            obj.delete_By_Name(student.getName().toLowerCase());
                            obj.delete_Record_From_File_By_ID(fileName, Id);
                            System.out.println("Record Deleted Successfully !!");
                        }    
                        else
                            System.out.println("Record doesnot exist !!");
                    }
                    break;
                    
                case 10:
                    System.exit(0);
                    
                default:
                    System.out.println("Choose the correct option :");
                    break;
            }
        }
    }
}