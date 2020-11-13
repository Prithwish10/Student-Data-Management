import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

    public static void main(String args[]){

        Utility obj = new Utility();
        ReadAndWriteFile file = new ReadAndWriteFile();
        Scanner sc = new Scanner(System.in);
       
        String fileName = "C:\\Users\\prith\\OneDrive\\Documents\\Employee Management System\\STUDENT_DETAILS.ser";
        ArrayList<Student> studentList = obj.readData(fileName);
       System.out.println(studentList);
        if(studentList != null){
            for(Student std : studentList){
                obj.insert_By_Name(std.getName().toLowerCase(), std);
                obj.insert_By_Phone(std.getPhone(), std);
                obj.insert_By_ID(std.getId(), std);
            }
        }
        //obj.readData();
        studentList = new ArrayList<>();
        while(true){
            System.out.println("1 : Insert Student Details");
            System.out.println("2 : Search Student Details by Student Name ");
            System.out.println("3 : Search Student Details by Student Phone ");
            System.out.println("4 : Search Student Details by Student ID ");
            System.out.println("5 : Perform Prefix Search to search by name ");
            System.out.println("6 : Perform Prefix Search to search by phone number ");
            System.out.println("7 : Exit");

            System.out.println("Enter which option do u want to choose :");
            boolean exceptionExist = false;
            int op = 0;
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
            
            Student std;
            switch(op){
                case 1:
                    //ENTER STUDENT DETAILS
                    std = obj.enterStudentDetail();
                    obj.insert_By_Name(std.getName().toLowerCase(), std);
                    obj.insert_By_Phone(std.getPhone(), std);
                    obj.insert_By_ID(std.getId(), std);
                    studentList.add(std);
                    System.out.println("\nINSERTION SUCCESSFUL !!\n");
                    obj.writeData(fileName, studentList);
                    break;

                case 2:
                    System.out.println("Enter the name by which you want to search :");
                    String name = sc.next();
                    obj.search_By_Name(name.toLowerCase());
                    break;

                case 3:
                    System.out.println("Enter the Phone number by which you want to search :");
                    String phone = sc.next();
                    obj.search_By_Phone(phone);
                    break;

                case 4:
                    System.out.println("Enter the Id :");
                    String Id = sc.next();
                    obj.search_By_ID(Id);
                    break;

                case 5:
                    System.out.println("Enter prefixes :");
                    String t = "";

                    while(true){
                        System.out.println("Press Q to Exit your search");
                        String prefix = sc.next();
                        t += prefix;
                        obj.prefixSearch_By_Name(t.toLowerCase());
                        if(t.equals("Q"))
                            break;
                        t = "";
                    }
                    break;
                case 6:
                    System.out.println("Enter prefixes :");
                    String phn = "";

                    while(true){
                        System.out.println("Press Q to Exit your search");
                        String prefix = sc.next();
                        phn += prefix;
                        obj.prefixSearch_By_Phone(phn);
                        if(phn.equals("Q"))
                            break;
                        phn = "";
                    }
                    break;

                case 7:
                    System.exit(0);
                    
                default:
                    System.out.println("Choose the correct option :");
                    break;
            }
            //obj.writeData();
        }
    }
}