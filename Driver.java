import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

    static Utility obj;
    static Scanner sc;

    static String fileName;
    static String chooseOption;
    static String name;
    static String phone;
    static String Id;

    static int op;

    static boolean exceptionExist;

    static ArrayList<Student> studentList;

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
            System.out.println("7 : Delete by name :");
            System.out.println("8 : Delete by phone :");
            System.out.println("9 : Exit");

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
                    System.out.print("Are You Sure You Want To Permanently Delete The Records With Name"+name+". "+ "Yes/No (Case Sensitive) :");
                    chooseOption = sc.next();

                    if(chooseOption.equals("No")){
                        break;
                    }
                    else if(chooseOption.equals("Yes")){

                        Student student = obj.is_Name_Exist(name);
                        if(student != null){
                            //All students name are stored in lower case
                            obj.delete_By_Name(name);
                            obj.delete_by_Phone(student.getPhone());
                            System.out.println("Record Deleted Successfully !!");
                        }    
                        else
                            System.out.println("Record doesnot exist !!");
                    }
                    break;

                case 8:
                    System.out.println("Enter the phone number of the student :");
                    phone = sc.next();
                    System.out.print("Are You Sure You Want To Permanently Delete The Records With Phone Number"+phone+". "+ "Yes/No (Case Sensitive) :");
                    chooseOption = sc.next();

                    if(chooseOption.equals("No")){
                        break;
                    }
                    else if(chooseOption.equals("Yes")){

                        Student student1 = obj.is_Phone_Exist(phone);
                        if(student1 != null){
                            //All students name are stored in lower case
                            obj.delete_by_Phone(phone);
                            obj.delete_By_Name(student1.getName().toLowerCase());
                            System.out.println("Record Deleted Successfully !!");
                        }    
                        else
                            System.out.println("Record doesnot exist !!");
                    }
                    break;
                case 9:
                    System.exit(0);
                    
                default:
                    System.out.println("Choose the correct option :");
                    break;
            }
        }
    }
}